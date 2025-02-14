package programarcomputadoresvariaveisdados;

import java.util.Scanner;

class Premio {
	private final int numero;

	public Premio(int numero) {
		if (!validarNumero(numero)) {
			throw new IllegalArgumentException("O número do prêmio deve ter exatamente 6 dígitos.");
		}
		this.numero = numero;
	}

	public int getNumero() {
		return numero;
	}

	public String getTresUltimosDigitos() {
		return String.format("%03d", numero % 1000);
	}

	private static boolean validarNumero(int numero) {
		return numero >= 100000 && numero <= 999999;
	}
}

class SorteioLBV {
	public static String gerarNumeroSorteado(Premio premio1, Premio premio2) {
		return premio1.getTresUltimosDigitos() + premio2.getTresUltimosDigitos();
	}
}

public class SorteioDaLBV {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		try {
			Premio primeiroPremio = lerPremio(scanner, "primeiro");
			Premio segundoPremio = lerPremio(scanner, "segundo");

			String numeroSorteado = SorteioLBV.gerarNumeroSorteado(primeiroPremio, segundoPremio);

			System.out.println("O número sorteado da LBV é: " + numeroSorteado);

		} catch (IllegalArgumentException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			scanner.close();
		}
	}

	private static Premio lerPremio(Scanner scanner, String descricao) {
		while (true) {
			try {
				System.out.print("Digite o número do " + descricao + " prêmio (6 dígitos): ");
				int numero = Integer.parseInt(scanner.nextLine());
				return new Premio(numero);
			} catch (NumberFormatException e) {
				System.out.println("Erro: Entrada inválida. Digite um número inteiro.");
			} catch (IllegalArgumentException e) {
				System.out.println("Erro: " + e.getMessage());
			}
		}
	}
}
