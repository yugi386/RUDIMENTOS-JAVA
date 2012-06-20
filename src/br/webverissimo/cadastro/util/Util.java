/* 
=======================================================================================
	 RUDIMENTOS DO JAVA 
	 AUTOR: YUGI
	 DATA: 17/06/2012
=======================================================================================
	 CLASSE UTILITÁRIOS DO SISTEMA
=======================================================================================
 */
 
package br.webverissimo.cadastro.util;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

	// CONVERTE STRING PARA DATA
    public static Date stringToDate(String data) {
        Date date = null;

        try {
            DateFormat formatter;
            formatter = new SimpleDateFormat("dd/MM/yyyy");
            date = (Date) formatter.parse(data);
            /*String s = formatter.format(date);*/
        } catch (ParseException e) {
            System.out.println("Exception :" + e);
        }

        return date;
    }
	
//*************************************************************************************

	// ESCREVE NA TELA COM A OPÇÃO DE CENTRALIZAR O TEXTO
    public static void write(String text,int tam) {
    	if (tam == 0) {
    		System.out.print(text);
    	} else {
    		tam = tam - text.length();
    		float metade = tam / 2;
    		tam = (int)(metade);
    		System.out.print(repete(" ", tam));
    		System.out.print(text);
    	}
    }
	
//*************************************************************************************

	// ESCREVE NA TELA COM A OPÇÃO DE CENTRALIZAR O TEXTO E INSERE QUEBRA DE LINHA
    public static void writeln(String text, int tam) {
		write(text,tam);
		System.out.println("");
    }
    
//*************************************************************************************

	// ESCREVE UMA LINHA NA TELA
    public static void line(String text, int tam) {
    	System.out.println( repete(text, tam));
    }
    
//*************************************************************************************

    // REPETE UMA STRING VARIAS VEZES 
    public static String repete(String text, int vezes) {
    	String ret = "";
    	
        for (int i=0; i<vezes; i++) {  
           ret = ret + text;  
        }
        return ret;  
     }  

//*************************************************************************************
	
	// ESCREVE NA TELA COM A OPÇÃO DE INSERIR RECUO NA MARGEM ESQUERDA
    public static void writesp(String text, int tam, int quebra) {
    	if (tam == 0) {
    		System.out.print(text);
    	} else {
    		System.out.print(repete(" ", tam));
    		if (quebra == 1) {
    			System.out.println(text);
    		} else {
    			System.out.print(text);
    		}
    	}
    }
    
//*************************************************************************************

	// RETORNA A DATA ATUAL EM FORMATO STRING
    public static String Data() {
		Date now = new Date();
		DateFormat df = DateFormat.getDateInstance();
		String s = df.format(now);
		return s;
	}  

//*************************************************************************************

	// RETORNA A DATA ATUAL EM FORMATO STRING DD/MM/AA
    public static String DataBanco() {
        
		Date now = new Date();
		DateFormat df = DateFormat.getDateInstance();
		String s = df.format(now);
		  
		String dia = s.substring(0, 2);
		String mes = s.substring(3, 5);
		String ano = s.substring(6, 10);

		String ret = ano + "-" + mes + "-" + dia;
		return ret;
	}      

// *************************************************************************************
	
	// RETORNA A DIFERENÇA EM DIAS DE DUAS DATAS EM FORMATO STRING DD/MM/AA 
	public static int Diferenca(String data1, String data2) {
        
		int dia = Integer.parseInt(data1.substring(0, 2));
		int mes = Integer.parseInt(data1.substring(3, 5));
		int ano = Integer.parseInt(data1.substring(6, 10));

		int dia2 = Integer.parseInt(data2.substring(0, 2));
		int mes2 = Integer.parseInt(data2.substring(3, 5));
		int ano2 = Integer.parseInt(data2.substring(6, 10));

		int dif = (dia * 1) + (mes * 30) + (ano * 365);
		dif = dif - ((dia2 * 1) + (mes2 * 30) + (ano2 * 365));

		return dif;
	}      
    
//*************************************************************************************

	// CONVERTE DATA PARA STRING
	public static String DateToString(Date datausu) {
		String data = "";
		SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
		data = formataData.format(datausu);
		return data;
	}

//*************************************************************************************

	// ESCREVE NA TELA COM A OPÇÃO DE INSERIR ESPACO NA MARGEM DIREITA 
	  public static void writesp2(String text, int tam, int quebra) {
		text = text.trim();  
		if (tam == 0) {
			System.out.print(text + "| ");
		} else {
			if (quebra == 1) {
				System.out.println(text + repete(" ", tam) + "| ");
			} else {
				System.out.print(text);
				System.out.print(repete(" ", tam) +  "| ");
			}
		}
	  }
  
//*************************************************************************************
	
	// MONTA O CABEÇALHO DE UMA LISTAGEM OU RELATÓRIO
	public static void cabecalho(String rotulo, int tamanho, int quebra){
	  int tam = tamanho;
	  rotulo = rotulo.trim();
	  
	  if (rotulo.length() >= tam) {
		  tam = 1;
	  } else {
		  tam = tam - rotulo.length() + 1;
	  }

	  writesp2(rotulo, tam, quebra);
	  
	}

//*************************************************************************************
	
	// MONTA O CORPO DE UMA LISTAGEM OU RELATÓRIO
	public static void corpo(String campo, int tamanho, int quebra){
		cabecalho(campo, tamanho, quebra);
	}
	
//*************************************************************************************
	
	// MONTA UM MENU NA TELA:
	public static void menu(String tit, String[] opc, int tela, int recuo ){
		Util.line("=", tela);
		Util.writeln(tit,tela);
		Util.line("=", tela);	
		
		for (int ct=0;ct<opc.length;ct++) {
			Util.writesp((ct+1) + " - " + opc[ct],recuo,1);
		}	
		
		Util.writesp("0 - Sair",recuo,1);
		Util.line("=", tela);
		Util.writesp("Escolha uma Opção: ", 0,0);
	}
	
//*************************************************************************************
	
	// MOSTRA UM TITULO NA TELA:
	public static void titulo(String tit, int tela, int largura ){
		Util.line("=", largura);
		Util.writeln(tit,tela);
		Util.line("=", largura);	
	}	
//*************************************************************************************	
	
	// MOSTRA MENSAGEM DE RETORNO
	public static void voltar(int tela, int largura ){
		Util.line("=", largura);
		Util.writeln("TECLE <SPACE + ENTER> PARA VOLTAR!",tela);
		Util.line("=", largura);	
        Keyboard.readString();
	}	
//*************************************************************************************
}