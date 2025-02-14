package programarcomputadoresrepeticao;

import java.util.Scanner;

class ValidadorCPF {
	public static int calcularSoma(String cpf, int pesoInicial) {
		int soma = 0;
		for (int i = 0; i < cpf.length(); i++) {
			soma += Character.getNumericValue(cpf.charAt(i)) * pesoInicial;
			pesoInicial--;
		}
		return soma;
	}

	public static int calcularPrimeiroDigito(String cpf) {
		int soma = calcularSoma(cpf, 10);
		int resto = soma % 11;
		return (resto < 2) ? 0 : 11 - resto;
	}

	public static int calcularSegundoDigito(String cpf, int dv1) {
		int soma = calcularSoma(cpf, 11) + dv1 * 2;
		int resto = soma % 11;
		return (resto < 2) ? 0 : 11 - resto;
	}

	public static void validarCPF(String cpf) {
		if (cpf.length() != 9 || !cpf.matches("\\d+")) {
			throw new IllegalArgumentException("O CPF deve conter exatamente 9 dígitos numéricos.");
		}
	}
}

public class ProgramaValidadorCPF {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean continuar = true;

		while (continuar) {
			try {
				System.out.print("Digite os 9 primeiros dígitos do CPF: ");
				String cpf = scanner.nextLine();

				ValidadorCPF.validarCPF(cpf);

				int dv1 = ValidadorCPF.calcularPrimeiroDigito(cpf);
				int dv2 = ValidadorCPF.calcularSegundoDigito(cpf, dv1);

				System.out.printf("O CPF completo com os dígitos verificadores é: %s-%d%d%n", cpf, dv1, dv2);

			} catch (IllegalArgumentException e) {
				System.out.println("Erro: " + e.getMessage());
			}

			System.out.print("Deseja verificar outro CPF? (S/N): ");
			String resposta = scanner.nextLine();
			continuar = resposta.equalsIgnoreCase("S");
		}

		scanner.close();
		
		System.out.println("Programa encerrado.");
	}
}
