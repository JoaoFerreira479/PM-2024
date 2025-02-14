package programarcomputadoresvariaveisdados;

import java.util.Scanner;

class CasalDespesas {
	private double despesasMarido;
	private double despesasEsposa;

	public CasalDespesas(double despesasMarido, double despesasEsposa) {
		if (despesasMarido < 0 || despesasEsposa < 0) {
			throw new IllegalArgumentException("Despesas não podem ser negativas.");
		}
		this.despesasMarido = despesasMarido;
		this.despesasEsposa = despesasEsposa;
	}

	public double calcularTotalDespesas() {
		return despesasMarido + despesasEsposa;
	}

	public double calcularPercentual(double valorPago) {
		return (valorPago / calcularTotalDespesas()) * 100;
	}

	public double calcularValorDevido() {
		return calcularTotalDespesas() / 2;
	}

	public double calcularSaldo(double valorPago) {
		return valorPago - calcularValorDevido();
	}

	public double getDespesasMarido() {
		return despesasMarido;
	}

	public double getDespesasEsposa() {
		return despesasEsposa;
	}
}

public class DespesasDeCasal {

	public static void main(String[] args) {
		
		try (Scanner scanner = new Scanner(System.in)) {
			
			System.out.print("Digite o valor das despesas do marido: ");
			double despesasMarido = scanner.nextDouble();

			System.out.print("Digite o valor das despesas da esposa: ");
			double despesasEsposa = scanner.nextDouble();

			try {
				CasalDespesas casal = new CasalDespesas(despesasMarido, despesasEsposa);

				double totalDespesas = casal.calcularTotalDespesas();
				double percentualMarido = casal.calcularPercentual(casal.getDespesasMarido());
				double percentualEsposa = casal.calcularPercentual(casal.getDespesasEsposa());
				double valorDevido = casal.calcularValorDevido();
				double saldoMarido = casal.calcularSaldo(casal.getDespesasMarido());
				double saldoEsposa = casal.calcularSaldo(casal.getDespesasEsposa());

				exibirResultados(despesasMarido, despesasEsposa, totalDespesas, percentualMarido, percentualEsposa,
						valorDevido, saldoMarido, saldoEsposa);
			} catch (IllegalArgumentException e) {
				System.out.println("Erro: " + e.getMessage());
			}
		}
	}

	private static void exibirResultados(double despesasMarido, double despesasEsposa, double despesasTotais,
			double percentualMarido, double percentualEsposa, double valorDevido, double saldoMarido,
			double saldoEsposa) {
		System.out.println("\nITEM            MARIDO      ESPOSA      TOTAL");
		System.out.println("===============================================");
		System.out.printf("Despesas pagas   %.2f     %.2f     %.2f\n", despesasMarido, despesasEsposa, despesasTotais);
		System.out.printf("%% pago           %.2f%%     %.2f%%     %.0f%%\n", percentualMarido, percentualEsposa,
				100.0);
		System.out.printf("Valor devido     %.2f     %.2f     %.2f\n", valorDevido, valorDevido, despesasTotais);
		System.out.printf("Saldo            %.2f     %.2f\n", saldoMarido, saldoEsposa);
	}
}
