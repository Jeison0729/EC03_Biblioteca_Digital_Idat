package com.idat.biblio.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.idat.biblio.dtos.LibroDTO;
import com.idat.biblio.entity.LibroEntity;
import com.idat.biblio.repository.LibroRepository;
import com.idat.biblio.service.LibroService;

import jakarta.transaction.Transactional;

@Service
public class LibroServiceImpl implements LibroService {

	private final LibroRepository libroRepository;

	public LibroServiceImpl(LibroRepository libroRepository) {
		this.libroRepository = libroRepository;
	}

	@Override
	public List<LibroDTO> buscarLibros(String q) {
		if (q == null || q.trim().isEmpty()) {
			return obtenerTodosLosLibros();
		}
		List<LibroEntity> libros = libroRepository.buscarPorTituloOAutor("%" + q.toLowerCase() + "%");
		return mapToDTO(libros);
	}

	@Override
	public List<LibroDTO> obtenerTodosLosLibros() {
		return mapToDTO(libroRepository.findAll());
	}

	private List<LibroDTO> mapToDTO(List<LibroEntity> libros) {
		return libros.stream().map(l -> new LibroDTO(l.getIdLibro(), l.getTitulo(), l.getAutor(), l.getIsbn(),
				l.getAnioPublicacion(), l.getCantidadDisponible())).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public LibroDTO crearLibro(LibroDTO dto) {
		LibroEntity entity = new LibroEntity();
		entity.setTitulo(dto.getTitulo());
		entity.setAutor(dto.getAutor());
		entity.setIsbn(dto.getIsbn());
		entity.setAnioPublicacion(dto.getAnioPublicacion());
		entity.setCantidadDisponible(dto.getCantidadDisponible());

		entity = libroRepository.save(entity);
		dto.setIdLibro(entity.getIdLibro());
		return dto;
	}

	@Override
	@Transactional
	public LibroDTO actualizarLibro(Long id, LibroDTO dto) {
		LibroEntity entity = libroRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Libro no encontrado"));

		entity.setTitulo(dto.getTitulo());
		entity.setAutor(dto.getAutor());
		entity.setIsbn(dto.getIsbn());
		entity.setAnioPublicacion(dto.getAnioPublicacion());
		entity.setCantidadDisponible(dto.getCantidadDisponible());

		libroRepository.save(entity);
		dto.setIdLibro(entity.getIdLibro());
		return dto;
	}

	@Override
	@Transactional
	public void eliminarLibro(Long id) {
		LibroEntity libro = libroRepository.findById(id).orElseThrow(() -> new RuntimeException("Libro no encontrado"));

		libroRepository.delete(libro);
	}

	@Override
	public LibroDTO obtenerLibroPorId(Long id) {
		LibroEntity entity = libroRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Libro no encontrado"));

		return new LibroDTO(entity.getIdLibro(), entity.getTitulo(), entity.getAutor(), entity.getIsbn(),
				entity.getAnioPublicacion(), entity.getCantidadDisponible());
	}
}