package com.idat.biblio.service;

import java.util.List;

import com.idat.biblio.dtos.LibroDTO;

public interface LibroService {

	List<LibroDTO> buscarLibros(String q);

	List<LibroDTO> obtenerTodosLosLibros();

	LibroDTO crearLibro(LibroDTO libroDTO);

	LibroDTO actualizarLibro(Long id, LibroDTO libroDTO);

	void eliminarLibro(Long id);

	LibroDTO obtenerLibroPorId(Long id);

}
