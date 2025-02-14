package programarcomputadoresalternativasdecisao;

import java.util.Scanner;

class PisPasep {
	private static final int[] PESOS = { 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

	public static int calcularDigitoVerificador(String numero) {
		if (numero.length() != 10 || !numero.matches("\\d+")) {
			throw new IllegalArgumentException("O número deve conter exatamente 10 dígitos numéricos.");
		}

		int soma = 0;

		for (int i = 0; i < 10; i++) {
			soma += Character.getNumericValue(numero.charAt(i)) * PESOS[i];
		}

		int resto = soma % 11;

		int digito = 11 - resto;
		return (digito == 10 || digito == 11) ? 0 : digito;
	}
}

public class ProgramaPisPasep {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		try {
			System.out.print("Digite os 10 primeiros dígitos do número PIS/PASEP (sem traço): ");
			String numero = scanner.nextLine();

			int digitoVerificador = PisPasep.calcularDigitoVerificador(numero);

			System.out.println("O dígito verificador é: " + digitoVerificador);

		} catch (IllegalArgumentException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			scanner.close();
		}
	}
}
