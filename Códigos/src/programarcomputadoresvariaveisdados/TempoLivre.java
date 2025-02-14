package programarcomputadoresvariaveisdados;

public class TempoLivre {
	public static void main(String[] args) {
		int tempoTotalMinutos = 100;

		int numeroDisciplinas = 6;

		int tempoPorDisciplina = calcularTempoPorDisciplina(tempoTotalMinutos, numeroDisciplinas);
		int tempoLivre = calcularTempoLivre(tempoTotalMinutos, numeroDisciplinas);

		exibirResultados(tempoPorDisciplina, tempoLivre);
	}

	private static int calcularTempoPorDisciplina(int tempoTotal, int numeroDisciplinas) {
		return tempoTotal / numeroDisciplinas;
	}

	private static int calcularTempoLivre(int tempoTotal, int numeroDisciplinas) {
		return tempoTotal % numeroDisciplinas;
	}

	private static int[] converterParaHorasEMinutos(int tempoEmMinutos) {
		int horas = tempoEmMinutos / 60;
		int minutos = tempoEmMinutos % 60;
		return new int[] { horas, minutos };
	}

	private static void exibirResultados(int tempoPorDisciplina, int tempoLivre) {
		int[] tempoDisciplinaFormatado = converterParaHorasEMinutos(tempoPorDisciplina);
		int[] tempoLivreFormatado = converterParaHorasEMinutos(tempoLivre);

		System.out.println("--- Tempo de Estudo ---");
		System.out.printf("Tempo por disciplina: %d horas e %d minutos\n", tempoDisciplinaFormatado[0],
				tempoDisciplinaFormatado[1]);
		System.out.printf("Tempo livre: %d horas e %d minutos\n", tempoLivreFormatado[0], tempoLivreFormatado[1]);
	}
}
