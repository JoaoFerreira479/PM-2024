package programarcomputadoresbasicos;

public class Quadrado {

	public static void main(String[] args) {
		int tamanho = 5;

		desenharQuadrado(tamanho);
	}

	public static void desenharQuadrado(int tamanho) {
		if (tamanho < 2) {
			System.out.println("O tamanho do quadrado deve ser maior ou igual a 2.");
			return;
		}

		imprimirLinhaSuperiorOuInferior(tamanho); 
		for (int i = 1; i < tamanho - 1; i++) { 
			imprimirLinhaDoMeio(tamanho);
		}
		imprimirLinhaSuperiorOuInferior(tamanho);
	}

	private static void imprimirLinhaSuperiorOuInferior(int tamanho) {
		for (int i = 0; i < tamanho; i++) {
			System.out.print("X");
		}
		System.out.println();
	}

	private static void imprimirLinhaDoMeio(int tamanho) {
		System.out.print("X");
		for (int i = 1; i < tamanho - 1; i++) {
			System.out.print(" ");
		}
		System.out.print("X");
		System.out.println();
	}
}
