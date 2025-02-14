package programarcomputadoresvariaveisdados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Pessoa {
	private String nomeCompleto;
	private String iniciais;

	public Pessoa(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
		this.iniciais = calcularIniciais(nomeCompleto);
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public String getIniciais() {
		return iniciais;
	}

	private String calcularIniciais(String nomeCompleto) {
		String[] palavras = nomeCompleto.trim().split("\\s+");
		if (palavras.length < 2) {
			throw new IllegalArgumentException("É necessário informar pelo menos o nome e o sobrenome.");
		}
		StringBuilder iniciaisBuilder = new StringBuilder();
		for (String palavra : palavras) {
			iniciaisBuilder.append(palavra.toUpperCase().charAt(0));
		}
		return iniciaisBuilder.toString();
	}
}

public class Iniciais {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		List<Pessoa> pessoas = new ArrayList<>();

		System.out.println("Digite nomes completos (nome e sobrenome). Para encerrar, digite 'sair'.");
		while (true) {
			System.out.print("Digite um nome completo: ");
			String entrada = scanner.nextLine();

			if (entrada.equalsIgnoreCase("sair")) {
				break;
			}

			try {
				Pessoa pessoa = new Pessoa(entrada);
				pessoas.add(pessoa);
			} catch (IllegalArgumentException e) {
				System.out.println("Erro: " + e.getMessage());
			}
		}

		System.out.println("\nIniciais extraídas:");
		for (Pessoa pessoa : pessoas) {
			System.out.println(pessoa.getNomeCompleto() + " -> " + pessoa.getIniciais());
		}

		scanner.close();
	}
}
