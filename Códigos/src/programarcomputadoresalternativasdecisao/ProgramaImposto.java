package programarcomputadoresalternativasdecisao;

import java.util.Scanner;
import java.util.TreeMap;

class CalculadoraImposto {
	private final TreeMap<Double, Double> faixasImposto;

	public CalculadoraImposto() {
		faixasImposto = new TreeMap<>();
		configurarFaixas();
	}

	private void configurarFaixas() {
		faixasImposto.put(1200.00, 0.0); // Até R$ 1.200,00 (isento)
		faixasImposto.put(2500.00, 0.10); // De R$ 1.201,00 a R$ 2.500,00 (10%)
		faixasImposto.put(5000.00, 0.15); // De R$ 2.501,00 a R$ 5.000,00 (15%)
		faixasImposto.put(Double.MAX_VALUE, 0.20); // Acima de R$ 5.000,00 (20%)
	}

	public double calcularImposto(double valorBase) {
		if (valorBase < 0) {
			throw new IllegalArgumentException("O valor base deve ser maior ou igual a zero.");
		}

		double taxa = faixasImposto.floorEntry(valorBase).getValue();
		return valorBase * taxa;
	}
}

public class ProgramaImposto {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		try {
			System.out.print("Digite o valor base: R$ ");
			double valorBase = validarEntradaDouble(scanner);

			CalculadoraImposto calculadora = new CalculadoraImposto();

			double imposto = calculadora.calcularImposto(valorBase);

			System.out.printf("Valor Base: R$ %.2f%n", valorBase);
			System.out.printf("Imposto a Pagar: R$ %.2f%n", imposto);

		} catch (IllegalArgumentException e) {
			System.out.println("Erro: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Erro: Entrada inválida. Por favor, insira um número válido.");
		} finally {
			scanner.close();
		}
	}

	private static double validarEntradaDouble(Scanner scanner) {
		try {
			double valor = scanner.nextDouble();
			if (valor < 0) {
				throw new IllegalArgumentException("O valor deve ser maior ou igual a zero.");
			}
			return valor;
		} catch (java.util.InputMismatchException e) {
			throw new IllegalArgumentException("Entrada inválida. Certifique-se de inserir um número válido.");
		}
	}
}
