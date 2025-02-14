package programarcomputadoresrepeticao;

import java.util.Scanner;

public class Criptografia1 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int opcao;

		do {
			exibirMenu();
			opcao = validarEntradaInteira(scanner, "Escolha uma opção: ");

			switch (opcao) {
			case 1:
				System.out.print("Digite a cadeia para codificar: ");
				String textoParaCodificar = scanner.nextLine();
				String textoCodificado = codificarTexto(textoParaCodificar);
				System.out.println("Texto codificado: " + textoCodificado);
				break;

			case 2:
				System.out.print("Digite a cadeia para decodificar: ");
				String textoParaDecodificar = scanner.nextLine();
				String textoDecodificado = decodificarTexto(textoParaDecodificar);
				System.out.println("Texto decodificado: " + textoDecodificado);
				break;

			case 0:
				System.out.println("Encerrando o programa.");
				break;

			default:
				System.out.println("Opção inválida. Escolha 1, 2 ou 0.");
			}
		} while (opcao != 0);

		scanner.close();
	}

	private static void exibirMenu() {
		System.out.println("\n=== Menu de Criptografia ===");
		System.out.println("1 - Codificar");
		System.out.println("2 - Decodificar");
		System.out.println("0 - Sair");
	}

	private static String codificarTexto(String texto) {
		StringBuilder resultado = new StringBuilder();
		for (char c : texto.toCharArray()) {
			resultado.append((char) (c + 1));
		}
		return resultado.toString();
	}

	private static String decodificarTexto(String texto) {
		StringBuilder resultado = new StringBuilder();
		for (char c : texto.toCharArray()) {
			resultado.append((char) (c - 1));
		}
		return resultado.toString();
	}

	private static int validarEntradaInteira(Scanner scanner, String mensagem) {
		int valor;
		while (true) {
			try {
				System.out.print(mensagem);
				valor = Integer.parseInt(scanner.nextLine());
				return valor;
			} catch (NumberFormatException e) {
				System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
			}
		}
	}
}
