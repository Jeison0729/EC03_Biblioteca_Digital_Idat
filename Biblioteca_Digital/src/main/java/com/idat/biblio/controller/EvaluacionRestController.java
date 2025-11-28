package com.idat.biblio.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idat.biblio.dtos.EvaluacionDTO;
import com.idat.biblio.dtos.EvaluacionRequestDTO;
import com.idat.biblio.service.EvaluacionService;

@RestController
@RequestMapping("/api/evaluaciones")
@CrossOrigin(origins = "*")
public class EvaluacionRestController {

	private final EvaluacionService evaluacionService;

	public EvaluacionRestController(EvaluacionService evaluacionService) {
		this.evaluacionService = evaluacionService;
	}

	// POST /api/evaluaciones → Registrar evaluación
	@PostMapping
	public ResponseEntity<EvaluacionDTO> registrarEvaluacion(@RequestBody EvaluacionRequestDTO request,
			Authentication authentication) {

		EvaluacionDTO evaluacion = evaluacionService.registrarEvaluacion(request, authentication);
		return ResponseEntity.ok(evaluacion);
	}

	// GET /api/evaluaciones/mis → Mis evaluaciones
	@GetMapping
	public ResponseEntity<List<EvaluacionDTO>> misEvaluaciones(Authentication authentication) {
		List<EvaluacionDTO> evaluaciones = evaluacionService.obtenerMisEvaluaciones(authentication);
		return ResponseEntity.ok(evaluaciones);
	}
	
	// GET  Todas las evaluaciones (solo ADMIN
	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<EvaluacionDTO>> todasEvaluaciones() {
		List<EvaluacionDTO> evaluaciones = evaluacionService.obtenerTodasEvaluaciones();
		return ResponseEntity.ok(evaluaciones);
	}
}