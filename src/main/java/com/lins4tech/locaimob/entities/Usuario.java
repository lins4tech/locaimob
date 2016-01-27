package com.lins4tech.locaimob.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQueries (value = {
		@NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
		@NamedQuery(name = "Usuario.findByUsername", query = "SELECT u FROM Usuario u WHERE u.username = :username")
})
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String FIND_ALL = "Usuario.findAll";
	public static final String FIND_BY_USERNAME = "Usuario.findByUsername";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idusuario;

	@Column(name = "datacadastro")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;

	@Column(name = "dataultimoacesso")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataUltimoAcesso;

	private String email;

	private String password;

	private String role;

	private String username;

	public Usuario() {
	}

	public int getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataUltimoAcesso() {
		return dataUltimoAcesso;
	}

	public void setDataUltimoAcesso(Date dataUltimoAcesso) {
		this.dataUltimoAcesso = dataUltimoAcesso;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}