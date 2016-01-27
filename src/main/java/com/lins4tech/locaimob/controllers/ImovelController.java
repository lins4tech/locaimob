package com.lins4tech.locaimob.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lins4tech.locaimob.entities.Imovel;
import com.lins4tech.locaimob.repositories.ImovelRepository;

@RestController
public class ImovelController {

	@Autowired
	private ImovelRepository imovelRepository;
	
	@RequestMapping(value = "imovel/save", method = RequestMethod.POST)
	public Imovel saveImovel(@RequestBody Imovel imovel) {
		return imovelRepository.saveImovel(imovel);
	}
	
	@RequestMapping(value = "imovel/remove", method = RequestMethod.POST)
	public Boolean removeImovel(Integer idImovel) {
		return imovelRepository.removeImovel(idImovel);
	}
	
	@RequestMapping(value = "imovel/findDisponivel", method = RequestMethod.GET)
	public List<Imovel> findImovelDisponivel() {
		return imovelRepository.findImovelDisponivel();
	}
	
	@RequestMapping(value = "imovel/findAlugado", method = RequestMethod.GET)
	public List<Imovel> findImovelAlugado() {
		return imovelRepository.findImovelAlugados(null);
	}
	
	@RequestMapping(value = "imovel/findByEstado", method = RequestMethod.GET)
	public List<Imovel> findImovelByEstado(String estado) {
		return imovelRepository.findImovelByEstado(estado);
	}
	
	@RequestMapping(value = "imovel/findByCidade", method = RequestMethod.GET)
	public List<Imovel> findImovelByCidade(String estado, String cidade) {
		return imovelRepository.findImovelByCidade(estado, cidade);
	}
	
	@RequestMapping(value = "imovel/findByBairro", method = RequestMethod.GET)
	public List<Imovel> findImovelByCidadeAndBairro(String estado, String cidade, String bairro) {
		return imovelRepository.findImovelByBairro(estado, cidade, bairro);
	}
	
	@RequestMapping(value = "imovel/findByDisponibilidadeAndEstado", method = RequestMethod.GET)
	public List<Imovel> findImovelByDisponibilidadeAndEstado(boolean isDisponivel, String estado) {
		return imovelRepository.findImovelByDisponibilidadeAndEstado(isDisponivel, estado);
	}
	
	@RequestMapping(value = "imovel/findByDisponibilidadeAndCidade", method = RequestMethod.GET)
	public List<Imovel> findImovelByDisponibilidadeAndEstado(boolean isDisponivel, String estado, String cidade) {
		return imovelRepository.findImovelByDisponibilidadeAndCidade(isDisponivel, estado, cidade);
	}
	
	@RequestMapping(value = "imovel/findByDisponibilidadeAndBairro", method = RequestMethod.GET)
	public List<Imovel> findImovelByDisponibilidadeAndBairro(boolean isDisponivel, String estado, String cidade, String bairro) {
		return imovelRepository.findImovelByDisponibilidadeAndBairro(isDisponivel, estado, cidade, bairro);
	}
}
