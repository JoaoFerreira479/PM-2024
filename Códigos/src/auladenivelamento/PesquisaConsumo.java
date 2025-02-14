package auladenivelamento;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PesquisaConsumo {
	static class Consumidor {
		int numero;
		double consumoKWh;
		String tipo;
		double totalPagar;

		public Consumidor(int numero, double consumoKWh, String tipo, double totalPagar) {
			this.numero = numero;
			this.consumoKWh = consumoKWh;
			this.tipo = tipo.toLowerCase();
			this.totalPagar = totalPagar;
		}
	}

	private final List<Consumidor> consumidores = new ArrayList<>();
	private final double precoKWh;

	public PesquisaConsumo(double precoKWh) {
		this.precoKWh = precoKWh;
	}

	public void adicionarConsumidor(int numero, double consumoKWh, String tipo) {
		double totalPagar = consumoKWh * precoKWh;
		consumidores.add(new Consumidor(numero, consumoKWh, tipo, totalPagar));
	}

	public static boolean validarTipo(String tipo) {
		return tipo.equalsIgnoreCase("residencial") || tipo.equalsIgnoreCase("comercial")
				|| tipo.equalsIgnoreCase("industrial");
	}

	public double calcularMaiorConsumo() {
		return consumidores.stream().mapToDouble(c -> c.consumoKWh).max().orElse(0);
	}

	public double calcularMenorConsumo() {
		return consumidores.stream().mapToDouble(c -> c.consumoKWh).min().orElse(0);
	}

	public Map<String, Double> calcularConsumoPorTipo() {
		return consumidores.stream()
				.collect(Collectors.groupingBy(c -> c.tipo, Collectors.summingDouble(c -> c.consumoKWh)));
	}

	public double calcularMediaConsumo() {
		return consumidores.stream().mapToDouble(c -> c.consumoKWh).average().orElse(0);
	}

	public void exibirResultados() {
		System.out.println("\n--- Resultados por Consumidor ---");
		for (Consumidor c : consumidores) {
			System.out.printf("Consumidor %d: Total a pagar: R$ %.2f\n", c.numero, c.totalPagar);
		}

		System.out.println("\n--- Estatísticas Gerais ---");
		System.out.printf("Maior consumo: %.2f kWh\n", calcularMaiorConsumo());
		System.out.printf("Menor consumo: %.2f kWh\n", calcularMenorConsumo());

		Map<String, Double> consumoPorTipo = calcularConsumoPorTipo();
		System.out.printf("Total consumo residencial: %.2f kWh\n", consumoPorTipo.getOrDefault("residencial", 0.0));
		System.out.printf("Total consumo comercial: %.2f kWh\n", consumoPorTipo.getOrDefault("comercial", 0.0));
		System.out.printf("Total consumo industrial: %.2f kWh\n", consumoPorTipo.getOrDefault("industrial", 0.0));
		System.out.printf("Média geral de consumo: %.2f kWh\n", calcularMediaConsumo());
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Digite o preço do kWh: ");
		double precoKWh = scanner.nextDouble();

		PesquisaConsumo pesquisa = new PesquisaConsumo(precoKWh);

		while (true) {
			System.out.print("Digite o número do consumidor (ou 0 para encerrar): ");
			int numero = scanner.nextInt();
			if (numero == 0)
				break;

			System.out.print("Digite a quantidade de kWh consumida: ");
			double consumoKWh = scanner.nextDouble();

			System.out.print("Digite o tipo do consumidor (residencial, comercial ou industrial): ");
			String tipo = scanner.next();

			if (!validarTipo(tipo)) {
				System.out.println("Tipo inválido! Tente novamente.");
				continue;
			}

			pesquisa.adicionarConsumidor(numero, consumoKWh, tipo);
		}

		pesquisa.exibirResultados();
		scanner.close();
	}
}
