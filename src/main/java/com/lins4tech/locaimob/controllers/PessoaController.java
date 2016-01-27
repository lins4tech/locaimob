package com.lins4tech.locaimob.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lins4tech.locaimob.entities.PessoaFisica;
import com.lins4tech.locaimob.entities.PessoaJuridica;
import com.lins4tech.locaimob.entities.vo.PessoaVO;
import com.lins4tech.locaimob.repositories.PessoaRepository;

@RestController
public class PessoaController {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@RequestMapping(value = "pf/save", method = RequestMethod.POST)
	public PessoaFisica savePessoaFisica(@RequestBody PessoaFisica pessoaFisica) {
		return pessoaRepository.savePessoaFisica(pessoaFisica);
	}
	
	@RequestMapping(value = "pj/save", method = RequestMethod.POST)
	public PessoaJuridica savePessoaJuridica(@RequestBody PessoaJuridica pessoaJuridica) {
		return pessoaRepository.savePessoaJuridica(pessoaJuridica);
	}
	
	@RequestMapping(value = "pf/remove", method = RequestMethod.POST)
	public Boolean removePessoaFisica(Integer idPessoa) {
		return pessoaRepository.removePessoaFisica(idPessoa);
	}
	
	@RequestMapping(value = "pj/remove", method = RequestMethod.POST)
	public Boolean removePessoaJuridica(Integer idPessoa) {
		return pessoaRepository.removePessoaJuridica(idPessoa);
	}
	
	@RequestMapping(value = "pf/findAll", method = RequestMethod.GET)
	public List<PessoaFisica> findPessoaFisicaAll() {
		return pessoaRepository.findAllPessoaFisica();
	}
	
	@RequestMapping(value = "pj/findAll", method = RequestMethod.GET)
	public List<PessoaJuridica> findPessoaJuridicaAll() {
		return pessoaRepository.findAllPessoaJuridica();
	}

	@RequestMapping(value = "pf/findByNomeOrCpf", method = RequestMethod.GET)
	public List<PessoaFisica> findPessoaFisicaByNomeOrCpf(String texto) {
		return pessoaRepository.findPessoaFisicaByNomeOrCpf(texto);
	}
	
	@RequestMapping(value = "pj/findByRazaoOrCnpj", method = RequestMethod.GET)
	public List<PessoaJuridica> findPessoaJuridicaByNomeOrCpf(String texto) {
		return pessoaRepository.findPessoaJuridicaByRazaoSocialOrCnpj(texto);
	}
	
	@RequestMapping(value = "pf/findByCpf", method = RequestMethod.GET)
	public PessoaFisica findPessoaFisicaByCpf(String cpf) {
		return pessoaRepository.findPessoaFisicaByCpf(cpf);
	}
	
	@RequestMapping(value = "pf/findByMesNascimento", method = RequestMethod.GET)
	public List<PessoaFisica> findPessoaFisicaByCpf(Integer mes) {
		return pessoaRepository.findPessoaFisicaByDataNascimento(mes);
	}
	
	@RequestMapping(value = "pj/findByCnpj", method = RequestMethod.GET)
	public PessoaJuridica findPessoaJuridicaByCnpj(String cnpj) {
		return pessoaRepository.findPessoaJuridicaByCnpj(cnpj);
	}
	
	@RequestMapping(value = "pessoa/findAll", method = RequestMethod.GET)
	public List<PessoaVO> findAllPessoas() {
		return pessoaRepository.findAllPessoaVO();
	}
	
}
