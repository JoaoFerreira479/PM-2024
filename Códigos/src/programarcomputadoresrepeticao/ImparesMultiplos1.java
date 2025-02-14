package programarcomputadoresrepeticao;

public class ImparesMultiplos1 {
	public static int calcularSomaImparesMultiplosDeTres(int inicio, int fim) {
		int soma = 0;

		for (int i = inicio; i <= fim; i++) {
			if (i % 2 != 0 && i % 3 == 0) {
				soma += i;
			}
		}

		return soma;
	}

	public static void main(String[] args) {
		int inicio = 1;
		int fim = 1000;

		int resultado = calcularSomaImparesMultiplosDeTres(inicio, fim);

		System.out.println("A soma de todos os números ímpares múltiplos de três na faixa de " + inicio + " a " + fim
				+ " é: " + resultado);
	}
}
