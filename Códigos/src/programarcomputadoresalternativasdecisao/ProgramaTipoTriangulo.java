package programarcomputadoresalternativasdecisao;

import java.util.Scanner;

class Triangulo {
	private final double lado1;
	private final double lado2;
	private final double lado3;

	public Triangulo(double lado1, double lado2, double lado3) {
		if (lado1 <= 0 || lado2 <= 0 || lado3 <= 0) {
			throw new IllegalArgumentException("Os lados devem ser maiores que zero.");
		}
		this.lado1 = lado1;
		this.lado2 = lado2;
		this.lado3 = lado3;
	}

	public boolean ehTriangulo() {
		return lado1 < lado2 + lado3 && lado2 < lado1 + lado3 && lado3 < lado1 + lado2;
	}

	public String determinarTipo() {
		if (!ehTriangulo()) {
			throw new IllegalStateException("Os lados fornecidos não formam um triângulo.");
		}

		if (lado1 == lado2 && lado2 == lado3) {
			return "Equilátero";
		} else if (lado1 == lado2 || lado2 == lado3 || lado1 == lado3) {
			return "Isósceles";
		} else {
			return "Escaleno";
		}
	}

	@Override
	public String toString() {
		return String.format("Lados do triângulo: %.2f, %.2f, %.2f", lado1, lado2, lado3);
	}
}

public class ProgramaTipoTriangulo {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		try {
			System.out.print("Digite o primeiro lado: ");
			double lado1 = validarEntrada(scanner);

			System.out.print("Digite o segundo lado: ");
			double lado2 = validarEntrada(scanner);

			System.out.print("Digite o terceiro lado: ");
			double lado3 = validarEntrada(scanner);

			Triangulo triangulo = new Triangulo(lado1, lado2, lado3);

			System.out.println("\n" + triangulo);

			if (triangulo.ehTriangulo()) {
				System.out.println("O triângulo formado é do tipo: " + triangulo.determinarTipo());
			} else {
				System.out.println("Os lados fornecidos não podem formar um triângulo.");
			}

		} catch (IllegalArgumentException e) {
			System.out.println("Erro: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Erro: Entrada inválida. Por favor, insira valores numéricos válidos.");
		} finally {
			scanner.close();
		}
	}

	private static double validarEntrada(Scanner scanner) {
		double valor;
		try {
			valor = scanner.nextDouble();
			if (valor <= 0) {
				throw new IllegalArgumentException("O valor do lado deve ser maior que zero.");
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("Valor inválido. Certifique-se de inserir um número positivo.");
		}
		return valor;
	}
}
