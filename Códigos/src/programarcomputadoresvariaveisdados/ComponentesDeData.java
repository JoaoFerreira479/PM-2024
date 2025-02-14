package programarcomputadoresvariaveisdados;

import java.util.Scanner;

class Data {
	private int dia;
	private int mes;
	private int ano;

	public Data(int dia, int mes, int ano) {
		if (!validarDiaMesAno(dia, mes, ano)) {
			throw new IllegalArgumentException("Data inválida: valores de dia, mês ou ano estão fora dos limites.");
		}
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
	}

	public int getDia() {
		return dia;
	}

	public int getMes() {
		return mes;
	}

	public int getAno() {
		return ano;
	}

	private static boolean validarDiaMesAno(int dia, int mes, int ano) {
		if (mes < 1 || mes > 12) {
			return false;
		}
		if (dia < 1 || dia > diasNoMes(mes)) {
			return false;
		}
		return true;
	}

	private static int diasNoMes(int mes) {
		switch (mes) {
		case 2:
			return 28;
		case 4:
		case 6:
		case 9:
		case 11:
			return 30;
		default:
			return 31;
		}
	}
}

class ManipuladorData {
	public static boolean validarFormatoData(String data) {
		return data.matches("\\d{2}/\\d{2}/\\d{2}");
	}

	public static Data converterParaData(String data) {
		String[] partes = data.split("/");
		int dia = Integer.parseInt(partes[0]);
		int mes = Integer.parseInt(partes[1]);
		int ano = Integer.parseInt(partes[2]);
		return new Data(dia, mes, ano);
	}
}

public class ComponentesDeData {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Digite uma data no formato dd/mm/aa: ");
		String entrada = scanner.nextLine();

		if (ManipuladorData.validarFormatoData(entrada)) {
			
			try {
				Data data = ManipuladorData.converterParaData(entrada);

				System.out.println("Dia: " + data.getDia());
				System.out.println("Mês: " + data.getMes());
				System.out.println("Ano: " + data.getAno());
			} catch (IllegalArgumentException e) {
				System.out.println("Erro: " + e.getMessage());
			}
		} else {
			System.out.println("Erro: Formato de data inválido. Use o formato dd/mm/aa.");
		}

		scanner.close();
	}
}
