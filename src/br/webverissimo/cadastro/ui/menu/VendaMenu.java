/* 
=======================================================================================
	 RUDIMENTOS DO JAVA 
	 AUTOR: YUGI
	 DATA: 17/06/2012
=======================================================================================
	 MENU VENDAS
=======================================================================================
 */

package br.webverissimo.cadastro.ui.menu;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import br.webverissimo.cadastro.ui.VendaUI;
import br.webverissimo.cadastro.util.Keyboard;
import br.webverissimo.cadastro.util.Util;

public class VendaMenu {

    public VendaMenu(){
        try {
            apresentar();
        } catch (SQLException ex) {
            Logger.getLogger(VendaMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//*************************************************************************************
    private void apresentar() throws SQLException {

        VendaUI VendaUI = new VendaUI();

        int opc = 0;
        String[] MenuOpc = {"Efetuar Venda","Cancelar Venda","Listar Venda","Excluir Venda"};
        
        do {
        	Util.menu("Vendas",MenuOpc, 80, 33);
            opc = Keyboard.readInt();

            switch ( opc ){
                case 1: VendaUI.incluir();
                   break;
                case 2: VendaUI.cancelar();
                	break;
                case 3: VendaUI.listar();
                   break;
                case 4: VendaUI.excluir();
                   break;
                case 0: break;

            }

        } while ( opc != 0);

    }
    
 //*************************************************************************************
}
