package com.idat.biblio.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import com.idat.biblio.dtos.ReservaDTO;
import com.idat.biblio.dtos.ReservaRequestDTO;

public interface ReservaService {

	ReservaDTO reservarLibro(ReservaRequestDTO request, Authentication authentication);

	List<ReservaDTO> obtenerMisReservas(Authentication authentication);

	void devolverLibro(Long reservaId, Authentication authentication);
}
