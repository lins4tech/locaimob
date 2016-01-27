package com.lins4tech.locaimob.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lins4tech.locaimob.entities.Corretor;
import com.lins4tech.locaimob.repositories.CorretorRepository;

@RestController
public class CorretorController {

	@Autowired
	public CorretorRepository corretorRepository;
	
	@RequestMapping(value = "corretor/save",  method = RequestMethod.POST)
	public Corretor saveCorretor(@RequestBody Corretor corretor) {
		corretor.setAtivo(true);
		return corretorRepository.saveCorretor(corretor);
	}
	
	@RequestMapping(value = "corretor/findAllAtivos",  method = RequestMethod.GET)
	public List<Corretor> findAllCorretorAtivos() {
		return corretorRepository.findAllAtivos();
	}
}
