/* 
=======================================================================================
	 RUDIMENTOS DO JAVA 
	 AUTOR: YUGI
	 DATA: 17/06/2012
=======================================================================================
	 MENU PAGAMENTOS
=======================================================================================
 */

package br.webverissimo.cadastro.ui.menu;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import br.webverissimo.cadastro.ui.pagUI;
import br.webverissimo.cadastro.util.Keyboard;
import br.webverissimo.cadastro.util.Util;

public class PagMenu {

    public PagMenu(){
        try {
            apresentar();
        } catch (SQLException ex) {
            Logger.getLogger(VendaMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//*************************************************************************************
    private void apresentar() throws SQLException {

        pagUI pagUI = new pagUI();

        int opc = 0;
        String[] MenuOpc = {"Efetuar Pagamento","Cancelar Pagamento","Listar Pagamento","Excluir Pagamento"};
        
        do {
        	Util.menu("Controle de Pagamentos",MenuOpc, 80, 29);
            opc = Keyboard.readInt();
        	
            switch ( opc ){
                case 1: pagUI.incluir();
                   break;
                case 2: pagUI.cancelar();
                	break;
                case 3: pagUI.listar();
                   break;
                case 4: pagUI.excluir();
                   break;
                case 0: break;
            }

        } while ( opc != 0);

    }
    
 //*************************************************************************************
}
