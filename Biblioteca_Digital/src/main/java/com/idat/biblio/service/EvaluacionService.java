package com.idat.biblio.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import com.idat.biblio.dtos.EvaluacionDTO;
import com.idat.biblio.dtos.EvaluacionRequestDTO;

public interface EvaluacionService {

	EvaluacionDTO registrarEvaluacion(EvaluacionRequestDTO request, Authentication authentication);

	List<EvaluacionDTO> obtenerMisEvaluaciones(Authentication authentication);
	
	List<EvaluacionDTO> obtenerTodasEvaluaciones();
}