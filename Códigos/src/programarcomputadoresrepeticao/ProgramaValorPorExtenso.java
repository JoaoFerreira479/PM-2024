package programarcomputadoresrepeticao;

import java.util.Scanner;

class ConversorValorPorExtenso {

	private static final String[] UNIDADES = { "", "um", "dois", "três", "quatro", "cinco", "seis", "sete", "oito",
			"nove" };

	private static final String[] DEZENAS = { "", "dez", "vinte", "trinta", "quarenta", "cinquenta", "sessenta",
			"setenta", "oitenta", "noventa" };

	private static final String[] DEZENAS_ESPECIAIS = { "dez", "onze", "doze", "treze", "quatorze", "quinze",
			"dezesseis", "dezessete", "dezoito", "dezenove" };

	private static final String[] CENTENAS = { "", "cem", "duzentos", "trezentos", "quatrocentos", "quinhentos",
			"seiscentos", "setecentos", "oitocentos", "novecentos" };

	public static String converterParteInteira(int valor) {
		if (valor == 0) {
			return "zero reais";
		}

		StringBuilder resultado = new StringBuilder();

		int centenas = valor / 100;
		int resto = valor % 100;

		if (centenas > 0) {
			resultado.append(centenas == 1 && resto == 0 ? "cem" : CENTENAS[centenas]);
			if (resto > 0) {
				resultado.append(" e ");
			}
		}

		if (resto >= 10 && resto <= 19) {
			resultado.append(DEZENAS_ESPECIAIS[resto - 10]);
		} else {
			int dezenas = resto / 10;
			int unidades = resto % 10;

			if (dezenas > 0) {
				resultado.append(DEZENAS[dezenas]);
				if (unidades > 0) {
					resultado.append(" e ");
				}
			}

			if (unidades > 0) {
				resultado.append(UNIDADES[unidades]);
			}
		}

		resultado.append(" reais");
		return resultado.toString();
	}

	public static String converterCentavos(int centavos) {
		if (centavos == 0) {
			return "";
		}

		if (centavos >= 10 && centavos <= 19) {
			return DEZENAS_ESPECIAIS[centavos - 10] + " centavos";
		}

		StringBuilder resultado = new StringBuilder();

		int dezenas = centavos / 10;
		int unidades = centavos % 10;

		if (dezenas > 0) {
			resultado.append(DEZENAS[dezenas]);
			if (unidades > 0) {
				resultado.append(" e ");
			}
		}

		if (unidades > 0) {
			resultado.append(UNIDADES[unidades]);
		}

		resultado.append(" centavos");
		return resultado.toString();
	}

	public static String converterValorMonetario(double valor) {
		int parteInteira = (int) valor;
		int centavos = (int) Math.round((valor - parteInteira) * 100);

		String textoParteInteira = converterParteInteira(parteInteira);
		String textoCentavos = converterCentavos(centavos);

		if (textoCentavos.isEmpty()) {
			return textoParteInteira;
		}

		return textoParteInteira + " e " + textoCentavos;
	}
}

public class ProgramaValorPorExtenso {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			try {
				System.out.print("Digite um valor monetário (0 para sair): R$ ");
				double valor = scanner.nextDouble();

				if (valor == 0) {
					System.out.println("Programa encerrado.");
					break;
				}

				if (valor < 0 || valor > 999999.99) {
					System.out.println("Erro: O valor deve estar entre 0 e 999.999,99.");
					continue;
				}

				String valorPorExtenso = ConversorValorPorExtenso.converterValorMonetario(valor);
				System.out.println("Valor por extenso: " + valorPorExtenso);

			} catch (Exception e) {
				System.out.println("Erro: Entrada inválida. Por favor, insira um valor numérico.");
				scanner.nextLine();
			}
		}

		scanner.close();
	}
}
