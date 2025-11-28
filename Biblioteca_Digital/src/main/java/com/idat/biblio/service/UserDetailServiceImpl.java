package com.idat.biblio.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.idat.biblio.repository.UsuarioRepository;
@Service
public class UserDetailServiceImpl implements UserDetailsService {
	private final UsuarioRepository repoUsuario;

	public UserDetailServiceImpl(UsuarioRepository repoUsuario) {
		this.repoUsuario = repoUsuario;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserDetails usuario=repoUsuario.findByCorreo(username)
				.orElseThrow(()-> new UsernameNotFoundException("Correo no existe"));
		return usuario;
	}

}
