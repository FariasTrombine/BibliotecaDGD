package Controller;

import Model.Livros;
import Service.LivroService;

import java.util.List;

public class LivroController {

    private final LivroService livroService;
    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    public void CadastrarLivro(Livros livro){
        livroService.Cadastrar(livro);
    }

    public Livros buscaPorId(Long id){
        return livroService.buscaPorId(id);
    }
    public List<Livros> buscaTodos(){
        return livroService.listarTodos();
    }

    public void alterar (Livros livro){
        livroService.alterar(livro);
    }
    public void excluir (Long id){
        livroService.excluir(id);
    }
    public void emprestar (Long id, Long idUsuarioEmprestimo){
        livroService.emprestar(id);
    }
    public void devolver (Long id){
        livroService.devolver(id);
    }
}
