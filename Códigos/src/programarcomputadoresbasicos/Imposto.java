package programarcomputadoresbasicos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Imposto {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		List<FaixaImposto> faixas = new ArrayList<>();
		faixas.add(new FaixaImposto(0, 1200, 0.0)); // Até R$ 1.200,00 (isento)
		faixas.add(new FaixaImposto(1201, 5000, 0.10)); // De R$ 1.201,00 a R$ 5.000,00 (10%)
		faixas.add(new FaixaImposto(5001, 10000, 0.15)); // De R$ 5.001,00 a R$ 10.000,00 (15%)
		faixas.add(new FaixaImposto(10001, Double.MAX_VALUE, 0.20)); // Acima de R$ 10.000,00 (20%)

		System.out.print("Digite o valor para calcular o imposto: ");
		double valor = scanner.nextDouble();

		double imposto = calcularImposto(valor, faixas);

		System.out.println("\nResumo do Cálculo:");
		System.out.printf("Valor informado: R$ %.2f\n", valor);
		System.out.printf("Imposto a pagar: R$ %.2f\n", imposto);

		scanner.close();
	}

	private static double calcularImposto(double valor, List<FaixaImposto> faixas) {
		double impostoTotal = 0.0;

		for (FaixaImposto faixa : faixas) {
			if (valor > faixa.getLimiteInferior()) {
				double valorNaFaixa = Math.min(valor, faixa.getLimiteSuperior()) - faixa.getLimiteInferior();
				double impostoNaFaixa = valorNaFaixa * faixa.getAliquota();
				impostoTotal += impostoNaFaixa;

				System.out
						.printf("Faixa: De R$ %.2f a R$ %.2f - %.0f%% sobre R$ %.2f -> Imposto: R$ %.2f\n",
								faixa.getLimiteInferior(),
								faixa.getLimiteSuperior() == Double.MAX_VALUE ? Double.POSITIVE_INFINITY
										: faixa.getLimiteSuperior(),
								faixa.getAliquota() * 100, valorNaFaixa, impostoNaFaixa);
			}
		}

		return impostoTotal;
	}
}

class FaixaImposto {
	private final double limiteInferior;
	private final double limiteSuperior;
	private final double aliquota;

	public FaixaImposto(double limiteInferior, double limiteSuperior, double aliquota) {
		this.limiteInferior = limiteInferior;
		this.limiteSuperior = limiteSuperior;
		this.aliquota = aliquota;
	}

	public double getLimiteInferior() {
		return limiteInferior;
	}

	public double getLimiteSuperior() {
		return limiteSuperior;
	}

	public double getAliquota() {
		return aliquota;
	}
}
