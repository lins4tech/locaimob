package com.lins4tech.locaimob.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the pessoa_juridica database table.
 * 
 */
@Entity
@Table(name = "pessoa_juridica")
@NamedQueries (value = {
		@NamedQuery(name = "PessoaJuridica.findAll", query = "SELECT p FROM PessoaJuridica p"),
		@NamedQuery(name = "PessoaJuridica.findById", query = "SELECT p FROM PessoaJuridica p WHERE p.idpessoa = :idpessoa"),
		@NamedQuery(name = "PessoaJuridica.findByRazaoSocialOrCnpj", query = "SELECT p FROM PessoaJuridica p WHERE p.razaoSocial LIKE :razaoSocial OR p.cnpj LIKE :cnpj ORDER BY p.razaoSocial"),
		@NamedQuery(name = "PessoaJuridica.findByCnpj", query = "SELECT p FROM PessoaJuridica p WHERE p.cnpj = :cnpj")
})
@PrimaryKeyJoinColumn(referencedColumnName = "idpessoa")
public class PessoaJuridica extends Pessoa {
	private static final long serialVersionUID = 1L;
	
	public static final String FIND_ALL = "PessoaJuridica.findAll";
	public static final String FIND_ID = "PessoaJuridica.findById";
	public static final String FIND_RAZAOSOCIAL_OR_CNPJ = "PessoaJuridica.findByRazaoSocialOrCnpj";
	public static final String FIND_CNPJ = "PessoaJuridica.findByRazaoSocialOrCnpj";

	@Column(name = "atividadeprincipal")
	private String atividadePrincipal;

	private String cnpj;

	@Column(name = "cpfsocioadministrador")
	private String cpfSocioAdministrador;

	@Column(name = "datafundacao")
	@Temporal(TemporalType.DATE)
	private Date dataFundacao;

	@Column(name = "nomefantasia")
	private String nomeFantasia;

	@Column(name = "nomesocioadministrador")
	private String nomeSocioAdministrador;

	@Column(name = "razaosocial")
	private String razaoSocial;

	public PessoaJuridica() {
	}

	public String getAtividadePrincipal() {
		return atividadePrincipal;
	}

	public void setAtividadePrincipal(String atividadePrincipal) {
		this.atividadePrincipal = atividadePrincipal;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCpfSocioAdministrador() {
		return cpfSocioAdministrador;
	}

	public void setCpfSocioAdministrador(String cpfSocioAdministrador) {
		this.cpfSocioAdministrador = cpfSocioAdministrador;
	}

	public Date getDataFundacao() {
		return dataFundacao;
	}

	public void setDataFundacao(Date dataFundacao) {
		this.dataFundacao = dataFundacao;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getNomeSocioAdministrador() {
		return nomeSocioAdministrador;
	}

	public void setNomeSocioAdministrador(String nomeSocioAdministrador) {
		this.nomeSocioAdministrador = nomeSocioAdministrador;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

}