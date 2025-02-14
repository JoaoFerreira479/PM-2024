package programarcomputadoresideiasedesafios;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Jogadores {
	private String nome;
	private String palavraSecreta;
	private List<String> tentativas;

	public Jogadores(String nome) {
		this.nome = nome;
		this.tentativas = new ArrayList<>();
	}

	public String getNome() {
		return nome;
	}

	public String getPalavraSecreta() {
		return palavraSecreta;
	}

	public void setPalavraSecreta(String palavraSecreta) {
		this.palavraSecreta = palavraSecreta;
	}

	public void registrarTentativa(String tentativa) {
		tentativas.add(tentativa);
	}

	public List<String> getTentativas() {
		return tentativas;
	}
}

class JogoAcha5 {
	private static final int TAMANHO_PALAVRA = 5;
	private List<String> bancoPalavras;
	private Jogadores jogador1;
	private Jogadores jogador2;
	private Random random;

	public JogoAcha5(List<String> bancoPalavras) {
		this.bancoPalavras = bancoPalavras;
		this.random = new Random();
	}

	public void iniciar(Scanner scanner) {
		System.out.println("Bem-vindo ao jogo Acha-5!");

		jogador1 = new Jogadores("Jogador");
		jogador2 = new Jogadores("Computador");

		jogador2.setPalavraSecreta(sortearPalavra());
		System.out.println("Computador escolheu sua palavra secreta.");
		jogador1.setPalavraSecreta(obterPalavraSecretaJogador(scanner));

		System.out.println("Que comece o jogo!");

		boolean jogoAtivo = true;
		boolean vezDoJogador = true;

		while (jogoAtivo) {
			if (vezDoJogador) {
				System.out.print("\nSua vez! Digite uma palavra de 5 letras: ");
				String tentativa = scanner.nextLine().toLowerCase();

				if (!validarPalavra(tentativa)) {
					System.out.println("Palavra inválida! Tente novamente.");
					continue;
				}

				jogador1.registrarTentativa(tentativa);
				int acertos = contarLetrasCorretas(tentativa, jogador2.getPalavraSecreta());
				System.out.printf("A palavra '%s' tem %d letra(s) correta(s)!\n", tentativa, acertos);

				if (acertos == TAMANHO_PALAVRA) {
					System.out.println("Parabéns! Você descobriu a palavra secreta do computador!");
					jogoAtivo = false;
				}
			} else {
				String tentativa = sortearPalavra();
				System.out.printf("\nVez do computador! Ele tentou: '%s'\n", tentativa);

				jogador2.registrarTentativa(tentativa);
				int acertos = contarLetrasCorretas(tentativa, jogador1.getPalavraSecreta());
				System.out.printf("O computador encontrou %d letra(s) correta(s)!\n", acertos);

				if (acertos == TAMANHO_PALAVRA) {
					System.out.println("O computador descobriu sua palavra secreta! Você perdeu!");
					jogoAtivo = false;
				}
			}

			vezDoJogador = !vezDoJogador;
		}

		System.out.println("\nFim de jogo!");
	}

	private String sortearPalavra() {
		return bancoPalavras.get(random.nextInt(bancoPalavras.size()));
	}

	private String obterPalavraSecretaJogador(Scanner scanner) {
		while (true) {
			System.out.print("Escolha sua palavra secreta de 5 letras: ");
			String palavra = scanner.nextLine().toLowerCase();
			if (validarPalavra(palavra)) {
				return palavra;
			}
			System.out.println("Palavra inválida! Certifique-se de que possui exatamente 5 letras.");
		}
	}

	private boolean validarPalavra(String palavra) {
		return palavra.matches("[a-z]{" + TAMANHO_PALAVRA + "}");
	}

	private int contarLetrasCorretas(String tentativa, String palavraSecreta) {
		int acertos = 0;
		for (char letra : tentativa.toCharArray()) {
			if (palavraSecreta.indexOf(letra) != -1) {
				acertos++;
			}
		}
		return acertos;
	}
}

public class Acha5 {
	public static void main(String[] args) {
		List<String> bancoPalavras = List.of("zinco", "ossos", "arara", "canto", "valsa", "piano", "campo", "vento",
				"porto", "risco");
		JogoAcha5 jogo = new JogoAcha5(bancoPalavras);
		Scanner scanner = new Scanner(System.in);
		jogo.iniciar(scanner);
		scanner.close();
	}
}
