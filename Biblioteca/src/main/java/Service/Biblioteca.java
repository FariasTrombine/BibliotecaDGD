package Service;

import DAO.LivrosDAO;
import DAO.UsuarioDAO;
import Model.Livros;
import Model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    List<Livros> listaLivros = new ArrayList<>();
    List<Usuario> listaUsuarios = new ArrayList<>();
    private final LivrosDAO livroDAO = new LivrosDAO();
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    //======================
    //CADASTRAR LIVRO
    //======================

    public void CadastrarLivros(Livros livro){
        listaLivros.add(livro);
        System.out.println("Livro cadastrado com sucesso!");
    }
    //======================
    //CADASTRAR USUARIO
    //======================

    public void CadastrarUsuarios(Usuario usuarios){
        listaUsuarios.add(usuarios);
        System.out.println("Usuario cadastrado com sucesso!");
    }
    //======================
    //LISTAR LIVROS
    //======================

    public void listarLivros(){
        if(listaLivros.isEmpty()){
            System.out.println("Nenhum livro cadastrado.");
            return;
        }
        System.out.println("\n====== LIVROS CADASTRADOS ======");
        for (Livros livro : listaLivros) {
            System.out.println("Os livros cadastrado são\n" + livro);
        }
    }
    //======================
    //LISTAR USUARIOS
    //======================
    public void listarUsuarios(){
        if(listaUsuarios.isEmpty()){
            System.out.println("Nenhum usuario cadastrado.");
            return;
        }
        System.out.println("\n====== USUARIO CADASTRADOS ======");
        for (Usuario usuario : listaUsuarios) {
            System.out.println("Os usuários cadastrado são\n" + usuario);
        }
    }
    //======================
    //Buscar Livro pelo id
    //======================

    public Livros buscarLivro(String titulo){
        for (Livros livro : listaLivros) {

            System.out.println("Comparando: [" + livro.getTitulo() + "] com [" + titulo + "]");

            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                System.out.println("LIVRO ENCONTRADO!");
                return livro;
            }
        }

        System.out.println("LIVRO NÃO ENCONTRADO!");
        return null;
    }


    //======================
    //Buscar Usuario pelo id
    //======================

    public Usuario buscarUsuario(String cpf){
        cpf = cpf.replaceAll("\\D", "");

        for (Usuario usuario : listaUsuarios) {

            String cpfUsuario = usuario.getCpf().replaceAll("\\D", "");

            if (cpfUsuario.equals(cpf)) {
                return usuario;
            }
        }

        return null;
    }
    //======================
    //Emprestar Livros
    //======================

    public void EmprestarLivro(Long idLivro, Long idUsuario){

        Livros livro = LivrosDAO.buscarPorId(idLivro);

        if (livro == null){
            System.out.println("Livro não encontrado");
            return;
        }
        if (!livro.isDisponivel()){
            System.out.println("Livro já está emprestado");
            return;

        }

        Usuario usuario = usuarioDAO.buscarUsuario(idUsuario);

        if (usuario == null){
            System.out.println("Usuário não encontrado");
            return;
        }

        boolean adicionou = usuario.adicionarLivro(livro);

        if(!adicionou){
            return;
        }
        livro.emprestarLivro();
        System.out.println("Livro emprestado com sucesso para o usuario."
                +usuario.getNome());

    }
    //======================
    //Devolver Livros
    //======================

    public void DevolverLivro(Long idLivro){
        Livros livros = livroDAO.buscarPorId(idLivro);

        if(livros == null){
            System.out.println("Livro não encontrado");
            return;
        }

        if (livros.isDisponivel()){
            System.out.println("Esse livro já está disponivel");

            return;
        }
        livroDAO.devolverLivro(idLivro);

    }

    //======================
    //Excluir Livros
    //======================

    public void ExcluirLivro(Long id){
        Livros livro = buscarLivro(String.valueOf(id));

        if (livro == null){
            System.out.println("Livro não encontrado");
            return;
        }
        if (!livro.isDisponivel()){
            System.out.println("Não e  possivel excluir livro emprestado");
            return;
        }
        listaLivros.remove(livro);
        System.out.println("Livro excluido com sucesso.");

    }
    //======================
    //Excluir Usuario
    //======================

    public void ExcluirUsuario(String cpf){
        Usuario usuario = buscarUsuario(cpf);

        if (usuario == null){
            System.out.println("Usuário não encontrado");
            return;
        }

        listaUsuarios.remove(usuario);
        System.out.println("Usuário excluido com sucesso");

    }

    public void AlterarLivro(String titulo, String novoTitulo, String novoAutor){
        Livros livro = buscarLivro(titulo);

        if (livro == null) {
            System.out.println("Não encontrou o livro para alterar.");
            return;
        }

        livro.setTitulo(novoTitulo);
        livro.setAutor(novoAutor);

        System.out.println("Livro alterado com sucesso.");
    }

    public void AlterarUsuario(String cpf, String novoNome, String novocpf, String novoemail, String novoTelefone){
        Usuario usuario = buscarUsuario(cpf);

        if(usuario == null){
            System.out.println("Usuario não encontrado");
            return;
        }
        usuario.setNome(novoNome);
        usuario.setCpf(novocpf);
        usuario.setEmail(novoemail);
        usuario.setTelefone(novoTelefone);
        System.out.println("Usuario Alterado com sucesso.");
    }
}