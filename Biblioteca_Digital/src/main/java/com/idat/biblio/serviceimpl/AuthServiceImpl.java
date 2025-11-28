package com.idat.biblio.serviceimpl;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.idat.biblio.dtos.LoginRequestDTO;
import com.idat.biblio.dtos.LoginResponseDTO;
import com.idat.biblio.dtos.RegistroRequestDTO;
import com.idat.biblio.entity.RolEntity;
import com.idat.biblio.entity.UsuarioEntity;
import com.idat.biblio.repository.RolRepository;
import com.idat.biblio.repository.UsuarioRepository;
import com.idat.biblio.service.AuthService;
import com.idat.biblio.service.JwtService;

import jakarta.transaction.Transactional;

@Service
public class AuthServiceImpl implements AuthService {

	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	private final UsuarioRepository usuarioRepository;
	private final PasswordEncoder passwordEncoder;
	private final com.idat.biblio.repository.RolRepository rolRepository;

	public AuthServiceImpl(AuthenticationManager authenticationManager, JwtService jwtService,
			UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, RolRepository rolRepository) {
		super();
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
		this.usuarioRepository = usuarioRepository;
		this.passwordEncoder = passwordEncoder;
		this.rolRepository = rolRepository;
	}

	@Transactional
	public LoginResponseDTO registro(RegistroRequestDTO request) {
	    // Validar que no exista el correo
	    if (usuarioRepository.findByCorreo(request.getCorreo()).isPresent()) {
	        throw new RuntimeException("El correo ya está registrado");
	    }

	    // ROL SEGÚN EL CORREO
	    String correo = request.getCorreo();
	    RolEntity rol;

	    if (correo.equals("admin@biblioteca.com") || correo.contains("admin")) {
	        rol = rolRepository.findByDescripcion("ROLE_ADMIN")
	            .orElseThrow(() -> new RuntimeException("Rol ADMIN no encontrado"));
	    } else {
	        rol = rolRepository.findByDescripcion("ROLE_USER")
	            .orElseThrow(() -> new RuntimeException("Rol USER no encontrado"));
	    }

	    // Crear usuario nuevo
	    UsuarioEntity nuevo = new UsuarioEntity();
	    nuevo.setCorreo(request.getCorreo());
	    nuevo.setPassword(passwordEncoder.encode(request.getPassword()));
	    nuevo.setNombreCompleto(request.getNombreCompleto());
	    nuevo.setLista(List.of(rol));  

	    nuevo = usuarioRepository.save(nuevo);

	    String token = jwtService.generateToken(nuevo);
	    return new LoginResponseDTO(token, nuevo.getCodigo(), nuevo.getNombreCompleto(), nuevo.getCorreo());
	}

	@Override
	public LoginResponseDTO login(LoginRequestDTO request) {
		// Autenticamos con correo y password
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getCorreo(), request.getPassword()));

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		UsuarioEntity usuario = usuarioRepository.findByCorreo(userDetails.getUsername())
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

		// Generamos el JWT (el id va dentro del token)
		String token = jwtService.generateToken(usuario);

		return new LoginResponseDTO(token, usuario.getCodigo(), usuario.getNombreCompleto(), usuario.getCorreo());
	}
}