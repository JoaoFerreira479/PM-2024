package programarcomputadoresrepeticao;

import java.util.Scanner;

class Tabuada {
	public static int lerNumeroValido(Scanner scanner) {
		int numero;
		while (true) {
			System.out.print("Digite um número entre 1 e 9: ");
			if (scanner.hasNextInt()) {
				numero = scanner.nextInt();
				if (numero >= 1 && numero <= 9) {
					break;
				} else {
					System.out.println("Erro: O número deve estar entre 1 e 9.");
				}
			} else {
				System.out.println("Erro: Entrada inválida. Digite um número inteiro.");
				scanner.next(); 
			}
		}
		return numero;
	}

	public static void exibirTabuada(int numero) {
		System.out.println("Tabuada do número " + numero + ":");
		for (int i = 1; i <= 10; i++) {
			System.out.println(numero + " x " + i + " = " + (numero * i));
		}
	}

	public static boolean perguntarSeDesejaContinuar(Scanner scanner) {
		System.out.print("Deseja ver outra tabuada? (S/N): ");
		String resposta = scanner.next();
		return resposta.equalsIgnoreCase("S");
	}
}

public class ProgramaTabuada {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		try {
			boolean continuar = true;
			while (continuar) {
				int numero = Tabuada.lerNumeroValido(scanner);

				Tabuada.exibirTabuada(numero);

				continuar = Tabuada.perguntarSeDesejaContinuar(scanner);
			}
			System.out.println("Programa encerrado. Obrigado!");
		} finally {
			scanner.close();
		}
	}
}
