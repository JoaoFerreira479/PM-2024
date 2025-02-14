package programarcomputadoresideiasedesafios;

import java.util.ArrayList;
import java.util.Scanner;

class ItemDeCompra {
	private String nome;
	private int quantidade;

	public ItemDeCompra(String nome, int quantidade) {
		this.nome = nome;
		this.quantidade = quantidade;
	}

	public String getNome() {
		return nome;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public String toString() {
		return nome + " (Quantidade: " + quantidade + ")";
	}
}

class ListaDeCompras {
	private ArrayList<ItemDeCompra> itens;

	public ListaDeCompras() {
		this.itens = new ArrayList<>();
	}

	public void adicionarItem(String nome, int quantidade) {
		for (ItemDeCompra item : itens) {
			if (item.getNome().equalsIgnoreCase(nome)) {
				item.setQuantidade(item.getQuantidade() + quantidade);
				return;
			}
		}
		itens.add(new ItemDeCompra(nome, quantidade));
	}

	public boolean removerItem(int indice) {
		if (indice < 1 || indice > itens.size()) {
			return false;
		}
		itens.remove(indice - 1);
		return true;
	}

	public boolean isVazia() {
		return itens.isEmpty();
	}

	public void exibirLista() {
		if (itens.isEmpty()) {
			System.out.println("A lista de compras está vazia.");
		} else {
			System.out.println("Itens na lista de compras:");
			for (int i = 0; i < itens.size(); i++) {
				System.out.println((i + 1) + ". " + itens.get(i));
			}
		}
	}

	public void imprimirLista() {
		if (itens.isEmpty()) {
			System.out.println("A lista de compras está vazia.");
		} else {
			System.out.println("Lista de compras:");
			for (ItemDeCompra item : itens) {
				System.out.println("- " + item);
			}
		}
	}
}

public class GerenciadorDeListaDeCompras {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ListaDeCompras lista = new ListaDeCompras();
		boolean continuar = true;

		System.out.println("Bem-vindo ao programa de Lista de Compras!");

		while (continuar) {
			System.out.println("\nEscolha uma opção:");
			System.out.println("1. Ver lista de compras");
			System.out.println("2. Adicionar item à lista");
			System.out.println("3. Remover item da lista");
			System.out.println("4. Imprimir lista de compras");
			System.out.println("5. Sair");
			System.out.print("Digite sua escolha: ");

			int opcao = scanner.nextInt();
			scanner.nextLine();

			switch (opcao) {
			case 1:
				lista.exibirLista();
				break;
			case 2:
				adicionarItem(lista, scanner);
				break;
			case 3:
				removerItem(lista, scanner);
				break;
			case 4:
				lista.imprimirLista();
				break;
			case 5:
				continuar = false;
				System.out.println("Encerrando o programa. Obrigado por usar a Lista de Compras!");
				break;
			default:
				System.out.println("Opção inválida! Tente novamente.");
			}
		}

		scanner.close();
	}

	private static void adicionarItem(ListaDeCompras lista, Scanner scanner) {
		System.out.print("\nDigite o nome do item: ");
		String nome = scanner.nextLine();
		System.out.print("Digite a quantidade: ");
		int quantidade = scanner.nextInt();
		scanner.nextLine();
		lista.adicionarItem(nome, quantidade);
		System.out.println("Item \"" + nome + "\" adicionado à lista.");
	}

	private static void removerItem(ListaDeCompras lista, Scanner scanner) {
		if (lista.isVazia()) {
			System.out.println("\nA lista de compras está vazia. Não há itens para remover.");
			return;
		}

		lista.exibirLista();
		System.out.print("\nDigite o número do item que deseja remover: ");
		int indice;

		try {
			indice = scanner.nextInt();
			scanner.nextLine(); 
			if (lista.removerItem(indice)) {
				System.out.println("Item removido com sucesso.");
			} else {
				System.out.println("Número inválido! Tente novamente.");
			}
		} catch (Exception e) {
			System.out.println("Entrada inválida! Tente novamente.");
			scanner.nextLine(); 
		}
	}
}
