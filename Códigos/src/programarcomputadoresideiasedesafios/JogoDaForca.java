package programarcomputadoresideiasedesafios;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

class Jogador {
	private String nome;
	private int vitorias;
	private int derrotas;

	public Jogador(String nome) {
		this.nome = nome;
		this.vitorias = 0;
		this.derrotas = 0;
	}

	public String getNome() {
		return nome;
	}

	public void registrarVitoria() {
		vitorias++;
	}

	public void registrarDerrota() {
		derrotas++;
	}

	public void exibirHistorico() {
		System.out.println("\nHistórico de " + nome + ":");
		System.out.println("Vitórias: " + vitorias);
		System.out.println("Derrotas: " + derrotas);
	}
}

public class JogoDaForca {
	private static final String[] PALAVRAS = { "JAVA", "PROGRAMACAO", "LINGUAGEM", "COMPUTADOR", "TECLADO" };
	private Jogador jogador;
	private String palavra;
	private char[] palavraEscondida;
	private Set<Character> letrasTentadas;
	private int tentativasRestantes;

	public JogoDaForca(Jogador jogador) {
		this.jogador = jogador;
		this.letrasTentadas = new HashSet<>();
	}

	public void iniciar() {
		Random random = new Random();
		palavra = PALAVRAS[random.nextInt(PALAVRAS.length)];
		palavraEscondida = new char[palavra.length()];
		Arrays.fill(palavraEscondida, '_');
		tentativasRestantes = 6;
	}

	public void jogar(Scanner scanner) {
		while (tentativasRestantes > 0 && new String(palavraEscondida).contains("_")) {
			exibirEstadoAtual();
			System.out.print("Digite uma letra: ");
			char letra = scanner.nextLine().toUpperCase().charAt(0);

			if (!letrasTentadas.add(letra)) {
				System.out.println("Você já tentou essa letra. Tente novamente.");
				continue;
			}

			processarTentativa(letra);
		}

		finalizarPartida();
	}

	private void exibirEstadoAtual() {
		System.out.println("\nPalavra: " + String.valueOf(palavraEscondida));
		System.out.println("Tentativas restantes: " + tentativasRestantes);
		System.out.println("Letras tentadas: " + letrasTentadas);
	}

	private void processarTentativa(char letra) {
		if (palavra.contains(String.valueOf(letra))) {
			System.out.println("Boa! A letra " + letra + " está na palavra.");
			for (int i = 0; i < palavra.length(); i++) {
				if (palavra.charAt(i) == letra) {
					palavraEscondida[i] = letra;
				}
			}
		} else {
			System.out.println("Ops! A letra " + letra + " não está na palavra.");
			tentativasRestantes--;
		}
	}

	private void finalizarPartida() {
		if (new String(palavraEscondida).equals(palavra)) {
			System.out.println("\nParabéns! Você acertou a palavra: " + palavra);
			jogador.registrarVitoria();
		} else {
			System.out.println("\nQue pena! Você perdeu. A palavra era: " + palavra);
			jogador.registrarDerrota();
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Bem-vindo ao Jogo da Forca!");
		System.out.print("Digite seu nome: ");
		String nome = scanner.nextLine();

		Jogador jogador = new Jogador(nome);
		boolean continuar = true;

		while (continuar) {
			JogoDaForca jogo = new JogoDaForca(jogador);
			jogo.iniciar();
			jogo.jogar(scanner);

			System.out.println("Deseja jogar novamente? (s/n)");
			continuar = scanner.nextLine().equalsIgnoreCase("s");
		}

		jogador.exibirHistorico();
	}
}
