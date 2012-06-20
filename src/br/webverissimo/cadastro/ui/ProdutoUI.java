/* 
=======================================================================================
	 RUDIMENTOS DO JAVA 
	 AUTOR: YUGI
	 DATA: 17/06/2012
=======================================================================================
	 INTERFACE PRODUTO
=======================================================================================
 */
package br.webverissimo.cadastro.ui;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.webverissimo.cadastro.model.dao.produtoDAO;
import br.webverissimo.cadastro.model.dto.produtoDTO;
import br.webverissimo.cadastro.util.Keyboard;
import br.webverissimo.cadastro.util.Util;
import br.webverissimo.cadastro.util.formatar;

public class ProdutoUI {

	produtoDAO produtoDAO = new produtoDAO();
    produtoDTO produtoDTO = new produtoDTO();

    int tela = 120;
    int largura = 120;

    public void listar() {
        try {
            List<Object> listaProdutos = produtoDAO.listar();
            
            int tela = 117;
            int largura = 117;
            
            Util.titulo("Lista de Produtos e Serviços", tela, largura);
        
        	for (int ct=0;ct<5;ct++) {
        		Util.cabecalho(formatar.produto_campos()[ct],formatar.produto_tam()[ct],0);
        	}
        	
        	Util.writeln("", 0);
    		Util.line("-", largura);
            Iterator<Object> produtos = listaProdutos.iterator();
            while ( produtos.hasNext() ) {
                produtoDTO = (produtoDTO)produtos.next();
                
                // mostrando dados dos registros
                Util.corpo(produtoDTO.getId()+"",formatar.produto_tam()[0],0);		
                Util.corpo(produtoDTO.getTipo(),formatar.produto_tam()[1],0);
                Util.corpo(produtoDTO.getDescricao(),formatar.produto_tam()[2],0);	
                Util.corpo(produtoDTO.getSaldo_atual()+"",formatar.produto_tam()[3],0);
                Util.corpo(produtoDTO.getPreco()+"",formatar.produto_tam()[4],1);
                
            }
            Util.voltar(tela, largura);
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

// *************************************************************************************
    public void incluir() throws SQLException {

    	Util.titulo("Incluir Produto ou Serviço", tela, largura);
        leitura();
        produtoDAO.incluir( produtoDTO );
    	Util.writeln("Produto Incluído! ",0);
    }    
    
// ******************************************************************************
    public void excluir() throws SQLException {

    	Util.titulo("Excluir Produto ou Serviço", tela, largura);
       
    	Util.write("Digite o ID do Produto ou Serviço: ",0);
        produtoDTO.setId(Keyboard.readInt());

        produtoDAO.excluir( produtoDTO );
        Util.writeln("Produto Excluído! ",0);
    }
    
// *************************************************************************************
    
    public void alterar() throws SQLException {
        
    	Util.titulo("Alterar Produto ou Serviço", tela, largura);   	

   		Util.write("Digite o ID do Produto ou Serviço: ",0);
   		produtoDTO.setId(Keyboard.readInt());
   		leitura();
    		
        produtoDAO.alterar( produtoDTO );
        Util.writeln("Produto Alterado! ",0);
    }    
    
// **************************************************************************************
// LEITURA DOS DADOS DO TECLADO
    
    public void leitura() throws SQLException {
    		Util.write("Digite a Descrição: ",0);
            produtoDTO.setDescricao(Keyboard.readString());

            char tipo;
    		Util.write("Produto ou serviço: [P ou S]: ",0);
    		tipo = Keyboard.readChar();
    		 
            if (tipo == 'p' || tipo == 'P') {
            	produtoDTO.setTipo("P");
            	Util.write("Saldo Atual: ",0);
            	produtoDTO.setSaldo_atual(Keyboard.readDouble());
            } else {
            	produtoDTO.setTipo("S");
            	produtoDTO.setSaldo_atual(0.0);
            }
	        
	        Util.write("Preço: ",0);
            produtoDTO.setPreco(Keyboard.readDouble());
    }    
}
