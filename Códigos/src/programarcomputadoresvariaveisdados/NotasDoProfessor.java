package programarcomputadoresvariaveisdados;

import java.util.Scanner;

class Aluno {
	private int notaExercicio1;
	private int notaExercicio2;
	private double notaProva;

	public Aluno(int notaExercicio1, int notaExercicio2, double notaProva) {
		this.notaExercicio1 = notaExercicio1;
		this.notaExercicio2 = notaExercicio2;
		this.notaProva = notaProva;
	}

	public int getNotaExercicio1() {
		return notaExercicio1;
	}

	public int getNotaExercicio2() {
		return notaExercicio2;
	}

	public double getNotaProva() {
		return notaProva;
	}
}

class CalculadoraNotas {
	public double calcularMediaExercicios(int nota1, int nota2) {
		int peso1 = 1;
		int peso2 = 2;
		return (nota1 * peso1 + nota2 * peso2) / (double) (peso1 + peso2);
	}

	public double calcularNotaFinal(double mediaExercicios, double notaProva) {
		double pesoExercicios = 2.0;
		double pesoProva = 8.0;
		return (mediaExercicios * pesoExercicios + notaProva * pesoProva) / (pesoExercicios + pesoProva);
	}
}

public class NotasDoProfessor {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		CalculadoraNotas calculadora = new CalculadoraNotas();

		System.out.print("Digite a nota do exercício 1 (de 0 a 10): ");
		int notaExercicio1 = lerNotaInteira(scanner);

		System.out.print("Digite a nota do exercício 2 (de 0 a 10): ");
		int notaExercicio2 = lerNotaInteira(scanner);

		System.out.print("Digite a nota da prova (de 0 a 10, com uma casa decimal): ");
		double notaProva = lerNotaDecimal(scanner);

		Aluno aluno = new Aluno(notaExercicio1, notaExercicio2, notaProva);

		double mediaExercicios = calculadora.calcularMediaExercicios(aluno.getNotaExercicio1(),
				aluno.getNotaExercicio2());
		double notaFinal = calculadora.calcularNotaFinal(mediaExercicios, aluno.getNotaProva());

		System.out.printf("\nMédia dos exercícios: %.1f\n", mediaExercicios);
		System.out.printf("Nota final: %.1f\n", notaFinal);

		scanner.close();
	}

	private static int lerNotaInteira(Scanner scanner) {
		while (true) {
			try {
				int nota = Integer.parseInt(scanner.nextLine());
				if (nota >= 0 && nota <= 10) {
					return nota;
				} else {
					System.out.print("Nota inválida! Digite um valor entre 0 e 10: ");
				}
			} catch (NumberFormatException e) {
				System.out.print("Entrada inválida! Digite um número inteiro: ");
			}
		}
	}

	private static double lerNotaDecimal(Scanner scanner) {
		while (true) {
			try {
				double nota = Double.parseDouble(scanner.nextLine());
				if (nota >= 0.0 && nota <= 10.0) {
					return nota;
				} else {
					System.out.print("Nota inválida! Digite um valor entre 0.0 e 10.0: ");
				}
			} catch (NumberFormatException e) {
				System.out.print("Entrada inválida! Digite um número decimal: ");
			}
		}
	}
}
