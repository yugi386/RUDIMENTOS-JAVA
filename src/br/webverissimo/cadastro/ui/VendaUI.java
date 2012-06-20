/* 
=======================================================================================
	 RUDIMENTOS DO JAVA 
	 AUTOR: YUGI
	 DATA: 17/06/2012
=======================================================================================
	 INTERFACE PARA VENDAS
=======================================================================================
 */
package br.webverissimo.cadastro.ui;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import br.webverissimo.cadastro.model.dao.clienteDAO;
import br.webverissimo.cadastro.model.dao.itensDAO;
import br.webverissimo.cadastro.model.dao.produtoDAO;
import br.webverissimo.cadastro.model.dao.vendaDAO;
import br.webverissimo.cadastro.model.dto.itensDTO;
import br.webverissimo.cadastro.model.dto.produtoDTO;
import br.webverissimo.cadastro.model.dto.vendaDTO;
import br.webverissimo.cadastro.util.Keyboard;
import br.webverissimo.cadastro.util.Util;
import br.webverissimo.cadastro.util.formatar;

public class VendaUI {

	vendaDAO vendaDAO = new vendaDAO();
    vendaDTO vendaDTO = new vendaDTO();
    int tela = 70;
    int largura = 70;

// ************************************************************************************
// MÉTODO PARA LISTAR TODAS AS VENDAS    
    public void listar() {		
	    List<Object> listaVendas;
		try {
			listaVendas = vendaDAO.listar(); //	LÊ OS DADOS DO BANCO
	        
	        Iterator<Object> vendas = CabLista(listaVendas, tela, largura);  // Cabeçalho da Lista de Vendas
	        imprimirItens(largura, vendas);
	    
	        Util.voltar(tela, largura);	//	FAZ UMA PAUSA PARA VOLTAR AO MENU ANTERIOR
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
    }
// ************************************************************************************
// MOSTRA O CABEÇALHO DO RELATÓRIO DE VENDAS
	private Iterator<Object> CabLista(List<Object> listaVendas, int tela, int largura) {
		
		Util.titulo("Lista de Vendas", tela, largura);
		
		// Mostrando Cabeçalho VENDAS: código, Código do Cliente, Data da Venda, Status, Total  
		for (int ct=0;ct<5;ct++){
			Util.cabecalho(formatar.venda_campos()[ct],formatar.venda_tam()[ct],0);
		}
		
		System.out.println("");
		System.out.print(Util.repete(" ", 12));
		System.out.print("| ");
		
		// Mostrando cabeçalho: itens da venda
		Util.cabecalho("Cód. Item",12,0);		//	codigo da venda
		Util.cabecalho("Cód. Produto",13,0);   	//	codigo do produto
		Util.cabecalho("Quantidade",23,1);   	//	Quantidade
		
		Util.line("=", largura);
		Iterator<Object> vendas = listaVendas.iterator();
		
		return vendas;
	}
    
 // ************************************************************************************
// LISTAS VENDAS POR PERÍODO:	
    public void listar2(List<Object> listaVendas) {
         Iterator<Object> produtos = CabLista(listaVendas, tela, largura);
         imprimirItens(largura, produtos);
         
         Util.voltar(tela, largura);	//	FAZ UMA PAUSA PARA VOLTAR AO MENU ANTERIOR

     }
    
// ************************************************************************************
// MOSTRA NA TELA DADOS DA VENDA E SEUS ITENS    
	private void imprimirItens(int largura, Iterator<Object> produtos) {
		while ( produtos.hasNext() ) {
		    vendaDTO = (vendaDTO)produtos.next();
		    
		    // mostrando dados dos registros
		    Util.corpo(vendaDTO.getId()+"",formatar.venda_tam()[0],0);			
		    Util.corpo(vendaDTO.getCliente_id()+"",formatar.venda_tam()[1],0);	
		    Util.corpo(vendaDTO.getData_venda()+"",formatar.venda_tam()[2],0);
		    Util.corpo(vendaDTO.getStatus(),formatar.venda_tam()[3],0);
		    Util.corpo(vendaDTO.getTotal()+"",formatar.venda_tam()[4],1);
		    
		    int tam = vendaDTO.getItens().size();
		    int recuo = 12;
		    itensDTO ItemDTO=null;
		    
		    for (int ct=0;ct<tam;ct++) {
		    	ItemDTO = vendaDTO.getItens().get(ct); 
		    	System.out.print(Util.repete(" ", recuo));
		    	System.out.print("| ");
		    	Util.corpo(ItemDTO.getId()+"",12,0);
		        Util.corpo(ItemDTO.getProduto_id()+"",13,0);
		        Util.corpo(ItemDTO.getQuant()+"",23,1);
		    }
		    
		    Util.line("-", largura);
		    
		}
	}

// *************************************************************************************
    public void incluir() throws SQLException {

        Util.titulo("Incluir Venda", tela, largura);
    	
    	Util.write("Digite o ID da Venda: ",0);	//	nao sera auto-incremento
        vendaDTO.setId(Keyboard.readInt());
        
        if ( vendaDAO.VerificaVendaExistente(vendaDTO.getId()) != 0 ) {
        	Util.writeln("Código da Venda já existente!", 1);		  
        	return;
        }
    	
    	Util.write("Digite o ID do Cliente: ",0);
        vendaDTO.setCliente_id(Keyboard.readInt());

        clienteDAO cli = new clienteDAO();
        if (cli.VerificaCliente(vendaDTO.getCliente_id()) != 1) {
        	Util.writeln("Cliente não está habilitado para compra!", 1);		  
        	return;
        }
        
       	Util.write("Data da Venda: ",0);
       	vendaDTO.setData_venda(Util.stringToDate(Keyboard.readString()));
        
       	vendaDTO.setStatus("V");	//	Status da venda
        
        // lendo itens da venda:
        Double tot = 0.0;			//	Total da Venda
        // instancia produtos:
        produtoDAO produto = new produtoDAO();
        produtoDTO produtoDTO = new produtoDTO();
        // instancia itens
        itensDAO itensDAO = new itensDAO();
        
        while (true) {
        	char continua = 'N';
            itensDTO itensDTO = new itensDTO();
            
            itensDTO.setVenda_id(vendaDTO.getId());		//	codigo da venda
            
            Util.write("Digite o código do Produto: ",0);
        	itensDTO.setProduto_id(Keyboard.readInt());	//	codigo do produto
        	
            Util.write("Digite a quantidade: ",0);        	
        	itensDTO.setQuant(Keyboard.readDouble());   //  quantidade
        	
        	produtoDTO = produto.ProcuraPreco(itensDTO.getProduto_id(), itensDTO.getQuant()); 	//  metodo para ver preco do produto
        	Double tmp = produtoDTO.getPreco();	
        	
        	if (tmp == 0 || tmp == null ) {
            	Util.writeln("Este produto está indisponível no momento ou em quantidade insuficiente! ",0);
        	} else {
        		itensDAO.incluir(itensDTO);					//	grava itens...
        		tot = tot + (tmp * itensDTO.getQuant());	//	total da compra preco * quant.
        	}

        	Util.write("Deseja gravar Outro Item? [S ou N] ",0);        	
        	continua = Keyboard.readChar();       
        	
        	if (continua != 'S' && continua != 's') {
        		break;
        	}
        	
        }
        
        vendaDTO.setTotal(tot);	//	seta total das vendas...
        
        if (tot != 0) {
        	vendaDAO.incluir( vendaDTO );	//	Faz a gravação das vendas!!!
        }
        
        Util.writeln("Venda Concluída!!!",0);      
    }    
    
// ******************************************************************************
    public void excluir() throws SQLException {
        Util.titulo("Excluir Venda", tela, largura);

        Util.write("Digite o ID da Venda: ",0);
        vendaDTO.setId(Keyboard.readInt());

        if ( vendaDAO.excluir( vendaDTO ) == true) { 
        	Util.writeln("Venda Excluída!!!",0);
        } else {	
        	Util.writeln("Impossível excluir uma venda não Cancelada!", 0);
        }	
    }
    
// *********************************************************************************
    public void cancelar() throws SQLException {
        
        Util.titulo("Cancelar Venda", tela, largura);      
        
    	Util.write("Digite o ID da Venda: ",0);
        int id = Keyboard.readInt();

        if ( vendaDAO.CancelarVenda(id) == true) { 
        	Util.writeln("Venda Cancelada!", 0);
        } else {	
        	Util.writeln("Esta Venda Já está Cancelada!", 0);
        }	
    }    
}