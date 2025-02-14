package programarcomputadoresvariaveisdados;

import java.util.Scanner;

class Adivinha {
	public int calcularNumeroPensado(int resultado) {
		return resultado - 3; // Fórmula simplificada
	}

	public void exibirInstrucoes() {
		System.out.println("Pense em um número (não informe agora). Vou tentar adivinhar!");
		System.out.println("\nAgora siga os passos abaixo:");
		System.out.println("1. Multiplique o número pensado por 2.");
		System.out.println("2. Some 6 ao resultado.");
		System.out.println("3. Divida o resultado por 2.");
	}
}

public class AdivinhaNumero {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Adivinha adivinha = new Adivinha();

		adivinha.exibirInstrucoes();

		System.out.print("Digite o número final que você obteve: ");
		int resultado = lerEntrada(scanner);

		int numeroPensado = adivinha.calcularNumeroPensado(resultado);

		System.out.printf("Você pensou no número: %d\n", numeroPensado);

		scanner.close();
	}

	private static int lerEntrada(Scanner scanner) {
		while (true) {
			try {
				int valor = Integer.parseInt(scanner.nextLine());
				if (valor < 3) {
					System.out.print("O número final deve ser maior ou igual a 3. Tente novamente: ");
				} else {
					return valor;
				}
			} catch (NumberFormatException e) {
				System.out.print("Entrada inválida! Por favor, insira um número válido: ");
			}
		}
	}
}
