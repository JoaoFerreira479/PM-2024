package programarcomputadoresrepeticao;

import java.util.Scanner;

public class LinhaReta {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		try {
			System.out.println("=== Desenho de Linha Reta ===");

			System.out.print("Digite a inclinação da reta (a): ");
			double inclinacao = lerDouble(scanner);

			System.out.print("Digite a interceptação com o eixo y (b): ");
			double interceptacao = lerDouble(scanner);

			System.out.print("Digite o valor inicial de x: ");
			int xInicial = lerInt(scanner);

			System.out.print("Digite o valor final de x: ");
			int xFinal = lerInt(scanner);

			if (xFinal < xInicial) {
				System.out.println("Erro: O valor final de x deve ser maior ou igual ao valor inicial.");
				return;
			}

			desenharLinha(inclinacao, interceptacao, xInicial, xFinal);

		} catch (IllegalArgumentException e) {
			System.out.println("Erro: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Erro inesperado. Por favor, tente novamente.");
		} finally {
			scanner.close();
		}
	}

	private static void desenharLinha(double inclinacao, double interceptacao, int xInicial, int xFinal) {
		System.out.println("\nPontos da linha reta:");
		System.out.println("x\ty");
		System.out.println("-------------");

		for (int x = xInicial; x <= xFinal; x++) {
			double y = calcularY(inclinacao, interceptacao, x);
			System.out.printf("%d\t%.2f%n", x, y);
		}
	}

	private static double calcularY(double inclinacao, double interceptacao, int x) {
		return inclinacao * x + interceptacao;
	}

	private static int lerInt(Scanner scanner) {
		while (!scanner.hasNextInt()) {
			System.out.print("Entrada inválida. Digite um número inteiro: ");
			scanner.next();
		}
		return scanner.nextInt();
	}

	private static double lerDouble(Scanner scanner) {
		while (!scanner.hasNextDouble()) {
			System.out.print("Entrada inválida. Digite um número decimal: ");
			scanner.next();
		}
		return scanner.nextDouble();
	}
}
