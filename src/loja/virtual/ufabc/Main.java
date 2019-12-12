package loja.virtual.ufabc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class Main {
	public static void main (String[] args) throws ParseException, SQLException {
		
		Scanner s = new Scanner (System.in);
		String opcao,nome,email,bairro,endereco,nomeFantasia,tipoComida = null,restaurante, nomeEntregador, modeloVeiculo,
				placaVeiculo,entregadorSimNao,clienteEntregador,meioPagamento,trocoSimNao,validadeCartaoString,nomeTitular,
				horarioInicial,horarioFinal;
		Long cpf,cnpj,rg,telefone,numeroCartao,cpfTitular;
		Integer disponibilidade = null,refeicao;
		Float trocoValor,preco;
		float dinheiroValor;
		Double validadeCartaoDouble;
		Mensagens msgs = new Mensagens();
		BancoDados bd = new BancoDados();
		//bd.buscaHorarioId();
		msgs.mensagemInicial();
		msgs.opcao();
		opcao = s.nextLine().trim();
		
		while (!opcao.equals("0")) {
			if (opcao.equals("1")) {
				
				System.out.println("Digite o número do CPF, somente números: ");
			    cpf = Long.valueOf(s.nextLine().trim());
			    boolean resposta = bd.buscaHumano(cpf);
			    if(resposta == false) {
					System.out.println("Primeiro acesso detectado! Preencha os dados para efetuar o cadastro.");
			    	System.out.println("Escreva seu nome completo: ");
			    	nome = s.nextLine().trim();
			    	if(nome != null) {
			    		System.out.println("Digite o número do seu RG, somente números: ");
			    		rg = Long.valueOf(s.nextLine().trim());
			    		if(rg != null) {
			    			System.out.println("Informe seu e-mail: ");
			    			email = s.nextLine().trim();
			    			if(email != null) {
			    				System.out.println("Informe seu telefone: ");
			    				telefone = Long.valueOf(s.nextLine().trim());
			    				if(telefone != null) {
			    					System.out.println("Informe seu logradouro e número: ");
			    					endereco = s.nextLine().trim();
			    					if(endereco != null) {
			    						System.out.println("Informe seu bairro: ");
			    						bairro = s.nextLine().trim();
			    								    					
			    						if(bairro != null) {
			    							System.out.println("Você é um entregador? Digite sim ou não: ");
			    							entregadorSimNao = s.nextLine().trim();
			    							if(entregadorSimNao.equals("não") || entregadorSimNao.equals("nao")) {
			    								System.out.println("Cadastro realizado com sucesso!");
			    								bd.salvaHumano(cpf,nome,rg,email,telefone,endereco,bairro);				    										    								
			    							}
			    							else if(entregadorSimNao.equals("sim")) {
			    								System.out.println("Informe o modelo do veículo: ");
			    								modeloVeiculo = s.nextLine().trim();
			    								if(modeloVeiculo != null) {
			    									System.out.println("Informe a placa do veículo");
			    									placaVeiculo = s.nextLine().trim();
			    									if(placaVeiculo != null) {
			    										bd.salvaHumanoVeiculo(cpf,nome,rg,email,telefone,endereco,bairro, modeloVeiculo,placaVeiculo);
			    										System.out.println("Cadastro realizado com sucesso!");
			    									}
			    								}
			    							}
			    						}
			    					}
			    				}
			    			}
			    		}
			    	}
			    } else {
			    	System.out.println("Cadastro encontrado no sistema! Você está como cliente ou entregador? Selecione a opção ");
			    	msgs.clienteEntregador();
			    	clienteEntregador = s.nextLine().trim();
				    if(clienteEntregador.equals("1")) {
				    	System.out.println("\nQual é o tipo de comida que deseja, das listadas abaixo? Escreva por extenso");
				    	bd.buscaComida();
				    	tipoComida = s.nextLine().trim();
				    	if (tipoComida != null) {
				    		System.out.println("\nEstes são os restaurantes filtrados pelo tipo de comida que atendem sua região, digite o desejado: ");
				    		bd.buscaRestaurantePorComidaERegiao(tipoComida,cpf);
					    	restaurante = s.nextLine().trim();
					    	if(restaurante != null) {
					    		//estou aq
					    		System.out.println("Essas são as refeições disponíveis e os preços de cada uma, oferecidos pelo restaurante escolhido. Escolha uma pelo número: ");
					    		bd.buscaRefeicaoPorRestaurante(restaurante);
						    	refeicao = Integer.valueOf(s.nextLine().trim());
						    	if(refeicao != null) {
						    		System.out.println("Informe o meio de pagamento: ");
						    		msgs.meioPagamento();
						    		meioPagamento = s.nextLine().trim();
						    		if(meioPagamento.equals("1")) {
						    			System.out.println("Vai precisar de troco?");
						    			msgs.troco();
						    			trocoSimNao = s.nextLine().trim();
						    			if(trocoSimNao.equals("1")) {
						    				System.out.println("Quanto em dinheiro você vai dar?");
						    				dinheiroValor = Float.valueOf((s.nextLine().trim())).floatValue();
						    				
						    				bd.salvaEncomendaDinheiroTroco(cpf,restaurante,refeicao,dinheiroValor);
						    				break;
						    				
						    			}
						    			else if(trocoSimNao.contentEquals("2")) {
						    				bd.salvaEncomendaDinheiro(cpf, restaurante, refeicao);
						    				break;
						    			}
						    		}
					    			else if(meioPagamento.equals("2")) {
					    				System.out.println("Cadastre o número do cartão de crédito: ");
					    				numeroCartao = Long.valueOf(s.nextLine().trim());
					    				if(numeroCartao != null) {
					    					System.out.println("Informe o nome do titular do cartão: ");
					    					nomeTitular = s.nextLine().trim();
					    					if(nomeTitular != null) {
					    						System.out.println("Informe o cpf do titular do cartão: ");
					    						cpfTitular = Long.valueOf(s.nextLine().trim());
					    						if(cpfTitular != null) {
					    							System.out.println("Informe a validade do cartão(MM/aaaa): ");
					    							validadeCartaoString = s.nextLine().trim();
					    							validadeCartaoString = "30/"+validadeCartaoString;
					    							 LocalDate validadeDate = LocalDate.parse(validadeCartaoString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
					    						        bd.salvaEncomendaCartao(cpf,restaurante,refeicao,numeroCartao,nomeTitular,cpfTitular, validadeDate);
					    						        break;
					    						}
					    					}
					    				}
					    				
					    			}
						    		}
						    		
						    	}
					    	}
					    		
				    	}else if (clienteEntregador.equals("2")) {
				    		System.out.println("Esses são os dados necessários para a entrega: ");
				    		bd.buscaEntrega(cpf);
				    		break;
				    		
				    	}
				    	

				    }			    						    	
			    }
			
			else if (opcao.equals("2")) {
				System.out.println("Digite o número do CNPJ, somente números: ");
				cnpj = Long.valueOf(s.nextLine().trim());
			    boolean resposta = bd.buscaRestaurante(cnpj);
			    
			    if(resposta == false) {
			    	System.out.println("Primeiro acesso detectado! Preencha os dados para efetuar o cadastro.");
			    	System.out.println("Digite o nome oficial do restaurante: ");
			    	nome = s.nextLine().trim();
			    	if(nome != null) {
			    		System.out.println("Digite o nome fantasia do restaurante: ");
			    		nomeFantasia = s.nextLine().trim();
			    		if(nomeFantasia != null) {
			    			System.out.println("Informe o logradouro e número do restaurante: ");
			    			endereco = s.nextLine().trim();
			    			if(endereco != null) {
			    				System.out.println("Informe a região/bairro do restaurante: ");
			    				bairro = s.nextLine().trim();
			    				if(bairro != null) {
			    					System.out.println("Informe o telefone comercial: ");
			    					telefone = Long.valueOf(s.nextLine().trim());
			    					if(telefone != null) {
			    						System.out.println("Selecione o tipo de comida oferecida aos clientes: ");
			    						msgs.tipoComida();
			    						tipoComida = s.nextLine();
			    						if(tipoComida.equals("1")) {
			    							tipoComida = "carne vermelha";
			    						}
			    						else if(tipoComida.equals("2")) {
			    							tipoComida = "vegetariana";
			    						}
			    						else if(tipoComida.equals("3")) {
			    							tipoComida = "pastel";
			    						}
			    						else if(tipoComida.equals("4")) {
			    							tipoComida = "lanche";
			    						}
			    						else if(tipoComida.equals("5")) {
			    							tipoComida = "japonesa";
			    						}
			    						else if(tipoComida.equals("6")) {
			    							tipoComida = "vegana";
			    						}
			    						else if(tipoComida.equals("7")) {
			    							tipoComida = "italiana";
			    						}
			    						
			    						if(tipoComida != null) {			    							
			    							System.out.println("Informe o início do horário de atendimento(HH:mm) ");
			    							horarioInicial = s.nextLine().trim();
			    							if(horarioInicial != null) {
			    								System.out.println("Informe o fim do horário de atendimento: ");
			    								horarioFinal = s.nextLine().trim();
			    								if(horarioFinal != null) {
			    									    LocalTime horarioInicialLocalTime = LocalTime.parse(horarioInicial, DateTimeFormatter.ofPattern("HH:mm"));
			    									    LocalTime horarioFinalLocalTime = LocalTime.parse(horarioFinal, DateTimeFormatter.ofPattern("HH:mm"));
			    									  bd.salvaRestaurante(cnpj,nome,nomeFantasia,endereco,bairro,telefone,tipoComida, horarioInicialLocalTime,horarioFinalLocalTime);			    					
							    						
			    								}	
			    							}
			    								  
			    						}
				    				}
			    				}
			    			}
			    		}
			    	}
			     }else {
			    	 System.out.println("Restaurante encontrado no sistema!\n O quê você deseja fazer?");
			    	 msgs.opcoesRestaurante();
			    	 String opcaoRestaurante = s.nextLine().trim();
			    	 if (opcaoRestaurante.equals("1")) {
			    		 bd.restauranteConsultaVendaAtiva(cnpj);
			    		 break;
			    	 }
			    	 else if(opcaoRestaurante.equals("2")) {
			    		 System.out.println("Digite o prato que deseja cadastrar: ");
			    			 String descricao = s.nextLine().trim();
			    			 if(descricao != null) {
			    				 System.out.println("Escreva o preço do prato(use o ponto como separador de decimal: ");
			    				 Float precoRefeicao = Float.valueOf(s.nextLine().trim());
			    				 
			    				 if(precoRefeicao != null) {
			    					 System.out.println("Esse prato já está disponível à venda aos clientes? ");
			    					 msgs.troco();
			    					 String simNao = s.nextLine().trim();
			    					 if(simNao.equals("1")) {
			    						 disponibilidade = Integer.valueOf(1);
			    					 }
			    					 else if(simNao.equals("2")) {
			    						 disponibilidade = Integer.valueOf(0);
			    					 }
			    					bd.restauranteCadastraRefeicao(cnpj,descricao,precoRefeicao,disponibilidade);	 
					    			System.out.println("Refeição cadastrada com sucesso!");
			    					//(Long cnpj, String refeicao, String descricao, Double preco, int disponibilidade)
					    		
			    				 }
			    			 }
			    		 }
			    	 }
			    
			    	
			   }
			    
				
			else {
				System.out.println("Opção não existente, sistema sendo desligado!");
				break;
			}
			
			System.out.println("\n------REINICIANDO SISTEMA---------\n");
			msgs.mensagemInicial();
			msgs.opcao();
			opcao = s.nextLine().trim();
		}
		
	}
	

}


