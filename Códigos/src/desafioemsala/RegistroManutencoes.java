package desafioemsala;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Manutencao implements Serializable {
	private static final long serialVersionUID = 1L;
	private String tipoServico;
	private LocalDate data;
	private String nomeResponsavel;
	private int prazoGarantia;
	private String observacoes;

	public Manutencao(String tipoServico, LocalDate data, String nomeResponsavel, int prazoGarantia,
			String observacoes) {
		this.tipoServico = tipoServico;
		this.data = data;
		this.nomeResponsavel = nomeResponsavel;
		this.prazoGarantia = prazoGarantia;
		this.observacoes = observacoes;
	}

	public String getTipoServico() {
		return tipoServico;
	}

	public LocalDate getData() {
		return data;
	}

	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	public int getPrazoGarantia() {
		return prazoGarantia;
	}

	public String getObservacoes() {
		return observacoes;
	}

	@Override
	public String toString() {
		return "Manutenção: " + tipoServico + "\nData: " + data + "\nResponsável: " + nomeResponsavel + "\nGarantia: "
				+ prazoGarantia + " meses" + "\nObservações: " + observacoes + "\n";
	}
}

class GerenciadorManutencoes {
	private Map<String, List<Manutencao>> manutencoesPorTipo;

	public GerenciadorManutencoes() {
		this.manutencoesPorTipo = new HashMap<>();
	}

	public void adicionarManutencao(Manutencao manutencao) {
		manutencoesPorTipo.computeIfAbsent(manutencao.getTipoServico().toLowerCase(), k -> new ArrayList<>())
				.add(manutencao);
	}

	public void listarManutencoes() {
		if (manutencoesPorTipo.isEmpty()) {
			System.out.println("Nenhuma manutenção registrada.");
		} else {
			System.out.println("\n--- Manutenções Registradas ---");
			for (String tipo : manutencoesPorTipo.keySet()) {
				System.out.println("Tipo de Serviço: " + tipo);
				for (Manutencao manutencao : manutencoesPorTipo.get(tipo)) {
					System.out.println(manutencao);
				}
			}
		}
	}

	public void buscarManutencoesPorTipo(String tipoServico) {
		List<Manutencao> manutencoes = manutencoesPorTipo.get(tipoServico.toLowerCase());
		if (manutencoes == null || manutencoes.isEmpty()) {
			System.out.println("Nenhuma manutenção encontrada para o tipo de serviço: " + tipoServico);
		} else {
			System.out.println("\n--- Manutenções do Tipo: " + tipoServico + " ---");
			for (Manutencao manutencao : manutencoes) {
				System.out.println(manutencao);
			}
		}
	}

	public void removerManutencao(String tipoServico, LocalDate data) {
		List<Manutencao> manutencoes = manutencoesPorTipo.get(tipoServico.toLowerCase());
		if (manutencoes != null) {
			manutencoes.removeIf(manutencao -> manutencao.getData().equals(data));
			if (manutencoes.isEmpty()) {
				manutencoesPorTipo.remove(tipoServico.toLowerCase());
			}
			System.out.println("Manutenção removida com sucesso.");
		} else {
			System.out.println("Nenhuma manutenção encontrada para remoção.");
		}
	}

	public void salvarEmArquivo(String nomeArquivo) throws IOException {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
			oos.writeObject(manutencoesPorTipo);
		}
	}

	@SuppressWarnings("unchecked")
	public void carregarDeArquivo(String nomeArquivo) throws IOException, ClassNotFoundException {
		File file = new File(nomeArquivo);
		if (!file.exists()) {
			return;
		}
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
			manutencoesPorTipo = (Map<String, List<Manutencao>>) ois.readObject();
		}
	}
}

public class RegistroManutencoes {
	private static final String NOME_ARQUIVO = "manutencoes.dat";
	private static final Scanner scanner = new Scanner(System.in);
	private static final GerenciadorManutencoes gerenciador = new GerenciadorManutencoes();

	public static void main(String[] args) {
		try {
			gerenciador.carregarDeArquivo(NOME_ARQUIVO);
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Nenhum dado prévio encontrado. Iniciando novo registro.");
		}

		while (true) {
			exibirMenu();
			int opcao = lerInteiro("Escolha uma opção: ");

			switch (opcao) {
			case 1:
				registrarManutencao();
				break;
			case 2:
				gerenciador.listarManutencoes();
				break;
			case 3:
				buscarManutencoes();
				break;
			case 4:
				removerManutencao();
				break;
			case 5:
				try {
					gerenciador.salvarEmArquivo(NOME_ARQUIVO);
					System.out.println("Dados salvos com sucesso. Até mais!");
				} catch (IOException e) {
					System.out.println("Erro ao salvar os dados: " + e.getMessage());
				}
				return;
			default:
				System.out.println("Opção inválida! Tente novamente.");
			}
		}
	}

	private static void exibirMenu() {
		System.out.println("\n--- Menu de Manutenções ---");
		System.out.println("1. Registrar nova manutenção");
		System.out.println("2. Listar manutenções registradas");
		System.out.println("3. Buscar manutenções por tipo de serviço");
		System.out.println("4. Remover manutenção");
		System.out.println("5. Sair");
	}

	private static void registrarManutencao() {
		System.out.print("Digite o tipo de serviço: ");
		String tipoServico = scanner.nextLine().trim();
		if (tipoServico.isEmpty()) {
			System.out.println("Tipo de serviço não pode ser vazio.");
			return;
		}

		LocalDate data;
		try {
			System.out.print("Digite a data (yyyy-mm-dd): ");
			String dataInput = scanner.nextLine();
			data = LocalDate.parse(dataInput);
		} catch (DateTimeParseException e) {
			System.out.println("Data inválida. Use o formato yyyy-mm-dd.");
			return;
		}

		System.out.print("Digite o nome do responsável: ");
		String nomeResponsavel = scanner.nextLine().trim();
		if (nomeResponsavel.isEmpty()) {
			System.out.println("Nome do responsável não pode ser vazio.");
			return;
		}

		int prazoGarantia = lerInteiro("Digite o prazo de garantia (em meses): ");
		if (prazoGarantia <= 0) {
			System.out.println("O prazo de garantia deve ser maior que zero.");
			return;
		}

		System.out.print("Digite as observações: ");
		String observacoes = scanner.nextLine();

		Manutencao manutencao = new Manutencao(tipoServico, data, nomeResponsavel, prazoGarantia, observacoes);
		gerenciador.adicionarManutencao(manutencao);
		System.out.println("Manutenção registrada com sucesso!");
	}

	private static void buscarManutencoes() {
		System.out.print("Digite o tipo de serviço para busca: ");
		String tipoServico = scanner.nextLine().trim();
		if (tipoServico.isEmpty()) {
			System.out.println("Tipo de serviço não pode ser vazio.");
			return;
		}
		gerenciador.buscarManutencoesPorTipo(tipoServico);
	}

	private static void removerManutencao() {
		System.out.print("Digite o tipo de serviço da manutenção a ser removida: ");
		String tipoServico = scanner.nextLine().trim();
		if (tipoServico.isEmpty()) {
			System.out.println("Tipo de serviço não pode ser vazio.");
			return;
		}

		LocalDate data;
		try {
			System.out.print("Digite a data da manutenção a ser removida (yyyy-mm-dd): ");
			String dataInput = scanner.nextLine();
			data = LocalDate.parse(dataInput);
		} catch (DateTimeParseException e) {
			System.out.println("Data inválida. Use o formato yyyy-mm-dd.");
			return;
		}

		gerenciador.removerManutencao(tipoServico, data);
	}

	private static int lerInteiro(String mensagem) {
		int valor = -1;
		while (valor == -1) {
			try {
				System.out.print(mensagem);
				valor = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Entrada inválida. Digite um número inteiro.");
			}
		}
		return valor;
	}
}
