package programarcomputadoresbasicos;

public class FuncoesAninhadas {
	public static void main(String[] args) {
		double numero = 3;
		double raiz = calcularRaiz(numero);
		double arredondado = arredondar(raiz);
		double exponencial = calcularExponencial(arredondado);

		exibirResultados(numero, raiz, arredondado, exponencial);
	}

	private static double calcularRaiz(double numero) {
		return Math.sqrt(numero);
	}

	private static double arredondar(double valor) {
		return Math.round(valor);
	}

	private static double calcularExponencial(double valor) {
		return Math.exp(valor);
	}

	private static void exibirResultados(double numero, double raiz, double arredondado, double exponencial) {
		System.out.printf("Número inicial: %.2f\n", numero);
		System.out.printf("Raiz quadrada: %.6f\n", raiz);
		System.out.printf("Valor arredondado: %.0f\n", arredondado);
		System.out.printf("Exponencial do valor arredondado: %.6f\n", exponencial);
	}
}
