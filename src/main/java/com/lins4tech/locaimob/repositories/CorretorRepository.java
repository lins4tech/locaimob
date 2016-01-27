package com.lins4tech.locaimob.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lins4tech.locaimob.entities.Corretor;

@Repository
public class CorretorRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional(readOnly = false)
	public Corretor saveCorretor(Corretor corretor) {
		if(corretor.getIdcorretor() == null || corretor.getIdcorretor() <= 0) {
			corretor.setDataCadastro(new Date());
		}
		return entityManager.merge(corretor);
	}
	
	@Transactional(readOnly = false)
	public Boolean removeCorretor(Integer idCorretor) {
		Corretor corretorInativo = entityManager.find(Corretor.class, idCorretor);
		corretorInativo.setAtivo(false);
		corretorInativo.setDataUltimaAtualizacao(new Date());
		entityManager.merge(corretorInativo);
		return true;
	}
	
	@Transactional(readOnly = true)
	public List<Corretor> findAllAtivos() {
		return entityManager.createNamedQuery(Corretor.FIND_ALL_ATIVOS, Corretor.class).getResultList();
	}
	
}
