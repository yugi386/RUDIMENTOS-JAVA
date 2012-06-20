/* 
=======================================================================================
	 RUDIMENTOS DO JAVA 
	 AUTOR: YUGI
	 DATA: 17/06/2012
=======================================================================================
	 MENU RELATÓRIOS
=======================================================================================
 */
package br.webverissimo.cadastro.ui.menu;

import java.sql.SQLException;
import br.webverissimo.cadastro.ui.RelatUI;
import br.webverissimo.cadastro.util.Keyboard;
import br.webverissimo.cadastro.util.Util;

public class RelatMenu {

 public RelatMenu() {

    try {
		apresentar();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
     
 }

	//*************************************************************************************
    private void apresentar() throws SQLException {

        RelatUI relatUI = new RelatUI();

        int opc = 0;
        String[] MenuOpc = {"Clientes","Clientes > Saldo Aberto","Vendas > Período","Vendas > Cliente + Período",
        		"Pagamentos > Período","Pagamentos > Cliente + período"};
        
        do {
        	Util.menu("Relatórios",MenuOpc, 80, 25);
            opc = Keyboard.readInt();

            switch ( opc ){
                case 1: relatUI.cliente();
                   break;
                case 2: relatUI.cliente_saldo();
                	break;
                case 3: relatUI.venda_periodo();
                   break;
                case 4: relatUI.venda_periodo_cliente();
                   break;
                case 5: relatUI.pag_periodo();
                   break;
                case 6: relatUI.pag_periodo_cliente();
                   break;
                case 0: break;

            }

        } while ( opc != 0);

    }
    
	 //*************************************************************************************
}
