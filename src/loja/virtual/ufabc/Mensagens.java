package loja.virtual.ufabc;

public class Mensagens {
	
	public static void main(String[] args) {
		
	}
	
	public static void opcao() {
		System.out.println("\nSelecione a op��o desejada: ");
		
	}
	
	public static void mensagemInicial() {
		System.out.println("Bem vindo � loja virtual de refei��es - UFABC");
		System.out.println("ATEN��O\nPrimeiro deve haver o cadastro do restaurante/entregador + cadastro de refei��o para s� ent�o o cliente fazer o pedido\n");
		System.out.println("Qual � o tipo de acesso?");
		System.out.println("1: Cliente/Entregador (pessoa f�sica)");
		System.out.println("2: Restaurante (pessoa jur�dica)");
		System.out.println("0: Sair do sistema");
	}
	
	public static void mensagemJaCadastrado() {
		System.out.println("Voc� j� tem cadastro?");
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
		System.out.println("2: Cart�o de cr�dito");
	}
	
	public static void troco() {
		System.out.println("1: Sim");
		System.out.println("2: N�o");
	}
	
	public static void opcoesRestaurante() {
		System.out.println("1: Consultar vendas");
		System.out.println("2: Cadastrar novo prato");
	}
	

}
