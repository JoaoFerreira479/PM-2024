package programarcomputadoresrepeticao;

import java.util.Scanner;
import java.util.Random;

public class Adivinhe {
	private static final int INTERVALO_MIN = 1; 
	private static final int INTERVALO_MAX = 10; 
	private static final int MAX_TENTATIVAS = 3;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.println("Bem-vindo ao jogo 'Adivinhe o Número'!");
			System.out.printf("Eu vou pensar em um número entre %d e %d. Você tem %d tentativas para adivinhar.%n",
					INTERVALO_MIN, INTERVALO_MAX, MAX_TENTATIVAS);

			executarJogo(scanner);
		} finally {
			scanner.close();
		}
	}

	private static void executarJogo(Scanner scanner) {
		int numeroSorteado = gerarNumeroAleatorio(INTERVALO_MIN, INTERVALO_MAX);

		for (int tentativa = 1; tentativa <= MAX_TENTATIVAS; tentativa++) {
			System.out.printf("Tentativa %d de %d: Digite seu palpite: ", tentativa, MAX_TENTATIVAS);
			int chute = lerEntradaInteira(scanner);

			if (chute == numeroSorteado) {
				System.out.printf("Parabéns! Você acertou! O número era %d.%n", numeroSorteado);
				return; 
			} else if (chute < numeroSorteado) {
				System.out.println("O número é maior que o seu palpite.");
			} else {
				System.out.println("O número é menor que o seu palpite.");
			}
		}

		System.out.printf("Você perdeu! O número era %d.%n", numeroSorteado);
	}

	private static int gerarNumeroAleatorio(int min, int max) {
		Random random = new Random();
		return random.nextInt((max - min) + 1) + min;
	}

	private static int lerEntradaInteira(Scanner scanner) {
		while (!scanner.hasNextInt()) {
			System.out.println("Entrada inválida! Por favor, digite um número inteiro.");
			scanner.next();
		}
		return scanner.nextInt();
	}
}
