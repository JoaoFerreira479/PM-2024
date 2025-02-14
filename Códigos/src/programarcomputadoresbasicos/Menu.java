package programarcomputadoresbasicos;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

	private static final ArrayList<String> clientes = new ArrayList<>();
	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		int opcao;

		do {
			exibirMenu();

			opcao = lerOpcao();

			switch (opcao) {
			case 0:
				System.out.println("Saindo do programa. Até mais!");
				break;
			case 1:
				incluirCliente();
				break;
			case 2:
				alterarCliente();
				break;
			case 3:
				excluirCliente();
				break;
			case 4:
				consultarClientes();
				break;
			default:
				System.out.println("Opção inválida! Tente novamente.");
			}
		} while (opcao != 0);

		scanner.close();
	}

	private static void exibirMenu() {
		System.out.println("\nCadastro de Clientes");
		System.out.println("0 - Fim");
		System.out.println("1 - Inclui");
		System.out.println("2 - Altera");
		System.out.println("3 - Exclui");
		System.out.println("4 - Consulta");
	}

	private static int lerOpcao() {
		int opcao = -1;
		while (true) {
			try {
				System.out.print("Opção: ");
				opcao = Integer.parseInt(scanner.nextLine());
				break;
			} catch (NumberFormatException e) {
				System.out.println("Entrada inválida! Digite um número válido.");
			}
		}
		return opcao;
	}

	private static void incluirCliente() {
		System.out.print("Digite o nome do cliente para inclusão: ");
		String nome = scanner.nextLine().trim();
		if (nome.isEmpty()) {
			System.out.println("O nome do cliente não pode ser vazio!");
			return;
		}
		clientes.add(nome);
		System.out.println("Cliente \"" + nome + "\" incluído com sucesso!");
	}

	private static void alterarCliente() {
		if (clientes.isEmpty()) {
			System.out.println("Nenhum cliente cadastrado para alterar.");
			return;
		}
		listarClientes();
		System.out.print("Digite o número do cliente que deseja alterar: ");
		int indice = lerIndice();
		if (indice < 0 || indice >= clientes.size()) {
			System.out.println("Número inválido!");
			return;
		}
		System.out.print("Digite o novo nome para o cliente: ");
		String novoNome = scanner.nextLine().trim();
		if (novoNome.isEmpty()) {
			System.out.println("O nome do cliente não pode ser vazio!");
			return;
		}
		String nomeAntigo = clientes.set(indice, novoNome);
		System.out.println("Cliente \"" + nomeAntigo + "\" alterado para \"" + novoNome + "\".");
	}

	private static void excluirCliente() {
		if (clientes.isEmpty()) {
			System.out.println("Nenhum cliente cadastrado para excluir.");
			return;
		}
		listarClientes();
		System.out.print("Digite o número do cliente que deseja excluir: ");
		int indice = lerIndice();
		if (indice < 0 || indice >= clientes.size()) {
			System.out.println("Número inválido!");
			return;
		}
		String nome = clientes.remove(indice);
		System.out.println("Cliente \"" + nome + "\" excluído com sucesso!");
	}

	private static void consultarClientes() {
		if (clientes.isEmpty()) {
			System.out.println("Nenhum cliente cadastrado.");
		} else {
			listarClientes();
		}
	}

	private static void listarClientes() {
		System.out.println("\n--- Clientes Cadastrados ---");
		for (int i = 0; i < clientes.size(); i++) {
			System.out.println(i + " - " + clientes.get(i));
		}
	}

	private static int lerIndice() {
		int indice = -1;
		while (true) {
			try {
				System.out.print("Número: ");
				indice = Integer.parseInt(scanner.nextLine());
				break;
			} catch (NumberFormatException e) {
				System.out.println("Entrada inválida! Digite um número válido.");
			}
		}
		
		return indice;
	}
}
