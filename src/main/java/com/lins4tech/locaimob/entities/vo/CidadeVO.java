package com.lins4tech.locaimob.entities.vo;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Lins on 09/01/2016.
 */
@Entity
public class CidadeVO {
    @Id
    private Integer idCidade;

    private String nomeCidade;

    private Integer idEstado;

    public Integer getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(Integer idCidade) {
        this.idCidade = idCidade;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }
}
