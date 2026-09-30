package Principal;



import DAO.LivrosDAO;
import DAO.UsuarioDAO;
import Model.Livros;
import Model.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {



    private  Scanner leitura  = new Scanner(System.in);
    ArrayList<Livros> listaLivros = new ArrayList<Livros>();
    private UsuarioDAO usarioDAO = new UsuarioDAO();
    private LivrosDAO livrosDAO = new LivrosDAO();



    public void exibeMenu(){
        var opcao = -1;
        while(opcao !=0){
            var menu = """
                1- Cadastrar Livros
                2- Cadastrar usuario
                3- Listar livros
                4- Listar usuarios
                5- emprestar Livro
                6- Devolver livro
                7- Excluir livro
                8- Excluir usuário
                9 - Alterar Livro
                10 - Alterar Usuario
                
                0 - sair
                """;
            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch(opcao){
                case 1:
                    CadastrarLivro();
                    break;
                case 2:
                    CadastroUsuario();
                    break;
                case 3:
                    listaLivros();
                    break;
                case 4:
                    listarUsuarios();
                    break;

                case 5:
                    EmprestarLivro();
                    break;

                case 6:
                    DevolverLivro();
                    break;

                case 7:
                    ExcluirLivro();
                    break;

                case 8:
                    ExcluirUsuario();
                    break;

                case 9:
                    AlterarLivro();
                    break;

                case 10:
                    AlterarUsuario();
                    break;

                case 0:
                    System.out.println("Saindo do sistema");
                    break;




            }

        }
    }

    public void CadastrarLivro(){

        System.out.println("====== BEM VINDO AO CADASTRO DE LIVROS ======");
        System.out.println("Quantos livros deseja cadastrar?");
        int quantidade = leitura.nextInt();
        leitura.nextLine();

        for (int i = 0; i < quantidade; i++) {
            System.out.println("\nCadastro do livro " + (i + 1));

            System.out.println("Digite o titulo do livro: ");
            String titulo = leitura.nextLine();
            System.out.println("Digite o autor do livro: ");
            String autor = leitura.nextLine();

            Livros livro = new Livros(titulo, autor);

            livrosDAO.cadastrarLivros(livro);

            System.out.println("Livro cadastrado com id: " + livro.getIdLivro());


        }

        System.out.println("======= CADASTRO FINALIZADO ======");
    }

    public void CadastroUsuario() {
        System.out.println("====== BEM VINDO AO CADASTRO DE USUÁRIOS ======");
        System.out.println("Quantos usuários deseja cadastrar?");
        int quantiUsuario = leitura.nextInt();
        leitura.nextLine();
        String cpf;
        for (int i = 0; i < quantiUsuario; i++) {
            System.out.println("Digite o nome do usuário: ");
            String nome = leitura.nextLine();
            while (true) {
                System.out.println("Digite o cpf do usuario: ");
                cpf = leitura.nextLine();
                cpf = cpf.replaceAll("\\D", "");
                if (cpf.length() == 11) {
                    System.out.println("CPF válido");
                    break;
                }
                System.out.println("CPF inválido tente novamente.");
            }

            System.out.println("Digite o email do usuario: ");
            String email = leitura.nextLine();
            System.out.println("Digite o telefone do usuario: ");
            String telefone = leitura.nextLine();
            Usuario usuario = new Usuario(nome, cpf, email, telefone);

            usarioDAO.cadastrarUsuari(usuario);

            System.out.println("ID: " + usuario.getId());
            System.out.println("Nome: " + usuario.getNome());
            System.out.println("CPF: " + usuario.getCpf());
            System.out.println("Email: " + usuario.getEmail());
        }
        System.out.println("======= CADASTRO FINALIZADO ======");
    }


    private void listaLivros() {
        List<Livros> livros = LivrosDAO.listar();
            if (livros.isEmpty()) {
                System.out.println("Nenhum livro cadastrado.");
                return;
            }
            System.out.println("\n====== LIVROS CADASTRADOS ======");
            for (Livros livro : livros) {
                System.out.println(livro);
            }

        }


    private void listarUsuarios() {

        List<Usuario> usuarios = UsuarioDAO.listarUsuario();

        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuario cadastrado.");
            return;
        }
        System.out.println("\n====== USUARIOS CADASTRADOS ======");
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }

    }


    public void EmprestarLivro(){
        System.out.print("Digite o ID do livro: ");
        Long idLivro = leitura.nextLong();

        System.out.print("Digite o ID do usuário: ");
        Long idUsuario = leitura.nextLong();

        livrosDAO.emprestarLivro(idLivro, idUsuario);

    }

    public void DevolverLivro(){
        System.out.println("\n====== DEVOLUÇÃO DE LIVROS======");
        System.out.print("Digite o ID do livro: ");
        Long idLivro = leitura.nextLong();

        livrosDAO.devolverLivro(idLivro);
    }

    public void ExcluirLivro(){
        System.out.println("Por favor digite o id do livro que deseja excluir");
        long id = leitura.nextInt();
        leitura.nextLine();

        livrosDAO.excluirLivro(id);

    }


    public void ExcluirUsuario(){
        System.out.println("Digite o cpf do usuario que deseja excluir");

        String cpf = leitura.nextLine();

        usarioDAO.excluirUsuario(cpf);
    }

    public void AlterarLivro(){
        System.out.println("\n====== ALTERAR LIVRO ======");

        livrosDAO.listar();

        System.out.print("Digite o id do livro: ");
        Long idantigo = leitura.nextLong();
        leitura.nextLine();

        Livros livro = livrosDAO.buscarPorId(idantigo);

        if (livro == null) {
            System.out.println("Livro não encontrado!");
            return;
        }


        System.out.print("Digite o novo nome do livro: ");
        String novoTitulo = leitura.nextLine();

        System.out.print("Digite o novo nome do autor: ");
        String novoAutor = leitura.nextLine();

        livro.setTitulo(novoTitulo);
        livro.setAutor(novoAutor);

        LivrosDAO.AlterarLivro(livro);
    }

    public void AlterarUsuario(){
        System.out.println("\n====== ALTERAR USUARIO ======");

        usarioDAO.listarUsuario();

        System.out.println("Digite o cpf do usuario que deseja alterar: ");
        String cpf = leitura.nextLine();

        System.out.println("Digite o novo nome do usuario");
        String Nome = leitura.nextLine();
        System.out.println("Digite o novo cpf do usuario");
        String novoCpf = leitura.nextLine();
        System.out.println("Digite o novo email do usuario");
        String Email = leitura.nextLine();
        System.out.println("Digite o novo telefone do usuario");
        String Telefone = leitura.nextLine();

        Usuario usuario = new Usuario(Nome,novoCpf,Email, Telefone);

        usarioDAO.AlterarUsuario(cpf,usuario);
    }
}


