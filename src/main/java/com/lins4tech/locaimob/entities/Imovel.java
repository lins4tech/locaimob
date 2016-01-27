package com.lins4tech.locaimob.entities;

import java.io.Serializable;
import javax.persistence.*;

import org.omg.CORBA.INTERNAL;

import java.util.Date;

/**
 * The persistent class for the imovel database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "Imovel.findDisponivel", query = "SELECT i FROM Imovel i WHERE i.ativo = true AND i.isDisponivel = true ORDER BY i.dataCadastro"),
	@NamedQuery(name = "Imovel.findAlugado", query = "SELECT i FROM Imovel i WHERE i.ativo = true AND i.isDisponivel = false ORDER BY i.dataCadastro"),
	@NamedQuery(name = "Imovel.findByEstado", query = "SELECT i FROM Imovel i WHERE i.ativo = true AND i.endereco.estado = :estado"),
	@NamedQuery(name = "Imovel.findByCidade", query = "SELECT i FROM Imovel i WHERE i.ativo = true AND i.endereco.estado = :estado AND i.endereco.cidade = :cidade"),
	@NamedQuery(name = "Imovel.findByBairro", query = "SELECT i FROM Imovel i WHERE i.ativo = true AND i.endereco.estado = :estado AND i.endereco.cidade = :cidade AND i.endereco.bairro = :bairro"),
	@NamedQuery(name = "Imovel.findByDisponibilidadeAndEstado", query = "SELECT i FROM Imovel i WHERE i.ativo = true AND i.isDisponivel = :isDisponivel AND i.endereco.estado = :estado"),
	@NamedQuery(name = "Imovel.findByDisponibilidadeAndCidade", query = "SELECT i FROM Imovel i WHERE i.ativo = true AND i.isDisponivel = :isDisponivel AND i.endereco.estado = :estado AND i.endereco.cidade = :cidade"),
	@NamedQuery(name = "Imovel.findByDisponibilidadeAndBairro", query = "SELECT i FROM Imovel i WHERE i.ativo = true AND i.isDisponivel = :isDisponivel AND i.endereco.estado = :estado AND i.endereco.cidade = :cidade AND i.endereco.bairro = :bairro")
	
})

public class Imovel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String FIND_DISPONIVEL = "Imovel.findDisponivel";
	public static final String FIND_ALUGADO = "Imovel.findAlugado";
	public static final String FIND_ESTADO = "Imovel.findByEstado";
	public static final String FIND_CIDADE = "Imovel.findByCidade";
	public static final String FIND_BAIRRO = "Imovel.findByBairro";
	public static final String FIND_DISPONIBILIDADE_ESTADO = "Imovel.findByDisponibilidadeAndEstado";
	public static final String FIND_DISPONIBILIDADE_CIDADE = "Imovel.findByDisponibilidadeAndCidade";
	public static final String FIND_DISPONIBILIDADE_BAIRRO = "Imovel.findByDisponibilidadeAndBairro";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idimovel;

	@Column(name = "areautil")
	private Double areaUtil;

	private Boolean ativo;

	@Column(name = "datacadastro")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;

	@Column(name = "dataultimaatualizacao")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataUltimaAtualizacao;

	private String descricao;
	
	@Column(name = "isdisponivel")
	private Boolean isDisponivel;

	@Column(name = "hasareaservico")
	private Boolean hasAreaServico;

	@Column(name = "hascozinha")
	private Boolean hasCozinha;

	@Column(name = "hasdespensa")
	private Boolean hasDespensa;

	@Column(name = "hasmobiliado")
	private Boolean hasMobiliado;

	@Column(name = "haspiscina")
	private Boolean hasPiscina;

	@Column(name = "hasvaranda")
	private Boolean hasVaranda;

	@Column(name = "qtdquarto")
	private int qtdQuarto;

	@Column(name = "qtdsuite")
	private int qtdSuite;

	@Column(name = "qtdvagagaragem")
	private int qtdVagaGaragem;

	@Column(name = "qtdwcsocial")
	private int qtdWcSocial;

	@Column(name = "taxaadministracao")
	private Double taxaAdministracao;

	@Column(name = "taxaprimeiroaluguel")
	private Double taxaPrimeiroAluguel;

	@Column(name = "tipoimovel")
	private String tipoImovel;

	@Column(name = "tituloimovel")
	private String tituloImovel;

	@Column(name = "valoralugueldiaria")
	private Double valorAluguelDiaria;

	@Column(name = "valoraluguelmensal")
	private Double valorAluguelMensal;

	@Column(name = "valorcondominio")
	private Double valorCondominio;

	@Column(name = "valoriptu")
	private Double valorIptu;

	// bi-directional many-to-one association to Corretor
	@ManyToOne
	@JoinColumn(name = "idcorretor")
	private Corretor corretor;

	// bi-directional many-to-one association to Endereco
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name = "idendereco")
	private Endereco endereco;

	// bi-directional many-to-one association to Pessoa
	@ManyToOne
	@JoinColumn(name = "idlocador")
	private Pessoa locador;

	public Imovel() {
	}

	public Integer getIdimovel() {
		return idimovel;
	}

	public void setIdimovel(Integer idimovel) {
		this.idimovel = idimovel;
	}

	public Double getAreaUtil() {
		return areaUtil;
	}

	public void setAreaUtil(Double areaUtil) {
		this.areaUtil = areaUtil;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getHasAreaServico() {
		return hasAreaServico;
	}

	public void setHasAreaServico(Boolean hasAreaServico) {
		this.hasAreaServico = hasAreaServico;
	}

	public Boolean getHasCozinha() {
		return hasCozinha;
	}

	public void setHasCozinha(Boolean hasCozinha) {
		this.hasCozinha = hasCozinha;
	}

	public Boolean getHasDespensa() {
		return hasDespensa;
	}

	public void setHasDespensa(Boolean hasDespensa) {
		this.hasDespensa = hasDespensa;
	}

	public Boolean getHasMobiliado() {
		return hasMobiliado;
	}

	public void setHasMobiliado(Boolean hasMobiliado) {
		this.hasMobiliado = hasMobiliado;
	}

	public Boolean getHasPiscina() {
		return hasPiscina;
	}

	public void setHasPiscina(Boolean hasPiscina) {
		this.hasPiscina = hasPiscina;
	}

	public Boolean getHasVaranda() {
		return hasVaranda;
	}

	public void setHasVaranda(Boolean hasVaranda) {
		this.hasVaranda = hasVaranda;
	}

	public int getQtdQuarto() {
		return qtdQuarto;
	}

	public void setQtdQuarto(int qtdQuarto) {
		this.qtdQuarto = qtdQuarto;
	}

	public int getQtdSuite() {
		return qtdSuite;
	}

	public void setQtdSuite(int qtdSuite) {
		this.qtdSuite = qtdSuite;
	}

	public int getQtdVagaGaragem() {
		return qtdVagaGaragem;
	}

	public void setQtdVagaGaragem(int qtdVagaGaragem) {
		this.qtdVagaGaragem = qtdVagaGaragem;
	}

	public int getQtdWcSocial() {
		return qtdWcSocial;
	}

	public void setQtdWcSocial(int qtdWcSocial) {
		this.qtdWcSocial = qtdWcSocial;
	}

	public Double getTaxaAdministracao() {
		return taxaAdministracao;
	}

	public void setTaxaAdministracao(Double taxaAdministracao) {
		this.taxaAdministracao = taxaAdministracao;
	}

	public Double getTaxaPrimeiroAluguel() {
		return taxaPrimeiroAluguel;
	}

	public void setTaxaPrimeiroAluguel(Double taxaPrimeiroAluguel) {
		this.taxaPrimeiroAluguel = taxaPrimeiroAluguel;
	}

	public String getTipoImovel() {
		return tipoImovel;
	}

	public void setTipoImovel(String tipoImovel) {
		this.tipoImovel = tipoImovel;
	}

	public String getTituloImovel() {
		return tituloImovel;
	}

	public void setTituloImovel(String tituloImovel) {
		this.tituloImovel = tituloImovel;
	}

	public Double getValorAluguelDiaria() {
		return valorAluguelDiaria;
	}

	public void setValorAluguelDiaria(Double valorAluguelDiaria) {
		this.valorAluguelDiaria = valorAluguelDiaria;
	}

	public Double getValorAluguelMensal() {
		return valorAluguelMensal;
	}

	public void setValorAluguelMensal(Double valorAluguelMensal) {
		this.valorAluguelMensal = valorAluguelMensal;
	}

	public Double getValorCondominio() {
		return valorCondominio;
	}

	public void setValorCondominio(Double valorCondominio) {
		this.valorCondominio = valorCondominio;
	}

	public Double getValorIptu() {
		return valorIptu;
	}

	public void setValorIptu(Double valorIptu) {
		this.valorIptu = valorIptu;
	}

	public Corretor getCorretor() {
		return corretor;
	}

	public void setCorretor(Corretor corretor) {
		this.corretor = corretor;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Pessoa getLocador() {
		return locador;
	}

	public void setLocador(Pessoa locador) {
		this.locador = locador;
	}

	public Boolean getIsDisponivel() {
		return isDisponivel;
	}

	public void setIsDisponivel(Boolean isDisponivel) {
		this.isDisponivel = isDisponivel;
	}
	

}