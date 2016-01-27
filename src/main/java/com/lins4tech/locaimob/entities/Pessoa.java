package com.lins4tech.locaimob.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the pessoa database table.
 * 
 */
@Entity
@NamedQuery(name = "Pessoa.findAll", query = "SELECT p FROM Pessoa p")
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idpessoa;
	
	@ManyToOne(cascade={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name = "idendereco")
	private Endereco endereco;

	@Column(name = "datacadastro")
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;

	@Column(name = "dataultimaatualizacao")
	@Temporal(TemporalType.DATE)
	private Date dataUltimaAtualizacao;

	private String email;

	@Column(name = "rendamensal")
	private Double rendaMensal;

	@Column(name = "telcelular")
	private String telCelular;

	@Column(name = "telresidencial")
	private String telResidencial;

	@Column(name = "tipopessoa")
	private String tipoPessoa;

	public Pessoa() {
	}

	public Integer getIdpessoa() {
		return idpessoa;
	}

	public void setIdpessoa(Integer idpessoa) {
		this.idpessoa = idpessoa;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataUltimaAtualizacao() {
		return dataUltimaAtualizacao;
	}

	public void setDataUltimaAtualizacao(Date dataUltimaAtualizacao) {
		this.dataUltimaAtualizacao = dataUltimaAtualizacao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Double getRendaMensal() {
		return rendaMensal;
	}

	public void setRendaMensal(Double rendaMensal) {
		this.rendaMensal = rendaMensal;
	}

	public String getTelCelular() {
		return telCelular;
	}

	public void setTelCelular(String telCelular) {
		this.telCelular = telCelular;
	}

	public String getTelResidencial() {
		return telResidencial;
	}

	public void setTelResidencial(String telResidencial) {
		this.telResidencial = telResidencial;
	}

	public String getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}