package banco;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

abstract class Conta {
	private String numero;
	private double saldo;

	public Conta(String numero, double saldo) {
		if (numero == null || numero.isEmpty()) {
			throw new IllegalArgumentException("Número da conta não pode ser nulo ou vazio.");
		}
		this.numero = numero;
		this.saldo = saldo;
	}

	public String getNumero() {
		return numero;
	}

	public double getSaldo() {
		return saldo;
	}

	public void depositar(double valor) {
		if (valor <= 0) {
			throw new IllegalArgumentException("Valor para depósito deve ser maior que zero.");
		}
		saldo += valor;
	}

	public void sacar(double valor) {
		if (valor <= 0) {
			throw new IllegalArgumentException("Valor para saque deve ser maior que zero.");
		}
		if (saldo < valor) {
			throw new IllegalArgumentException("Saldo insuficiente.");
		}
		saldo -= valor;
	}

	public abstract String gerarExtrato();
}

class ContaCorrente extends Conta {
	private double limiteChequeEspecial;

	public ContaCorrente(String numero, double saldo, double limiteChequeEspecial) {
		super(numero, saldo);
		this.limiteChequeEspecial = limiteChequeEspecial;
	}

	@Override
	public String gerarExtrato() {
		return "Conta Corrente: " + getNumero() + "\nSaldo: " + getSaldo() + "\nLimite Cheque Especial: "
				+ limiteChequeEspecial;
	}
}

class ContaPoupanca extends Conta {
	private double rendimentoMensal;

	public ContaPoupanca(String numero, double saldo, double rendimentoMensal) {
		super(numero, saldo);
		this.rendimentoMensal = rendimentoMensal;
	}

	@Override
	public String gerarExtrato() {
		return "Conta Poupança: " + getNumero() + "\nSaldo: " + getSaldo() + "\nRendimento Mensal: " + rendimentoMensal;
	}
}

class FundoDeRendaFixa extends Conta {
	private double taxaAdministracao;

	public FundoDeRendaFixa(String numero, double saldo, double taxaAdministracao) {
		super(numero, saldo);
		this.taxaAdministracao = taxaAdministracao;
	}

	@Override
	public String gerarExtrato() {
		return "Fundo de Renda Fixa: " + getNumero() + "\nSaldo: " + getSaldo() + "\nTaxa de Administração: "
				+ taxaAdministracao;
	}
}

class Cliente {
	private String nome;
	private String telefone;
	private String endereco;
	private String cpfCnpj;
	private List<Conta> contas;

	public Cliente(String nome, String telefone, String endereco, String cpfCnpj) {
		if (nome == null || nome.isEmpty() || cpfCnpj == null || cpfCnpj.isEmpty()) {
			throw new IllegalArgumentException("Nome e CPF/CNPJ não podem ser nulos ou vazios.");
		}
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
		this.cpfCnpj = cpfCnpj;
		this.contas = new ArrayList<>();
	}

	public void adicionarConta(Conta conta) {
		if (conta == null) {
			throw new IllegalArgumentException("Conta não pode ser nula.");
		}
		contas.add(conta);
	}

	public double investimentoTotal() {
		return contas.stream().mapToDouble(Conta::getSaldo).sum();
	}

	public String gerarExtratoContas() {
		String informacoesCliente = "Cliente: " + nome + "\nTelefone: " + telefone + "\nEndereço: " + endereco
				+ "\nCPF/CNPJ: " + cpfCnpj + "\n";
		return informacoesCliente + contas.stream().map(Conta::gerarExtrato).collect(Collectors.joining("\n\n"));
	}

	@Override
	public String toString() {
		return "Cliente: " + nome + "\nTelefone: " + telefone + "\nEndereço: " + endereco + "\nCPF/CNPJ: " + cpfCnpj;
	}

	public String getNome() {
		return nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}
}

class Banco {
	private Map<String, Cliente> clientes;

	public Banco() {
		this.clientes = new HashMap<>();
	}

	public void adicionarCliente(Cliente cliente) {
		if (clientes.containsKey(cliente.getCpfCnpj())) {
			throw new IllegalArgumentException("Cliente com este CPF/CNPJ já está cadastrado.");
		}
		clientes.put(cliente.getCpfCnpj(), cliente);
	}

	public Cliente buscarCliente(String cpfCnpj) {
		return clientes.get(cpfCnpj);
	}

	public String gerarExtratos() {
		return clientes.values().stream().map(Cliente::gerarExtratoContas).collect(Collectors.joining("\n\n"));
	}
}

public class SistemaBancario {
	public static void main(String[] args) {
		Banco banco = new Banco();

		Cliente cliente = new Cliente("João Silva", "123456789", "Rua A, 123", "123.456.789-00");
		ContaCorrente cc = new ContaCorrente("001", 1000.0, 500.0);
		ContaPoupanca cp = new ContaPoupanca("002", 2000.0, 0.02);
		FundoDeRendaFixa frf = new FundoDeRendaFixa("003", 5000.0, 0.01);

		cliente.adicionarConta(cc);
		cliente.adicionarConta(cp);
		cliente.adicionarConta(frf);

		banco.adicionarCliente(cliente);

		System.out.println("Extratos de Todos os Clientes:");
		System.out.println(banco.gerarExtratos());

		System.out.println("\nInvestimento Total do Cliente:");
		System.out.println(cliente.investimentoTotal());
	}
}
