package programarcomputadoresideiasedesafios;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Emprestimo {
	private String tipoObjeto;
	private String nomeObjeto;
	private String nomePessoa;
	private LocalDate dataEmprestimo;
	private LocalDate dataDevolucao;
	private LocalDate ultimaCobranca;

	public Emprestimo(String tipoObjeto, String nomeObjeto, String nomePessoa, LocalDate dataEmprestimo,
			LocalDate ultimaCobranca) {
		this.tipoObjeto = tipoObjeto;
		this.nomeObjeto = nomeObjeto;
		this.nomePessoa = nomePessoa;
		this.dataEmprestimo = dataEmprestimo;
		this.ultimaCobranca = ultimaCobranca;
		this.dataDevolucao = null;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public LocalDate getDataEmprestimo() {
		return dataEmprestimo;
	}

	public LocalDate getDataDevolucao() {
		return dataDevolucao;
	}

	public void registrarDevolucao(LocalDate dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public boolean estaEmprestado() {
		return dataDevolucao == null;
	}

	@Override
	public String toString() {
		return tipoObjeto + " - " + nomeObjeto + " (Emprestado para: " + nomePessoa + ", Data de empréstimo: "
				+ dataEmprestimo + ", Última cobrança: " + ultimaCobranca
				+ (dataDevolucao != null ? ", Devolvido em: " + dataDevolucao : ", Ainda não devolvido") + ")";
	}
}

class GerenciadorDeEmprestimos {
	private List<Emprestimo> emprestimos;

	public GerenciadorDeEmprestimos() {
		this.emprestimos = new ArrayList<>();
	}

	public void registrarEmprestimo(String tipoObjeto, String nomeObjeto, String nomePessoa) {
		Emprestimo emprestimo = new Emprestimo(tipoObjeto, nomeObjeto, nomePessoa, LocalDate.now(), LocalDate.now());
		emprestimos.add(emprestimo);
	}

	public boolean registrarDevolucao(int indice) {
		if (indice < 1 || indice > emprestimos.size()) {
			return false;
		}
		Emprestimo emprestimo = emprestimos.get(indice - 1);
		if (emprestimo.estaEmprestado()) {
			emprestimo.registrarDevolucao(LocalDate.now());
			return true;
		}
		return false;
	}

	public void exibirEmprestimos() {
		if (emprestimos.isEmpty()) {
			System.out.println("Não há empréstimos registrados.");
		} else {
			for (int i = 0; i < emprestimos.size(); i++) {
				System.out.println((i + 1) + ". " + emprestimos.get(i));
			}
		}
	}

	public void exibirRelatorio(int dias) {
		LocalDate hoje = LocalDate.now();
		boolean encontrou = false;

		for (Emprestimo emprestimo : emprestimos) {
			if (emprestimo.estaEmprestado() && ChronoUnit.DAYS.between(emprestimo.getDataEmprestimo(), hoje) > dias) {
				System.out.println(emprestimo);
				encontrou = true;
			}
		}

		if (!encontrou) {
			System.out.println("Nenhum objeto foi emprestado há mais de " + dias + " dias.");
		}
	}
}

public class ControleDeEmprestimos {
	public static void main(String[] args) {
		GerenciadorDeEmprestimos gerenciador = new GerenciadorDeEmprestimos();
		Scanner scanner = new Scanner(System.in);
		boolean continuar = true;

		System.out.println("Bem-vindo ao programa de Controle de Empréstimos!");

		while (continuar) {
			System.out.println("\nEscolha uma opção:");
			System.out.println("1. Registrar novo empréstimo");
			System.out.println("2. Registrar devolução");
			System.out.println("3. Exibir lista de empréstimos");
			System.out.println("4. Exibir relatório de objetos emprestados há mais de X dias");
			System.out.println("5. Sair");
			System.out.print("Digite sua escolha: ");

			int opcao = scanner.nextInt();
			scanner.nextLine();

			switch (opcao) {
			case 1:
				System.out.print("Digite o tipo do objeto: ");
				String tipo = scanner.nextLine();
				System.out.print("Digite o nome do objeto: ");
				String nome = scanner.nextLine();
				System.out.print("Digite o nome da pessoa: ");
				String pessoa = scanner.nextLine();
				gerenciador.registrarEmprestimo(tipo, nome, pessoa);
				break;
			case 2:
				gerenciador.exibirEmprestimos();
				System.out.print("Digite o número do empréstimo a ser devolvido: ");
				int indice = scanner.nextInt();
				if (gerenciador.registrarDevolucao(indice)) {
					System.out.println("Devolução registrada com sucesso!");
				} else {
					System.out.println("Número inválido!");
				}
				break;
			case 3:
				gerenciador.exibirEmprestimos();
				break;
			case 4:
				System.out.print("Digite o número de dias para o relatório: ");
				int dias = scanner.nextInt();
				gerenciador.exibirRelatorio(dias);
				break;
			case 5:
				continuar = false;
				System.out.println("Encerrando o programa.");
				break;
			default:
				System.out.println("Opção inválida.");
			}
		}

		scanner.close();
	}
}
