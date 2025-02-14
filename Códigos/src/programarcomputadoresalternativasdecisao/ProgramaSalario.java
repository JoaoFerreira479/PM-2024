package programarcomputadoresalternativasdecisao;

import java.util.Scanner;

class Salario {
	private static final double LIMITE_INSS = 150.00; // Limite máximo para desconto do INSS
	private static final double ACRESCIMO_HORAS_EXTRAS = 1.5; // 50% de acréscimo
	private static final int HORAS_MENSAL = 176; // Total de horas em um mês padrão (22 dias úteis x 8 horas)

	private final double valorNominal;
	private final int horasExtras;

	public Salario(double valorNominal, int horasExtras) {
		if (valorNominal <= 0) {
			throw new IllegalArgumentException("O valor nominal do salário deve ser maior que zero.");
		}
		if (horasExtras < 0) {
			throw new IllegalArgumentException("O número de horas extras não pode ser negativo.");
		}
		this.valorNominal = valorNominal;
		this.horasExtras = horasExtras;
	}

	public double calcularValorHorasExtras() {
		double valorHora = valorNominal / HORAS_MENSAL;
		return valorHora * horasExtras * ACRESCIMO_HORAS_EXTRAS;
	}

	public double calcularDescontoINSS() {
		double salarioComHorasExtras = valorNominal + calcularValorHorasExtras();
		return Math.min(salarioComHorasExtras * 0.10, LIMITE_INSS);
	}

	public double calcularSalarioLiquido() {
		return valorNominal + calcularValorHorasExtras() - calcularDescontoINSS();
	}

	@Override
	public String toString() {
		return String.format(
				"Salário Nominal: R$ %.2f%nHoras Extras: %d horas%nValor Horas Extras: R$ %.2f%nDesconto INSS: R$ %.2f%nSalário Líquido: R$ %.2f",
				valorNominal, horasExtras, calcularValorHorasExtras(), calcularDescontoINSS(),
				calcularSalarioLiquido());
	}
}

public class ProgramaSalario {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		try {
			System.out.print("Digite o valor nominal do salário: R$ ");
			double valorNominal = validarEntradaDouble(scanner);

			System.out.print("Digite o número de horas extras: ");
			int horasExtras = validarEntradaInt(scanner);

			Salario salario = new Salario(valorNominal, horasExtras);

			System.out.println("\n=== Detalhamento do Salário ===");
			System.out.println(salario);

		} catch (IllegalArgumentException e) {
			System.out.println("Erro: " + e.getMessage());
		} catch (java.util.InputMismatchException e) {
			System.out.println("Erro: Entrada inválida. Certifique-se de inserir números válidos.");
		} finally {
			scanner.close();
		}
	}

	private static double validarEntradaDouble(Scanner scanner) {
		try {
			double valor = scanner.nextDouble();
			if (valor <= 0) {
				throw new IllegalArgumentException("O valor deve ser maior que zero.");
			}
			return valor;
		} catch (java.util.InputMismatchException e) {
			throw new IllegalArgumentException("Entrada inválida. Certifique-se de inserir um número válido.");
		}
	}

	private static int validarEntradaInt(Scanner scanner) {
		try {
			int valor = scanner.nextInt();
			if (valor < 0) {
				throw new IllegalArgumentException("O valor deve ser maior ou igual a zero.");
			}
			return valor;
		} catch (java.util.InputMismatchException e) {
			throw new IllegalArgumentException("Entrada inválida. Certifique-se de inserir um número válido.");
		}
	}
}
