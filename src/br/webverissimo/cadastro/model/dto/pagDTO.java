/* 
=======================================================================================
	 RUDIMENTOS DO JAVA 
	 AUTOR: YUGI
	 DATA: 17/06/2012
=======================================================================================
	 OBJETO DTO - PAGAMENTO
=======================================================================================
 */
package br.webverissimo.cadastro.model.dto;

import java.util.Date;

public class pagDTO {
	
	private int id;
	private Date data_pag;
	private int venda_id;
	private double valor_pago;
	private double desconto;
	private String status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getData_pag() {
		return data_pag;
	}
	public void setData_pag(Date data_pag) {
		this.data_pag = data_pag;
	}
	public int getVenda_id() {
		return venda_id;
	}
	public void setVenda_id(int venda_id) {
		this.venda_id = venda_id;
	}
	public double getValor_pago() {
		return valor_pago;
	}
	public void setValor_pago(double valor_pago) {
		this.valor_pago = valor_pago;
	}
	public double getDesconto() {
		return desconto;
	}
	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
