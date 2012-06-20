/* 
=======================================================================================
	 RUDIMENTOS DO JAVA 
	 AUTOR: YUGI
	 DATA: 17/06/2012
=======================================================================================
	 CONTROLADOR DE VENDAS
=======================================================================================
 */

package br.webverissimo.cadastro.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.webverissimo.cadastro.model.dto.ItensDTO;
import br.webverissimo.cadastro.model.dto.VendaDTO;
import br.webverissimo.cadastro.util.Database;
import br.webverissimo.cadastro.util.Util;

public class VendaDAO {

	private Database database=new Database();

	//*************************************************************************************    
	// LISTA TODAS AS VENDAS
	    public List<Object> listar() throws SQLException{

	        String sql = "select id, cliente_id, data_venda, status, total from venda";

	        ResultSet rs = database.getConnection().createStatement().executeQuery(sql);
	        List<Object> listaVenda = new ArrayList<Object>();
	        
	        while ( rs.next()){
	            listaVenda.add( preencherVendaDTO(rs)  );
	        }

	        rs.close();
	        return listaVenda;
	    }

// ************************************************************************
// lista vendas com filtro por datas e cliente	    
public List<Object> listar(String dataini, String datafim, int cliente) throws SQLException{

   	String sql = "";
	    	
	if (cliente == 0) {
		sql = "select id, cliente_id, data_venda, status, total from venda where " +
				"data_venda >= ? and data_venda <= ?";
	} else {
		sql = "select id, cliente_id, data_venda, status, total from venda where " +
				"data_venda >= ? and data_venda <= ? and cliente_id = ?";
	}

	 PreparedStatement pstmt = database.getConnection().prepareStatement(sql);
     pstmt.setDate(1, new java.sql.Date (Util.stringToDate(dataini).getTime()));
     pstmt.setDate(2, new java.sql.Date (Util.stringToDate(datafim).getTime()));
     if (cliente != 0) {
    	 pstmt.setInt(3, cliente);    	 
     }
     ResultSet rs = pstmt.executeQuery();

  List<Object> listaVenda = new ArrayList<Object>();
	        
	        while ( rs.next()){
	            listaVenda.add( preencherVendaDTO(rs)  );
	        }

	        rs.close();
	        return listaVenda;
	    }	    
 //*************************************************************************************
 // PREENCHE A LISTA COM DADOS DAS VENDAS
	    private VendaDTO preencherVendaDTO(ResultSet rs) throws SQLException {
	       
	       VendaDTO vendaDTO = new VendaDTO();	//	instância DTO
	       
	       vendaDTO.setId(rs.getInt("id"));
	       vendaDTO.setCliente_id(rs.getInt("cliente_id"));
	       vendaDTO.setData_venda(rs.getDate("data_venda"));
	       vendaDTO.setStatus(rs.getString("status"));
	       vendaDTO.setTotal(rs.getDouble("total"));
	       vendaDTO.setItens( itens_venda(vendaDTO.getId()) );
	       
	       return vendaDTO;
	    }
	    
//*************************************************************************************
//  // PREENCHE A LISTA COM DADOS DOS ITENS DAS VENDAS 
public List<ItensDTO> itens_venda(int id) throws SQLException{
	    	
   String sql = "select id, produto_id, quant from itens where venda_id = ?";
	        
	        PreparedStatement pstmt = database.getConnection().prepareStatement(sql);
	        pstmt.setInt(1, id);	//	coloca o código da venda
	        ResultSet rs = pstmt.executeQuery();
	        
	        ItensDTO itensDTO = null;	//	instancia o objeto de itens da venda
	        List<ItensDTO> listaitens = new ArrayList<ItensDTO>();
	        
	        while( rs.next()){
	        	itensDTO = new ItensDTO();
	        	// preenchedo itens da venda
	            itensDTO.setId(rs.getInt("id"));
	            itensDTO.setProduto_id(rs.getInt("produto_id"));
	            itensDTO.setQuant(rs.getDouble("quant"));
	            
	            // Adiciona os itens da venda na lista de vendas
	            listaitens.add( itensDTO  );
	        }
	        // Fecha a conexão
	        rs.close();
	        pstmt.close();
	        
	        return listaitens;
 }	    
	    
 //*************************************************************************************
 // METODO DE INCLUSÃO DE VENDAS
	    
  public void incluir(VendaDTO vendaDTO) throws SQLException {
	   	
	// insere a venda:

    String sql = "insert into venda (id, cliente_id, data_venda, status, total) values (?,?,?,?,?)";
            
    PreparedStatement pstmt = database.getConnection().prepareStatement(sql);
    
    pstmt.setInt(1, vendaDTO.getId());
    pstmt.setInt(2, vendaDTO.getCliente_id());
    pstmt.setDate(3, new java.sql.Date (vendaDTO.getData_venda().getTime()));
    pstmt.setString(4, vendaDTO.getStatus());
    pstmt.setDouble(5, vendaDTO.getTotal());
	
    pstmt.executeUpdate();
    pstmt.close();
    
    // colocando a conta para o cliente:
    ClienteDAO cli = new ClienteDAO();
    cli.alterarSaldo(vendaDTO.getCliente_id(),vendaDTO.getTotal(),1);  // tipo = 1 compra
    
 }


	//*************************************************************************************
	// EXCLUSÃO DE ITENS DA VENDA - só exclui vendas canceladas!

    public boolean excluir(VendaDTO vendaDTO) throws SQLException {
	    
    	String ret = VerificaVendaCancelada(vendaDTO.getId()).trim(); // Verifica se a venda está cancelada!!! 
    	
    	if (  ret.equals("C")) {
	       String sql = "delete from venda where id = ? and status = 'C'";
	       PreparedStatement pstmt = database.getConnection().prepareStatement(sql);
	       pstmt.setInt(1,  vendaDTO.getId());

	       pstmt.executeUpdate();
	       pstmt.close();
	       
	       ItensDAO itensDAO = null;	//	instancia o objeto para gravacao do itens
	   	   itensDAO = new ItensDAO();
	   	
	   	   itensDAO.excluir(vendaDTO.getId());	//	excluindo itens desta venda
	   	   return true;
    	} else {
    		 return false;
    	}  
	   		
    }

 // ***************************************************************************************
 // verifica se a venda está cancelada    
    public String VerificaVendaCancelada(int id) throws SQLException {

         String sql = "select status from venda where id = ?";

         PreparedStatement pstmt = database.getConnection().prepareStatement(sql);
         pstmt.setInt(1, id);  
         ResultSet rs = pstmt.executeQuery();
	      
         String ret = "";
         while ( rs.next()){
        	ret =	rs.getString("status");
         }

      rs.close();
	  return ret;       
}
    
// ***************************************************************************************
  // cancela uma venda devolvendo os produtos para o estoque e o dinheiro para o cliente
public boolean CancelarVenda(int id) throws SQLException {
	
	if(  VerificaVendaCancelada(id).trim().equals("C") ){	//	verifica se a venda está cancelada
		return false;
	}
    	
    // se a venda nao esta cancelada:
	
	// muda o status da venda para cancelada:
        String sql = "update venda set status='C' where id =?";
        PreparedStatement pstmt = database.getConnection().prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
        pstmt.close();
    
    // devolvendo dinheiro para o cliente:    
       sql = "select total, cliente_id from venda where id = ?";
       pstmt = database.getConnection().prepareStatement(sql);
       pstmt.setInt(1, id);  
       ResultSet rs = pstmt.executeQuery();
      
        double tot = 0;
        int cli_id = 0;
        while ( rs.next()){
        	tot = rs.getDouble("total");
        	cli_id = rs.getInt("cliente_id");
        }
        rs.close();  
        
        ClienteDAO cli = new ClienteDAO();
        cli.alterarSaldo(cli_id,tot,2);  // tipo = 2 cancelamento
      
        // devolvendo itens para o estoque:
        sql = "select produto_id, quant from itens where venda_id = ?";
        pstmt = database.getConnection().prepareStatement(sql);
        pstmt.setInt(1, id);  
        rs = pstmt.executeQuery();
       
        ProdutoDAO prod = new ProdutoDAO();
        while ( rs.next()){
           prod.alterarQuant(rs.getInt("produto_id"),  rs.getDouble("quant"),2); // tipo 2 = devolve produto para estoque
        }
        rs.close();
        
        return true;
  }

// ---------------------------------------------------------------------------------------------------------------
// verifica se a venda já existe:
public int VerificaVendaExistente(int id) throws SQLException { 
	String sql = "";
	sql = "select count(*) as total from venda where id = ?";
	
	PreparedStatement pstmt = database.getConnection().prepareStatement(sql);
	pstmt.setInt(1, id);  
	ResultSet rs = pstmt.executeQuery();
	
	 int tot = 0;
	 while ( rs.next()){
	 	tot = rs.getInt("total");
	 }
	 rs.close();
	 
	 return tot;
} 
	     
}    