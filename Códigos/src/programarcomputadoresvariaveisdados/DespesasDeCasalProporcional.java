package programarcomputadoresvariaveisdados;

import java.util.Scanner;

class CasalDespesasProporcional {
	private double despesasMarido;
	private double despesasEsposa;
	private double rendaMarido;
	private double rendaEsposa;

	public CasalDespesasProporcional(double despesasMarido, double despesasEsposa, double rendaMarido,
			double rendaEsposa) {
		if (despesasMarido < 0 || despesasEsposa < 0 || rendaMarido <= 0 || rendaEsposa <= 0) {
			throw new IllegalArgumentException("Despesas e rendas devem ser valores positivos.");
		}
		this.despesasMarido = despesasMarido;
		this.despesasEsposa = despesasEsposa;
		this.rendaMarido = rendaMarido;
		this.rendaEsposa = rendaEsposa;
	}

	public double calcularTotalDespesas() {
		return despesasMarido + despesasEsposa;
	}

	public double calcularTotalRendas() {
		return rendaMarido + rendaEsposa;
	}

	public double calcularValorDevidoProporcional(double rendaIndividual) {
		return (rendaIndividual / calcularTotalRendas()) * calcularTotalDespesas();
	}

	public double calcularSaldo(double valorPago, double valorDevidoPessoa) {
		return valorPago - valorDevidoPessoa;
	}

	public double getDespesasMarido() {
		return despesasMarido;
	}

	public double getDespesasEsposa() {
		return despesasEsposa;
	}

	public double getRendaMarido() {
		return rendaMarido;
	}

	public double getRendaEsposa() {
		return rendaEsposa;
	}
}

class ExibicaoResultados {
	public static void exibirTabela(double despesasMarido, double despesasEsposa, double totalDespesas,
			double rendaMarido, double rendaEsposa, double valorDevidoMarido, double valorDevidoEsposa,
			double saldoMarido, double saldoEsposa) {
		System.out.println("\nITEM                MARIDO      ESPOSA      TOTAL");
		System.out.println("===================================================");
		System.out.printf("Despesas pagas       %.2f     %.2f     %.2f\n", despesasMarido, despesasEsposa,
				totalDespesas);
		System.out.printf("Renda                %.2f     %.2f     %.2f\n", rendaMarido, rendaEsposa,
				rendaMarido + rendaEsposa);
		System.out.printf("Valor devido         %.2f     %.2f     %.2f\n", valorDevidoMarido, valorDevidoEsposa,
				totalDespesas);
		System.out.printf("Saldo                %.2f     %.2f\n", saldoMarido, saldoEsposa);
	}
}

public class DespesasDeCasalProporcional {
	public static void main(String[] args) {
		
		try (Scanner scanner = new Scanner(System.in)) {

			double despesasMarido = lerEntrada(scanner, "Digite o valor das despesas do marido: ");
			double despesasEsposa = lerEntrada(scanner, "Digite o valor das despesas da esposa: ");

			double rendaMarido = lerEntrada(scanner, "Digite a renda do marido: ");
			double rendaEsposa = lerEntrada(scanner, "Digite a renda da esposa: ");

			try {
				CasalDespesasProporcional casal = new CasalDespesasProporcional(despesasMarido, despesasEsposa,
						rendaMarido, rendaEsposa);

				double totalDespesas = casal.calcularTotalDespesas();
				double valorDevidoMarido = casal.calcularValorDevidoProporcional(casal.getRendaMarido());
				double valorDevidoEsposa = casal.calcularValorDevidoProporcional(casal.getRendaEsposa());
				double saldoMarido = casal.calcularSaldo(casal.getDespesasMarido(), valorDevidoMarido);
				double saldoEsposa = casal.calcularSaldo(casal.getDespesasEsposa(), valorDevidoEsposa);

				ExibicaoResultados.exibirTabela(despesasMarido, despesasEsposa, totalDespesas, rendaMarido, rendaEsposa,
						valorDevidoMarido, valorDevidoEsposa, saldoMarido, saldoEsposa);
			} catch (IllegalArgumentException e) {
				System.out.println("Erro: " + e.getMessage());
			}
		}
	}

	private static double lerEntrada(Scanner scanner, String mensagem) {
		System.out.print(mensagem);
		double valor = scanner.nextDouble();
		if (valor < 0) {
			throw new IllegalArgumentException("Os valores não podem ser negativos.");
		}
		return valor;
	}
}
