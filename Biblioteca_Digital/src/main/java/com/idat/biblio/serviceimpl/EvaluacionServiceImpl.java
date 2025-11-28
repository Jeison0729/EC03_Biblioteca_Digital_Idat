package com.idat.biblio.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.idat.biblio.dtos.EvaluacionDTO;
import com.idat.biblio.dtos.EvaluacionRequestDTO;
import com.idat.biblio.entity.EvaluacionEntity;
import com.idat.biblio.entity.UsuarioEntity;
import com.idat.biblio.repository.EvaluacionRepository;
import com.idat.biblio.service.EvaluacionService;

@Service
public class EvaluacionServiceImpl implements EvaluacionService {

	private final EvaluacionRepository evaluacionRepository;

	public EvaluacionServiceImpl(EvaluacionRepository evaluacionRepository) {
		this.evaluacionRepository = evaluacionRepository;
	}

	@Override
	@Transactional
	public EvaluacionDTO registrarEvaluacion(EvaluacionRequestDTO request, Authentication authentication) {

		if (request.getComentario() == null || request.getComentario().trim().isEmpty()) {
			throw new RuntimeException("El comentario es obligatorio");
		}

		if (request.getComentario().length() > 500) {
			throw new RuntimeException("El comentario no puede superar los 500 caracteres");
		}

		if (request.getPuntaje() == null || request.getPuntaje() < 1 || request.getPuntaje() > 5) {
			throw new RuntimeException("El puntaje debe estar entre 1 y 5");
		}

		// Obtener usuario del JWT
		UsuarioEntity usuario = (UsuarioEntity) authentication.getPrincipal();

		// Crear la evaluación
		EvaluacionEntity evaluacion = new EvaluacionEntity();
		evaluacion.setUsuario(usuario);
		evaluacion.setComentario(request.getComentario().trim());
		evaluacion.setPuntaje(request.getPuntaje());
		evaluacion.setFechaRegistro(LocalDateTime.now());

		evaluacion = evaluacionRepository.save(evaluacion);

		return new EvaluacionDTO(evaluacion.getId(), usuario.getCodigo(), evaluacion.getComentario(),
				evaluacion.getPuntaje(), evaluacion.getFechaRegistro());
	}

	@Override
	public List<EvaluacionDTO> obtenerMisEvaluaciones(Authentication authentication) {
		UsuarioEntity usuario = (UsuarioEntity) authentication.getPrincipal();

		List<EvaluacionEntity> evaluaciones = evaluacionRepository.findByUsuarioCodigo(usuario.getCodigo());

		return evaluaciones.stream().map(e -> new EvaluacionDTO(e.getId(), usuario.getCodigo(), e.getComentario(),
				e.getPuntaje(), e.getFechaRegistro())).collect(Collectors.toList());
	}

	@Override
	public List<EvaluacionDTO> obtenerTodasEvaluaciones() {
		List<EvaluacionEntity> evaluaciones = evaluacionRepository.findAll();

		return evaluaciones.stream().map(e -> new EvaluacionDTO(e.getId(), e.getUsuario().getCodigo(),
				e.getComentario(), e.getPuntaje(), e.getFechaRegistro())).collect(Collectors.toList());
	}
}