package programarcomputadoresarquivos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class Estatisticas {
	private int quantidadeLinhas;
	private int quantidadePalavras;
	private int quantidadeLetras;
	private Map<Character, Integer> frequenciaLetras;

	public Estatisticas() {
		this.quantidadeLinhas = 0;
		this.quantidadePalavras = 0;
		this.quantidadeLetras = 0;
		this.frequenciaLetras = new HashMap<>();
	}

	public void incrementarLinhas() {
		quantidadeLinhas++;
	}

	public void adicionarPalavras(int palavras) {
		quantidadePalavras += palavras;
	}

	public void adicionarLetras(int letras) {
		quantidadeLetras += letras;
	}

	public void incrementarFrequenciaLetra(char letra) {
		frequenciaLetras.put(letra, frequenciaLetras.getOrDefault(letra, 0) + 1);
	}

	public void exibirEstatisticas() {
		System.out.println("=== Estatísticas do Arquivo ===");
		System.out.println("Quantidade de linhas: " + quantidadeLinhas);
		System.out.println("Quantidade de palavras: " + quantidadePalavras);
		System.out.println("Quantidade de letras: " + quantidadeLetras);
		System.out.println("Frequência de cada letra:");
		frequenciaLetras.forEach((letra, frequencia) -> System.out.println(letra + ": " + frequencia));
	}
}

public class EstatisticaDeTexto {
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Erro: Nenhum arquivo especificado.");
			return;
		}

		String caminhoArquivo = args[0];
		Estatisticas estatisticas = new Estatisticas();

		try {
			processarArquivo(caminhoArquivo, estatisticas);
			estatisticas.exibirEstatisticas();
		} catch (IOException e) {
			System.out.println("Erro ao processar o arquivo: " + e.getMessage());
		}
	}

	private static void processarArquivo(String caminhoArquivo, Estatisticas estatisticas) throws IOException {
		try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo))) {
			String linha;

			while ((linha = leitor.readLine()) != null) {
				estatisticas.incrementarLinhas();
				int palavras = contarPalavras(linha);
				estatisticas.adicionarPalavras(palavras);
				contarLetras(linha, estatisticas);
			}
		}
	}

	private static int contarPalavras(String linha) {
		String[] palavras = linha.trim().split("\\s+");
		return linha.isBlank() ? 0 : palavras.length;
	}

	private static void contarLetras(String linha, Estatisticas estatisticas) {
		for (char c : linha.toCharArray()) {
			if (Character.isLetter(c)) {
				estatisticas.adicionarLetras(1);
				estatisticas.incrementarFrequenciaLetra(Character.toLowerCase(c));
			}
		}
	}
}
