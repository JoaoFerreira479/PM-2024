package programarcomputadoresvariaveisdados;

import java.util.Scanner;

public class Losangos {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Digite a linha do topo do primeiro losango: ");
		int linhaTopo = scanner.nextInt();

		System.out.print("Digite a coluna do topo do primeiro losango: ");
		int colunaTopo = scanner.nextInt();

		final int deslocamento = 15;

		final int tamanho = 4;

		desenharLosangos(linhaTopo, colunaTopo, deslocamento, tamanho);

		scanner.close();
	}

	private static void desenharLosangos(int linhaTopo, int colunaTopo, int deslocamento, int tamanho) {
		for (int i = -tamanho; i <= tamanho; i++) {
			int largura = calcularLargura(tamanho, i);

			desenharLinha(largura, colunaTopo, deslocamento);
		}
	}

	private static int calcularLargura(int tamanho, int linha) {
		return tamanho - Math.abs(linha);
	}

	private static void desenharLinha(int largura, int colunaTopo, int deslocamento) {
		imprimirEspacos(colunaTopo - largura);

		imprimirCaracteres('x', 2 * largura + 1);

		imprimirEspacos(deslocamento);

		imprimirCaracteres('x', 2 * largura + 1);

		System.out.println();
	}

	private static void imprimirCaracteres(char caractere, int quantidade) {
		for (int i = 0; i < quantidade; i++) {
			System.out.print(caractere);
		}
	}

	private static void imprimirEspacos(int quantidade) {
		imprimirCaracteres(' ', quantidade);
	}
}
