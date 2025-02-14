package programarcomputadoresvariaveisdados;

import java.util.Scanner;

class ManipuladorCadeia {
	public static String[] dividirCadeia(String cadeia) {
		int pontoMedio = cadeia.length() / 2;
		String primeiraMetade = cadeia.substring(0, pontoMedio);
		String segundaMetade = cadeia.substring(pontoMedio);
		return new String[] { primeiraMetade, segundaMetade };
	}

	public static boolean validarCadeia(String cadeia) {
		return cadeia != null && cadeia.length() <= 255;
	}
}

public class MetadesDeCadeia {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		String cadeia;
		while (true) {
			System.out.print("Digite uma cadeia de caracteres (até 255 caracteres): ");
			cadeia = scanner.nextLine();

			if (ManipuladorCadeia.validarCadeia(cadeia)) {
				break;
			} else {
				System.out.println("Erro: A cadeia deve ter no máximo 255 caracteres e não pode ser vazia.");
			}
		}

		String[] metades = ManipuladorCadeia.dividirCadeia(cadeia);

		System.out.println("Primeira metade: " + metades[0]);
		System.out.println("Segunda metade: " + metades[1]);

		scanner.close();
	}
}
