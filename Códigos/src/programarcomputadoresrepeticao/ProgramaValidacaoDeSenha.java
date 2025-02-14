package programarcomputadoresrepeticao;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Autenticador {
	private final Map<String, String> usuarios;

	public Autenticador() {
		this.usuarios = new HashMap<>();
	}

	public void inicializarUsuarios() {
		usuarios.put("usuario1", "senha123");
		usuarios.put("admin", "admin123");
		usuarios.put("teste", "teste123");
	}

	public boolean validarCredenciais(String nome, String senha) {
		if (!usuarios.containsKey(nome)) {
			return false;
		}
		return usuarios.get(nome).equals(senha);
	}
}

public class ProgramaValidacaoDeSenha {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Autenticador autenticador = new Autenticador();
		autenticador.inicializarUsuarios();

		int maxTentativas = 3;

		System.out.println("Bem-vindo ao sistema de autenticação.");

		for (int tentativa = 1; tentativa <= maxTentativas; tentativa++) {
			System.out.print("Digite seu nome de usuário: ");
			String nome = scanner.nextLine();

			if (!validarTamanhoEntrada(nome, 4, 8)) {
				System.out.println("Erro: O nome deve ter entre 4 e 8 caracteres.");
				continue;
			}

			System.out.print("Digite sua senha: ");
			String senha = scanner.nextLine();

			if (!validarTamanhoEntrada(senha, 4, 8)) {
				System.out.println("Erro: A senha deve ter entre 4 e 8 caracteres.");
				continue;
			}

			if (autenticador.validarCredenciais(nome, senha)) {
				System.out.println("Autenticação bem-sucedida. Bem-vindo, " + nome + "!");
				scanner.close();
				return;
			} else {
				System.out.println("Erro: Nome de usuário ou senha incorretos.");
				int tentativasRestantes = maxTentativas - tentativa;
				if (tentativasRestantes > 0) {
					System.out.println("Você ainda tem " + tentativasRestantes + " tentativa(s).");
				}
			}
		}

		System.out.println("Número máximo de tentativas atingido. Acesso bloqueado.");
		scanner.close();
	}

	private static boolean validarTamanhoEntrada(String entrada, int min, int max) {
		return entrada.length() >= min && entrada.length() <= max;
	}
}
