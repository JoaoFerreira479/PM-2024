package programarcomputadoresbasicos;

public class DevagarSeVaiAoLonge {
	public static void main(String[] args) {
		int distanciaDiaria = 800; 
		int diasPorSemana = 5;
		int semanasPorAno = 45; 

		double distanciaTotalKm = calcularDistanciaAnual(distanciaDiaria, diasPorSemana, semanasPorAno);

		exibirResultado(distanciaDiaria, diasPorSemana, semanasPorAno, distanciaTotalKm);
	}

	private static double calcularDistanciaAnual(int distanciaDiaria, int diasPorSemana, int semanasPorAno) {
		int distanciaPorDia = distanciaDiaria * 2; // Ida e volta
		int distanciaTotalMetros = distanciaPorDia * diasPorSemana * semanasPorAno;
		return distanciaTotalMetros / 1000.0; // Converte para quilômetros
	}

	private static void exibirResultado(int distanciaDiaria, int diasPorSemana, int semanasPorAno,
			double distanciaTotalKm) {
		System.out.println("Parâmetros do Cálculo:");
		System.out.printf("- Distância de ida: %d metros\n", distanciaDiaria);
		System.out.printf("- Dias trabalhados por semana: %d\n", diasPorSemana);
		System.out.printf("- Semanas trabalhadas por ano: %d\n\n", semanasPorAno);

		System.out.printf("Distância total percorrida ao final de um ano: %.2f km\n", distanciaTotalKm);
	}
}
