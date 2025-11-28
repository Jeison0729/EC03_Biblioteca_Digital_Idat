package com.idat.biblio.service;

import com.idat.biblio.dtos.LoginRequestDTO;
import com.idat.biblio.dtos.LoginResponseDTO;
import com.idat.biblio.dtos.RegistroRequestDTO;

public interface AuthService {

	LoginResponseDTO login(LoginRequestDTO request);

	LoginResponseDTO registro(RegistroRequestDTO request);
}
