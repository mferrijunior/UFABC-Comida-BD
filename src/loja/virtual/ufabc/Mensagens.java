package loja.virtual.ufabc;

public class Mensagens {
	
	public static void main(String[] args) {
		
	}
	
	public static void opcao() {
		System.out.println("\nSelecione a opção desejada: ");
		
	}
	
	public static void mensagemInicial() {
		System.out.println("Bem vindo à loja virtual de refeições - UFABC");
		System.out.println("ATENÇÃO\nPrimeiro deve haver o cadastro do restaurante/entregador + cadastro de refeição para só então o cliente fazer o pedido\n");
		System.out.println("Qual é o tipo de acesso?");
		System.out.println("1: Cliente/Entregador (pessoa física)");
		System.out.println("2: Restaurante (pessoa jurídica)");
		System.out.println("0: Sair do sistema");
	}
	
	public static void mensagemJaCadastrado() {
		System.out.println("Você já tem cadastro?");
	}
	
	public static void tipoComida() {
		System.out.println("1: Carne vermelha");
		System.out.println("2: Vegetariana");
		System.out.println("3: Pastel");
		System.out.println("4: Lanche");
		System.out.println("5: Japonesa");
		System.out.println("6: Vegana");
		System.out.println("7: Italiana");
	}
	
	public static void clienteEntregador() {
		System.out.println("1: Cliente");
		System.out.println("2: Entregador");
	}
	
	public static void meioPagamento() {
		System.out.println("1: Dinheiro");
		System.out.println("2: Cartão de crédito");
	}
	
	public static void troco() {
		System.out.println("1: Sim");
		System.out.println("2: Não");
	}
	
	public static void opcoesRestaurante() {
		System.out.println("1: Consultar vendas");
		System.out.println("2: Cadastrar novo prato");
	}
	

}
