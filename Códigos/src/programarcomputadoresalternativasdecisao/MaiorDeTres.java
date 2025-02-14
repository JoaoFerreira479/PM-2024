package programarcomputadoresalternativasdecisao;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class Numeros {
	private final int numero1;
	private final int numero2;
	private final int numero3;

	public Numeros(int numero1, int numero2, int numero3) {
		if (!saoDiferentes(numero1, numero2, numero3)) {
			throw new IllegalArgumentException("Os números devem ser diferentes.");
		}
		this.numero1 = numero1;
		this.numero2 = numero2;
		this.numero3 = numero3;
	}

	public int getNumero1() {
		return numero1;
	}

	public int getNumero2() {
		return numero2;
	}

	public int getNumero3() {
		return numero3;
	}

	public int maior() {
		return Math.max(numero1, Math.max(numero2, numero3));
	}

	private static boolean saoDiferentes(int numero1, int numero2, int numero3) {
		Set<Integer> numeros = new HashSet<>();
		numeros.add(numero1);
		numeros.add(numero2);
		numeros.add(numero3);
		return numeros.size() == 3;
	}
}

public class MaiorDeTres {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		try {
			System.out.print("Digite o primeiro número: ");
			int numero1 = lerNumero(scanner);

			System.out.print("Digite o segundo número: ");
			int numero2 = lerNumero(scanner);

			System.out.print("Digite o terceiro número: ");
			int numero3 = lerNumero(scanner);

			Numeros numeros = new Numeros(numero1, numero2, numero3);
			int maior = numeros.maior();

			System.out.println("O maior número é: " + maior);

		} catch (IllegalArgumentException e) {
			System.out.println("Erro: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Erro: Entrada inválida. Certifique-se de digitar números inteiros.");
		} finally {
			scanner.close();
		}
	}

	private static int lerNumero(Scanner scanner) {
		while (true) {
			try {
				return Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.print("Erro: Entrada inválida. Digite um número inteiro: ");
			}
		}
	}
}
