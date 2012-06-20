/* 
=======================================================================================
	 TRABALHO FINAL DA DISCIPLINA DE DE JAVA - MENU PRINCIPAL 
	 AUTOR: YUGI
	 DATA: 17/06/2012
=======================================================================================
	 OBJETO VENDA - VENDA DTO
=======================================================================================
 */

package br.webverissimo.cadastro.model.dto;

import java.util.Date;
import java.util.List;

public class VendaDTO {

	private int id;
	private int cliente_id;
	private Date data_venda;
	private String status;
	private double total;
	private List<ItensDTO> itens;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCliente_id() {
		return cliente_id;
	}
	public void setCliente_id(int cliente_id) {
		this.cliente_id = cliente_id;
	}
	public Date getData_venda() {
		return data_venda;
	}
	public void setData_venda(Date data_venda) {
		this.data_venda = data_venda;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double d) {
		this.total = d;
	}
	public List<ItensDTO> getItens() {
		return itens;
	}
	public void setItens(List<ItensDTO> itens) {
		this.itens = itens;
	}
	
}
