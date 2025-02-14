package programarcomputadoresalternativasdecisao;

import java.util.Scanner;

enum DiaSemana {
	DOMINGO(1, "Domingo"), SEGUNDA(2, "Segunda-feira"), TERCA(3, "Terça-feira"), QUARTA(4, "Quarta-feira"),
	QUINTA(5, "Quinta-feira"), SEXTA(6, "Sexta-feira"), SABADO(7, "Sábado");

	private final int numero;
	private final String nome;

	DiaSemana(int numero, String nome) {
		this.numero = numero;
		this.nome = nome;
	}

	public int getNumero() {
		return numero;
	}

	public String getNome() {
		return nome;
	}

	public static String obterNomeDia(int numero) {
		for (DiaSemana dia : DiaSemana.values()) {
			if (dia.getNumero() == numero) {
				return dia.getNome();
			}
		}
		return null;
	}
}

public class ProgramaDiaDaSemana {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		try {
			System.out.print("Digite um número de 1 a 7 para o dia da semana: ");
			int numeroDia = validarEntradaInteira(scanner);

			String nomeDia = DiaSemana.obterNomeDia(numeroDia);

			if (nomeDia != null) {
				System.out.println("O dia correspondente é: " + nomeDia);
			} else {
				System.out.println("Erro: Número inválido. Insira um número entre 1 e 7.");
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			scanner.close();
		}
	}

	private static int validarEntradaInteira(Scanner scanner) {
		if (!scanner.hasNextInt()) {
			throw new IllegalArgumentException("Entrada inválida. Por favor, insira um número inteiro.");
		}

		int numero = scanner.nextInt();

		if (numero < 1 || numero > 7) {
			throw new IllegalArgumentException("O número deve estar entre 1 e 7.");
		}

		return numero;
	}
}
