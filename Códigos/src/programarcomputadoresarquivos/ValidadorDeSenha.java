package programarcomputadoresarquivos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Usuario {
	private String nome;
	private String senha;

	public Usuario(String nome, String senha) {
		this.nome = nome;
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public String getSenha() {
		return senha;
	}
}

public class ValidadorDeSenha {
	private static final String ARQUIVO_USUARIOS = "usuarios.txt";
	private static final int MAX_TENTATIVAS = 3;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Map<String, String> usuarios = carregarUsuarios();

		while (true) {
			System.out.println("\n=== MENU ===");
			System.out.println("1. Cadastrar usuário");
			System.out.println("2. Autenticar usuário");
			System.out.println("3. Sair");
			System.out.print("Escolha uma opção: ");
			int opcao = scanner.nextInt();
			scanner.nextLine(); 

			switch (opcao) {
			case 1:
				cadastrarUsuario(scanner, usuarios);
				break;
			case 2:
				autenticarUsuario(scanner, usuarios);
				break;
			case 3:
				System.out.println("Encerrando o programa...");
				salvarUsuarios(usuarios);
				return;
			default:
				System.out.println("Opção inválida!");
			}
		}
	}

	private static Map<String, String> carregarUsuarios() {
		Map<String, String> usuarios = new HashMap<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_USUARIOS))) {
			String linha;
			while ((linha = reader.readLine()) != null) {
				String[] partes = linha.split(";");
				if (partes.length == 2) {
					usuarios.put(partes[0], partes[1]);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo de usuários não encontrado. Será criado um novo.");
		} catch (IOException e) {
			System.err.println("Erro ao ler o arquivo de usuários: " + e.getMessage());
		}

		return usuarios;
	}

	private static void salvarUsuarios(Map<String, String> usuarios) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_USUARIOS))) {
			for (Map.Entry<String, String> entrada : usuarios.entrySet()) {
				writer.write(entrada.getKey() + ";" + entrada.getValue());
				writer.newLine();
			}
		} catch (IOException e) {
			System.err.println("Erro ao salvar os usuários: " + e.getMessage());
		}
	}

	private static void cadastrarUsuario(Scanner scanner, Map<String, String> usuarios) {
		System.out.print("Digite o nome de usuário: ");
		String nome = scanner.nextLine();

		if (usuarios.containsKey(nome)) {
			System.out.println("Usuário já cadastrado.");
			return;
		}

		String senha = solicitarSenha(scanner);
		usuarios.put(nome, criptografarSenha(senha));
		System.out.println("Usuário cadastrado com sucesso!");
	}

	private static void autenticarUsuario(Scanner scanner, Map<String, String> usuarios) {
		System.out.print("Digite o nome de usuário: ");
		String nome = scanner.nextLine();

		if (!usuarios.containsKey(nome)) {
			System.out.println("Usuário não encontrado.");
			return;
		}

		String senhaCriptografada = usuarios.get(nome);
		for (int i = 1; i <= MAX_TENTATIVAS; i++) {
			System.out.print("Digite a senha: ");
			String senha = scanner.nextLine();

			if (criptografarSenha(senha).equals(senhaCriptografada)) {
				System.out.println("Autenticação bem-sucedida!");
				return;
			} else {
				System.out.println("Senha incorreta. Tentativa " + i + " de " + MAX_TENTATIVAS);
			}
		}

		System.out.println("Número máximo de tentativas excedido.");
	}

	private static String solicitarSenha(Scanner scanner) {
		while (true) {
			System.out.print("Digite a senha (4 a 8 caracteres): ");
			String senha = scanner.nextLine();

			if (senha.length() < 4 || senha.length() > 8) {
				System.out.println("A senha deve ter entre 4 e 8 caracteres.");
				continue;
			}

			System.out.print("Confirme a senha: ");
			String confirmacao = scanner.nextLine();

			if (!senha.equals(confirmacao)) {
				System.out.println("As senhas não coincidem. Tente novamente.");
			} else {
				return senha;
			}
		}
	}

	private static String criptografarSenha(String senha) {
		StringBuilder criptografada = new StringBuilder();
		for (char c : senha.toCharArray()) {
			criptografada.append((char) (c + 10));
		}
		return criptografada.toString();
	}
}
