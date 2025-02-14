package programarcomputadoresalternativasdecisao;

import java.util.Scanner;

public class MultiplicacaoRapidaPor11 {
	public static String multiplicarPor11(int numero) {
		if (numero < 10 || numero > 99) {
			throw new IllegalArgumentException("O número deve ter exatamente dois dígitos entre 10 e 99.");
		}

		int unidade = numero % 10; 
		int dezena = numero / 10; 

		int soma = dezena + unidade;

		if (soma >= 10) {
			dezena += soma / 10;
			soma %= 10;
		}

		return "" + dezena + soma + unidade;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		try {
			System.out.print("Digite um número de dois dígitos para multiplicar por 11: ");
			int numero = scanner.nextInt();

			String resultado = multiplicarPor11(numero);

			System.out.println("O resultado da multiplicação por 11 é: " + resultado);
		} catch (IllegalArgumentException e) {
			System.out.println("Erro: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Erro inesperado. Por favor, tente novamente.");
		} finally {
			scanner.close();
		}
	}
}
