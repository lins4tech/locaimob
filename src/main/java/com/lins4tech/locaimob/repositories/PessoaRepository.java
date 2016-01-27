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
import com.lins4tech.locaimob.entities.PessoaFisica;
import com.lins4tech.locaimob.entities.PessoaJuridica;
import com.lins4tech.locaimob.entities.vo.PessoaVO;

@Repository
public class PessoaRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(readOnly = false)
	public PessoaFisica savePessoaFisica(PessoaFisica pf) {
		if (pf.getIdpessoa() != null && pf.getIdpessoa() > 0) {
			pf.setDataUltimaAtualizacao(new Date());
		} else {
			pf.setDataCadastro(new Date());
		}
		return entityManager.merge(pf);
	}

	@Transactional(readOnly = false)
	public PessoaJuridica savePessoaJuridica(PessoaJuridica pj) {
		if (pj.getIdpessoa() != null && pj.getIdpessoa() > 0) {
			pj.setDataUltimaAtualizacao(new Date());
		} else {
			pj.setDataCadastro(new Date());
		}
		return entityManager.merge(pj);
	}

	@Transactional(readOnly = false)
	public Boolean removePessoaFisica(Integer id) {
		PessoaFisica pf = findPessoaFisicaById(id);
		if (pf != null) {
			try {
				entityManager.remove(pf);
				return true;
			} catch (PersistenceException e) {
				return false;
			}
		}
		return false;
	}

	@Transactional(readOnly = false)
	public Boolean removePessoaJuridica(Integer id) {
		PessoaJuridica pj = findPessoaJuridicaById(id);
		if (pj != null) {
			try {
				entityManager.remove(pj);
				return true;
			} catch (PersistenceException e) {
				return false;
			}
		}
		return false;
	}

	@Transactional(readOnly = true)
	public PessoaFisica findPessoaFisicaById(Integer id) {
		try {
			return entityManager.createNamedQuery(PessoaFisica.FIND_ID, PessoaFisica.class).setParameter("idpessoa", id)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Transactional(readOnly = true)
	public PessoaJuridica findPessoaJuridicaById(Integer id) {
		try {
			return entityManager.createNamedQuery(PessoaJuridica.FIND_ID, PessoaJuridica.class)
					.setParameter("idpessoa", id).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Transactional(readOnly = true)
	public List<PessoaFisica> findAllPessoaFisica() {
		return entityManager.createNamedQuery(PessoaFisica.FIND_ALL, PessoaFisica.class).getResultList();
	}

	@Transactional(readOnly = true)
	public List<PessoaJuridica> findAllPessoaJuridica() {
		return entityManager.createNamedQuery(PessoaJuridica.FIND_ALL, PessoaJuridica.class).getResultList();
	}

	@Transactional(readOnly = true)
	public List<PessoaFisica> findPessoaFisicaByNomeOrCpf(String text) {
		TypedQuery<PessoaFisica> query = entityManager.createNamedQuery(PessoaFisica.FIND_NOME_OR_CPF,
				PessoaFisica.class);
		text = text + "%";
		query.setParameter("nome", text);
		query.setParameter("cpf", text);
		return query.getResultList();
	}

	@Transactional(readOnly = true)
	public List<PessoaJuridica> findPessoaJuridicaByRazaoSocialOrCnpj(String text) {
		TypedQuery<PessoaJuridica> query = entityManager.createNamedQuery(PessoaJuridica.FIND_RAZAOSOCIAL_OR_CNPJ,
				PessoaJuridica.class);
		text = text + "%";
		query.setParameter("razaoSocial", text);
		query.setParameter("cnpj", text);
		return query.getResultList();
	}

	@Transactional(readOnly = true)
	public PessoaFisica findPessoaFisicaByCpf(String cpf) {
		TypedQuery<PessoaFisica> query = entityManager.createNamedQuery(PessoaFisica.FIND_CPF, PessoaFisica.class);
		query.setParameter("cpf", cpf);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Transactional(readOnly = true)
	public PessoaJuridica findPessoaJuridicaByCnpj(String cnpj) {
		TypedQuery<PessoaJuridica> query = entityManager.createNamedQuery(PessoaJuridica.FIND_CNPJ,
				PessoaJuridica.class);
		query.setParameter("cnpj", cnpj);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Transactional(readOnly = true)
	public List<PessoaFisica> findPessoaFisicaByDataNascimento(Integer mes) {
		TypedQuery<PessoaFisica> query = entityManager.createNamedQuery(PessoaFisica.FIND_DATANASCIMENTO,
				PessoaFisica.class);
		query.setParameter("mes", mes);
//		query.setParameter("dataInicio", dataInicio, TemporalType.DATE);
//		query.setParameter("dataInicio", dataFim, TemporalType.DATE);
		return query.getResultList();
	}
	
	@Transactional(readOnly = true)
	public List<PessoaVO> findAllPessoaVO() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.idpessoa, CONCAT( IFNULL(pf.nomeCompleto,''), IFNULL(pj.razaoSocial,'')) AS nome, ");
		sql.append("CONCAT(IFNULL(pf.cpf,''), IFNULL(pj.cnpj,'')) AS cpfOrCnpj, p.tipopessoa as tipoPessoa, ");
		sql.append("CONCAT(en.logradouro, ' ', IFNULL(en.complemento,''), ' - ', en.bairro, ', ', en.cidade,'/', en.estado, ' - CEP: ', en.cep) AS enderecoCompleto ");
		sql.append("FROM pessoa p ");
		sql.append("LEFT JOIN pessoa_fisica pf ON p.idpessoa = pf.idpessoa ");
		sql.append("LEFT JOIN pessoa_juridica pj ON p.idpessoa = pj.idpessoa ");
		sql.append("LEFT JOIN endereco en ON en.idendereco = p.idendereco ");
		sql.append("ORDER BY nome");
		TypedQuery<PessoaVO> query = (TypedQuery<PessoaVO>) entityManager.createNativeQuery(sql.toString(),PessoaVO.class);
		return query.getResultList();
	}

}
