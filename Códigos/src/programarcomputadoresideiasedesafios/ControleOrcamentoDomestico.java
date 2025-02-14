package programarcomputadoresideiasedesafios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

enum TipoTransacao {
	RECEITA, DESPESA
}

class Transacao {
	private TipoTransacao tipo;
	private String categoria;
	private double valor;
	private LocalDate data;

	public Transacao(TipoTransacao tipo, String categoria, double valor, LocalDate data) {
		this.tipo = tipo;
		this.categoria = categoria;
		this.valor = valor;
		this.data = data;
	}

	public TipoTransacao getTipo() {
		return tipo;
	}

	public double getValor() {
		return valor;
	}

	public LocalDate getData() {
		return data;
	}

	public String getCategoria() {
		return categoria;
	}

	@Override
	public String toString() {
		return tipo + " - Categoria: " + categoria + ", Valor: " + valor + ", Data: " + data;
	}
}

class OrcamentoDomestico {
	private List<Transacao> transacoes;

	public OrcamentoDomestico() {
		this.transacoes = new ArrayList<>();
	}

	public void adicionarTransacao(TipoTransacao tipo, String categoria, double valor, LocalDate data) {
		transacoes.add(new Transacao(tipo, categoria, valor, data));
	}

	public double calcularSaldo() {
		double saldo = 0.0;
		for (Transacao transacao : transacoes) {
			saldo += (transacao.getTipo() == TipoTransacao.RECEITA ? transacao.getValor() : -transacao.getValor());
		}
		return saldo;
	}

	public List<Transacao> listarTransacoes() {
		return transacoes;
	}

	public Map<String, Double> calcularResumoPorCategoria() {
		Map<String, Double> resumo = new HashMap<>();
		for (Transacao transacao : transacoes) {
			resumo.put(transacao.getCategoria(), resumo.getOrDefault(transacao.getCategoria(), 0.0)
					+ (transacao.getTipo() == TipoTransacao.RECEITA ? transacao.getValor() : -transacao.getValor()));
		}
		return resumo;
	}

	public Map<Integer, Double> calcularResumoMensal(int ano) {
		Map<Integer, Double> resumoMensal = new HashMap<>();
		for (Transacao transacao : transacoes) {
			if (transacao.getData().getYear() == ano) {
				int mes = transacao.getData().getMonthValue();
				resumoMensal.put(mes,
						resumoMensal.getOrDefault(mes, 0.0)
								+ (transacao.getTipo() == TipoTransacao.RECEITA ? transacao.getValor()
										: -transacao.getValor()));
			}
		}
		return resumoMensal;
	}
}

public class ControleOrcamentoDomestico {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		OrcamentoDomestico orcamento = new OrcamentoDomestico();
		boolean continuar = true;

		System.out.println("Bem-vindo ao programa de Orçamento Doméstico!");

		while (continuar) {
			System.out.println("\nEscolha uma opção:");
			System.out.println("1. Adicionar Receita");
			System.out.println("2. Adicionar Despesa");
			System.out.println("3. Exibir Saldo Atual");
			System.out.println("4. Exibir Resumo por Categoria");
			System.out.println("5. Exibir Resumo Mensal");
			System.out.println("6. Listar todas as Transações");
			System.out.println("7. Sair");
			System.out.print("Digite sua escolha: ");

			int opcao = scanner.nextInt();
			scanner.nextLine(); 

			switch (opcao) {
			case 1:
			case 2:
				adicionarTransacao(orcamento, scanner, opcao == 1 ? TipoTransacao.RECEITA : TipoTransacao.DESPESA);
				break;
			case 3:
				System.out.printf("Saldo atual: %.2f\n", orcamento.calcularSaldo());
				break;
			case 4:
				exibirResumoPorCategoria(orcamento);
				break;
			case 5:
				exibirResumoMensal(orcamento, scanner);
				break;
			case 6:
				listarTransacoes(orcamento);
				break;
			case 7:
				continuar = false;
				System.out.println("Encerrando o programa. Obrigado por usar o Orçamento Doméstico!");
				break;
			default:
				System.out.println("Opção inválida! Tente novamente.");
			}
		}

		scanner.close();
	}

	private static void adicionarTransacao(OrcamentoDomestico orcamento, Scanner scanner, TipoTransacao tipo) {
		System.out.print("Digite a categoria: ");
		String categoria = scanner.nextLine();
		System.out.print("Digite o valor: ");
		double valor = scanner.nextDouble();
		System.out.print("Digite a data (yyyy-mm-dd): ");
		String dataInput = scanner.next();
		LocalDate data = LocalDate.parse(dataInput);

		orcamento.adicionarTransacao(tipo, categoria, valor, data);
		System.out.println(tipo + " adicionada com sucesso!");
	}

	private static void exibirResumoPorCategoria(OrcamentoDomestico orcamento) {
		Map<String, Double> resumo = orcamento.calcularResumoPorCategoria();
		System.out.println("Resumo por categoria:");
		for (Map.Entry<String, Double> entrada : resumo.entrySet()) {
			System.out.printf("Categoria: %s, Saldo: %.2f\n", entrada.getKey(), entrada.getValue());
		}
	}

	private static void exibirResumoMensal(OrcamentoDomestico orcamento, Scanner scanner) {
		System.out.print("Digite o ano (yyyy): ");
		int ano = scanner.nextInt();
		Map<Integer, Double> resumoMensal = orcamento.calcularResumoMensal(ano);
		System.out.println("Resumo mensal:");
		for (Map.Entry<Integer, Double> entrada : resumoMensal.entrySet()) {
			System.out.printf("Mês: %d, Saldo: %.2f\n", entrada.getKey(), entrada.getValue());
		}
	}

	private static void listarTransacoes(OrcamentoDomestico orcamento) {
		List<Transacao> transacoes = orcamento.listarTransacoes();
		if (transacoes.isEmpty()) {
			System.out.println("Nenhuma transação registrada.");
		} else {
			System.out.println("Lista de transações:");
			for (Transacao transacao : transacoes) {
				System.out.println(transacao);
			}
		}
	}
}
