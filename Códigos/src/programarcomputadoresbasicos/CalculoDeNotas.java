package programarcomputadoresbasicos;

import java.util.ArrayList;
import java.util.List;

public class CalculoDeNotas {
	public static void main(String[] args) {
		List<Nota> notas = new ArrayList<>();
		notas.add(new Nota(8.0, 1)); 
		notas.add(new Nota(7.5, 2)); 
		notas.add(new Nota(10.0, 3)); 
		notas.add(new Nota(9.0, 4)); 

		double mediaPonderada = calcularMediaPonderada(notas);

		exibirNotas(notas);
		System.out.printf("\nMédia Ponderada: %.1f\n", mediaPonderada);
	}

	private static double calcularMediaPonderada(List<Nota> notas) {
		double somaNotasPonderadas = 0.0;
		int somaPesos = 0;

		for (Nota nota : notas) {
			somaNotasPonderadas += nota.getValor() * nota.getPeso();
			somaPesos += nota.getPeso();
		}

		return somaNotasPonderadas / somaPesos;
	}

	private static void exibirNotas(List<Nota> notas) {
		System.out.println("Notas do aluno:");
		for (int i = 0; i < notas.size(); i++) {
			Nota nota = notas.get(i);
			System.out.printf("Nota %d: %.1f (Peso: %d)\n", i + 1, nota.getValor(), nota.getPeso());
		}
	}
}

class Nota {
	private final double valor;
	private final int peso;

	public Nota(double valor, int peso) {
		this.valor = valor;
		this.peso = peso;
	}

	public double getValor() {
		return valor;
	}

	public int getPeso() {
		return peso;
	}
}
