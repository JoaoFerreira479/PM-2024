package programarcomputadoresalternativasdecisao;

import java.util.Scanner;

class Disciplina {
	private final String nome;
	private final double nota;
	private final int aulasMinistradas;
	private final int faltas;

	public Disciplina(String nome, double nota, int aulasMinistradas, int faltas) {
		if (nome == null || nome.trim().isEmpty()) {
			throw new IllegalArgumentException("O nome da disciplina não pode ser vazio.");
		}
		if (nota < 0 || nota > 10) {
			throw new IllegalArgumentException("A nota deve estar entre 0 e 10.");
		}
		if (!Aprovacao.validaNotaMultipla(nota)) {
			throw new IllegalArgumentException("A nota deve ser múltipla de 0,5.");
		}
		if (aulasMinistradas <= 0) {
			throw new IllegalArgumentException("A quantidade de aulas ministradas deve ser maior que zero.");
		}
		if (faltas < 0 || faltas > aulasMinistradas) {
			throw new IllegalArgumentException(
					"A quantidade de faltas deve estar entre 0 e o total de aulas ministradas.");
		}
		this.nome = nome;
		this.nota = nota;
		this.aulasMinistradas = aulasMinistradas;
		this.faltas = faltas;
	}

	public String getNome() {
		return nome;
	}

	public double getNota() {
		return nota;
	}

	public int getAulasMinistradas() {
		return aulasMinistradas;
	}

	public int getFaltas() {
		return faltas;
	}

	public double getFrequencia() {
		return ((aulasMinistradas - faltas) / (double) aulasMinistradas) * 100;
	}
}

class Aprovacao {
	public static String verificarAprovacao(Disciplina disciplina) {
		if (disciplina.getFrequencia() < 75) {
			return "Reprovado por frequência (Frequência: " + String.format("%.2f", disciplina.getFrequencia()) + "%)";
		}

		double notaFinal = disciplina.getNota();
		if (notaFinal >= 5 && notaFinal <= 10) {
			return "Aprovado";
		} else if (notaFinal == 4 || notaFinal == 4.5) {
			return "Segunda Época";
		} else {
			return "Reprovado por nota";
		}
	}

	public static boolean validaNotaMultipla(double nota) {
		return (nota * 10) % 5 == 0;
	}
}

class InterfaceUsuario {
	public static void executar() {
		Scanner scanner = new Scanner(System.in);

		try {
			System.out.print("Digite o nome da disciplina: ");
			String disciplinaNome = scanner.nextLine();

			System.out.print("Digite a nota final (deve ser múltipla de 0,5): ");
			double notaFinal = scanner.nextDouble();

			System.out.print("Digite a quantidade de aulas ministradas: ");
			int aulasMinistradas = scanner.nextInt();

			System.out.print("Digite a quantidade de faltas: ");
			int faltas = scanner.nextInt();

			Disciplina disciplina = new Disciplina(disciplinaNome, notaFinal, aulasMinistradas, faltas);

			String resultado = Aprovacao.verificarAprovacao(disciplina);

			exibirResultado(disciplina, resultado);

		} catch (IllegalArgumentException e) {
			System.out.println("Erro: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Erro: Entrada inválida. Certifique-se de digitar os dados corretamente.");
		} finally {
			scanner.close();
		}
	}

	private static void exibirResultado(Disciplina disciplina, String resultado) {
		System.out.println("\n=== Resultado ===");
		System.out.println("Disciplina: " + disciplina.getNome());
		System.out.println("Nota Final: " + disciplina.getNota());
		System.out.println("Frequência: " + String.format("%.2f", disciplina.getFrequencia()) + "%");
		System.out.println("Resultado: " + resultado);
	}
}

public class ProgramaAprovacao {
	public static void main(String[] args) {
		InterfaceUsuario.executar();
	}
}
