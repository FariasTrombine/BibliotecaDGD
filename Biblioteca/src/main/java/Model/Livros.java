package Model;

public class Livros {

    private Long idLivro;
    private String titulo;
    private String autor;
    private boolean Disponivel;

    public Livros(Long idLivro,String titulo, String autor, boolean Disponivel) {
        this.titulo = titulo;
        this.autor = autor;
        this.Disponivel = true;
    }

    public Livros() {
    }

    public Livros(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    public Livros(Long idLivroAlterar, String novoTitulo, String novoAutor) {
        this.titulo = novoTitulo;
        this.autor = novoAutor;

    }

    public Long getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(Long id) {
        this.idLivro = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public boolean isDisponivel() {
        return Disponivel;
    }

    public void setDisponivel(boolean Disponivel) {
        this.Disponivel = Disponivel;
    }

    @Override
    public String toString() {
        return "ID: " + idLivro
                + "\nTitulo: " + titulo
                + "\nAutor: " + autor
                + "\nDisponivel: " + Disponivel;
    }
}