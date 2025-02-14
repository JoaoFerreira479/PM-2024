package programarcomputadoresrepeticao;

import java.util.Scanner;

public class Criptografia2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String input;
		int opcao;

		do {
			System.out.println("=== MENU ===");
			System.out.println("1. Criptografar");
			System.out.println("2. Decifrar");
			System.out.println("3. Sair");
			System.out.print("Escolha uma opção: ");

			while (!scanner.hasNextInt()) {
				System.out.println("Entrada inválida. Por favor, digite um número entre 1 e 3.");
				scanner.next();
			}
			opcao = scanner.nextInt();
			scanner.nextLine();

			switch (opcao) {
			case 1 -> {
				System.out.print("Digite a cadeia a ser criptografada: ");
				input = scanner.nextLine();
				System.out.println("Cadeia criptografada: " + criptografar(input));
			}
			case 2 -> {
				System.out.print("Digite a cadeia a ser decifrada: ");
				input = scanner.nextLine();
				try {
					System.out.println("Cadeia decifrada: " + decifrar(input));
				} catch (IllegalArgumentException e) {
					System.out.println("Erro: " + e.getMessage());
				}
			}
			case 3 -> System.out.println("Saindo...");
			default -> System.out.println("Opção inválida. Escolha uma opção entre 1 e 3.");
			}
		} while (opcao != 3);

		scanner.close();
	}

	public static String criptografar(String texto) {
		texto = texto.replaceAll("\\s+", ""); 
		int length = texto.length();
		int rows = (int) Math.ceil((double) length / 5);
		char[][] matriz = new char[rows][5];

		int index = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < 5; j++) {
				if (index < length) {
					matriz[i][j] = texto.charAt(index++);
				} else {
					matriz[i][j] = ' '; 
				}
			}
		}

		StringBuilder resultado = new StringBuilder();
		for (int j = 0; j < 5; j++) {
			for (int i = 0; i < rows; i++) {
				if (matriz[i][j] != ' ') {
					resultado.append(matriz[i][j]);
				}
			}
			if (j < 4) {
				resultado.append("/");
			}
		}

		return resultado.toString();
	}

	public static String decifrar(String textoCriptografado) {
		String[] colunas = textoCriptografado.split("/");
		if (colunas.length != 5) {
			throw new IllegalArgumentException("Formato inválido. O texto deve conter 5 colunas separadas por '/'.");
		}

		int[] tamanhos = new int[5];
		int totalCaracteres = 0;

		for (int i = 0; i < 5; i++) {
			tamanhos[i] = colunas[i].length();
			totalCaracteres += tamanhos[i];
		}

		int rows = (int) Math.ceil((double) totalCaracteres / 5);
		char[][] matriz = new char[rows][5];

		for (int j = 0; j < 5; j++) {
			char[] caracteres = colunas[j].toCharArray();
			for (int i = 0; i < caracteres.length; i++) {
				matriz[i][j] = caracteres[i];
			}
		}

		StringBuilder resultado = new StringBuilder();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < 5; j++) {
				if (matriz[i][j] != '\0') {
					resultado.append(matriz[i][j]);
				}
			}
		}

		return resultado.toString();
	}
}
