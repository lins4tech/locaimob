package com.lins4tech.locaimob.entities.vo;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Classe criada para ajudar na lista de pessoas através do input
 * autocomplete(usado no form de contratos e imoveis)
 *
 * @author Lins
 */
@Entity
public class PessoaVO {

    @Id
    private Integer idPessoa;

    private String nome;

    private String cpfOrCnpj;

    private String tipoPessoa;

    private String enderecoCompleto;

    public Integer getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpfOrCnpj() {
        return cpfOrCnpj;
    }

    public void setCpfOrCnpj(String cpgOrCnpj) {
        this.cpfOrCnpj = cpgOrCnpj;
    }

    public String getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public String getEnderecoCompleto() {
        return enderecoCompleto;
    }

    public void setEnderecoCompleto(String enderecoCompleto) {
        this.enderecoCompleto = enderecoCompleto;
    }
}
