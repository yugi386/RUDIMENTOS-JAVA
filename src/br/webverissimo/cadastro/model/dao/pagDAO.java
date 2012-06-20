/* 
=======================================================================================
	 RUDIMENTOS DO JAVA 
	 AUTOR: YUGI
	 DATA: 17/06/2012
=======================================================================================
	 CONTROLADOR PAGAMENTOS
=======================================================================================
 */
package br.webverissimo.cadastro.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.webverissimo.cadastro.model.dto.PagDTO;
import br.webverissimo.cadastro.util.Database;
import br.webverissimo.cadastro.util.Util;

public class PagDAO {

	private Database database=new Database();

//*************************************************************************************    
// LISTA DE PAGAMENTOS	
	    public List<Object> listar() throws SQLException{

	        String sql = "select id, data_pag, venda_id, valor_pago, desconto, status from pagamento";

	        ResultSet rs = database.getConnection().createStatement().executeQuery(sql);
	        List<Object> listaPag = new ArrayList<Object>();
	        
	        while ( rs.next()){
	            listaPag.add( preencherPagDTO(rs)  );
	        }

	        rs.close();
	        return listaPag;
	    }

//*************************************************************************************    
// // LISTA DE PAGAMENTOS PERIODO + CLIENTE 	    
public List<Object> listar(String dataini, String datafim, int cliente) throws SQLException{

	String sql = "";
	
	if (cliente == 0) {
    sql = "select id, data_pag, venda_id, valor_pago, desconto, status " +
    		"from pagamento  where data_pag >= ? and data_pag <= ?";
	} else {
	    sql = "select data_pag, venda_id, valor_pago, status, cliente_id, total " +
	    		"from pag  where data_pag >= ? and data_pag <= ? and " +
	    		"cliente_id = ?";
	}

	 PreparedStatement pstmt = database.getConnection().prepareStatement(sql);
     pstmt.setDate(1, new java.sql.Date (Util.stringToDate(dataini).getTime()));
     pstmt.setDate(2, new java.sql.Date (Util.stringToDate(datafim).getTime()));
     if (cliente != 0) {
    	 pstmt.setInt(3, cliente);    	 
     }
     ResultSet rs = pstmt.executeQuery();
     
    List<Object> listaPag = new ArrayList<Object>();
    
    while ( rs.next()){
    	if (cliente == 0){	
    		listaPag.add( preencherPagDTO(rs)  );
    	} else {
    		listaPag.add( preencherPagDTO2(rs)  );
    	}
    }

    rs.close();
    return listaPag;
}	    
	    
 //*************************************************************************************
//PREENCHE A LISTA DE PAGAMENTOS POR PERIODO
	    private PagDTO preencherPagDTO(ResultSet rs) throws SQLException {
	       
	       PagDTO pagDTO = new PagDTO();		//	instância DTO
	       
	       pagDTO.setId(rs.getInt("id"));
	       pagDTO.setData_pag(rs.getDate("data_pag"));
	       pagDTO.setValor_pago(rs.getDouble("valor_pago"));
	       pagDTO.setStatus(rs.getString("status"));
	       pagDTO.setDesconto(rs.getDouble("desconto"));
	       pagDTO.setVenda_id(rs.getInt("venda_id"));
	       
	       return pagDTO;
	    }

 //*************************************************************************************
// PREENCHE A LISTA DE PAGAMENTOS POR PERIODO + CLIENTE	    
	    private PagDTO preencherPagDTO2(ResultSet rs) throws SQLException {
	       
	       PagDTO pagDTO = new PagDTO();		//	instância DTO
	       
	       pagDTO.setId(rs.getInt("cliente_id"));		//	substitui ID do pagamento por ID do Cliente	
	       pagDTO.setData_pag(rs.getDate("data_pag"));
	       pagDTO.setValor_pago(rs.getDouble("valor_pago"));
	       pagDTO.setStatus(rs.getString("status"));
	       pagDTO.setDesconto(rs.getDouble("total"));	//	substitui desconto por total...
	       pagDTO.setVenda_id(rs.getInt("venda_id"));
	       
	       return pagDTO;
	    }
//*************************************************************************************	    

// METODO DE INCLUSÃO DE PAGAMENTO
    
 public void incluir(PagDTO pagDTO) throws SQLException {
   	
// insere a venda:

   String sql = "insert into pagamento (id, data_pag, venda_id, valor_pago, desconto, status) values (null,?,?,?,?,?)";
           
   PreparedStatement pstmt = database.getConnection().prepareStatement(sql);
   
   pstmt.setDate(1, new java.sql.Date (pagDTO.getData_pag().getTime()));
   pstmt.setInt(2, pagDTO.getVenda_id());
   pstmt.setDouble(3, pagDTO.getValor_pago());
   pstmt.setDouble(4, pagDTO.getDesconto());
   pstmt.setString(5, pagDTO.getStatus());   

   pstmt.executeUpdate();
   pstmt.close();
   
// verificando ID do cliente:
   sql = "select cliente_id from venda where id = ?";
   pstmt = database.getConnection().prepareStatement(sql);
   pstmt.setInt(1, pagDTO.getVenda_id());  
   ResultSet rs = pstmt.executeQuery();      
   
   int cliente_id = 0;
   while ( rs.next()){
   	cliente_id = rs.getInt("cliente_id");
   }
   rs.close();      
   
   // somando a dívida ao cliente:
   ClienteDAO cli = new ClienteDAO();
   cli.alterarSaldo(cliente_id,pagDTO.getValor_pago(),2);  // se pagou entao diminue divida
   
}	    

//*************************************************************************************
// EXCLUSÃO DE pagamento - só exclui pagamentos cancelados.

 public boolean excluir(PagDTO pagDTO) throws SQLException {
	    
 	String ret = VerificaPagCancelado(pagDTO.getId()).trim(); 
 	
 	if (  ret.equals("C")) {
	       String sql = "delete from pagamento where id = ? and status = 'C'";
	       PreparedStatement pstmt = database.getConnection().prepareStatement(sql);
	       pstmt.setInt(1,  pagDTO.getId());

	       pstmt.executeUpdate();
	       pstmt.close();
	       return true;
	       
 	} else {
 		 return false;
 	}  
	   		
 } 

// ******************************************************************************************** 
//verifica se o pagamento está cancelado.    
 public String VerificaPagCancelado(int id) throws SQLException {

      String sql = "select status from pagamento where id = ?";

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
 
//***************************************************************************************
// cancela um pagamento - devolve o dinheiro para o cliente.
public boolean CancelarPag(int id) throws SQLException {
	
	if(  VerificaPagCancelado(id).trim().equals("C") ){
		return false;
	}
  	
  // rotina de cancelamento: 
	
	// muda o status da pag para cancelada:
      String sql = "update pagamento set status='C' where id =?";
      PreparedStatement pstmt = database.getConnection().prepareStatement(sql);
      pstmt.setInt(1, id);
      pstmt.executeUpdate();
      pstmt.close();
  
   // verificando ID da venda e valor pago pelo cliente:
      sql = "select venda_id, valor_pago from pagamento where id = ?";
      pstmt = database.getConnection().prepareStatement(sql);
      pstmt.setInt(1, id);  
      ResultSet rs = pstmt.executeQuery();      
      
      int venda_id = 0;
      double valor_pago = 0;
      
      while ( rs.next()){
      	venda_id = rs.getInt("venda_id");
      	valor_pago = rs.getDouble("valor_pago");
      }
      rs.close();  
  
  //  verificando ID do cliente:
      sql = "select cliente_id from venda where id = ?";
      pstmt = database.getConnection().prepareStatement(sql);
      pstmt.setInt(1, venda_id);  
      rs = pstmt.executeQuery();      
      
      int cliente_id = 0;
      while ( rs.next()){
      	cliente_id = rs.getInt("cliente_id");
      }
      rs.close();      
      
      // somando a dívida ao cliente:
      ClienteDAO cli = new ClienteDAO();
      cli.alterarSaldo(cliente_id,valor_pago,1);  // se cancelou pagamento entao aumenta divida
    
      return true;
      
}
 
 
}
