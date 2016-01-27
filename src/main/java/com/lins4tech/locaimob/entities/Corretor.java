package com.lins4tech.locaimob.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the corretor database table.
 * 
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "Corretor.findAllAtivos", query = "SELECT c FROM Corretor c WHERE c.ativo = true ORDER BY c.nomeCorretor"),
		@NamedQuery(name = "Corretor.findAllInativos", query = "SELECT c FROM Corretor c WHERE c.ativo = false ORDER BY c.nomeCorretor")
})

public class Corretor implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL_ATIVOS = "Corretor.findAllAtivos";
	public static final String FIND_ALL_INATIVOS = "Corretor.findAllInativos";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idcorretor;

	@Column(name = "datacadastro")
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;

	@Column(name = "datanascimento")
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;

	@Column(name = "nomecorretor")
	private String nomeCorretor;

	@Column(name = "email")
	private String email;

	@Column(name = "ativo")
	private Boolean ativo;

	@Column(name = "dataultimaatualizacao")
	@Temporal(TemporalType.DATE)
	private Date dataUltimaAtualizacao;

	public Corretor() {
	}

	public Integer getIdcorretor() {
		return idcorretor;
	}

	public void setIdcorretor(Integer idcorretor) {
		this.idcorretor = idcorretor;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getNomeCorretor() {
		return nomeCorretor;
	}

	public void setNomeCorretor(String nomeCorretor) {
		this.nomeCorretor = nomeCorretor;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Date getDataUltimaAtualizacao() {
		return dataUltimaAtualizacao;
	}

	public void setDataUltimaAtualizacao(Date dataUltimaAtualizacao) {
		this.dataUltimaAtualizacao = dataUltimaAtualizacao;
	}

}