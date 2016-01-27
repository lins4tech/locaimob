package com.lins4tech.locaimob.entities.vo;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Lins on 09/01/2016.
 */
@Entity
public class BairroVO {

    @Id
    private Integer idBairro;

    private String nomeBairro;

    private Integer idCidade;

    public Integer getIdBairro() {
        return idBairro;
    }

    public void setIdBairro(Integer idBairro) {
        this.idBairro = idBairro;
    }

    public String getNomeBairro() {
        return nomeBairro;
    }

    public void setNomeBairro(String nomeBairro) {
        this.nomeBairro = nomeBairro;
    }

    public Integer getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(Integer idCidade) {
        this.idCidade = idCidade;
    }



}
