package programarcomputadoresvariaveisdados;

import java.util.Scanner;

public class ContaTelefonica {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		final double CUSTO_ASSINATURA = 21.40;
		final double CUSTO_IMPULSOS_EXCEDENTES = 0.03;
		final double CUSTO_INTERURBANOS = 0.40; 
		final double CUSTO_CHAMADAS_CELULAR = 0.40; 

		Conta conta = coletarDados(scanner);

		conta.adicionarServico("Impulsos Excedentes", conta.getImpulsosExcedentes(), CUSTO_IMPULSOS_EXCEDENTES);
		conta.adicionarServico("Interurbanos", conta.getImpulsosInterurbanos(), CUSTO_INTERURBANOS);
		conta.adicionarServico("Chamadas para Celular", conta.getImpulsosCelular(), CUSTO_CHAMADAS_CELULAR);

		double total = conta.calcularTotal(CUSTO_ASSINATURA);

		exibirRelatorio(conta, CUSTO_ASSINATURA, total);

		scanner.close();
	}

	private static Conta coletarDados(Scanner scanner) {
		System.out.print("Digite a quantidade de impulsos excedentes (acima de 90): ");
		int impulsosExcedentes = scanner.nextInt();

		System.out.print("Digite o número de impulsos para interurbanos: ");
		int impulsosInterurbanos = scanner.nextInt();

		System.out.print("Digite o número de impulsos para chamadas para celular: ");
		int impulsosCelular = scanner.nextInt();

		return new Conta(impulsosExcedentes, impulsosInterurbanos, impulsosCelular);
	}

	private static void exibirRelatorio(Conta conta, double assinatura, double total) {
		System.out.println("\n--- Conta Telefônica ---");
		System.out.printf("Custo da assinatura: R$ %.2f\n", assinatura);
		conta.exibirServicos();
		System.out.printf("TOTAL A PAGAR: R$ %.2f\n", total);
	}
}

class Conta {
	private int impulsosExcedentes;
	private int impulsosInterurbanos;
	private int impulsosCelular;
	private double totalServicos = 0.0;

	private final StringBuilder servicosDetalhados = new StringBuilder();

	public Conta(int impulsosExcedentes, int impulsosInterurbanos, int impulsosCelular) {
		this.impulsosExcedentes = impulsosExcedentes;
		this.impulsosInterurbanos = impulsosInterurbanos;
		this.impulsosCelular = impulsosCelular;
	}

	public int getImpulsosExcedentes() {
		return impulsosExcedentes;
	}

	public int getImpulsosInterurbanos() {
		return impulsosInterurbanos;
	}

	public int getImpulsosCelular() {
		return impulsosCelular;
	}

	public void adicionarServico(String nomeServico, int quantidade, double custoPorUnidade) {
		double custo = quantidade * custoPorUnidade;
		totalServicos += custo;
		servicosDetalhados.append(String.format("%s: %d impulsos (Custo: R$ %.2f)\n", nomeServico, quantidade, custo));
	}

	public double calcularTotal(double assinatura) {
		return assinatura + totalServicos;
	}

	public void exibirServicos() {
		System.out.print(servicosDetalhados.toString());
	}
}
