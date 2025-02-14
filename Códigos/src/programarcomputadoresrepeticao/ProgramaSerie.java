package programarcomputadoresrepeticao;

import java.util.Scanner;

public class ProgramaSerie {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("=== Cálculo da Série ===");
		System.out.println("Digite um valor inteiro para N (0 para sair):");

		while (true) {
			try {
				System.out.print("\nInforme o valor de N: ");
				int n = lerEntradaInt(scanner);

				if (n == 0) {
					System.out.println("Encerrando o programa. Obrigado!");
					break;
				}

				double resultado = calcularSerie(n);
				System.out.printf("Resultado da série para N = %d: %.2f%n", n, resultado);

			} catch (IllegalArgumentException e) {
				System.out.println("Erro: " + e.getMessage());
			} catch (Exception e) {
				System.out.println("Erro inesperado. Por favor, tente novamente.");
			}
		}

		scanner.close();
	}

	public static double calcularSerie(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException("O valor de N deve ser maior que zero.");
		}

		double soma = 0.0;
		for (int i = 1; i <= n; i++) {
			soma += (double) i / (n - i + 1);
		}
		return soma;
	}

	private static int lerEntradaInt(Scanner scanner) {
		while (!scanner.hasNextInt()) {
			System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
			scanner.next(); 
		}
		return scanner.nextInt();
	}
}
