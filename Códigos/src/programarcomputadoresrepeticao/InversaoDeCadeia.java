package programarcomputadoresrepeticao;

import java.util.Scanner;

public class InversaoDeCadeia {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("=== Programa de Inversão de Cadeia ===");
		System.out.println("Digite uma cadeia de caracteres para invertê-la.");
		System.out.println("Pressione Enter sem digitar nada para encerrar o programa.");

		while (true) {
			System.out.print("Digite uma cadeia de caracteres: ");
			String entrada = scanner.nextLine();

			if (entrada == null || entrada.isEmpty()) {
				System.out.println("Programa encerrado.");
				break;
			}

			String cadeiaInvertida = inverterCadeia(entrada);

			System.out.println("Cadeia invertida: " + cadeiaInvertida);
			System.out.println();
		}

		scanner.close();
	}

	public static String inverterCadeia(String cadeia) {
		return new StringBuilder(cadeia).reverse().toString();
	}
}
