package Principal;



import Model.Livros;
import Model.Usuario;
import Service.Biblioteca;

import javax.crypto.SealedObject;
import javax.crypto.spec.PSource;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {



    private  Scanner leitura  = new Scanner(System.in);
    ArrayList<Livros> listaLivros = new ArrayList<Livros>();
    private Biblioteca biblioteca = new Biblioteca();


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
            biblioteca.CadastrarLivros(livro);

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
            biblioteca.CadastrarUsuarios(usuario);

            System.out.println("Usuario Cadastrado com sucesso, ID do usuario " + usuario.getId() + ", Nome de Usuario: " + usuario.getNome());
        }
        System.out.println("======= CADASTRO FINALIZADO ======");
    }


    private void listaLivros() {
        System.out.println("\n====== LISTA DE LIVROS ======");
        biblioteca.listarLivros();
    }


    private void listarUsuarios() {
        System.out.println("\n====== LISTA DE USUARIOS ======");
        biblioteca.listarUsuarios();
    }


    public void EmprestarLivro(){
        System.out.println("\n====== EMPRÉSTIMO DE LIVRO ======");
        biblioteca.listarLivros();

        System.out.println("Por favor Digite o nome do livro: ");
        String nome = leitura.nextLine();

        biblioteca.listarUsuarios();

        System.out.println("Por favor Digite o cpf do usuario");
        String cpf = leitura.nextLine();
        biblioteca.EmprestarLivro(nome,cpf);
    }

    public void DevolverLivro(){
        System.out.println("\n====== DEVOLUÇÃO DE LIVROS======");
        biblioteca.listarLivros();

        System.out.println("Por favor digite o nome do livro: ");
        String nome = leitura.nextLine();

        biblioteca.listarUsuarios();
        System.out.println("Por favor digite o cpf do usuario: ");
        String cpf = leitura.nextLine();

        biblioteca.DevolverLivro(nome,cpf);
    }

    public void ExcluirLivro(){
        System.out.println("Por favor digite o id do livro que deseja excluir");
        long id = leitura.nextInt();
        leitura.nextLine();

        biblioteca.ExcluirLivro(id);

    }


    public void ExcluirUsuario(){
        System.out.println("Digite o cpf do usuario que deseja excluir");

        String cpf = leitura.nextLine();

        biblioteca.ExcluirUsuario(cpf);
    }

    public void AlterarLivro(){
        System.out.println("\n====== ALTERAR LIVRO ======");

        biblioteca.listarLivros();

        System.out.print("Digite o nome do livro: ");
        String nome = leitura.nextLine();

        System.out.print("Digite o novo nome do livro: ");
        String novoTitulo = leitura.nextLine();

        System.out.print("Digite o novo nome do autor: ");
        String novoAutor = leitura.nextLine();

        biblioteca.AlterarLivro(nome, novoTitulo, novoAutor);
    }

    public void AlterarUsuario(){
        System.out.println("\n====== ALTERAR USUARIO ======");

        biblioteca.listarUsuarios();

        System.out.println("Digite o cpf do usuario que deseja alterar: ");
        String cpf = leitura.nextLine();

        System.out.println("Digite o novo nome do usuario");
        String novoNome = leitura.nextLine();
        System.out.println("Digite o novo cpf do usuario");
        String novoCpf = leitura.nextLine();
        System.out.println("Digite o novo email do usuario");
        String novoEmail = leitura.nextLine();
        System.out.println("Digite o novo telefone do usuario");
        String novoTelefone = leitura.nextLine();

        biblioteca.AlterarUsuario(cpf,novoNome,novoCpf,novoEmail,novoTelefone);
    }
}


