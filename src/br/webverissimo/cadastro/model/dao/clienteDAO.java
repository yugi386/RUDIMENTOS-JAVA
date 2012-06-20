/* 
=======================================================================================
	 RUDIMENTOS DO JAVA 
	 AUTOR: YUGI
	 DATA: 17/06/2012
=======================================================================================
	 CADASTRO DE CLIENTES - CONTROLADOR
=======================================================================================
 */

package br.webverissimo.cadastro.model.dao;
import br.webverissimo.cadastro.model.dto.clienteDTO;
import br.webverissimo.cadastro.util.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class clienteDAO {

    private Database database=new Database();

//************************************************************************************* 
// Lista todos os clientes    
    public List<Object> listar() throws SQLException{

        String sql = "select id, razao_social, endereco, complemento, bairro, cidade, estado, cep, tel, saldo_aberto, ativo from cliente";

        ResultSet rs = database.getConnection().createStatement().executeQuery(sql);
        List<Object> listaCliente = new ArrayList<Object>();
        
        while ( rs.next()){
            listaCliente.add( preencherClienteDTO(rs)  );
        }

        rs.close();
        return listaCliente;
    }

    //*************************************************************************************
       
    public Object preencherClienteDTO(ResultSet rs) throws SQLException {
       
       clienteDTO clienteDTO = new clienteDTO();	//	instância DTO
       
       clienteDTO.setId(rs.getInt("id"));
       clienteDTO.setRazao_social(rs.getString("razao_social"));
       clienteDTO.setEndereco(rs.getString("endereco"));
       clienteDTO.setComplemento(rs.getString("complemento"));
       clienteDTO.setBairro(rs.getString("bairro"));
       clienteDTO.setCidade(rs.getString("cidade"));
       clienteDTO.setEstado(rs.getString("estado"));
       clienteDTO.setCep(rs.getString("cep"));
       clienteDTO.setTel(rs.getString("tel"));
       clienteDTO.setSaldo_aberto(rs.getDouble("saldo_aberto"));
       clienteDTO.setAtivo(rs.getString("ativo"));
       
       return clienteDTO;
    }
   
 //*************************************************************************************
 // METODO DE INCLUSÃO DE CLIENTES
    
    public void incluir(clienteDTO clienteDTO) throws SQLException {
   	
        String sql = "insert into cliente ("  
                + "id, razao_social, endereco, complemento, bairro, cidade, estado, cep, tel, saldo_aberto, ativo) "
                + "values (null,?,?,?,?,?,?,?,?,?,?)";
                
        PreparedStatement pstmt = database.getConnection().prepareStatement(sql);
        
        pstmt.setString(1, clienteDTO.getRazao_social());
        pstmt.setString(2, clienteDTO.getEndereco());
        pstmt.setString(3, clienteDTO.getComplemento());
        pstmt.setString(4, clienteDTO.getBairro());
        pstmt.setString(5, clienteDTO.getCidade());
        pstmt.setString(6, clienteDTO.getEstado());
        pstmt.setString(7, clienteDTO.getCep());
        pstmt.setString(8, clienteDTO.getTel());
        pstmt.setDouble(9, clienteDTO.getSaldo_aberto());
        pstmt.setString(10, clienteDTO.getAtivo());
		
        pstmt.executeUpdate();

        pstmt.close();
    }
    


//*************************************************************************************
// EXCLUSÃO DE CLIENTES

    public void excluir(clienteDTO clienteDTO) throws SQLException {
       String sql = "delete from cliente where id = ?";
       PreparedStatement pstmt = database.getConnection().prepareStatement(sql);
       pstmt.setInt(1,  clienteDTO.getId());

       pstmt.executeUpdate();
       pstmt.close();
   }


 // *************************************************************************************
    public void alterar(clienteDTO clienteDTO) throws SQLException {
    	
        String sql = "update cliente set razao_social=?, endereco=?, complemento=?, bairro=?,"
                + "  cidade=? , estado=?, cep=?, tel=?, saldo_aberto=?, ativo=?  where id =?";
        
        PreparedStatement pstmt = database.getConnection().prepareStatement(sql);
        
        pstmt.setString(1, clienteDTO.getRazao_social());
        pstmt.setString(2, clienteDTO.getEndereco());
        pstmt.setString(3, clienteDTO.getComplemento());
        pstmt.setString(4, clienteDTO.getBairro());
        pstmt.setString(5, clienteDTO.getCidade());
        pstmt.setString(6, clienteDTO.getEstado());
        pstmt.setString(7, clienteDTO.getCep());
        pstmt.setString(8, clienteDTO.getTel());
        pstmt.setDouble(9, clienteDTO.getSaldo_aberto());
        pstmt.setString(10, clienteDTO.getAtivo());
        pstmt.setInt(11, clienteDTO.getId());
		
        pstmt.executeUpdate();

        pstmt.close();
    }
    
 // *************************************************************************************
 // Altera o saldo do cliente para compra ou devolucao
    public void alterarSaldo(int id, Double Saldo, int tipo) throws SQLException {
    	
    	String sql = "";
    	
    	if (tipo == 1) {
    		sql = "update cliente set saldo_aberto=(saldo_aberto +?)  where id =?";
    	} else if (tipo == 2) {
    		sql = "update cliente set saldo_aberto=(saldo_aberto -?)  where id =?";    		
    	}
        
        PreparedStatement pstmt = database.getConnection().prepareStatement(sql);
        
        pstmt.setDouble(1, Saldo);
        pstmt.setInt(2, id);
		
        pstmt.executeUpdate();

        pstmt.close();
    }    
    
    
 // ***************************************************************************************
 // verifica se cliente é apto para comprar    
     public int VerificaCliente(int id) throws SQLException {

         String sql = "select ativo, saldo_aberto from cliente where id = ?";

         PreparedStatement pstmt = database.getConnection().prepareStatement(sql);
         pstmt.setInt(1, id);  
         ResultSet rs = pstmt.executeQuery();
                 
         int ret = 2;
         // verificando se cliente é apto para comprar
         while ( rs.next()){
         	if ( rs.getDouble("saldo_aberto") > 1000 || !rs.getString("ativo").equals("S")) {
         		ret = 0;
         	} else {
         		ret = 1;
         	}	
         }

         rs.close();
         return ret;
     }    
}    