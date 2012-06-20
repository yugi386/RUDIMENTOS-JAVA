/* 
=======================================================================================
	 RUDIMENTOS DO JAVA 
	 AUTOR: YUGI
	 DATA: 17/06/2012
=======================================================================================
	 CADASTRO DE CLIENTES - INTERFACE
=======================================================================================
 */

package br.webverissimo.cadastro.ui;

import br.webverissimo.cadastro.model.dao.clienteDAO;
import br.webverissimo.cadastro.model.dto.clienteDTO;
import br.webverissimo.cadastro.util.Keyboard;
import br.webverissimo.cadastro.util.Util;
import br.webverissimo.cadastro.util.formatar;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class ClienteUI {

    clienteDAO clienteDAO = new clienteDAO();
    clienteDTO clienteDTO = new clienteDTO();

    int tela = 120;
    int largura = 400;
    
 // *********************************************************************   
    public void listar() {

            List<Object> listaClientes = null;
            
			try {
				listaClientes = clienteDAO.listar();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            Util.titulo("Lista de Clientes", tela, largura);
            MostraCli(listaClientes);
    }

// ******************************************************************************************
//  Mostra registros dos clientes na tela    
private void MostraCli(List<Object> listaClientes) {    
	
	for (int ct=0;ct<11;ct++) {
    	Util.cabecalho(formatar.cliente_campos()[ct],formatar.cliente_tam()[ct],0);	
    }
    
	    Util.writeln("", 0);
		Util.line("-", largura);
	    Iterator<Object> clientes = listaClientes.iterator();
	    while ( clientes.hasNext() ) {
	        clienteDTO = (clienteDTO)clientes.next();
	        
	        // mostrando dados dos registros
	        Util.corpo(clienteDTO.getId()+"",formatar.cliente_tam()[0],0);			
	        Util.corpo(clienteDTO.getRazao_social(),formatar.cliente_tam()[1],0);	
	        Util.corpo(clienteDTO.getEndereco(),formatar.cliente_tam()[2],0);
	        Util.corpo(clienteDTO.getComplemento(),formatar.cliente_tam()[3],0);
	        Util.corpo(clienteDTO.getBairro(),formatar.cliente_tam()[4],0);
	        Util.corpo(clienteDTO.getCidade(),formatar.cliente_tam()[5],0);
	        Util.corpo(clienteDTO.getEstado(),formatar.cliente_tam()[6],0);
	        Util.corpo(clienteDTO.getCep(),formatar.cliente_tam()[7],0);
	        Util.corpo(clienteDTO.getTel(),formatar.cliente_tam()[8],0);
	        Util.corpo(clienteDTO.getSaldo_aberto()+"",formatar.cliente_tam()[9],0);
	        Util.corpo(clienteDTO.getAtivo(),formatar.cliente_tam()[10],1);
	        
	    }
	    Util.voltar(tela, largura);
}
    
// ***************************************************************************************************
// LISTA CLIENTES COM SALDO ABERTO...    
    public void listar(List<Object> listaClientes ) {
            Util.titulo("Lista de Clientes - Saldo Aberto", tela, largura);
            MostraCli(listaClientes);
    }
    
// *************************************************************************************
    public void incluir() throws SQLException {

        Util.titulo("Incluir Cliente", tela, largura);
        leitura();
        clienteDAO.incluir( clienteDTO );
    }    
    
// ******************************************************************************
    public void excluir() throws SQLException {
        
        Util.titulo("Excluir Cliente", tela, largura);
        
    	Util.writeln("Digite o ID do Cliente: ",0);
        clienteDTO.setId(Keyboard.readInt());

        clienteDAO.excluir( clienteDTO );
    }
    
// *************************************************************************************
    
    public void alterar() throws SQLException {
        
        Util.titulo("Alterar Cliente", tela, largura);
        
   		Util.writeln("Digite o ID do Cliente: ",0);
   		clienteDTO.setId(Keyboard.readInt());
   		
   		leitura();
        clienteDAO.alterar( clienteDTO );
    }    
    
// **************************************************************************************
// LEITURA DOS DADOS DO TECLADO
    
    public void leitura() throws SQLException {
    		Util.writeln("Digite a Razão Social: ",0);
            clienteDTO.setRazao_social(Keyboard.readString());

            Util.writeln("Digite o Endereço: ",0);
	        clienteDTO.setEndereco(Keyboard.readString());
    		
	        Util.writeln("Complemento: ",0);
            clienteDTO.setComplemento(Keyboard.readString());
	        
            Util.writeln("Bairro: ",0);
	        clienteDTO.setBairro(Keyboard.readString());
            
	        Util.writeln("Cidade: ",0);
            clienteDTO.setCidade(Keyboard.readString());
	        
            Util.writeln("Estado: ",0);
	        clienteDTO.setEstado(Keyboard.readString());
            
	        Util.writeln("CEP: ",0);
            clienteDTO.setCep(Keyboard.readString());
	        
            Util.writeln("Telefone: ",0);
	        clienteDTO.setTel(Keyboard.readString());
            
	        Util.writeln("Saldo em aberto: ",0);
            clienteDTO.setSaldo_aberto(Keyboard.readDouble());
	        
            Util.writeln("Cliente Ativo? [ S / N ]: ",0);
	        clienteDTO.setAtivo(Keyboard.readString());
    }    
}
