package com.lins4tech.locaimob.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the pessoa_fisica database table.
 */
@Entity
@Table(name = "pessoa_fisica")
@NamedQueries({
        @NamedQuery(name = "PessoaFisica.findAll", query = "SELECT p FROM PessoaFisica p"),
        @NamedQuery(name = "PessoaFisica.findById", query = "SELECT p FROM PessoaFisica p WHERE p.idpessoa = :id"),
        @NamedQuery(name = "PessoaFisica.findByNomeOrCpf", query = "SELECT p FROM PessoaFisica p WHERE p.nomeCompleto LIKE :nome OR p.cpf LIKE :cpf ORDER BY p.nomeCompleto"),
        @NamedQuery(name = "PessoaFisica.findByCpf", query = "SELECT p FROM PessoaFisica p WHERE p.cpf = :cpf"),
        @NamedQuery(name = "PessoaFisica.findByDataNascimento", query = "SELECT p FROM PessoaFisica p WHERE MONTH(p.dataNascimento) = :mes ORDER BY p.dataNascimento")
})


@PrimaryKeyJoinColumn(referencedColumnName = "idpessoa")
public class PessoaFisica extends Pessoa {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "PessoaFisica.findAll";
    public static final String FIND_ID = "PessoaFisica.findById";
    public static final String FIND_NOME_OR_CPF = "PessoaFisica.findByNomeOrCpf";
    public static final String FIND_CPF = "PessoaFisica.findByCpf";
    public static final String FIND_DATANASCIMENTO = "PessoaFisica.findByDataNascimento";

    private String cpf;

    @Column(name = "cpfconjuge")
    private String cpfConjuge;

    @Column(name = "datanascimento")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    @Column(name = "estadocivil")
    private String estadoCivil;

    private String nacionalidade;

    @Column(name = "nacionalidadeconjuge")
    private String nacionalidadeConjuge;

    @Column(name = "nomecompleto")
    private String nomeCompleto;

    @Column(name = "nomeconjuge")
    private String nomeConjuge;

    @Column(name = "profissao")
    private String profissao;

    private String rg;

    @Column(name = "rgconjuge")
    private String rgConjuge;

    @Column(name = "rgexpedidor")
    private String rgExpedidor;

    @Column(name = "rgexpedidorconjuge")
    private String rgExpedidorConjuge;

    private String sexo;

    @Column(name = "sexoconjuge")
    private String sexoConjuge;

    public PessoaFisica() {
    }


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpfConjuge() {
        return cpfConjuge;
    }

    public void setCpfConjuge(String cpfConjuge) {
        this.cpfConjuge = cpfConjuge;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getNacionalidadeConjuge() {
        return nacionalidadeConjuge;
    }

    public void setNacionalidadeConjuge(String nacionalidadeConjuge) {
        this.nacionalidadeConjuge = nacionalidadeConjuge;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getNomeConjuge() {
        return nomeConjuge;
    }

    public void setNomeConjuge(String nomeConjuge) {
        this.nomeConjuge = nomeConjuge;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getRgConjuge() {
        return rgConjuge;
    }

    public void setRgConjuge(String rgConjuge) {
        this.rgConjuge = rgConjuge;
    }

    public String getRgExpedidor() {
        return rgExpedidor;
    }

    public void setRgExpedidor(String rgExpedidor) {
        this.rgExpedidor = rgExpedidor;
    }

    public String getRgExpedidorConjuge() {
        return rgExpedidorConjuge;
    }

    public void setRgExpedidorConjuge(String rgExpedidorConjuge) {
        this.rgExpedidorConjuge = rgExpedidorConjuge;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getSexoConjuge() {
        return sexoConjuge;
    }

    public void setSexoConjuge(String sexoConjuge) {
        this.sexoConjuge = sexoConjuge;
    }
}