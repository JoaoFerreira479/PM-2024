package programarcomputadoresalternativasdecisao;

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
}

public class ProgramaJogoDeFichas {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		try {
			System.out.print("Digite a cor da primeira ficha (Branca ou Preta): ");
			CorFicha primeiraFicha = CorFicha.fromString(scanner.nextLine());

			System.out.print("Digite a cor da segunda ficha (Branca ou Preta): ");
			CorFicha segundaFicha = CorFicha.fromString(scanner.nextLine());

			double rateio = JogoDeFichas.calcularRateio(primeiraFicha, segundaFicha);

			System.out.println("O rateio do jogo é: " + rateio);

		} catch (IllegalArgumentException e) {
			System.out.println("Erro: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Erro inesperado. Por favor, tente novamente.");
		} finally {
			scanner.close();
		}
	}
}
