package programarcomputadoresalternativasdecisao;

import java.util.Scanner;

enum Operador {
	SOMA('+'), SUBTRACAO('-'), MULTIPLICACAO('*'), DIVISAO('/');

	private final char simbolo;

	Operador(char simbolo) {
		this.simbolo = simbolo;
	}

	public char getSimbolo() {
		return simbolo;
	}

	public static Operador fromChar(char simbolo) {
		for (Operador op : values()) {
			if (op.simbolo == simbolo) {
				return op;
			}
		}
		throw new IllegalArgumentException("Operador inválido. Use apenas +, -, * ou /.");
	}
}

class Calculadora {
	public static double calcular(int num1, int num2, Operador operador) {
		switch (operador) {
		case SOMA:
			return num1 + num2;
		case SUBTRACAO:
			return num1 - num2;
		case MULTIPLICACAO:
			return num1 * num2;
		case DIVISAO:
			if (num2 == 0) {
				throw new IllegalArgumentException("Erro: Divisão por zero não é permitida.");
			}
			return (double) num1 / num2;
		default:
			throw new IllegalStateException("Operador desconhecido.");
		}
	}
}

public class ProgramaCalculadora {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		try {
			System.out.print("Digite o primeiro número: ");
			int num1 = lerNumero(scanner);

			System.out.print("Digite o segundo número: ");
			int num2 = lerNumero(scanner);

			System.out.print("Digite o operador (+, -, *, /): ");
			char operadorChar = scanner.next().charAt(0);

			Operador operador = Operador.fromChar(operadorChar);

			double resultado = Calculadora.calcular(num1, num2, operador);
			System.out.println("Resultado: " + resultado);

		} catch (IllegalArgumentException e) {
			System.out.println("Erro: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Erro inesperado. Por favor, tente novamente.");
		} finally {
			scanner.close();
		}
	}

	private static int lerNumero(Scanner scanner) {
		while (!scanner.hasNextInt()) {
			System.out.println("Entrada inválida. Digite um número inteiro válido.");
			scanner.next(); 
		}
		return scanner.nextInt();
	}
}
