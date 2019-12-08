package loja.virtual.ufabc;

import java.sql.Connection;
import java.sql.ResultSet;

public class BancoDadosBAKCUPENTREGADOR {
	Connection con = null;
	
	public BancoDadosBAKCUPENTREGADOR() {
		//Neste construtor, escreva o codigo necess√°rio para
		// conexao com o banco de dados do PostgresSQL
	}
	
	public Integer buscaHumano(long cpf) {
		//Nesse mÈtodo,procura se o CPF existe na bsae de dados - tabela humano
		return null;
	}
	
	public void salvaHumano(long cpf,String nome,Long rg,String email,Long telefone,String endereco,String bairro) {
		//Esse mÈtodo salva o cliente;
		
	}
	
	public ResultSet buscaRestaurante(Long cnpj) {
		ResultSet rs=null;
		//Nesse mÈtodo, procura se o CNPJ existe na base de dados - tabela restaurante_sede;
		return rs;
	}
	
	public ResultSet salvaRestaurante(Long cnpj,String nome,String nomeFantasia,String endereco,String bairro,Long telefone, String tipoComida, String nomeEntregador, String modeloVeiculo, String placaVeiculo) {
		ResultSet rs = null;
		//Nesse mÈtodo, salva o restaurante - tabela restaurante_sede // restaurante_unidade // entregador etc
		return rs;
	}
	
	public void buscaComida() {
		
		//Nesse mÈtodo, busca as todos os tipos de comida pro cliente escolher o tipo de comida que deseja
		System.out.println("Italiana");
		System.out.println("Japonesa");
		System.out.println("Lanches");
		System.out.println("Churrasco");
	
	}
	
	public void buscaRestaurantePorComidaERegiao(String tipoComida) {
		System.out.println("\nEstes s„o os restaurantes que atendem sua regi„o");
		System.out.println("Italy food");
		System.out.println("Japanese House");
		System.out.println("Horti-Frutti");
		
		
	}
	
	public void buscaPratosPorRestaurante(String restaurante) {
		System.out.println("Macarr„o");
		System.out.println("Pizza");
		System.out.println("Esfiha");
		
		
	}
	
	public void buscaMedalhas (String atleta) {
		// Dado o nome do atleta, escreva o c√≥digo que busca
		//   e imprime na tela a lista de medalhas conquistadas 
		//   por esse atleta junto  com as edicoes de jogos 
		//   olimpicos e modalidades em que foram conquistadas.
		
	}

	public void buscaJogos (String atleta) {
		// Dado o nome do atleta, escreva o c√≥digo que busca
		//   e imprime as edicoes de jogos olimpicos disputados
		//   por esse atleta.
	}

	public void buscaModalidades (int ano) {
		// Dado o ano de realizacao dos jogos, escreva o codigo que busca
		//   as modalidades disputadas nos jogos olimpicos
		//   do ano dado como parametro
	}

	public void buscaMedalhistas (int ano, String modalidade) {
		// Dado o ano de realizacao dos jogos e o nome da modalidade, 
		//   escreva o codigo que busca
		//   e imprime na tela os medalhistas (ouro, prata e
		//   bronze) da modalidade dada como par√¢metro disputada
		//   no ano que tamb√©m √© dado como par√¢metro
	}

	public void cadastraNovaEdicaoJogos (int ano, String estacao, String cidade) {
		// Insere uma nova informacao no banco de dados, referente
		//   a uma nova edicao de Jogos Olimpicos. A insercao dessas informacoes
		//   deve ser realizada na tabela "Jogos" do seu banco de dados.
		//   Tenha o cuidado de buscar o "id" correto para nao violar a restricao de
		//   chave primaria da tabela. Sugiro buscar o maior id da tabela e adicionar 1.
	}

}
