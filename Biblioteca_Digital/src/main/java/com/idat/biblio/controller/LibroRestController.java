package com.idat.biblio.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.idat.biblio.dtos.LibroDTO;
import com.idat.biblio.service.LibroService;

@RestController
@RequestMapping("/api/libros")
@CrossOrigin(origins = "*")
public class LibroRestController {
	private final LibroService libroService;

	public LibroRestController(LibroService libroService) {
		this.libroService = libroService;
	}

	// (USER y ADMIN)
	@GetMapping
	public ResponseEntity<List<LibroDTO>> buscarLibros(@RequestParam(required = false) String q) {
		List<LibroDTO> libros = (q == null || q.isBlank()) ? libroService.obtenerTodosLosLibros()
				: libroService.buscarLibros(q);
		return ResponseEntity.ok(libros);
	}

	// Solo ADMIN 
	@PostMapping
	public ResponseEntity<LibroDTO> crearLibro(@RequestBody LibroDTO libroDTO) {
		LibroDTO creado = libroService.crearLibro(libroDTO);
		return ResponseEntity.ok(creado);
	}

	// Solo ADMIN 
	@PutMapping("/{id}")
	public ResponseEntity<LibroDTO> actualizarLibro(@PathVariable Long id, @RequestBody LibroDTO libroDTO) {
		LibroDTO actualizado = libroService.actualizarLibro(id, libroDTO);
		return ResponseEntity.ok(actualizado);
	}

	// Solo ADMIN
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarLibro(@PathVariable Long id) {
		libroService.eliminarLibro(id);
		return ResponseEntity.noContent().build();
	}

	// (USER y ADMIN)
	@GetMapping("/{id}")
	public ResponseEntity<LibroDTO> obtenerPorId(@PathVariable Long id) {
		LibroDTO libro = libroService.obtenerLibroPorId(id);
		return ResponseEntity.ok(libro);
	}
}