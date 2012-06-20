/* 
=======================================================================================
	 RUDIMENTOS DO JAVA 
	 AUTOR: YUGI
	 DATA: 17/06/2012
=======================================================================================
	 MENU DE CLIENTES
=======================================================================================
 */

package br.webverissimo.cadastro.ui.menu;
import br.webverissimo.cadastro.ui.ClienteUI;
import br.webverissimo.cadastro.util.Keyboard;
import br.webverissimo.cadastro.util.Util;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ClienteMenu {

    public ClienteMenu(){
        try {
            apresentar();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//*************************************************************************************
	private void apresentar() throws SQLException {
	
	    ClienteUI clienteUI = new ClienteUI();
	
	    int opc = 0;
	    String[] MenuOpc = {"Incluir","Listar","Excluir","Alterar"};
	    
	    do {
	    	Util.menu("Cadastro de Clientes",MenuOpc, 80, 33);
	        opc = Keyboard.readInt();
	
	        switch ( opc ){
	            case 1: clienteUI.incluir();
	               break;
	            case 2: clienteUI.listar();
	               break;
	            case 3: clienteUI.excluir();
	               break;
	            case 4: clienteUI.alterar();
	                break;
	            case 0: break;
	
	        }
	
	    } while ( opc != 0);
	
	}
//*************************************************************************************
}
