package com.lins4tech.locaimob.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lins4tech.locaimob.entities.Contrato;
import com.lins4tech.locaimob.entities.Imovel;

@Repository
public class ContratoRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(readOnly = false)
	public Contrato saveContrato(Contrato contrato) {
		if (contrato.getIdcontrato() != null && contrato.getIdcontrato() > 0) {
			contrato.setDataUltimaAtualizacao(new Date());
		} else {
			contrato.setDataCadastro(new Date());
		}
		return entityManager.merge(contrato);
	}

	@Transactional(readOnly = false)
	public boolean removeContrato(Integer idContrato) {
		try {
			Contrato contrato = entityManager.find(Contrato.class, idContrato);
			entityManager.remove(contrato);
			return true;
		} catch (PersistenceException e) {
			return false;
		}
	}

	public Boolean finalizarContrato(Integer idContrato, Date dataTermino) {
		try {
			Contrato contrato = entityManager.find(Contrato.class, idContrato);
			contrato.setDataTermino(dataTermino);
			Imovel imovel = contrato.getImovel();
			imovel.setIsDisponivel(true);
			contrato = entityManager.merge(contrato);
			entityManager.merge(imovel);
			return true;
		} catch (PersistenceException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Transactional(readOnly = true)
	public List<Contrato> findContratoAll() {
		return entityManager.createNamedQuery(Contrato.FIND_ALL, Contrato.class).getResultList();
	}

	@Transactional(readOnly = true)
	public List<Contrato> findContratoByImovel(Integer idImovel) {
		TypedQuery<Contrato> query = entityManager.createNamedQuery(Contrato.FIND_IMOVEL, Contrato.class);
		query.setParameter("idImovel", idImovel);
		return query.getResultList();
	}

	@Transactional(readOnly = true)
	public List<Contrato> findContratoByAtivo() {
		return entityManager.createNamedQuery(Contrato.FIND_ATIVO, Contrato.class).getResultList();
	}

	@Transactional(readOnly = true)
	public Contrato findContratoByAtivoAndImovel(Integer idImovel) {
		TypedQuery<Contrato> query = entityManager.createNamedQuery(Contrato.FIND_ATIVO_IMOVEL, Contrato.class);
		query.setParameter("idImovel", idImovel);
		try {
			return query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}

	@Transactional(readOnly = true)
	public List<Contrato> findContratoByAtivoAndLocatario(Integer idLocatario) {
		TypedQuery<Contrato> query = entityManager.createNamedQuery(Contrato.FIND_ATIVO_LOCATARIO, Contrato.class);
		query.setParameter("idLocatario", idLocatario);
		return query.getResultList();
	}

	@Transactional(readOnly = true)
	public List<Contrato> findContratoByAtivoAndLocador(Integer idLocador) {
		TypedQuery<Contrato> query = entityManager.createNamedQuery(Contrato.FIND_ATIVO_LOCADOR, Contrato.class);
		query.setParameter("idLocador", idLocador);
		return query.getResultList();
	}

}
