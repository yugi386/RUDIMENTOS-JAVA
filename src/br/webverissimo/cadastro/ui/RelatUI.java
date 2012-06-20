/* 
=======================================================================================
	 RUDIMENTOS DO JAVA 
	 AUTOR: YUGI
	 DATA: 17/06/2012
=======================================================================================
	 INTEFACE RELATORIOS DO SISTEMA
=======================================================================================
 */

package br.webverissimo.cadastro.ui;

import java.sql.SQLException;
import java.util.List;
import br.webverissimo.cadastro.model.dao.ClienteDAO;
import br.webverissimo.cadastro.model.dao.PagDAO;
import br.webverissimo.cadastro.model.dao.RelatDAO;
import br.webverissimo.cadastro.model.dao.VendaDAO;
import br.webverissimo.cadastro.model.dto.ClienteDTO;
import br.webverissimo.cadastro.util.Keyboard;
import br.webverissimo.cadastro.util.Util;

public class RelatUI {

	 ClienteDAO clienteDAO = new ClienteDAO();
	 ClienteDTO clienteDTO = new ClienteDTO();
	 int tela = 70;
	 int largura = 70;

// **************************************************************************************************	 
// Lista de clientes:	 
    public void cliente() throws SQLException {
    	ClienteUI ClienteUI = new ClienteUI();
    	ClienteUI.listar();
	}
// **************************************************************************************************	 
// cliente com saldo em aberto.
public void cliente_saldo() {
    	RelatDAO relatDAO = new RelatDAO();
        List<Object> listaClientes = null;
		try {
			listaClientes = relatDAO.lista_cliSaldo();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ClienteUI ClienteUI = new ClienteUI();
        ClienteUI.listar(listaClientes);
}

// ******************************************************************************************

public void venda_periodo() throws SQLException{
	String dataini, datafim;
	
    Util.titulo("Vendas por período", tela, largura);	
     
	Util.write("Digite a Data Inicial: ",0);	//	
    dataini = Keyboard.readString();
    
    Util.write("Digite a Data Final: ",0);	//	
    datafim = Keyboard.readString();
    
    int cliente = 0;
    
    // Pegando dados:
    VendaDAO venda = new VendaDAO();
    List<Object> lista;
    VendaUI mostra = new VendaUI();
    lista = venda.listar(dataini, datafim, cliente);
    mostra.listar2(lista);
}

// ***************************************************************************************
public void venda_periodo_cliente() throws SQLException{
	String dataini, datafim; 
	int cliente;

    Util.titulo("Vendas por período Cliente", tela, largura);
	
	Util.write("Digite o Código do Cliente: ",0);	//	
    cliente = Keyboard.readInt();
    	
	Util.write("Digite a Data Inicial: ",0);	//	
    dataini = Keyboard.readString();
    
    Util.write("Digite a Data Final: ",0);	//	
    datafim = Keyboard.readString();
    
 // Pegando dados:
    VendaDAO venda = new VendaDAO();
    List<Object> lista;
    VendaUI mostra = new VendaUI();
    lista = venda.listar(dataini, datafim, cliente);
    mostra.listar2(lista);
}


//***************************************************************************************
public void pag_periodo_cliente() throws SQLException{
	String dataini, datafim;

	Util.titulo("Pagamento por Período - Cliente",tela,largura);
	
	Util.write("Digite o Código do Cliente: ",0);	//	
	int cliente = Keyboard.readInt();
	 	
	Util.write("Digite a Data Inicial: ",0);	//	
	 dataini = Keyboard.readString();
	 
	Util.write("Digite a Data Final: ",1);	//	
	datafim = Keyboard.readString();
	 
	//Pegando dados:
	PagDAO pag = new PagDAO();
	List<Object> lista;
	PagUI mostra = new PagUI();
	lista = pag.listar(dataini, datafim, cliente);
	mostra.listar2(lista,2);

}

//***************************************************************************************
public void pag_periodo() throws SQLException{
	String dataini, datafim;

    Util.titulo("Pagamento por Período",tela,largura);	
	     
	Util.write("Digite a Data Inicial: ",0);	//	
	dataini = Keyboard.readString();
	
	Util.write("Digite a Data Final: ",0);	//	
	datafim = Keyboard.readString();
	
	// Pegando dados:
	PagDAO pag = new PagDAO();
	List<Object> lista;
	PagUI mostra = new PagUI();
	lista = pag.listar(dataini, datafim, 0);
	mostra.listar2(lista,1);

}

}
