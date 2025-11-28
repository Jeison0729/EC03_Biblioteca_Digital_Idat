package com.idat.biblio.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idat.biblio.dtos.LoginRequestDTO;
import com.idat.biblio.dtos.LoginResponseDTO;
import com.idat.biblio.dtos.RegistroRequestDTO;
import com.idat.biblio.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthRestController {
	private final AuthService authService;

	public AuthRestController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
		LoginResponseDTO response = authService.login(request);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/registro")
	public ResponseEntity<LoginResponseDTO> registro(@RequestBody RegistroRequestDTO request) {
	    LoginResponseDTO response = authService.registro(request);
	    return ResponseEntity.ok(response);
	}
}
