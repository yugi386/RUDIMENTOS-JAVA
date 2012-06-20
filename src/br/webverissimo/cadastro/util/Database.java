/* 
=======================================================================================
	 RUDIMENTOS DO JAVA
	 AUTOR: YUGI
	 DATA: 17/06/2012
=======================================================================================
	 CONEXÃO COM O BANCO DE DADOS
=======================================================================================
 */

package br.webverissimo.cadastro.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {

    public Database(){
    }

// ****************************************************************************************
    
    public Connection getConnection(){

        Connection conexao=null;
        try {
            Class.forName("com.mysql.jdbc.Driver"); 					// carrega o driver de acesso ao MYSQL
            String url = "jdbc:mysql://localhost:3306/oficina";
            String usuario = "root";
            String senha = "";
            conexao = DriverManager.getConnection(url, usuario, senha); // instancia a conexão com o banco de dados
        } catch (Exception ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        return conexao;
    }
	
// ****************************************************************************************
}
