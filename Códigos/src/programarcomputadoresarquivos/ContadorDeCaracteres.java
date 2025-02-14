package programarcomputadoresarquivos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class ContadorDeCaracteres {
	private static final String MENSAGEM_CAMINHO = "Digite o caminho do arquivo: ";
	private static final String MENSAGEM_SUCESSO = "A quantidade de caracteres no arquivo é: ";
	private static final String ERRO_CAMINHO_INVALIDO = "Erro: O caminho especificado não existe ou não é válido.";
	private static final String ERRO_LEITURA = "Erro ao ler o arquivo.";

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		try {
			System.out.print(MENSAGEM_CAMINHO);
			String caminhoArquivo = scanner.nextLine();

			validarCaminho(caminhoArquivo);

			int totalCaracteres = contarCaracteres(caminhoArquivo);
			System.out.println(MENSAGEM_SUCESSO + totalCaracteres);

		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(ERRO_LEITURA);
		} finally {
			scanner.close();
		}
	}

	private static void validarCaminho(String caminhoArquivo) {
		if (!Files.exists(Paths.get(caminhoArquivo))) {
			throw new IllegalArgumentException(ERRO_CAMINHO_INVALIDO);
		}
	}

	private static int contarCaracteres(String caminhoArquivo) throws IOException {
		int totalCaracteres = 0;

		try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
			String linha;
			while ((linha = reader.readLine()) != null) {
				totalCaracteres += linha.length();
			}
		}

		return totalCaracteres;
	}
}
