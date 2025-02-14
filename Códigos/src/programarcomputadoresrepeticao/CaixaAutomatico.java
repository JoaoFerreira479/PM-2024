package programarcomputadoresrepeticao;

import java.util.Scanner;

public class CaixaAutomatico {
	private static final int NOTA_50 = 50;
	private static final int NOTA_10 = 10;
	private static final int MULTIPLO_NOTA = 10;
	private static int notasDe10 = 0;
	private static int notasDe50 = 0;
	private static int totalRetiradas = 0;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int opcao;

		do {
			exibirMenu();
			opcao = lerEntradaInt(scanner);

			switch (opcao) {
			case 1:
				realizarRetirada(scanner);
				break;
			case 2:
				adicionarNotas(scanner);
				break;
			case 3:
				apresentarRelatorio();
				break;
			case 0:
				System.out.println("Encerrando o programa. Obrigado!");
				break;
			default:
				System.out.println("Opção inválida. Por favor, tente novamente.");
			}
		} while (opcao != 0);

		scanner.close();
	}

	private static void exibirMenu() {
		System.out.println("\n=== Caixa Automático ===");
		System.out.println("1 - Realizar retirada");
		System.out.println("2 - Adicionar notas ao caixa");
		System.out.println("3 - Apresentar relatório");
		System.out.println("0 - Sair");
		System.out.print("Escolha uma opção: ");
	}

	private static int lerEntradaInt(Scanner scanner) {
		while (!scanner.hasNextInt()) {
			System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
			scanner.next(); 
		}
		return scanner.nextInt();
	}

	private static void realizarRetirada(Scanner scanner) {
		System.out.print("Digite o valor da retirada: ");
		int valor = lerEntradaInt(scanner);

		if (valor % MULTIPLO_NOTA != 0) {
			System.out.println("Erro: O valor deve ser múltiplo de " + MULTIPLO_NOTA + ".");
			return;
		}

		int quantidade50 = Math.min(valor / NOTA_50, notasDe50);
		int restante = valor - (quantidade50 * NOTA_50);
		int quantidade10 = Math.min(restante / NOTA_10, notasDe10);

		if (quantidade50 * NOTA_50 + quantidade10 * NOTA_10 < valor) {
			System.out.println("Erro: Não há notas suficientes para realizar a retirada.");
			return;
		}

		notasDe50 -= quantidade50;
		notasDe10 -= quantidade10;
		totalRetiradas += valor;

		System.out.println("Retirada realizada com sucesso!");
		System.out.printf("Notas de 50: %d%n", quantidade50);
		System.out.printf("Notas de 10: %d%n", quantidade10);
	}

	private static void adicionarNotas(Scanner scanner) {
		System.out.print("Digite a quantidade de notas de 50 a adicionar: ");
		int adiciona50 = lerEntradaInt(scanner);
		System.out.print("Digite a quantidade de notas de 10 a adicionar: ");
		int adiciona10 = lerEntradaInt(scanner);

		if (adiciona50 < 0 || adiciona10 < 0) {
			System.out.println("Erro: A quantidade de notas não pode ser negativa.");
			return;
		}

		notasDe50 += adiciona50;
		notasDe10 += adiciona10;

		System.out.println("Notas adicionadas com sucesso!");
	}

	private static void apresentarRelatorio() {
		int totalDisponivel = calcularValorTotalDisponivel();

		System.out.println("\n=== Relatório do Caixa ===");
		System.out.printf("Notas de 50 disponíveis: %d%n", notasDe50);
		System.out.printf("Notas de 10 disponíveis: %d%n", notasDe10);
		System.out.printf("Valor total disponível: R$ %d%n", totalDisponivel);
		System.out.printf("Valor total de retiradas efetuadas: R$ %d%n", totalRetiradas);
	}

	private static int calcularValorTotalDisponivel() {
		return (notasDe50 * NOTA_50) + (notasDe10 * NOTA_10);
	}
}
