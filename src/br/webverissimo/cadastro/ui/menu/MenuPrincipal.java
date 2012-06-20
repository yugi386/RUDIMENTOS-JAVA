/* 
=======================================================================================
	 RUDIMENTOS DO JAVA 
	 AUTOR: YUGI
	 DATA: 17/06/2012
=======================================================================================
	 MENU PRINCIPAL
=======================================================================================
 */
 
package br.webverissimo.cadastro.ui.menu;
import br.webverissimo.cadastro.util.Keyboard;
import br.webverissimo.cadastro.util.Util;

public class MenuPrincipal {

    public MenuPrincipal(){
        apresentar();
    }
    
//*************************************************************************************
    private void apresentar() {
    	
        int opc = 0;
        String[] MenuOpc = {"Clientes","Produtos","Vendas","Pagamentos","Relatórios"};
        
        do {
        	Util.menu("Menu Principal",MenuOpc, 80, 33);
            opc = Keyboard.readInt();

            switch ( opc ){
                case 1: new ClienteMenu();
                   break;
                case 2: new ProdutoMenu();
                	break;
                case 3: new VendaMenu();
            		break;                	
                case 4: new PagMenu();
            		break;
                case 5: new RelatMenu();
        		break;            		
            }
        } while ( opc != 0);

    }
//*************************************************************************************
}
