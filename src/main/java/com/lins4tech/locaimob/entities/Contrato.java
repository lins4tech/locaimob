package com.lins4tech.locaimob.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the contrato database table.
 * 
 */
@Entity
@NamedQueries({ @NamedQuery(name = "Contrato.findAll", query = "SELECT c FROM Contrato c"),
		@NamedQuery(name = "Contrato.findByImovel", query = "SELECT c FROM Contrato c WHERE c.imovel.idimovel = :idimovel"),
		@NamedQuery(name = "Contrato.findByLocatario", query = "SELECT c FROM Contrato c WHERE c.locatario.idpessoa = :idlocatario"),
		@NamedQuery(name = "Contrato.findByAtivo", query = "SELECT c FROM Contrato c WHERE c.dataTermino IS NULL"),
		@NamedQuery(name = "Contrato.findByAtivoAndImovel", query = "SELECT c FROM Contrato c WHERE c.dataTermino IS NULL and c.imovel.idimovel = :idImovel"),
		@NamedQuery(name = "Contrato.findByAtivoAndLocatario", query = "SELECT c FROM Contrato c WHERE c.dataTermino IS NULL and c.locatario.idpessoa = :idLocatario"),
		@NamedQuery(name = "Contrato.findByAtivoAndLocador", query = "SELECT c FROM Contrato c WHERE c.dataTermino IS NULL and c.imovel.locador.idpessoa = :idLocador") })

public class Contrato implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "Imovel.findAll";
	public static final String FIND_IMOVEL = "Imovel.findByImovel";
	public static final String FIND_LOCATARIO = "Imovel.findByLocatario";
	public static final String FIND_ATIVO = "Imovel.findByAtivo";
	public static final String FIND_ATIVO_IMOVEL = "Imovel.findByAtivoAndImovel";
	public static final String FIND_ATIVO_LOCATARIO = "Imovel.findByAtivoAndLocatario";
	public static final String FIND_ATIVO_LOCADOR = "Imovel.findByAtivoAndLocador";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idcontrato;

	@ManyToOne
	@JoinColumn(name = "idcorretor")
	private Corretor corretor;

	// bi-directional many-to-one association to Pessoa
	@ManyToOne
	@JoinColumn(name = "idfiador")
	private Pessoa fiador;

	// bi-directional many-to-one association to Imovel
	@ManyToOne
	@JoinColumn(name = "idimovel")
	private Imovel imovel;

	// bi-directional many-to-one association to Pessoa
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "idlocatario")
	private Pessoa locatario;

	@Column(name = "apolicesegurofianca")
	private String apoliceSeguroFianca;

	@Column(name = "dataassinatura")
	@Temporal(TemporalType.DATE)
	private Date dataAssinatura;

	@Column(name = "datacadastro")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;

	@Column(name = "datafimperiodo")
	@Temporal(TemporalType.DATE)
	private Date dataFimPeriodo;

	@Column(name = "datainicioperiodo")
	@Temporal(TemporalType.DATE)
	private Date dataInicioPeriodo;

	@Column(name = "datatermino")
	@Temporal(TemporalType.DATE)
	private Date dataTermino;

	@Column(name = "dataultimaatualizacao")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataUltimaAtualizacao;

	@Column(name = "diavencimento")
	private Integer diaVencimento;

	@Column(name = "formapagamento")
	private String formaPagamento;

	@Column(name = "hasagua")
	private Boolean hasAgua;

	@Column(name = "hascondominio")
	private Boolean hasCondominio;

	@Column(name = "hasenergia")
	private Boolean hasEnergia;

	@Column(name = "hasgas")
	private Boolean hasGas;

	@Column(name = "hasiptu")
	private Boolean hasIptu;

	private String observacao;

	@Column(name = "porcentagemjurosdia")
	private Double porcentagemJurosDia;

	@Column(name = "porcentagemmultaatraso")
	private Double porcentagemMultaAtraso;

	@Column(name = "principalindicereajuste")
	private String principalIndiceReajuste;

	@Column(name = "qtdaluguelmultadescumprimento")
	private Integer qtdAluguelMultaDescumprimento;

	@Column(name = "seguradorasegurofianca")
	private String seguradoraSeguroFianca;

	@Column(name = "tipoaluguel")
	private String tipoAluguel;

	@Column(name = "valoradministracao")
	private Double valorAdministracao;

	@Column(name = "valoratualaluguel")
	private Double valorAtualaluguel;

	@Column(name = "valorBaseAluguel")
	private Double valorbasealuguel;

	@Column(name = "valorCaucao")
	private Double valorcaucao;

	public Contrato() {
	}

	public Integer getIdcontrato() {
		return idcontrato;
	}

	public void setIdcontrato(Integer idcontrato) {
		this.idcontrato = idcontrato;
	}

	public String getApoliceSeguroFianca() {
		return apoliceSeguroFianca;
	}

	public void setApoliceSeguroFianca(String apoliceSeguroFianca) {
		this.apoliceSeguroFianca = apoliceSeguroFianca;
	}

	public Date getDataAssinatura() {
		return dataAssinatura;
	}

	public void setDataAssinatura(Date dataAssinatura) {
		this.dataAssinatura = dataAssinatura;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataFimPeriodo() {
		return dataFimPeriodo;
	}

	public void setDataFimPeriodo(Date dataFimPeriodo) {
		this.dataFimPeriodo = dataFimPeriodo;
	}

	public Date getDataInicioPeriodo() {
		return dataInicioPeriodo;
	}

	public void setDataInicioPeriodo(Date dataInicioPeriodo) {
		this.dataInicioPeriodo = dataInicioPeriodo;
	}

	public Date getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}

	public Date getDataUltimaAtualizacao() {
		return dataUltimaAtualizacao;
	}

	public void setDataUltimaAtualizacao(Date dataUltimaAtualizacao) {
		this.dataUltimaAtualizacao = dataUltimaAtualizacao;
	}

	public Integer getDiaVencimento() {
		return diaVencimento;
	}

	public void setDiaVencimento(Integer diaVencimento) {
		this.diaVencimento = diaVencimento;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Boolean getHasAgua() {
		return hasAgua;
	}

	public void setHasAgua(Boolean hasAgua) {
		this.hasAgua = hasAgua;
	}

	public Boolean getHasCondominio() {
		return hasCondominio;
	}

	public void setHasCondominio(Boolean hasCondominio) {
		this.hasCondominio = hasCondominio;
	}

	public Boolean getHasEnergia() {
		return hasEnergia;
	}

	public void setHasEnergia(Boolean hasEnergia) {
		this.hasEnergia = hasEnergia;
	}

	public Boolean getHasGas() {
		return hasGas;
	}

	public void setHasGas(Boolean hasGas) {
		this.hasGas = hasGas;
	}

	public Boolean getHasIptu() {
		return hasIptu;
	}

	public void setHasIptu(Boolean hasIptu) {
		this.hasIptu = hasIptu;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Double getPorcentagemJurosDia() {
		return porcentagemJurosDia;
	}

	public void setPorcentagemJurosDia(Double porcentagemJurosDia) {
		this.porcentagemJurosDia = porcentagemJurosDia;
	}

	public Double getPorcentagemMultaAtraso() {
		return porcentagemMultaAtraso;
	}

	public void setPorcentagemMultaAtraso(Double porcentagemMultaAtraso) {
		this.porcentagemMultaAtraso = porcentagemMultaAtraso;
	}

	public String getPrincipalIndiceReajuste() {
		return principalIndiceReajuste;
	}

	public void setPrincipalIndiceReajuste(String principalIndiceReajuste) {
		this.principalIndiceReajuste = principalIndiceReajuste;
	}

	public Integer getQtdAluguelMultaDescumprimento() {
		return qtdAluguelMultaDescumprimento;
	}

	public void setQtdAluguelMultaDescumprimento(Integer qtdAluguelMultaDescumprimento) {
		this.qtdAluguelMultaDescumprimento = qtdAluguelMultaDescumprimento;
	}

	public String getSeguradoraSeguroFianca() {
		return seguradoraSeguroFianca;
	}

	public void setSeguradoraSeguroFianca(String seguradoraSeguroFianca) {
		this.seguradoraSeguroFianca = seguradoraSeguroFianca;
	}

	public String getTipoAluguel() {
		return tipoAluguel;
	}

	public void setTipoAluguel(String tipoAluguel) {
		this.tipoAluguel = tipoAluguel;
	}

	public Double getValorAdministracao() {
		return valorAdministracao;
	}

	public void setValorAdministracao(Double valorAdministracao) {
		this.valorAdministracao = valorAdministracao;
	}

	public Double getValorAtualaluguel() {
		return valorAtualaluguel;
	}

	public void setValorAtualaluguel(Double valorAtualaluguel) {
		this.valorAtualaluguel = valorAtualaluguel;
	}

	public Double getValorbasealuguel() {
		return valorbasealuguel;
	}

	public void setValorbasealuguel(Double valorbasealuguel) {
		this.valorbasealuguel = valorbasealuguel;
	}

	public Double getValorcaucao() {
		return valorcaucao;
	}

	public void setValorcaucao(Double valorcaucao) {
		this.valorcaucao = valorcaucao;
	}

	public Corretor getCorretor() {
		return corretor;
	}

	public void setCorretor(Corretor corretor) {
		this.corretor = corretor;
	}

	public Pessoa getFiador() {
		return fiador;
	}

	public void setFiador(Pessoa fiador) {
		this.fiador = fiador;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public Pessoa getLocatario() {
		return locatario;
	}

	public void setLocatario(Pessoa locatario) {
		this.locatario = locatario;
	}

}