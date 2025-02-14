package programarcomputadoresalternativasdecisao;

import java.util.Scanner;

class TipoCaractere {
	public static String identificarTipo(char c) {
		if (Character.isLetter(c)) {
			return "Letra";
		} else if (Character.isDigit(c)) {
			return "Dígito";
		} else if (ehOperadorAritmetico(c)) {
			return "Operador Aritmético";
		} else {
			return "Nenhum dos anteriores";
		}
	}

	private static boolean ehOperadorAritmetico(char c) {
		return c == '+' || c == '-' || c == '*' || c == '/' || c == '%';
	}
}

public class ProgramaTipoCaractere {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		try {
			System.out.print("Digite um caractere: ");
			String entrada = scanner.next();

			if (entrada.length() != 1) {
				throw new IllegalArgumentException("Erro: Por favor, insira apenas um único caractere.");
			}

			char caractere = entrada.charAt(0);
			String tipo = TipoCaractere.identificarTipo(caractere);

			System.out.println("O caractere '" + caractere + "' é do tipo: " + tipo);

		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} catch (java.util.InputMismatchException e) {
			System.out.println("Erro: Entrada inválida. Certifique-se de inserir apenas caracteres.");
		} finally {
			scanner.close();
		}
	}
}
