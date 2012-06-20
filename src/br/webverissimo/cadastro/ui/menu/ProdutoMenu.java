/* 
=======================================================================================
	 RUDIMENTOS DO JAVA 
	 AUTOR: YUGI
	 DATA: 17/06/2012
=======================================================================================
	 MENU PRODUTOS
=======================================================================================
 */

package br.webverissimo.cadastro.ui.menu;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.webverissimo.cadastro.ui.ProdutoUI;
import br.webverissimo.cadastro.util.Keyboard;
import br.webverissimo.cadastro.util.Util;

public class ProdutoMenu {

    public ProdutoMenu(){
        try {
            apresentar();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//*************************************************************************************
    private void apresentar() throws SQLException {

        ProdutoUI ProdutoUI = new ProdutoUI();

        int opc = 0;
        String[] MenuOpc = {"Incluir","Listar","Excluir","Alterar"};
        
        do {
        	Util.menu("Cadastro de Produtos",MenuOpc, 80, 33);
            opc = Keyboard.readInt();

            switch ( opc ){
                case 1: ProdutoUI.incluir();
                   break;
                case 2: ProdutoUI.listar();
                   break;
                case 3: ProdutoUI.excluir();
                   break;
                case 4: ProdutoUI.alterar();
                    break;
                case 0: break;

            }

        } while ( opc != 0);

    }
    
 //*************************************************************************************
}
