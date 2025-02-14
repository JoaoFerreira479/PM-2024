package programarcomputadoresalternativasdecisao;

import java.util.Scanner;

class ContaTelefonica {
	private static double valorAssinatura = 17.90;
	private static double custoImpulsoExtra = 0.04;
	private static double custoChamadaCelular = 0.09;

	private final int impulsosTotais;
	private final int impulsosCelular;
	private final double valorInterurbanos;

	public ContaTelefonica(int impulsosTotais, int impulsosCelular, double valorInterurbanos) {
		if (impulsosTotais < 0 || impulsosCelular < 0 || valorInterurbanos < 0) {
			throw new IllegalArgumentException("Os valores de impulsos e interurbanos devem ser não negativos.");
		}
		if (impulsosCelular > impulsosTotais) {
			throw new IllegalArgumentException(
					"O número de impulsos para celular não pode ser maior que o total de impulsos.");
		}
		this.impulsosTotais = impulsosTotais;
		this.impulsosCelular = impulsosCelular;
		this.valorInterurbanos = valorInterurbanos;
	}

	public double calcularCustoImpulsosExtras() {
		int impulsosExtras = Math.max(impulsosTotais - 90, 0);
		return impulsosExtras * custoImpulsoExtra;
	}

	public double calcularCustoChamadaCelular() {
		return impulsosCelular * custoChamadaCelular;
	}

	public double calcularValorTotal() {
		return valorAssinatura + calcularCustoImpulsosExtras() + calcularCustoChamadaCelular() + valorInterurbanos;
	}

	public static double getValorAssinatura() {
		return valorAssinatura;
	}

	public static void configurarTarifas(double assinatura, double custoImpulso, double custoCelular) {
		if (assinatura < 0 || custoImpulso < 0 || custoCelular < 0) {
			throw new IllegalArgumentException("As tarifas devem ser não negativas.");
		}
		valorAssinatura = assinatura;
		custoImpulsoExtra = custoImpulso;
		custoChamadaCelular = custoCelular;
	}
}

public class ProgramaContaTelefonica {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		try {
			configurarTarifasPadrao();

			System.out.print("Digite o número total de impulsos: ");
			int impulsosTotais = validarEntradaInt(scanner);

			System.out.print("Digite o número de impulsos para celular: ");
			int impulsosCelular = validarEntradaInt(scanner);

			System.out.print("Digite o valor das chamadas de interurbanos: R$ ");
			double valorInterurbanos = validarEntradaDouble(scanner);

			ContaTelefonica conta = new ContaTelefonica(impulsosTotais, impulsosCelular, valorInterurbanos);

			exibirDetalhamentoConta(conta);

		} catch (IllegalArgumentException e) {
			System.out.println("Erro: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Erro: Entrada inválida. Por favor, insira valores numéricos válidos.");
		} finally {
			scanner.close();
		}
	}

	private static void configurarTarifasPadrao() {
		ContaTelefonica.configurarTarifas(17.90, 0.04, 0.09);
	}

	private static int validarEntradaInt(Scanner scanner) {
		int valor = scanner.nextInt();
		if (valor < 0) {
			throw new IllegalArgumentException("O valor deve ser maior ou igual a zero.");
		}
		return valor;
	}

	private static double validarEntradaDouble(Scanner scanner) {
		double valor = scanner.nextDouble();
		if (valor < 0) {
			throw new IllegalArgumentException("O valor deve ser maior ou igual a zero.");
		}
		return valor;
	}

	private static void exibirDetalhamentoConta(ContaTelefonica conta) {
		System.out.println("\n=== Detalhamento da Conta Telefônica ===");
		System.out.printf("Valor da assinatura: R$ %.2f%n", ContaTelefonica.getValorAssinatura());
		System.out.printf("Custo de impulsos extras: R$ %.2f%n", conta.calcularCustoImpulsosExtras());
		System.out.printf("Custo de chamadas para celular: R$ %.2f%n", conta.calcularCustoChamadaCelular());
		System.out.printf("Custo de interurbanos: R$ %.2f%n",
				conta.calcularValorTotal() - ContaTelefonica.getValorAssinatura() - conta.calcularCustoImpulsosExtras()
						- conta.calcularCustoChamadaCelular());
		System.out.printf("Valor total da conta: R$ %.2f%n", conta.calcularValorTotal());
	}
}
