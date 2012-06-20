/* 
=======================================================================================
	 RUDIMENTOS DO JAVA 
	 AUTOR: YUGI
	 DATA: 17/06/2012
=======================================================================================
	 INTERFACE PARA ITENS DAS VENDAS
=======================================================================================
 */

package br.webverissimo.cadastro.model.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import br.webverissimo.cadastro.model.dto.itensDTO;
import br.webverissimo.cadastro.util.Database;

public class itensDAO {

    private Database database=new Database();

 //*************************************************************************************
 // METODO DE INCLUSÃO DE ITENS DE VENDA
 
    public void incluir(itensDTO itensDTO) throws SQLException {
   	
        String sql = "insert into itens (id, venda_id, produto_id, quant) values (null,?,?,?)";
                
        PreparedStatement pstmt = database.getConnection().prepareStatement(sql);
        
        pstmt.setInt(1, itensDTO.getVenda_id());
        pstmt.setInt(2, itensDTO.getProduto_id());
        pstmt.setDouble(3, itensDTO.getQuant());
		
        pstmt.executeUpdate();
        pstmt.close();
        
        // dando baixa no estoque
        produtoDAO prod = new produtoDAO();
        prod.alterarQuant(itensDTO.getProduto_id(),  itensDTO.getQuant(),1); // tipo 1 = baixa no estoque
        
    }
   


//*************************************************************************************
// EXCLUSÃO DE ITENS DE VENDA - excluir pelo id da venda (venda_id)

    public void excluir(int id) throws SQLException {
       String sql = "delete from itens where venda_id = ?";
       PreparedStatement pstmt = database.getConnection().prepareStatement(sql);
       pstmt.setInt(1,  id);

       pstmt.executeUpdate();
       pstmt.close();
   }
}    