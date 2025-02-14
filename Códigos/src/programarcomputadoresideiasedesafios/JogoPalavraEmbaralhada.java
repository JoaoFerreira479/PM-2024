package programarcomputadoresideiasedesafios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class BancoDePalavras {
	private List<String> palavras;

	public BancoDePalavras() {
		this.palavras = Arrays.asList("JAVA", "PROGRAMACAO", "LINGUAGEM", "COMPUTADOR", "TECLADO");
	}

	public String obterPalavraAleatoria() {
		Random random = new Random();
		return palavras.get(random.nextInt(palavras.size()));
	}
}

class PalavraEmbaralhada {
	private String palavraOriginal;
	private String palavraEmbaralhada;

	public PalavraEmbaralhada(String palavra) {
		this.palavraOriginal = palavra;
		this.palavraEmbaralhada = embaralharPalavra(palavra);
	}

	private String embaralharPalavra(String palavra) {
		List<Character> letras = new ArrayList<>();
		for (char letra : palavra.toCharArray()) {
			letras.add(letra);
		}
		Collections.shuffle(letras);

		StringBuilder palavraEmbaralhada = new StringBuilder();
		for (char letra : letras) {
			palavraEmbaralhada.append(letra);
		}

		return palavraEmbaralhada.toString();
	}

	public String getPalavraEmbaralhada() {
		return palavraEmbaralhada;
	}

	public String getPalavraOriginal() {
		return palavraOriginal;
	}
}

public class JogoPalavraEmbaralhada {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		BancoDePalavras bancoDePalavras = new BancoDePalavras();
		boolean continuar = true;

		System.out.println("Bem-vindo ao jogo de Palavra Embaralhada!");

		while (continuar) {
			String palavraEscolhida = bancoDePalavras.obterPalavraAleatoria();
			PalavraEmbaralhada jogo = new PalavraEmbaralhada(palavraEscolhida);

			System.out.println("\nA palavra embaralhada é: " + jogo.getPalavraEmbaralhada());
			System.out.println("Você tem 30 segundos para adivinhar!");

			long inicioTempo = System.currentTimeMillis();
			System.out.print("Digite sua resposta: ");
			String resposta = scanner.nextLine();
			long tempoDecorrido = (System.currentTimeMillis() - inicioTempo) / 1000;

			exibirResultado(jogo, resposta, tempoDecorrido);

			System.out.println("Deseja jogar novamente? (s/n)");
			continuar = scanner.nextLine().equalsIgnoreCase("s");
		}

		System.out.println("Obrigado por jogar!");
		scanner.close(); 
	}

	private static void exibirResultado(PalavraEmbaralhada jogo, String resposta, long tempoDecorrido) {
		if (tempoDecorrido > 30) {
			System.out.println("\nTempo esgotado! A palavra era: " + jogo.getPalavraOriginal());
		} else if (resposta.equalsIgnoreCase(jogo.getPalavraOriginal())) {
			System.out.println("\nParabéns! Você acertou em " + tempoDecorrido + " segundos.");
		} else {
			System.out.println("\nQue pena! Resposta errada. A palavra era: " + jogo.getPalavraOriginal());
		}
	}
}
