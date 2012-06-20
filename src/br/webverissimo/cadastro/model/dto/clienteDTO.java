/* 
=======================================================================================
	 RUDIMENTOS DO JAVA 
	 AUTOR: YUGI
	 DATA: 17/06/2012
=======================================================================================
	 OBJETO DTO - CLIENTE
=======================================================================================
 */

package br.webverissimo.cadastro.model.dto;

public class ClienteDTO {

	private int id;
	private String razao_social;
	private String endereco;
	private String complemento;
	private String bairro;
	private String cidade;
	private String estado;
	private String cep;
	private String tel;
	private double saldo_aberto;
	private String ativo;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRazao_social() {
		return razao_social;
	}
	public void setRazao_social(String razao_social) {
		this.razao_social = razao_social;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public double getSaldo_aberto() {
		return saldo_aberto;
	}
	public void setSaldo_aberto(double saldo_aberto) {
		this.saldo_aberto = saldo_aberto;
	}
	public String getAtivo() {
		return ativo;
	}
	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}
	
}
