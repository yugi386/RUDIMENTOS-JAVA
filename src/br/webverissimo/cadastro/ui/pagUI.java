/* 
=======================================================================================
	 RUDIMENTOS DO JAVA 
	 AUTOR: YUGI
	 DATA: 17/06/2012
=======================================================================================
	 INTERFACE PAGAMENTOS 
=======================================================================================
 */

package br.webverissimo.cadastro.ui;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import br.webverissimo.cadastro.model.dao.pagDAO;
import br.webverissimo.cadastro.model.dao.vendaDAO;
import br.webverissimo.cadastro.model.dto.pagDTO;
import br.webverissimo.cadastro.util.Keyboard;
import br.webverissimo.cadastro.util.Util;
import br.webverissimo.cadastro.util.formatar;

public class pagUI {

	pagDAO pagDAO = new pagDAO();
    pagDTO pagDTO = new pagDTO();
    int tela = 83;
	int largura = 83;
	
// *************************************************************************************
// lista todos os pagamentos	
    public void listar() {
        try {
            List<Object> listaPag = pagDAO.listar();
            Util.titulo("Lista de Pagamentos", tela, largura);
    		Util.writeln("", 0);
    		Util.line("=", largura);

            apresentaPag(listaPag,1);
            Util.voltar(tela, largura);
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
 // *************************************************************************************
 // apresenta os pagamentos na tela   
	private void apresentaPag(List<Object> listaPag, int tipo ) {
		Iterator<Object> produtos = listaPag.iterator();
      
		int[] formata = formatar.pag_tam();
		if (tipo == 2) {
			formata = formatar.pagCli_tam();
		}
		
		while ( produtos.hasNext() ) {
		    pagDTO = (pagDTO)produtos.next();
		    
		    // mostrando dados dos registros
		    Util.corpo(pagDTO.getId()+"",formata[0],0);			
		    Util.corpo(pagDTO.getData_pag() +"",formata[1],0);	
		    Util.corpo(pagDTO.getVenda_id()+"",formata[2],0);
		    Util.corpo(pagDTO.getValor_pago()+"",formata[3],0);
		    Util.corpo(pagDTO.getDesconto()+"",formata[4],0);
		    Util.corpo(pagDTO.getStatus(),formata[5],1);
		    
		}

	}

// *************************************************************************************    
//  lista pagamento por periodo e periodo + cliente
    public void listar2( List<Object> listaPag, int tipo ) {

    	if (tipo == 1) {	
	    	Util.titulo("Lista de Pagamentos por período", tela, largura);
			for (int ct=0;ct<6;ct++){
				Util.cabecalho(formatar.pag_campos()[ct],formatar.pag_tam()[ct],0);	
			}
			Util.writeln("", 0);
			Util.line("=", largura);
	    	apresentaPag(listaPag,1);
			Util.voltar(tela, largura);
    	} else {
    		int tela = 94;
    		int largura = 94;	
    		Util.titulo("Lista de Pagamentos por Cliente + Periodo", tela, largura);
			for (int ct=0;ct<6;ct++){
				Util.cabecalho(formatar.pagCli_campos()[ct],formatar.pagCli_tam()[ct],0);	
			}
			Util.writeln("", 0);
			Util.line("=", largura);
	    	apresentaPag(listaPag,2);
			Util.voltar(tela, largura);
    	}

    }
    
// *************************************************************************************
    public void incluir() throws SQLException {

    	Util.titulo("Incluir Pagamento", tela, largura);
    	
    	Util.write("Digite o ID da Venda: ",0);	//	nao sera auto-incremento
        pagDTO.setVenda_id(Keyboard.readInt());
        vendaDAO vendaDAO = new vendaDAO();
        
        if ( vendaDAO.VerificaVendaExistente(pagDTO.getVenda_id()) == 0 ) {
        	Util.writeln("Venda inexistente!", 1);		  
        	return;
        }
    	
       	Util.write("Data do Pagamento: ",0);
       	pagDTO.setData_pag(Util.stringToDate(Keyboard.readString()));
        
       	pagDTO.setStatus("P");	//	Status do pagamento
        
       	Util.write("Valor a ser pago: ",0);	//	valor do pagamento
        pagDTO.setValor_pago(Keyboard.readInt());

       	Util.write("Valor do desconto: ",0);	//	valor do desconto
        pagDTO.setDesconto(Keyboard.readInt());
        
        if (pagDTO.getValor_pago() > 0 && pagDTO.getDesconto() >= 0) {
        	pagDAO.incluir( pagDTO );	//	faz o pagamento
        	Util.writeln("Pagamento Concluído!",0);
        } else {
        	Util.writeln("Valor de pagamento ou desconto inválido! ",0);
        }	
    }    
    
// ******************************************************************************
    public void excluir() throws SQLException {
        
    	Util.titulo("Excluir Pagamento", tela, largura);
        
    	Util.write("Digite o ID do Pagamento: ",0);
        pagDTO.setId(Keyboard.readInt());

        if ( pagDAO.excluir( pagDTO ) == false) {
        	Util.writeln("Impossível excluir um pagamento não Cancelado!", 0);
        } else {
        	Util.writeln("Pagamento Excluído!", 0);
        }
    }
    
// *********************************************************************************
    
    public void cancelar() throws SQLException {
        
        Util.titulo("Cancelar Pagamento", tela, largura);
        
    	Util.write("Digite o ID do Pagamento: ",0);
        int id = Keyboard.readInt();

        if (pagDAO.CancelarPag(id)) {
        	Util.writeln("Pagamento Cancelado!", 0);
        } else {
    		Util.writeln("Este Pagamento já está cancelado!", 0);
        }
    }    
    
}