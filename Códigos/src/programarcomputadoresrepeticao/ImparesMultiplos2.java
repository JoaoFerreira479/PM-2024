package programarcomputadoresrepeticao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ImparesMultiplos2 {
	public static List<Integer> obterImparesMultiplosDeTres(int inicio, int fim) {
		List<Integer> numeros = new ArrayList<>();
		for (int i = fim; i >= inicio; i--) {
			if (i % 2 != 0 && i % 3 == 0) {
				numeros.add(i);
			}
		}
		return numeros;
	}

	public static void exibirNumeros(List<Integer> numeros) {
		if (numeros.isEmpty()) {
			System.out.println("Nenhum número ímpar múltiplo de três foi encontrado no intervalo informado.");
		} else {
			System.out.println("Números ímpares múltiplos de três no intervalo fornecido (ordem decrescente):");
			System.out.println(numeros);
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		try {
			System.out.print("Digite o valor inicial do intervalo: ");
			int inicio = scanner.nextInt();

			System.out.print("Digite o valor final do intervalo: ");
			int fim = scanner.nextInt();

			if (inicio > fim) {
				System.out.println("Erro: O valor inicial não pode ser maior que o valor final.");
			} else {
				List<Integer> numeros = obterImparesMultiplosDeTres(inicio, fim);
				exibirNumeros(numeros);
			}

		} catch (Exception e) {
			System.out.println("Erro: Entrada inválida. Certifique-se de digitar números inteiros.");
		} finally {
			scanner.close();
		}
	}
}
