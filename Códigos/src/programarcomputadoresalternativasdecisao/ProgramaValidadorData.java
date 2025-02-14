package programarcomputadoresalternativasdecisao;

import java.util.Scanner;

class ValidadorData {
	public static String validarData(String data) {
		if (data.length() != 10) {
			return "Erro: A data deve ter 10 caracteres no formato DD/MM/AAAA.";
		}

		if (data.charAt(2) != '/' || data.charAt(5) != '/') {
			return "Erro: A data deve conter barras ('/') nas posições corretas.";
		}

		try {
			int dia = Integer.parseInt(data.substring(0, 2));
			int mes = Integer.parseInt(data.substring(3, 5));
			int ano = Integer.parseInt(data.substring(6, 10));

			if (mes < 1 || mes > 12) {
				return "Erro: O mês deve estar entre 1 e 12.";
			}

			if (!diaValido(dia, mes, ano)) {
				return "Erro: O dia não é válido para o mês informado.";
			}

			if (ano < 1900 || ano > 2100) {
				return "Erro: O ano deve estar entre 1900 e 2100.";
			}

			return "Data válida.";

		} catch (NumberFormatException e) {
			return "Erro: A data deve conter apenas números no formato DD/MM/AAAA.";
		}
	}

	private static boolean diaValido(int dia, int mes, int ano) {
		int[] diasNoMes = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		if (mes == 2 && anoBissexto(ano)) {
			diasNoMes[1] = 29; // Fevereiro em anos bissextos tem 29 dias
		}

		return dia >= 1 && dia <= diasNoMes[mes - 1];
	}

	private static boolean anoBissexto(int ano) {
		return (ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0);
	}
}

public class ProgramaValidadorData {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		try {
			System.out.print("Digite uma data no formato DD/MM/AAAA: ");
			String data = scanner.nextLine();

			String resultado = ValidadorData.validarData(data);
			System.out.println(resultado);

		} catch (Exception e) {
			System.out.println("Erro: Ocorreu um problema ao validar a data.");
		} finally {
			scanner.close();
		}
	}
}
