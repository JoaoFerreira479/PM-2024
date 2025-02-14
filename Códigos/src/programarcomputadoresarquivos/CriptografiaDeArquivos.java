package programarcomputadoresarquivos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class CriptografiaDeArquivos {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		try {
			while (true) {
				System.out.println("\n=== Criptografia de Arquivos ===");
				System.out.println("1 - Criptografar Arquivo");
				System.out.println("2 - Descriptografar Arquivo");
				System.out.println("0 - Sair");
				System.out.print("Escolha uma opção: ");
				int opcao = validarOpcao(scanner);

				if (opcao == 0) {
					System.out.println("Encerrando o programa...");
					break;
				}

				System.out.print("Digite o caminho do arquivo de entrada: ");
				String caminhoEntrada = scanner.nextLine();

				System.out.print("Digite o caminho do arquivo de saída: ");
				String caminhoSaida = scanner.nextLine();

				switch (opcao) {
				case 1 -> criptografarArquivo(caminhoEntrada, caminhoSaida);
				case 2 -> descriptografarArquivo(caminhoEntrada, caminhoSaida);
				default -> System.out.println("Opção inválida.");
				}
			}
		} finally {
			scanner.close();
		}
	}

	public static void criptografarArquivo(String caminhoEntrada, String caminhoSaida) {
		processarArquivo(caminhoEntrada, caminhoSaida, 1);
	}

	public static void descriptografarArquivo(String caminhoEntrada, String caminhoSaida) {
		processarArquivo(caminhoEntrada, caminhoSaida, -1);
	}

	private static void processarArquivo(String caminhoEntrada, String caminhoSaida, int deslocamento) {
		try (FileInputStream entrada = new FileInputStream(caminhoEntrada);
				FileOutputStream saida = new FileOutputStream(caminhoSaida)) {

			int byteLido;
			while ((byteLido = entrada.read()) != -1) {
				saida.write(byteLido + deslocamento);
			}

			System.out.println("Operação concluída com sucesso. Arquivo salvo em: " + caminhoSaida);
		} catch (FileNotFoundException e) {
			System.out.println("Erro: Arquivo de entrada não encontrado.");
		} catch (IOException e) {
			System.out.println("Erro ao processar o arquivo: " + e.getMessage());
		}
	}

	private static int validarOpcao(Scanner scanner) {
		while (true) {
			try {
				int opcao = Integer.parseInt(scanner.nextLine());
				if (opcao >= 0 && opcao <= 2) {
					return opcao;
				}
				System.out.print("Opção inválida. Digite novamente (0, 1 ou 2): ");
			} catch (NumberFormatException e) {
				System.out.print("Entrada inválida. Digite novamente (0, 1 ou 2): ");
			}
		}
	}
}
