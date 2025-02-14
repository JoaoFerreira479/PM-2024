package programarcomputadoresrepeticao;

import java.util.Scanner;

enum CorFicha {
	BRANCA, PRETA;

	public static CorFicha fromString(String cor) {
		switch (cor.toLowerCase()) {
		case "branca":
			return BRANCA;
		case "preta":
			return PRETA;
		default:
			throw new IllegalArgumentException("Cores inválidas. Use 'Branca' ou 'Preta'.");
		}
	}
}

class JogoDeFichas {
	private static final double LIMITE_APOSTA = 100.0;

	public static double calcularRateio(CorFicha primeiraFicha, CorFicha segundaFicha) {
		if (primeiraFicha == CorFicha.BRANCA && segundaFicha == CorFicha.BRANCA) {
			return 0.0;
		} else if ((primeiraFicha == CorFicha.BRANCA && segundaFicha == CorFicha.PRETA)
				|| (primeiraFicha == CorFicha.PRETA && segundaFicha == CorFicha.BRANCA)) {
			return 0.5;
		} else if (primeiraFicha == CorFicha.PRETA && segundaFicha == CorFicha.PRETA) {
			return 2.0;
		} else {
			throw new IllegalStateException("Erro na lógica do cálculo.");
		}
	}

	public static void validarAposta(double valorAposta) {
		if (valorAposta < 0 || valorAposta > LIMITE_APOSTA) {
			throw new IllegalArgumentException("Valor da aposta deve estar entre 0 e " + LIMITE_APOSTA + ".");
		}
	}
}

public class JogoDeFichasNovo {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		double acumulado = 0.0;

		try {
			boolean continuar = true;

			while (continuar) {
				System.out.print("Digite a cor da primeira ficha (Branca ou Preta): ");
				CorFicha primeiraFicha = CorFicha.fromString(scanner.nextLine());

				System.out.print("Digite a cor da segunda ficha (Branca ou Preta): ");
				CorFicha segundaFicha = CorFicha.fromString(scanner.nextLine());

				System.out.print("Digite o valor da aposta (limite de R$100): ");
				double valorAposta = scanner.nextDouble();
				scanner.nextLine(); 

				JogoDeFichas.validarAposta(valorAposta);

				double rateio = JogoDeFichas.calcularRateio(primeiraFicha, segundaFicha);
				double ganho = valorAposta * rateio;
				acumulado += ganho;

				System.out.printf("Rateio: %.2f%n", rateio);
				System.out.printf("Ganho nesta rodada: R$%.2f%n", ganho);
				System.out.printf("Total acumulado: R$%.2f%n", acumulado);

				System.out.print("Deseja jogar novamente? (S/N): ");
				continuar = scanner.nextLine().equalsIgnoreCase("S");
			}

			System.out.printf("Valor total acumulado: R$%.2f%n", acumulado);
			System.out.println("Obrigado por jogar!");

		} catch (IllegalArgumentException e) {
			System.out.println("Erro: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Erro inesperado. Verifique sua entrada.");
		} finally {
			scanner.close();
		}
	}
}
