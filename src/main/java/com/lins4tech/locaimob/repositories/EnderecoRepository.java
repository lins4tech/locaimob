package com.lins4tech.locaimob.repositories;

import com.lins4tech.locaimob.entities.vo.BairroVO;
import com.lins4tech.locaimob.entities.vo.CidadeVO;
import com.lins4tech.locaimob.entities.vo.EstadoVO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Lins on 09/01/2016.
 */
@Repository
public class EnderecoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<EstadoVO> findAllEstados() {
        return entityManager.createNativeQuery("SELECT e.idEstado, e.sigla, e.nome FROM tblestado e ORDER BY e.nome", EstadoVO.class).getResultList();
    }

    @Transactional(readOnly = true)
    public List<CidadeVO> findCidadeByEstado(Integer idEstado) {
        String sql = "SELECT c.idCidade, c.idEstado, c.nomeCidade FROM tblcidade c WHERE c.idEstado = :idEstado ORDER BY c.nomeCidade";
        TypedQuery<CidadeVO> typedQuery = (TypedQuery<CidadeVO>) entityManager.createNativeQuery(sql, CidadeVO.class);
        typedQuery.setParameter("idEstado", idEstado);
        List<CidadeVO> results = typedQuery.getResultList();
        System.out.println("QUANTIDADE DE CIDADES do EstadoID=" + idEstado + " :" + results.size());
        return results;
    }

    @Transactional(readOnly = true)
    public List<BairroVO> findBairroByCidade(Integer idCidade) {
        String sql = "SELECT b.idBairro, b.idCidade, b.nomeBairro FROM tblbairro b WHERE b.idCidade = :idCidade ORDER BY b.nomeBairro";
        TypedQuery<BairroVO> typedQuery = (TypedQuery<BairroVO>) entityManager.createNativeQuery(sql, BairroVO.class);
        typedQuery.setParameter("idCidade", idCidade);
        return typedQuery.getResultList();
    }
}
