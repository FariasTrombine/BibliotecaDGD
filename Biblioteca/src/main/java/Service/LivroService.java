package Service;

import Model.Livros;
import Repository.LivrosRepository;

import java.util.List;

public class LivroService {

    private final LivrosRepository livrosRepository;

    public LivroService(LivrosRepository livrosRepository) {
        this.livrosRepository = livrosRepository;
    }


    public void Cadastrar (Livros livro){

        if (livro == null){
            throw  new IllegalArgumentException(
                    "Livro não pode ser nulo");
        }
        if (livro.getTitulo() == null || livro.getTitulo().isBlank()){
            throw  new IllegalArgumentException(
                    "Titulo obrigatório."
            );
        }
        if(livro.getAutor()== null || livro.getAutor().isBlank()){
            throw new IllegalArgumentException(
                    "Nome do autor do livro obrigatório."
            );
        }

        livro.setDisponivel(true);

        livrosRepository.cadastrarLivros(livro);
    }
    public  Livros buscaPorId (Long id){

        if (id == null || id <= 0){
            throw  new IllegalArgumentException(
                    "ID inválido"
            );

        }
        return livrosRepository.buscarPorId(id);
    }

    public List<Livros> listarTodos(){
        return livrosRepository.listarTodos();

    }

    public void alterar (Livros livro){
        if (livro.getIdLivro() == null || livro.getIdLivro() <= 0){
            throw  new IllegalArgumentException(
                    "Id do livro é obrigatório"
            );
        }
        if (livro.getTitulo() == null || livro.getTitulo().isBlank()){
            throw  new IllegalArgumentException(
                    "Titulo do livro e obrigatório."
            );
        }
        if (livro.getAutor() == null || livro.getAutor().isBlank()){
            throw new IllegalArgumentException(
                    "Nome do autor é obrigatório."
            );
        }
        Livros existente = livrosRepository.buscarPorId(livro.getIdLivro());

        if (existente == null){
            throw  new IllegalArgumentException(
                    "Livro não encontrado."
            );
        }
        livrosRepository.Atualizar(livro);
    }
    public void excluir (Long id){
        Livros livro = livrosRepository.buscarPorId(id);

        if (livro == null){
            throw  new IllegalArgumentException(
                    "Livro não encontrado."
            );
        }
        if (!livro.isDisponivel()){
            throw new IllegalArgumentException(
                    "Livro já está emprestado."
            );
        }
        livro.setDisponivel(false);

        livrosRepository.Atualizar(livro);
    }

    public void emprestar (Long id){
        Livros livro = livrosRepository.buscarPorId(id);

        if (livro == null){
            throw  new IllegalArgumentException(
                    "Livro não encontrado."
            );
        }

        if (!livro.isDisponivel()){
            throw new IllegalArgumentException(
                    "Livro ja está emprestado."
            );
        }
        livro.setDisponivel(false);
        livrosRepository.Atualizar(livro);
    }

    public void devolver (Long id){
        Livros livro = livrosRepository.buscarPorId(id);

        if (livro == null ||  !livro.isDisponivel() ){
            throw  new IllegalArgumentException(
                    "O livro já está disponível."
            );
        }
        livro.setDisponivel(true);
        livrosRepository.Atualizar(livro);
    }

}
