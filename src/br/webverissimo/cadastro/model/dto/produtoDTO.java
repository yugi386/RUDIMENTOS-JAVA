/* 
=======================================================================================
	 RUDIMENTOS DO JAVA 
	 AUTOR: YUGI
	 DATA: 17/06/2012
=======================================================================================
	 OBJETO DTO - PRODUTO
=======================================================================================
 */
package br.webverissimo.cadastro.model.dto;

public class produtoDTO {

	private int id;
	private String descricao;
	private double saldo_atual;
	private double preco;
	private String tipo;
	
	public int getId() {
		return id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getSaldo_atual() {
		return saldo_atual;
	}
	public void setSaldo_atual(double saldo_atual) {
		this.saldo_atual = saldo_atual;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getTipo() {
		return tipo;
	}
	
}
