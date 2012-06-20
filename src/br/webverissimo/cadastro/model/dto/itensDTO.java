/* 
=======================================================================================
	 RUDIMENTOS DO JAVA 
	 AUTOR: YUGI
	 DATA: 17/06/2012
=======================================================================================
	 OBJETO DTO - ITENS
=======================================================================================
 */

package br.webverissimo.cadastro.model.dto;

public class ItensDTO {

	private int id;
	private int venda_id;
	private int produto_id;
	private double quant;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getVenda_id() {
		return venda_id;
	}
	public void setVenda_id(int venda_id) {
		this.venda_id = venda_id;
	}
	public int getProduto_id() {
		return produto_id;
	}
	public void setProduto_id(int produto_id) {
		this.produto_id = produto_id;
	}
	public double getQuant() {
		return quant;
	}
	public void setQuant(double quant) {
		this.quant = quant;
	}
	
}
