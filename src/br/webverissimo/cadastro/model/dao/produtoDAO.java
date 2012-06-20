/* 
=======================================================================================
	 RUDIMENTOS DO JAVA 
	 AUTOR: YUGI
	 DATA: 17/06/2012
=======================================================================================
	 CONTROLADOR DOS PRODUTOS
=======================================================================================
 */

package br.webverissimo.cadastro.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.webverissimo.cadastro.model.dto.produtoDTO;
import br.webverissimo.cadastro.util.Database;

public class produtoDAO {

    private Database database=new Database();

//*************************************************************************************    
    public List<Object> listar() throws SQLException{

        String sql = "select id, tipo, descricao, saldo_atual, preco from produto";

        ResultSet rs = database.getConnection().createStatement().executeQuery(sql);
        List<Object> listaProduto = new ArrayList<Object>();
        
        while ( rs.next()){
            listaProduto.add( preencherProdutoDTO(rs)  );
        }

        rs.close();
        return listaProduto;
    }

    //*************************************************************************************
       
    private Object preencherProdutoDTO(ResultSet rs) throws SQLException {
       
       produtoDTO produtoDTO = new produtoDTO();	//	instância DTO
       
       produtoDTO.setId(rs.getInt("id"));
       produtoDTO.setTipo(rs.getString("tipo"));
       produtoDTO.setDescricao(rs.getString("descricao"));
       produtoDTO.setSaldo_atual(rs.getDouble("saldo_atual"));
       produtoDTO.setPreco(rs.getDouble("preco"));
       
       return produtoDTO;
    }
   
 //*************************************************************************************
 // METODO DE INCLUSÃO DE PRODUTOS
    
    public void incluir(produtoDTO produtoDTO) throws SQLException {
   	
        String sql = "insert into produto ("  
                + "id, tipo, descricao, saldo_atual, preco) "
                + "values (null,?,?,?,?)";
                
        PreparedStatement pstmt = database.getConnection().prepareStatement(sql);
        
        pstmt.setString(1, produtoDTO.getTipo());
        pstmt.setString(2, produtoDTO.getDescricao());
        pstmt.setDouble(3, produtoDTO.getSaldo_atual());
        pstmt.setDouble(4, produtoDTO.getPreco());
		
        pstmt.executeUpdate();

        pstmt.close();
    }
    


//*************************************************************************************
// EXCLUSÃO DE PRODUTOS

    public void excluir(produtoDTO produtoDTO) throws SQLException {
       String sql = "delete from produto where id = ?";
       PreparedStatement pstmt = database.getConnection().prepareStatement(sql);
       pstmt.setInt(1,  produtoDTO.getId());

       pstmt.executeUpdate();
       pstmt.close();
   }


 // *************************************************************************************
    public void alterar(produtoDTO produtoDTO) throws SQLException {
    	
        String sql = "update produto set descricao=?, tipo=?, saldo_atual=?, preco=?  where id =?";
        
        PreparedStatement pstmt = database.getConnection().prepareStatement(sql);
        
        pstmt.setString(1, produtoDTO.getDescricao());
        pstmt.setString(2, produtoDTO.getTipo());
        pstmt.setDouble(3, produtoDTO.getSaldo_atual());
        pstmt.setDouble(4, produtoDTO.getPreco());
        pstmt.setInt(5, produtoDTO.getId());
		
        pstmt.executeUpdate();

        pstmt.close();
    }
    
// ***************************************************************************************
// procura o preço de um produto e verifica se o quantidade é suficiente para a venda    
    public produtoDTO ProcuraPreco(int id, double quant) throws SQLException {

        String sql = "select preco, saldo_atual, tipo from produto where id = ?";

        PreparedStatement pstmt = database.getConnection().prepareStatement(sql);
        pstmt.setInt(1, id);  
        ResultSet rs = pstmt.executeQuery();
                
        produtoDTO Produto = new produtoDTO();
        // lendo preco do produto ou servico - atenção: servico tem saldo atual sempre zero
        while ( rs.next()){
        	if ( rs.getDouble("saldo_atual") >= quant || rs.getString("tipo").equals("S")) {
        		Produto.setPreco(rs.getDouble("preco"));
        	}	
        }

        rs.close();
        return Produto;
    }
    
    
 // *************************************************************************************
// altera a quantidade de um produto - dando baixa    
    public void alterarQuant(int id, double quant, int tipo) throws SQLException {
    	String sql = "";
    	if (tipo == 1) {
    		sql = "update produto set saldo_atual=(saldo_atual - ?) where id =? and tipo = 'P'";
    	} else {
    		sql = "update produto set saldo_atual=(saldo_atual + ?) where id =?  and tipo = 'P'";
    	}
        
        PreparedStatement pstmt = database.getConnection().prepareStatement(sql);
        
        pstmt.setDouble(1, quant);
        pstmt.setInt(2, id);
		
        pstmt.executeUpdate();

        pstmt.close();
    }    
}    