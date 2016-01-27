package com.lins4tech.locaimob.repositories;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lins4tech.locaimob.entities.Imovel;

@Repository
public class ImovelRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional(readOnly = false)
	public Imovel saveImovel(Imovel imovel) {
		if(imovel.getIdimovel() != null && imovel.getIdimovel() > 0) {
			imovel.setDataUltimaAtualizacao(new Date());
		}else{
			imovel.setDataCadastro(new Date());
		}
		return entityManager.merge(imovel);
	}
	
	@Transactional(readOnly = false)
	public Boolean removeImovel(Integer id) {
		Imovel imovelInativo = findImovelById(id);
		imovelInativo.setAtivo(false);
		imovelInativo.setDataUltimaAtualizacao(new Date());
		entityManager.merge(imovelInativo);
		return true;
	}
	
	@Transactional(readOnly = true)
	public List<Imovel> findImovelDisponivel() {
		TypedQuery<Imovel> query = entityManager.createNamedQuery(Imovel.FIND_DISPONIVEL, Imovel.class);
		return query.getResultList();
	}
	
	@Transactional(readOnly = true)
	public List<Imovel> findImovelAlugados(List<Integer> ids) {
		TypedQuery<Imovel> query = entityManager.createNamedQuery(Imovel.FIND_ALUGADO, Imovel.class);
		return query.getResultList();
	}
	
	@Transactional(readOnly = true)
	public Imovel findImovelById(Integer id) {
		return entityManager.find(Imovel.class, id);
	}
	
	@Transactional(readOnly = true)
	public List<Imovel> findImovelByEstado(String estado) {
		TypedQuery<Imovel> query = entityManager.createNamedQuery(Imovel.FIND_ESTADO, Imovel.class);
		query.setParameter("estado", estado);
		return query.getResultList();
	}
	
	@Transactional(readOnly = true)
	public List<Imovel> findImovelByCidade(String estado, String cidade) {
		TypedQuery<Imovel> query = entityManager.createNamedQuery(Imovel.FIND_CIDADE, Imovel.class);
		query.setParameter("estado", estado);
		query.setParameter("cidade", cidade);
		return query.getResultList();
	}
	
	@Transactional(readOnly = true)
	public List<Imovel> findImovelByBairro(String estado, String cidade, String bairro) {
		TypedQuery<Imovel> query = entityManager.createNamedQuery(Imovel.FIND_BAIRRO, Imovel.class);
		query.setParameter("estado", estado);
		query.setParameter("cidade", cidade);
		query.setParameter("bairro", bairro);
		return query.getResultList();
	}
	
	@Transactional(readOnly = true)
	public List<Imovel> findImovelByDisponibilidadeAndEstado(boolean isDisponivel, String estado) {
		TypedQuery<Imovel> query = entityManager.createNamedQuery(Imovel.FIND_DISPONIBILIDADE_ESTADO, Imovel.class);
		query.setParameter("isDisponivel", isDisponivel);
		query.setParameter("estado", estado);
		return query.getResultList();
	}
	
	@Transactional(readOnly = true)
	public List<Imovel> findImovelByDisponibilidadeAndCidade(boolean isDisponivel, String estado, String cidade) {
		TypedQuery<Imovel> query = entityManager.createNamedQuery(Imovel.FIND_DISPONIBILIDADE_CIDADE, Imovel.class);
		query.setParameter("isDisponivel", isDisponivel);
		query.setParameter("estado", estado);
		query.setParameter("cidade", cidade);
		return query.getResultList();
	}
	
	@Transactional(readOnly = true)
	public List<Imovel> findImovelByDisponibilidadeAndBairro(boolean isDisponivel, String estado, String cidade, String bairro) {
		TypedQuery<Imovel> query = entityManager.createNamedQuery(Imovel.FIND_DISPONIBILIDADE_BAIRRO, Imovel.class);
		query.setParameter("isDisponivel", isDisponivel);
		query.setParameter("estado", estado);
		query.setParameter("cidade", cidade);
		query.setParameter("bairro", bairro);
		return query.getResultList();
	}
	
}
