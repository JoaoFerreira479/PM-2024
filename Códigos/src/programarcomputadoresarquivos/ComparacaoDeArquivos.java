package programarcomputadoresarquivos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class ComparacaoDeArquivos {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		try {
			System.out.print("Digite o caminho do primeiro arquivo: ");
			String caminhoArquivo1 = scanner.nextLine();

			System.out.print("Digite o caminho do segundo arquivo: ");
			String caminhoArquivo2 = scanner.nextLine();

			validarCaminho(caminhoArquivo1);
			validarCaminho(caminhoArquivo2);

			int diferenca = compararArquivos(caminhoArquivo1, caminhoArquivo2);

			if (diferenca == -1) {
				System.out.println("Os arquivos são iguais.");
			} else {
				System.out.println("Os arquivos diferem no caractere de número: " + diferenca);
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Erro: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Erro ao acessar os arquivos: " + e.getMessage());
		} finally {
			scanner.close();
		}
	}

	private static void validarCaminho(String caminho) {
		if (caminho == null || caminho.isEmpty()) {
			throw new IllegalArgumentException("O caminho do arquivo não pode ser vazio.");
		}
		if (!Files.exists(Paths.get(caminho))) {
			throw new IllegalArgumentException("O arquivo \"" + caminho + "\" não existe.");
		}
		if (!Files.isReadable(Paths.get(caminho))) {
			throw new IllegalArgumentException("O arquivo \"" + caminho + "\" não pode ser lido.");
		}
	}

	private static int compararArquivos(String caminhoArquivo1, String caminhoArquivo2) throws IOException {
		try (BufferedReader leitor1 = new BufferedReader(new FileReader(caminhoArquivo1));
				BufferedReader leitor2 = new BufferedReader(new FileReader(caminhoArquivo2))) {
			int posicao = 1;
			int charArquivo1, charArquivo2;

			while ((charArquivo1 = leitor1.read()) != -1 && (charArquivo2 = leitor2.read()) != -1) {
				if (charArquivo1 != charArquivo2) {
					return posicao;
				}
				posicao++;
			}

			if (leitor1.read() != -1 || leitor2.read() != -1) {
				return posicao;
			}
		}
		return -1;
	}
}
