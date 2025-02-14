package programarcomputadoresrepeticao;

import java.util.Scanner;

public class PesquisaNotas {

	private static final int TOTAL_NOTAS = 10;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		double[] notas = new double[TOTAL_NOTAS];
		boolean continuar = true;

		do {
			exibirMenu();
			int opcao = validarOpcao(scanner);

			switch (opcao) {
			case 1:
				lerNotas(scanner, notas);
				break;
			case 2:
				pesquisarNota(scanner, notas);
				break;
			case 3:
				mostrarNotas(notas);
				break;
			case 0:
				continuar = false;
				System.out.println("Encerrando o programa...");
				break;
			default:
				System.out.println("Opção inválida. Tente novamente.");
			}
		} while (continuar);

		scanner.close();
	}

	private static void exibirMenu() {
		System.out.println("\n=== MENU ===");
		System.out.println("1. Ler notas");
		System.out.println("2. Pesquisar nota");
		System.out.println("3. Mostrar notas");
		System.out.println("0. Sair");
		System.out.print("Escolha uma opção: ");
	}

	private static int validarOpcao(Scanner scanner) {
		while (!scanner.hasNextInt()) {
			System.out.println("Entrada inválida. Digite um número inteiro.");
			scanner.next(); // Descarta a entrada inválida
		}
		return scanner.nextInt();
	}

	private static void lerNotas(Scanner scanner, double[] notas) {
		System.out.println("Digite as 10 notas (valores entre 0 e 10):");
		for (int i = 0; i < TOTAL_NOTAS; i++) {
			notas[i] = lerNotaValida(scanner, i + 1);
		}
		System.out.println("Notas registradas com sucesso!");
	}

	private static double lerNotaValida(Scanner scanner, int indice) {
		double nota;
		do {
			System.out.printf("Nota %d: ", indice);
			while (!scanner.hasNextDouble()) {
				System.out.println("Entrada inválida. Digite um número decimal.");
				scanner.next(); // Descarta a entrada inválida
			}
			nota = scanner.nextDouble();
			if (nota < 0 || nota > 10) {
				System.out.println("A nota deve estar entre 0 e 10. Tente novamente.");
			}
		} while (nota < 0 || nota > 10);
		return nota;
	}

	private static void pesquisarNota(Scanner scanner, double[] notas) {
		System.out.print("Digite a nota que deseja pesquisar: ");
		double notaPesquisa = lerNotaValida(scanner, 0);
		boolean encontrada = false;

		for (double nota : notas) {
			if (nota == notaPesquisa) {
				encontrada = true;
				break;
			}
		}

		if (encontrada) {
			System.out.printf("A nota %.1f foi encontrada no vetor.%n", notaPesquisa);
		} else {
			System.out.printf("A nota %.1f não foi encontrada no vetor.%n", notaPesquisa);
		}
	}

	private static void mostrarNotas(double[] notas) {
		System.out.println("\n=== Notas cadastradas ===");
		for (int i = 0; i < TOTAL_NOTAS; i++) {
			System.out.printf("Nota %d: %.1f%n", i + 1, notas[i]);
		}
	}
}
