package loja.virtual.ufabc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;


public class BancoDados {
	
	Connection conn = null;
	public BancoDados() throws SQLException{
		 conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/loja-virtual-comida-ufabc", "postgres","130296");
			System.out.println("Conectado!");
	}
	
	public Boolean buscaHumano(long cpf) throws SQLException {
		//Nesse método,procura se o CPF existe na bsae de dados - tabela humano
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select from humano\n"+
		"where cpf ="+cpf);
		if(rs.next()) {
			return true;			
		}		
		rs.close();
		st.close();
		return false;
	}
	
	public void salvaHumano(long cpf,String nome,Long rg,String email,Long telefone,String endereco,String bairro) throws SQLException {
		//insert na TB humano cpf,rg,nome,bairro,endereco
		//insert na TB email cpf(FK_humanocpf), email
		//insert na TB telefone
		//insert na TB telefone_humano (cpf + numero)
		Statement st = conn.createStatement();
		st.execute("insert into humano(cpf,rg,nome,bairro_humano,endereco_humano)\n"+
		"values('"+cpf+"','"+rg+"', '"+nome+"', '"+bairro+"', '"+endereco+"')");
		st.execute("insert into email(email,cpf)\n"+
				"values('"+email+"', '"+cpf+"')");
		st.execute("insert into telefone values ('"+telefone+"')");
		st.execute("insert into telefone_humano(numero,cpf)\n"+
		"values('"+telefone+"', '"+cpf+"')");
		st.close();
		
	}
	
	public void salvaHumanoVeiculo(long cpf,String nome,Long rg,String email,Long telefone,String endereco,String bairro, String modeloVeiculo, String placaVeiculo) throws SQLException {
		//insert na TB humano cpf,rg,nome,bairro,endereco
				//insert na TB email cpf(FK_humanocpf), email
				//insert na TB telefone
				//insert na TB telefone_humano (cpf + numero)
				//insert na TB veiculo
				Statement st = conn.createStatement();
				st.execute("insert into humano(cpf,rg,nome,bairro_humano,endereco_humano)\n"+
				"values('"+cpf+"','"+rg+"', '"+nome+"', '"+bairro+"', '"+endereco+"')");
				st.execute("insert into email(email,cpf)\n"+
						"values('"+email+"', '"+cpf+"')");
				st.execute("insert into telefone values ('"+telefone+"')");
				st.execute("insert into telefone_humano(numero,cpf)\n"+
				"values('"+telefone+"', '"+cpf+"')");
				st.execute("insert into veiculo(placa,modelo,cpf)\n"+
				"values('"+placaVeiculo+"','"+modeloVeiculo+"','"+cpf+"')");
				st.close();
		
	}
	
	public Boolean buscaRestaurante(Long cnpj) throws SQLException {
		//Nesse método, procura se o CNPJ existe na base de dados - tabela restaurante_sede;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery("select from restaurante_sede\n"+
				"where cnpj ="+cnpj);
				if(rs.next()) {
					return true;			
				}		
				rs.close();
				st.close();
				return false;
	}
	
	public Integer buscaHorarioId(String endereco) throws SQLException {
		//Nesse método, procura se o CNPJ existe na base de dados - tabela restaurante_sede;
				Integer horarioId = null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery("select horario_id from horario order by horario_id desc");
				while(rs.next()) {			
				System.out.println(rs.getString(1));	
				horarioId = Integer.valueOf(rs.getString(1));
				}
				rs.close();
				st.close();
				return horarioId;
	}
	
	public Integer buscaRefeicaoId() throws SQLException {
			Integer refeicaoId = null;
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select refeicao_id from refeicao order by refeicao_id desc");
			rs.next();			
			System.out.println(rs.getInt(1));	
			refeicaoId = rs.getInt(1);
			rs.close();
			st.close();
			return refeicaoId;
	}
	
	public String buscaEnderecoPorCnpj(Long cnpj) throws SQLException {
		String endereco = null;
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select endereco from sede_unidade\n"+
		"where cnpj="+cnpj+"");
		while(rs.next()) {			
		System.out.println(rs.getString(1));	
		endereco = rs.getString(1);
		}
		rs.close();
		st.close();
		return endereco;
}

	
	public void salvaRestaurante(Long cnpj,String nome,String nomeFantasia,String endereco,String bairro,Long telefone, String tipoComida, LocalTime horarioInicial, LocalTime horarioFinal) throws SQLException {
		//restaurante_sede cnpj nome_real nome_fantasia
		//restaurante_unidade endereco
		//sede_unidade cnpj endereco
		//regiao bairro
		//regiao_unidade bairro, endereco
		//tipo_comida tipo
		//tipo_unidade tipo, endereco
		//telefone telefone
		//telefone_unidade numero, endereco
		//refeicao refeicao_id(TEM Q SER SERIAL, NAO MEXO), descricao, preco, disponibilidade
		//horario horario_id(serial),comeco,fim,dia=null
		//horario_unidade horario_id (FK q tem q pegar no horario_id), endereco, nome_fantasia
		Integer horarioId = null;
		ResultSet rs = null;
		Statement st = conn.createStatement();
		st.execute("insert into restaurante_sede(cnpj,nome_real,nome_fantasia)\n"+
				"values('"+cnpj+"','"+nome+"', '"+nomeFantasia+"')");
		st.execute("insert into restaurante_unidade(endereco)\n"+
				"values('"+endereco+"')");
		st.execute("insert into sede_unidade(cnpj,endereco)\n"+
				"values('"+cnpj+"','"+endereco+"')");
		st.execute("insert into regiao(bairro)\n"+
				"values('"+bairro+"')");
		st.execute("insert into regiao_unidade(bairro,endereco)\n"+
				"values('"+bairro+"','"+endereco+"')");
		st.execute("insert into tipo_comida(tipo)\n"+
				"values('"+tipoComida+"')");
		st.execute("insert into tipo_unidade(tipo,endereco)\n"+
				"values('"+tipoComida+"','"+endereco+"')");
		st.execute("insert into telefone(telefone)\n"+
				"values('"+telefone+"')");
		st.execute("insert into telefone_unidade(numero,endereco)\n"+
				"values('"+telefone+"','"+endereco+"')");
		st.execute("insert into horario(comeco,fim)\n"+
				"values('"+horarioInicial+"','"+horarioFinal+"')");
		st.close();
		horarioId = buscaHorarioId(endereco);
		st = conn.createStatement();
		st.execute("insert into horario_unidade(horario_id, endereco, nome_fantasia)\n"+
		"values('"+horarioId+"','"+endereco+"','"+nomeFantasia+"')");
		st.close();
	}
	
	public void restauranteConsultaVendaAtiva(Long cnpj) throws SQLException {
		//TERMINAR! - FAZER NUMERO DE VENDAS EFETUADAS
		Statement st = conn.createStatement();	
		ResultSet rs = st.executeQuery("select * from compra \n"+
		"where cnpj_restaurante="+cnpj+")");
		 ResultSetMetaData rsmd = rs.getMetaData();
		 String refeicaoid  = rsmd.getColumnName(1);
		 String cpf_cliente = rsmd.getColumnName(2);
		 String cpf_entregador = rsmd.getColumnName(3);
		 String numero_cartao = rsmd.getColumnName(4);
		 String placa_entregador  = rsmd.getColumnName(5);
		 String tempo_para_enviar = rsmd.getColumnName(6);
		 String cnpj_restaurante = rsmd.getColumnName(7);
		 
	}
	
	public void restauranteCadastraRefeicao(Long cnpj, String refeicao, String descricao, Float preco, int disponibilidade) throws SQLException {
		Integer refeicaoId = null;
		String endereco = null;
		//inserir na tabela refeicao- descricao, preco(float), disponibilidade(numeric 1,0)
		//a tabela refeicao_unidade tem o refeicao_id e o endereco (restaurante_unidade) como FKs - é a tab de relacionamento
		Statement st = conn.createStatement();
		st.execute("insert into refeicao(descricao, preco, disponibilidade)\n"+
		"values('"+descricao+"','"+preco+"','"+disponibilidade+"')");
		st.close();
		refeicaoId = buscaRefeicaoId();
		endereco = buscaEnderecoPorCnpj(cnpj);
		st = conn.createStatement();
		st.execute("insert into refeicao_unidade(refeicao_id, endereco)\n"+
				 "values("+refeicaoId+",'"+endereco+"')");
		
	}
	
	public void buscaComida() {
		
		//Nesse método, busca as todos os tipos de comida pro cliente escolher o tipo de comida que deseja
		System.out.println("Italiana");
		System.out.println("Japonesa");
		System.out.println("Lanches");
		System.out.println("Churrasco");
	
	}
	
	public void buscaRestaurantePorComidaERegiao(String tipoComida, Long cpf) {
		System.out.println("\nItaly food");
		System.out.println("Japanese House");
		System.out.println("Horti-Frutti");
		
		
	}
	
	public void buscaPratosPorRestaurante(String restaurante) {
		System.out.println("Macarrão");
		System.out.println("Pizza");
		System.out.println("Esfiha");
				
	}
	
	public void salvaEncomendaDinheiro(Long cpf, String restaurante, String refeicao) {
		//cadastra encomenda sem cartão de crédito
		//procura na tabela de entregadores, um entregador hipoteticamente disponível;
		//cadastra o tempo disponivel tambem
		//System.out do tempo!
	}
	
	public void salvaEncomendaCartao(Long cpf, String restaurante, String refeicao, Long numeroCartao, String nomeTitular, Long cpfTitular, LocalDate validadeDate) {
		//cadastra encomenda com cartão de crédito
		//cadastra cartao com todos os dados
		//procura na tabela de entregadores, um entregador hipoteticamente disponível;
		//cadastra o tempo disponivel tambem
		//System.out do tempo! (atenção que o JDBC com postgresql suporta LocalDate format)
	}
	
	public void buscaRefeicaoPorRestaurante(String restaurante) {
		//busca todas as refeicoes/pratos disponiveis por restaurante
	}
	
	
}
