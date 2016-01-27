package com.lins4tech.locaimob.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lins4tech.locaimob.entities.Contrato;
import com.lins4tech.locaimob.repositories.ContratoRepository;

@RestController
public class ContratoController {

	@Autowired
	private ContratoRepository contratoRepository;
	
	@RequestMapping(value = "contrato/save", method = RequestMethod.POST)
	public Contrato saveContrato(@RequestBody Contrato contrato) {
		return contratoRepository.saveContrato(contrato);
	}
	
	@RequestMapping(value = "contrato/remove", method = RequestMethod.POST)
	public Boolean removeContrato(Integer idContrato) {
		return contratoRepository.removeContrato(idContrato);
	}
	
	@RequestMapping(value = "contrato/finalizar", method = RequestMethod.POST)
	public Boolean finalizarContrato(Integer idContrato, Date dataTermino) {
		return contratoRepository.finalizarContrato(idContrato, dataTermino);
	}
	
	@RequestMapping(value = "contrato/findByImovel", method = RequestMethod.GET)
	public List<Contrato> findByImovel(Integer idImovel) {
		return contratoRepository.findContratoByImovel(idImovel);
	}
	
	@RequestMapping(value = "contrato/findAtivos", method = RequestMethod.GET)
	public List<Contrato> findAtivos() {
		return contratoRepository.findContratoByAtivo();
	}
	
	@RequestMapping(value = "contrato/findAtivoByImovel", method = RequestMethod.GET)
	public Contrato findByAtivoByImovel(Integer idImovel) {
		return contratoRepository.findContratoByAtivoAndImovel(idImovel);
	}
	
	@RequestMapping(value = "contrato/findAtivoByLocatario", method = RequestMethod.GET)
	public List<Contrato> findByAtivoByLocatario(Integer idLocatario) {
		return contratoRepository.findContratoByAtivoAndLocatario(idLocatario);
	}
	
	@RequestMapping(value = "contrato/findAtivoByLocador", method = RequestMethod.GET)
	public List<Contrato> findByAtivoByLocador(Integer idLocador) {
		return contratoRepository.findContratoByAtivoAndLocador(idLocador);
	}
	
}
