package programarcomputadoresbasicos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OtimizacaoDeCorte {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		final int comprimentoPeca = 45;

		int[] tamanhosTabuas = { 300, 400, 500 };

		System.out.print("Digite a quantidade de peças de 45 cm necessárias: ");
		int quantidadePecas = scanner.nextInt();

		List<ResultadoCorte> resultados = new ArrayList<>();
		for (int tamanho : tamanhosTabuas) {
			resultados.add(calcularCorte(tamanho, comprimentoPeca, quantidadePecas));
		}

		exibirResultados(resultados);

		scanner.close();
	}

	private static ResultadoCorte calcularCorte(int tamanhoTabua, int comprimentoPeca, int quantidadePecas) {
		int pecasPorTabua = tamanhoTabua / comprimentoPeca;

		int tabuasNecessarias = (int) Math.ceil((double) quantidadePecas / pecasPorTabua);

		int sobraNaUltimaTabua = (tabuasNecessarias * tamanhoTabua) - (quantidadePecas * comprimentoPeca);

		return new ResultadoCorte(tamanhoTabua, pecasPorTabua, tabuasNecessarias, sobraNaUltimaTabua);
	}

	private static void exibirResultados(List<ResultadoCorte> resultados) {
		System.out.println("\nOpções de corte para as tábuas disponíveis:");
		for (ResultadoCorte resultado : resultados) {
			System.out.println("Para tábuas de " + resultado.getTamanhoTabua() + " cm:");
			System.out.println("- Pedaços por tábua: " + resultado.getPecasPorTabua());
			System.out.println("- Tábuas necessárias: " + resultado.getTabuasNecessarias());
			System.out.println("- Sobra de madeira na última tábua: " + resultado.getSobraNaUltimaTabua() + " cm\n");
		}
	}
}

class ResultadoCorte {
	private final int tamanhoTabua;
	private final int pecasPorTabua;
	private final int tabuasNecessarias;
	private final int sobraNaUltimaTabua;

	public ResultadoCorte(int tamanhoTabua, int pecasPorTabua, int tabuasNecessarias, int sobraNaUltimaTabua) {
		this.tamanhoTabua = tamanhoTabua;
		this.pecasPorTabua = pecasPorTabua;
		this.tabuasNecessarias = tabuasNecessarias;
		this.sobraNaUltimaTabua = sobraNaUltimaTabua;
	}

	public int getTamanhoTabua() {
		return tamanhoTabua;
	}

	public int getPecasPorTabua() {
		return pecasPorTabua;
	}

	public int getTabuasNecessarias() {
		return tabuasNecessarias;
	}

	public int getSobraNaUltimaTabua() {
		return sobraNaUltimaTabua;
	}
}
