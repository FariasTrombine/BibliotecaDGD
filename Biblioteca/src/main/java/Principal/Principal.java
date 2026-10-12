package Principal;



import Controller.LivroController;
import Controller.UsuarioController;
import Repository.LivrosRepository;
import Repository.UsuarioRepository;
import Model.Livros;
import Model.Usuario;
import Service.LivroService;
import Service.UsuarioService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    Scanner leitura = new Scanner(System.in);
    LivrosRepository livroRepository = new LivrosRepository();
    LivroService livroService = new LivroService(livroRepository);
    LivroController livroController = new LivroController(livroService);

    UsuarioRepository usuarioRepository = new UsuarioRepository();
    UsuarioService usuarioService = new UsuarioService(usuarioRepository);
    UsuarioController usuarioController = new UsuarioController(usuarioService);

    int opcao;


    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    1 - Cadastrar Livros
                    2 - Cadastrar usuario
                    3 - Listar livros
                    4 - Listar usuarios
                    5 - emprestar Livro
                    6 - Devolver livro
                    7 - Excluir livro
                    8 - Excluir usuário
                    9 - Alterar Livro
                    10- Alterar Usuario
                    0 - sair
                    """;
            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("\n====== CADASTRO DE LIVRO ======");
                    System.out.print("Digite o título do livro: ");
                    String titulo = leitura.nextLine();

                    System.out.print("Digite o autor do livro: ");
                    String autor = leitura.nextLine();

                    Livros livro = new Livros(titulo, autor);

                    livroController.CadastrarLivro(livro);

                    break;

                case 2:
                    System.out.println("\n====== CADASTRO DE USUÁRIO ======");

                    System.out.print("Digite o nome do usuário: ");
                    String nome = leitura.nextLine();

                    System.out.print("Digite o CPF: ");
                    String cpf = leitura.nextLine();

                    System.out.print("Digite o e-mail: ");
                    String email = leitura.nextLine();

                    System.out.print("Digite o telefone: ");
                    String telefone = leitura.nextLine();

                    Usuario usuario = new Usuario(
                            nome,
                            cpf,
                            email,
                            telefone
                    );

                    usuarioController.cadastrarUsuario(usuario);

                    break;

                case 3:
                    System.out.println("\n====== LISTA DE LIVROS ======");

                    livroController.buscaTodos();

                    break;
                case 4:
                    System.out.println("\n====== LISTA DE USUÁRIOS ======");

                    usuarioController.buscarTodosUsuarios();

                    break;


                case 5:
                    System.out.println("\n====== EMPRÉSTIMO DE LIVRO ======");

                    System.out.print("Digite o ID do livro: ");
                    Long idLivroEmprestimo = leitura.nextLong();
                    leitura.nextLine();

                    System.out.print("Digite o ID do usuário: ");
                    Long idUsuarioEmprestimo = leitura.nextLong();
                    leitura.nextLine();

                    livroController.emprestar(idLivroEmprestimo, idUsuarioEmprestimo
                    );
                    break;

                case 6:
                    System.out.println("\n====== DEVOLUÇÃO DE LIVRO ======");

                    System.out.print("Digite o ID do livro: ");
                    Long idLivroDevolucao = leitura.nextLong();
                    leitura.nextLine();

                    livroController.devolver(idLivroDevolucao);

                    break;


                case 7:
                    System.out.println("\n====== EXCLUSÃO DE LIVRO ======");

                    System.out.print("Digite o ID do livro: ");
                    Long idLivroExcluir = leitura.nextLong();
                    leitura.nextLine();

                    livroController.excluir(idLivroExcluir);

                    break;

                case 8:
                    System.out.println("\n====== EXCLUSÃO DE USUÁRIO ======");

                    System.out.print("Digite o ID do usuário: ");
                    Long idUsuarioExcluir = leitura.nextLong();
                    leitura.nextLine();

                    usuarioController.excluir(idUsuarioExcluir);

                    break;

                case 9:
                    System.out.println("\n====== ALTERAÇÃO DE LIVRO ======");

                    System.out.print("Digite o ID do livro: ");
                    Long idLivroAlterar = leitura.nextLong();
                    leitura.nextLine();

                    System.out.print("Digite o novo título: ");
                    String novoTitulo = leitura.nextLine();

                    System.out.print("Digite o novo autor: ");
                    String novoAutor = leitura.nextLine();

                    Livros livroAlterado = new Livros(
                            idLivroAlterar,
                            novoTitulo,
                            novoAutor
                    );

                    livroController.alterar(livroAlterado);

                    break;


                case 10:
                    System.out.println("\n====== ALTERAÇÃO DE USUÁRIO ======");

                    System.out.print("Digite o cpf do usuario: ");
                    String cpfusuario= leitura.nextLine();


                    System.out.print("Digite o novo nome: ");
                    String novoNome = leitura.nextLine();

                    System.out.print("Digite o novo CPF: ");
                    String novoCpf = leitura.nextLine();

                    System.out.print("Digite o novo e-mail: ");
                    String novoEmail = leitura.nextLine();

                    System.out.print("Digite o novo telefone: ");
                    String novoTelefone = leitura.nextLine();

                    Usuario usuarioAlterado = new Usuario(
                            novoNome,
                            novoCpf,
                            novoEmail,
                            novoTelefone
                    );

                    usuarioController.Alterar(cpfusuario,usuarioAlterado);

                    break;

                case 0:
                    System.out.println("Saindo do sistema");
                    break;


                default:
                    System.out.println("\nOpção inválida!");
            }

        } while (opcao != 0);
        leitura.close();
    }
}

