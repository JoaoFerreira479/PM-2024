package programarcomputadoresbasicos;

import java.util.HashMap;
import java.util.Map;

public class PalavraGrande {

	private static final Map<Character, String[]> PADROES = new HashMap<>();

	static {
		PADROES.put('S', new String[] { " ******** ", " *        ", " *        ", " ******** ", "        * ",
				"        * ", " ******** " });
		PADROES.put('O', new String[] { " ******** ", " *      * ", " *      * ", " *      * ", " *      * ",
				" *      * ", " ******** " });
		PADROES.put('L', new String[] { " *        ", " *        ", " *        ", " *        ", " *        ",
				" *        ", " ******** " });
	}

	public static void main(String[] args) {
		String palavra = "SOL";

		imprimirPalavra(palavra);
	}

	private static void imprimirPalavra(String palavra) {
		for (char letra : palavra.toUpperCase().toCharArray()) {
			if (!PADROES.containsKey(letra)) {
				System.out.println("Erro: Padrão não definido para o caractere '" + letra + "'.");
				return;
			}
		}

		for (int linha = 0; linha < 7; linha++) {
			StringBuilder linhaFormatada = new StringBuilder();

			for (char letra : palavra.toUpperCase().toCharArray()) {
				linhaFormatada.append(PADROES.get(letra)[linha]).append("   ");
			}

			System.out.println(linhaFormatada.toString());
		}
	}
}
