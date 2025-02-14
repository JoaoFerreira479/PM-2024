package programarcomputadoresideiasedesafios;

import java.util.ArrayList;
import java.util.Scanner;

class Prova {
	private char[] gabarito;
	private ArrayList<Aluno> alunos;

	public Prova(int numQuestoes) {
		this.gabarito = new char[numQuestoes];
		this.alunos = new ArrayList<>();
	}

	public void cadastrarGabarito(Scanner scanner) {
		System.out.println("Digite o gabarito das questões (V para Verdadeiro, F para Falso):");
		for (int i = 0; i < gabarito.length; i++) {
			while (true) {
				System.out.print("Questão " + (i + 1) + ": ");
				char resposta = scanner.nextLine().toUpperCase().charAt(0);
				if (resposta == 'V' || resposta == 'F') {
					gabarito[i] = resposta;
					break;
				} else {
					System.out.println("Resposta inválida! Digite V ou F.");
				}
			}
		}
	}

	public void cadastrarRespostasAluno(Scanner scanner, String nomeAluno) {
		char[] respostas = new char[gabarito.length];
		System.out.println("Digite as respostas do aluno " + nomeAluno + " (V para Verdadeiro, F para Falso):");
		for (int i = 0; i < respostas.length; i++) {
			while (true) {
				System.out.print("Questão " + (i + 1) + ": ");
				char resposta = scanner.nextLine().toUpperCase().charAt(0);
				if (resposta == 'V' || resposta == 'F') {
					respostas[i] = resposta;
					break;
				} else {
					System.out.println("Resposta inválida! Digite V ou F.");
				}
			}
		}
		alunos.add(new Aluno(nomeAluno, respostas));
	}

	public void corrigirProvas(int fatorCorrecao) {
		for (Aluno aluno : alunos) {
			int acertos = 0;
			int erros = 0;
			for (int i = 0; i < gabarito.length; i++) {
				if (aluno.getRespostas()[i] == gabarito[i]) {
					acertos++;
				} else {
					erros++;
				}
			}
			if (fatorCorrecao > 0) {
				acertos -= (erros / fatorCorrecao);
				if (acertos < 0)
					acertos = 0;
			}
			aluno.setNota(acertos);
		}
	}

	public void exibirResultados() {
		System.out.println("\nResultados:");
		for (Aluno aluno : alunos) {
			System.out.printf("Aluno: %s - Nota: %d/%d\n", aluno.getNome(), aluno.getNota(), gabarito.length);
		}
	}
}

class Aluno {
	private String nome;
	private char[] respostas;
	private int nota;

	public Aluno(String nome, char[] respostas) {
		this.nome = nome;
		this.respostas = respostas;
	}

	public String getNome() {
		return nome;
	}

	public char[] getRespostas() {
		return respostas;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}
}

public class ProvasVF {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		final int NUM_QUESTOES = 20;

		System.out.println("Bem-vindo ao programa de correção de Provas Verdadeiro ou Falso!");
		Prova prova = new Prova(NUM_QUESTOES);

		prova.cadastrarGabarito(scanner);

		while (true) {
			System.out.print("\nDigite o nome do aluno (ou 'fim' para encerrar): ");
			String nomeAluno = scanner.nextLine();
			if (nomeAluno.equalsIgnoreCase("fim")) {
				break;
			}
			prova.cadastrarRespostasAluno(scanner, nomeAluno);
		}

		System.out.print("\nDigite o fator de correção (0 para desativar, 2 ou 3 para ativar): ");
		int fatorCorrecao = scanner.nextInt();

		prova.corrigirProvas(fatorCorrecao);

		prova.exibirResultados();

		scanner.close();
	}
}
