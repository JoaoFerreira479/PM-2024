package programarcomputadoresrepeticao;

import java.util.Scanner;

class CPF {
	public static void validarEntrada(String cpf) {
		if (cpf == null || cpf.length() != 9 || !cpf.matches("\\d+")) {
			throw new IllegalArgumentException("CPF inválido. Deve conter exatamente 9 dígitos numéricos.");
		}
	}

	public static int calcularDV1(int[] cpf) {
		int soma = 0;
		for (int i = 0; i < 9; i++) {
			soma += cpf[i] * (10 - i);
		}
		int resto = soma % 11;
		return (resto < 2) ? 0 : 11 - resto;
	}

	public static int calcularDV2(int[] cpf, int dv1) {
		int soma = 0;
		for (int i = 0; i < 9; i++) {
			soma += cpf[i] * (11 - i);
		}
		soma += dv1 * 2;
		int resto = soma % 11;
		return (resto < 2) ? 0 : 11 - resto;
	}

	public static int calcularNumeroDeControle(int dv1, int dv2) {
		return dv1 * 10 + dv2;
	}
}

public class ProgramaCPF {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		try {
			System.out.print("Digite os 9 primeiros dígitos do CPF (apenas números): ");
			String entradaCpf = scanner.nextLine();

			CPF.validarEntrada(entradaCpf);

			int[] cpfNumeros = new int[9];
			for (int i = 0; i < 9; i++) {
				cpfNumeros[i] = Character.getNumericValue(entradaCpf.charAt(i));
			}

			int dv1 = CPF.calcularDV1(cpfNumeros);
			int dv2 = CPF.calcularDV2(cpfNumeros, dv1);

			int numeroDeControle = CPF.calcularNumeroDeControle(dv1, dv2);

			System.out.println("Primeiro dígito verificador (DV1): " + dv1);
			System.out.println("Segundo dígito verificador (DV2): " + dv2);
			System.out.println("Número de controle do CPF: " + numeroDeControle);

		} catch (IllegalArgumentException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			scanner.close();
		}
	}
}
