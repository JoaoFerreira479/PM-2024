package programarcomputadoresrepeticao;

import java.util.Scanner;

class ManipuladorStrings {
	public static String inserirHifens(String palavra) {
		if (palavra == null || palavra.isEmpty()) {
			throw new IllegalArgumentException("A palavra não pode estar vazia.");
		}

		StringBuilder resultado = new StringBuilder();
		for (int i = 0; i < palavra.length(); i++) {
			resultado.append(palavra.charAt(i));
			if (i < palavra.length() - 1) {
				resultado.append('-');
			}
		}
		return resultado.toString();
	}

	public static String inserirCaracter(String palavra, char caractere) {
		if (palavra == null || palavra.isEmpty()) {
			throw new IllegalArgumentException("A palavra não pode estar vazia.");
		}

		StringBuilder resultado = new StringBuilder();
		for (int i = 0; i < palavra.length(); i++) {
			resultado.append(palavra.charAt(i));
			if (i < palavra.length() - 1) {
				resultado.append(caractere);
			}
		}
		return resultado.toString();
	}
}

public class InsercaoCaracteres {

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		boolean continuar = true;

		while (continuar) {
			try {
				System.out.println("\n=== Opções ===");
				System.out.println("1. Inserir hifens entre caracteres");
				System.out.println("2. Inserir um caractere personalizado entre caracteres");
				System.out.println("3. Sair");
				System.out.print("Escolha uma opção: ");
				int opcao = validarEntradaInt();

				switch (opcao) {
				case 1:
					processarInsercaoHifens();
					break;
				case 2:
					processarInsercaoCaracterPersonalizado();
					break;
				case 3:
					continuar = false;
					System.out.println("Encerrando o programa.");
					break;
				default:
					System.out.println("Opção inválida. Tente novamente.");
				}
			} catch (IllegalArgumentException e) {
				System.out.println("Erro: " + e.getMessage());
			} catch (Exception e) {
				System.out.println("Erro inesperado. Tente novamente.");
			}
		}

		scanner.close();
	}

	private static void processarInsercaoHifens() {
		System.out.print("Digite uma palavra: ");
		String palavra = scanner.nextLine().trim();

		String resultado = ManipuladorStrings.inserirHifens(palavra);
		System.out.println("Resultado: " + resultado);
	}

	private static void processarInsercaoCaracterPersonalizado() {
		System.out.print("Digite uma palavra: ");
		String palavra = scanner.nextLine().trim();

		System.out.print("Digite o caractere a ser inserido: ");
		String entradaCaractere = scanner.nextLine().trim();
		if (entradaCaractere.length() != 1) {
			throw new IllegalArgumentException("Você deve inserir exatamente um caractere.");
		}
		char caractere = entradaCaractere.charAt(0);

		String resultado = ManipuladorStrings.inserirCaracter(palavra, caractere);
		System.out.println("Resultado: " + resultado);
	}

	private static int validarEntradaInt() {
		while (!scanner.hasNextInt()) {
			System.out.print("Entrada inválida. Digite um número inteiro: ");
			scanner.next();
		}
		return scanner.nextInt();
	}
}
