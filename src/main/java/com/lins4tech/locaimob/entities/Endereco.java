package com.lins4tech.locaimob.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the endereco database table.
 * 
 */
@Entity
@NamedQuery(name = "Endereco.findAll", query = "SELECT e FROM Endereco e")
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idendereco;

	private String bairro;

	private String cep;

	private String cidade;

	private String complemento;

	private String estado;

	private String logradouro;

	private String pais;

	public Endereco() {
	}

	public int getIdendereco() {
		return idendereco;
	}

	public void setIdendereco(int idendereco) {
		this.idendereco = idendereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

}