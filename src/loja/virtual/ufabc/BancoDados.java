package loja.virtual.ufabc;

import java.io.Reader;
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
		 conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/v4-dev-bkpmfj", "postgres","www.");
			System.out.println("Conectado!");
	}
	
	public boolean buscaHumano(Long cpf) throws SQLException {
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
	
	public void salvaHumano(Long cpf,String nome,Long rg,String email,Long telefone,String endereco,String bairro) throws SQLException {
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
	
	public void salvaHumanoVeiculo(Long cpf,String nome,Long rg,String email,Long telefone,String endereco,String bairro, String modeloVeiculo, String placaVeiculo) throws SQLException {
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
	
	public boolean buscaRestaurante(Long cnpj) throws SQLException {
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
			refeicaoId = Integer.valueOf(rs.getInt(1));
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
		st.execute("insert into horario_unidade(horario_id, endereco)\n"+
		"values('"+horarioId+"','"+endereco+"')");
		st.close();
	}
	
	
	public ResultSet nomeEntregadorCompra(Long cnpj) throws SQLException {
		ResultSet nomeEntregador = null;
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select h.nome from compra as c\n"+
		"JOIN humano as h ON h.cpf = c.cpf_entregador\n"+
		"WHERE c.cnpj_restaurante="+cnpj);
		
		return nomeEntregador;
		
	}
	
	public void buscaEntrega(Long cpf) throws SQLException {
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select h.nome, h.endereco_humano, r.descricao, r.preco from compra as c\n"+
		"JOIN refeicao as r on r.refeicao_id = c.refeicao_id\n"+
		"JOIN humano as h on h.cpf = c.cpf_cliente\n"+
		"WHERE c.cpf_entregador="+cpf);
		System.out.println("Nome do Cliente | Endereço | Prato | Preço ");
		while(rs.next()) {
			 System.out.println(rs.getString(1)+" | "+rs.getString(2)+" | "+rs.getString(3)+" | "+rs.getFloat(4));
				
		}
		rs.close();
		st.close();
	}
	
	
	public void restauranteConsultaVendaAtiva(Long cnpj) throws SQLException {
		Statement st = conn.createStatement();	
		//from tb compra refeicao_id cpf_cliente cpf_entregador
		//MEIO PAGAMENTO SERÁ MELHORIA
		ResultSet rs = st.executeQuery("select h.nome,h.endereco_humano,r.descricao,r.preco from compra as c\n"+
		"JOIN humano as h ON h.cpf = c.cpf_cliente\n"+
		"JOIN refeicao as r ON c.refeicao_id=r.refeicao_id\n"+
		"where cnpj_restaurante="+cnpj);
		 System.out.println("Nome do Cliente | Endereço | Prato | Preço ");
		 //ResultSet rs2 = nomeEntregadorCompra(cnpj);
		 while (rs.next()) {
			 //rs2.next();
			 System.out.println(rs.getString(1)+" | "+rs.getString(2)+" | "+rs.getString(3)+" | "+rs.getFloat(4));
			// System.out.println(rs.getString(1)+" | "+rs.getString(2)+" | "+rs2.getInt(1));
				
		 }
		 rs.close();
		 st.close();
		 
	
	}
	
	
	public void restauranteCadastraRefeicao(Long cnpj, String descricao, Float preco, Integer disponibilidade) throws SQLException {
		Integer refeicaoId = null;
		String endereco = null;
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
	
	public void buscaComida() throws SQLException {	
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select distinct tipo from tipo_comida");
		while(rs.next()) {
			System.out.println(rs.getString(1));	
		}
		rs.close();
		st.close();
	}
	
	public void buscaRestaurantePorComidaERegiao(String tipoComida, Long cpf) throws SQLException {
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select rs.nome_fantasia from restaurante_sede as rs\n"+
		"JOIN sede_unidade as su ON rs.cnpj = su.cnpj\n"+
		"JOIN regiao_unidade as ru ON ru.endereco = su.endereco\n"+
		"JOIN tipo_unidade as tu ON tu.endereco = ru.endereco\n"+
		"JOIN humano as h ON ru.bairro = h.bairro_humano\n"+
		"WHERE h.cpf="+cpf+" and tu.tipo ='"+tipoComida+"'");
		while(rs.next()) {
			System.out.println(rs.getString(1));
		}
		
		
	}

	
	public void salvaEncomendaDinheiroTroco(Long cpf, String restaurante, Integer refeicao, float dinheiroValor) throws SQLException {
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select preco from refeicao where refeicao_id="+refeicao);
		rs.next();
		float precoBanco = (rs.getFloat(1));
		float troco = dinheiroValor - precoBanco;
		rs.close();
		ResultSet rs2 = st.executeQuery("select cpf,placa from veiculo");
		rs2.next();
		Long cpfEntregador = Long.valueOf(rs2.getLong(1));
		String placa = rs2.getString(2);
		rs2.close();
		ResultSet rs3 = st.executeQuery("select cnpj from restaurante_sede where nome_fantasia like '"+restaurante+"'");
		rs3.next();
		Long cnpj = Long.valueOf(rs3.getLong(1));
		rs3.close();
		Integer meioPagamento = null;
		ResultSet rs4 = st.executeQuery("select max(meiopagamento_id) from meio_pagamento");
		if(rs4.next()) {
			meioPagamento = Integer.valueOf(rs4.getInt(1) + 1);
			
		}
		else {
			meioPagamento = Integer.valueOf(1);
		}
		rs4.close();
		st.execute("insert into meio_pagamento(meiopagamento_id)\n"+
		"values("+meioPagamento+")");
		st.execute("insert into dinheiro(troco, meiopagamento_id)\n"+
		"values("+troco+","+meioPagamento+")");
		st.execute("insert into compra(refeicao_id,cpf_cliente,cpf_entregador,meiopagamento_id,placa,tempo_espera,cnpj_restaurante)\n"+
		"values("+refeicao+", "+cpf+","+cpfEntregador+","+meioPagamento+",'"+placa+"',"+30+","+cnpj+")");
		System.out.println("Compra cadastrada com sucesso! O tempo estimado para a entrega é de "+30+"minutos");

	}
	
	public void salvaEncomendaDinheiro(Long cpf, String restaurante, Integer refeicao) throws SQLException {
		Statement st = conn.createStatement();
		ResultSet rs2 = st.executeQuery("select cpf,placa from veiculo");
		rs2.next();
		Long cpfEntregador = Long.valueOf(rs2.getLong(1));
		String placa = rs2.getString(2);
		rs2.close();
		ResultSet rs3 = st.executeQuery("select cnpj from restaurante_sede where nome_fantasia like '"+restaurante+"'");
		rs3.next();
		Long cnpj = Long.valueOf(rs3.getLong(1));
		rs3.close();
		Integer meioPagamento = null;
		ResultSet rs4 = st.executeQuery("select max(meiopagamento_id) from meio_pagamento");
		if(rs4.next()) {
			meioPagamento = Integer.valueOf(rs4.getInt(1) + 1);
			
		}
		else {
			meioPagamento = Integer.valueOf(1);
		}
		rs4.close();
		st.execute("insert into meio_pagamento(meiopagamento_id)\n"+
		"values("+meioPagamento+")");
		st.execute("insert into compra(refeicao_id,cpf_cliente,cpf_entregador,meiopagamento_id,placa,tempo_espera,cnpj_restaurante)\n"+
		"values("+refeicao+", "+cpf+","+cpfEntregador+","+meioPagamento+",'"+placa+"',"+30+","+cnpj+")");
		System.out.println("Compra cadastrada com sucesso! O tempo estimado para a entrega é de "+30+"minutos");

	}
	
	public void salvaEncomendaCartao(Long cpf, String restaurante, Integer refeicao, Long numeroCartao, String nomeTitular, Long cpfTitular, LocalDate validadeDate) throws SQLException {
		Statement st = conn.createStatement();
		ResultSet rs2 = st.executeQuery("select cpf,placa from veiculo");
		rs2.next();
		Long cpfEntregador = Long.valueOf(rs2.getLong(1));
		String placa = rs2.getString(2);
		rs2.close();
		ResultSet rs3 = st.executeQuery("select cnpj from restaurante_sede where nome_fantasia like '"+restaurante+"'");
		rs3.next();
		Long cnpj = Long.valueOf(rs3.getLong(1));
		rs3.close();
		Integer meioPagamento = null;
		ResultSet rs4 = st.executeQuery("select max(meiopagamento_id) from meio_pagamento");
		if(rs4.next()) {
			meioPagamento = Integer.valueOf(rs4.getInt(1) + 1);
			
		}
		else {
			meioPagamento = Integer.valueOf(1);
		}
		rs4.close();
		st.execute("insert into meio_pagamento(meiopagamento_id)\n"+
		"values("+meioPagamento+")");
		st.execute("insert into cartao(numero_cartao,cpf,meiopagamento_id,validade,nome_titular)\n"+
		"values("+numeroCartao+","+cpf+","+meioPagamento+",'"+validadeDate+"','"+nomeTitular+"')");
		st.execute("insert into compra(refeicao_id,cpf_cliente,cpf_entregador,meiopagamento_id,placa,tempo_espera,cnpj_restaurante)\n"+
		"values("+refeicao+", "+cpf+","+cpfEntregador+","+meioPagamento+",'"+placa+"',"+30+","+cnpj+")");
		System.out.println("Compra cadastrada com sucesso! O tempo estimado para a entrega é de "+30+"minutos");

	}
	
	public void buscaRefeicaoPorRestaurante(String restaurante) throws SQLException {
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select r.refeicao_id, r.descricao, r.preco from restaurante_sede as rs\n"+
		"JOIN sede_unidade as su ON rs.cnpj = su.cnpj\n"+
		"JOIN refeicao_unidade as ru ON ru.endereco = su.endereco\n"+
		"JOIN refeicao as r ON r.refeicao_id = ru.refeicao_id\n"+
		"where rs.nome_fantasia like '"+restaurante+"'");
		 ResultSetMetaData rsmd = rs.getMetaData();
		 String refeicaoid  = rsmd.getColumnName(1);
		 String descricao = rsmd.getColumnName(2);
		 String preco = rsmd.getColumnName(3);
		 System.out.println(refeicaoid+" | "+descricao+" | "+preco);
		while(rs.next()) {
			System.out.println(rs.getString(1)+" | "+rs.getString(2)+" | "+rs.getString(3));
		}
	}
	
	
}
