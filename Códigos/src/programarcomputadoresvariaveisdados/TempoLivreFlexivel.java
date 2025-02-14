package programarcomputadoresvariaveisdados;

import java.util.Scanner;

class TempoDeEstudo {
	private int tempoTotalMinutos;
	private int numeroDisciplinas;

	public TempoDeEstudo(int tempoTotalMinutos, int numeroDisciplinas) {
		this.tempoTotalMinutos = tempoTotalMinutos;
		this.numeroDisciplinas = numeroDisciplinas;
	}

	public int calcularTempoPorDisciplina() {
		return tempoTotalMinutos / numeroDisciplinas;
	}

	public int calcularTempoLivre() {
		return tempoTotalMinutos % numeroDisciplinas;
	}

	public static int[] converterParaHorasEMinutos(int tempoEmMinutos) {
		int horas = tempoEmMinutos / 60;
		int minutos = tempoEmMinutos % 60;
		return new int[] { horas, minutos };
	}
}

public class TempoLivreFlexivel {
	public static void main(String[] args) {
		
		try (Scanner scanner = new Scanner(System.in)) {
			
			System.out.print("Digite o tempo total disponível para estudo (em minutos): ");
			int tempoTotalMinutos = scanner.nextInt();

			System.out.print("Digite o número de disciplinas: ");
			int numeroDisciplinas = scanner.nextInt();

			if (numeroDisciplinas <= 0) {
				System.out.println("Erro: O número de disciplinas deve ser maior que zero.");
				return;
			}

			TempoDeEstudo estudo = new TempoDeEstudo(tempoTotalMinutos, numeroDisciplinas);

			int tempoPorDisciplina = estudo.calcularTempoPorDisciplina();
			int tempoLivre = estudo.calcularTempoLivre();

			exibirResultados(tempoPorDisciplina, tempoLivre);
		}
	}

	private static void exibirResultados(int tempoPorDisciplina, int tempoLivre) {
		int[] tempoDisciplinaFormatado = TempoDeEstudo.converterParaHorasEMinutos(tempoPorDisciplina);
		int[] tempoLivreFormatado = TempoDeEstudo.converterParaHorasEMinutos(tempoLivre);

		System.out.println("\n--- Tempo de Estudo ---");
		System.out.printf("Tempo por disciplina: %d horas e %d minutos\n", tempoDisciplinaFormatado[0],
				tempoDisciplinaFormatado[1]);
		System.out.printf("Tempo livre: %d horas e %d minutos\n", tempoLivreFormatado[0], tempoLivreFormatado[1]);
	}
}
