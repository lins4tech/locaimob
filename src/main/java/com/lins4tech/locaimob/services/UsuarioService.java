package com.lins4tech.locaimob.services;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.lins4tech.locaimob.entities.Usuario;
import com.lins4tech.locaimob.repositories.UsuarioRepository;

public class UsuarioService implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByUsername(username);
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuário Não Existe");
		}
		return getUserDetails(usuario);
	}
	
	private User getUserDetails(Usuario usuario) {
		return new User(usuario.getUsername(), usuario.getPassword(), Collections.singleton(createAuthority(usuario)));
	}

	private GrantedAuthority createAuthority(Usuario usuario) {
		return new SimpleGrantedAuthority(usuario.getRole());
	}
	
}
