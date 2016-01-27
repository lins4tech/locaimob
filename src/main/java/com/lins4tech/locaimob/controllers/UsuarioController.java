package com.lins4tech.locaimob.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lins4tech.locaimob.entities.Usuario;
import com.lins4tech.locaimob.repositories.UsuarioRepository;

@RestController
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository; 
	
	@RequestMapping(value = "usuario/current", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	//@ResponseBody Não Precisa
	public Usuario currentUsuario(Principal principal) {
		Assert.notNull(principal);
		return usuarioRepository.findByUsername(principal.getName());
	}
}
