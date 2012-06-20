/* 
=======================================================================================
	 RUDIMENTOS DO JAVA 
	 AUTOR: YUGI
	 DATA: 17/06/2012
=======================================================================================
	 CONTROLADOR RELATORIOS
=======================================================================================
 */
package br.webverissimo.cadastro.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.webverissimo.cadastro.model.dao.clienteDAO;
import br.webverissimo.cadastro.util.Database;


// ***************************************************************************************

public class relatDAO {
	
	 private Database database=new Database();
	
 // ==========================================================================================
	
// [2] LISTAGEM DE CLIENTE COM SALDO ABERTO
	public List<Object> lista_cliSaldo() throws SQLException {
	
    String sql = "select id, razao_social, endereco, complemento, bairro, cidade, estado, cep, tel, saldo_aberto, ativo from cliente where saldo_aberto <> 0";

    ResultSet rs = database.getConnection().createStatement().executeQuery(sql);
    List<Object> listaCliente = new ArrayList<Object>();
    
    clienteDAO clienteDAO = new clienteDAO();
    while ( rs.next()){
        listaCliente.add( clienteDAO.preencherClienteDTO(rs)  );
    }

    rs.close();
    return listaCliente;
	}
	
	
	
}
