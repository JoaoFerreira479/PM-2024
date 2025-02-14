package programarcomputadoresvariaveisdados;

import java.util.Scanner;

class Casa {
	private double ladoA;
	private double ladoB;

	public Casa(double ladoA, double ladoB) {
		if (ladoA <= 0 || ladoB <= 0) {
			throw new IllegalArgumentException("Os lados da casa devem ser valores positivos.");
		}
		this.ladoA = ladoA;
		this.ladoB = ladoB;
	}

	public double calcularDiagonal() {
		return Math.sqrt(Math.pow(ladoA, 2) + Math.pow(ladoB, 2));
	}

	public double calcularDiagonalEmCentimetros() {
		return calcularDiagonal() * 100;
	}
}

public class ComprimentoDeFio {
	public static void main(String[] args) {
		
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Digite o comprimento da casa (lado A em metros): ");
			double ladoA = scanner.nextDouble();

			System.out.print("Digite a largura da casa (lado B em metros): ");
			double ladoB = scanner.nextDouble();

			try {
				Casa casa = new Casa(ladoA, ladoB);

				double diagonal = casa.calcularDiagonal();
				double diagonalEmCentimetros = casa.calcularDiagonalEmCentimetros();

				exibirResultados(diagonal, diagonalEmCentimetros);
			} catch (IllegalArgumentException e) {
				System.out.println("Erro: " + e.getMessage());
			}
		}
	}

	private static void exibirResultados(double diagonal, double diagonalEmCentimetros) {
		System.out.println("\n--- Cálculo do Comprimento de Fio ---");
		System.out.printf("Diagonal da casa (mínimo de fio necessário): %.2f metros\n", diagonal);
		System.out.printf("Diagonal em centímetros: %.2f cm\n", diagonalEmCentimetros);
	}
}
