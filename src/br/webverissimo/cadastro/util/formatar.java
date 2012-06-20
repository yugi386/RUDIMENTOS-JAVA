/* 
=======================================================================================
	 RUDIMENTOS DO JAVA
	 AUTOR: YUGI
	 DATA: 17/06/2012
=======================================================================================
	 FORMATAÇÃO DE RELATÓRIOS
=======================================================================================
 */

package br.webverissimo.cadastro.util;

public class formatar {

	// RÓTULOS DA TABELA CLIENTE:
	public static String[] cliente_campos() {
		String[] cliente = new String[11];
		cliente[0] = "Código";
		cliente[1] = "Razão Social";
		cliente[2] = "Endereço";
		cliente[3] = "Complemento";
		cliente[4] = "Bairro";
		cliente[5] = "Cidade";
		cliente[6] = "Estado";
		cliente[7] = "CEP";
		cliente[8] = "Telefone";
		cliente[9] = "Saldo em Aberto";
		cliente[10] = "Usuário Ativo?";
		return cliente;
	}

//*************************************************************************************
		
	// TAMANHO DOS CAMPOS DA TABELA CLIENTE
	public static int[] cliente_tam() {
		int[] cliente = new int[11];
		cliente[0] = 11;	//	Código
		cliente[1] = 60;	//	Razão Social
		cliente[2] = 60;	//	Endereço
		cliente[3] = 60;	//	Complemento
		cliente[4] = 60;	//	bairro
		cliente[5] = 60;	//  cidade
		cliente[6] = 2;		//  estado
		cliente[7] = 9;		//  CEP
		cliente[8] = 13;	//  Telefone
		cliente[9] = 14;	//  Saldo em Aberto
		cliente[10] = 1;	//  ATIVO?
					
		String[] rotulo = cliente_campos();
		
		for (int ct=0;ct<rotulo.length;ct++){
			if (rotulo[ct].length() > cliente[ct]) {
				cliente[ct] = rotulo[ct].length();
			}
		}
		return cliente;
	}

// ====================================================================================
	
	// RÓTULOS DA TABELA PRODUTO:
	public static String[] produto_campos() {
		String[] produto = new String[5];
		produto[0] = "Código";
		produto[1] = "Tipo";
		produto[2] = "Descrição";
		produto[3] = "Saldo Atual";
		produto[4] = "Preço";
		return produto;
	}

//*************************************************************************************
	
	// TAMANHO DOS CAMPOS TABELA PRODUTO
	public static int[] produto_tam() {
		int[] produto = new int[5];
		produto[0] = 11;	//	Código
		produto[1] = 1;		//	Tipo
		produto[2] = 60;	//	Descricao
		produto[3] = 14;	//  Saldo Atual
		produto[4] = 14;	//  preco
					
		String[] rotulo = produto_campos();
		
		for (int ct=0;ct<rotulo.length;ct++){
			if (rotulo[ct].length() > produto[ct]) {
				produto[ct] = rotulo[ct].length();
			}
		}
		return produto;
	}

// ====================================================================================
		
	// RÓTULOS DA TABELA VENDA
	public static String[] venda_campos() {
		String[] venda = new String[5];
		venda[0] = "Código";
		venda[1] = "Cód. Cliente";
		venda[2] = "Data da Venda";
		venda[3] = "Status";
		venda[4] = "Total da Venda";
		return venda;
	}
	
//*************************************************************************************
	
	// TAMANHO DOS CAMPOS TABELA VENDA
	public static int[] venda_tam() {
		int[] venda = new int[5];
		venda[0] = 11;	//	Código
		venda[1] = 11;	//	Cód. Cliente
		venda[2] = 10;	//	Data
		venda[3] = 1;		//  Status
		venda[4] = 14;	//  Total
					
		String[] rotulo = venda_campos();
		
		for (int ct=0;ct<rotulo.length;ct++){
			if (rotulo[ct].length() > venda[ct]) {
				venda[ct] = rotulo[ct].length();
			}
		}
		return venda;
	}

// ====================================================================================
		
	// RÓTULOS DA TABELA PAGAMENTO
	public static String[] pag_campos() {
		String[] pag = new String[6];
		pag[0] = "Código";
		pag[1] = "Data Pgto";
		pag[2] = "Cód. Venda";
		pag[3] = "Valor Pago";
		pag[4] = "Desconto";
		pag[5] = "Status";
		return pag;
	}

//*************************************************************************************
	
	// TAMANHO DOS CAMPOS TABELA PAGAMENTO
	public static int[] pag_tam() {
		int[] pag = new int[6];
		pag[0] = 11; // "Código";
		pag[1] = 10; // "Data Pgto";
		pag[2] = 11; // "Cód. Venda";
		pag[3] = 14; // "Valor Pago";
		pag[4] = 14; // "Desconto";
		pag[5] = 1;  //  "Status";
				
		String[] rotulo = pag_campos();
				
		for (int ct=0;ct<rotulo.length;ct++){
			if (rotulo[ct].length() > pag[ct]) {
				pag[ct] = rotulo[ct].length();
			}
			
		}
				
	return pag;
}
	
//*************************************************************************************
// TAMANHO DOS CAMPOS VIEW pag - relatorio pagamento cliente peridodo
		public static int[] pagCli_tam() {
			int[] pag = new int[6];
			pag[0] = 11; // "Código cliente";
			pag[1] = 10; // "Data Pgto";
			pag[2] = 11; // "Cód. Venda";
			pag[3] = 14; // "Valor Pago";
			pag[4] = 14; // "Total da venda";
			pag[5] = 1;  //  "Status";
					
			String[] rotulo = pagCli_campos();
					
			for (int ct=0;ct<rotulo.length;ct++){
				if (rotulo[ct].length() > pag[ct]) {
					pag[ct] = rotulo[ct].length();
				}
				
			}
					
		return pag;
	}	
// ====================================================================================
	
	// RÓTULOS DA TABELA PAGAMENTO (QUANDO FOR IMPRIMIR RELATÓRIO PAGAMENTO > CLIENTE + PERIODO)
	public static String[] pagCli_campos() {
		String[] pag = new String[6];
		pag[0] = "Código do Cliente";
		pag[1] = "Data Pgto";
		pag[2] = "Cód. Venda";
		pag[3] = "Valor Pago";
		pag[4] = "Total da Venda";
		pag[5] = "Status Pag.";
		return pag;
	}	
// ====================================================================================	
}
