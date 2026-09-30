package Model;

public class Livros {

    private Long idLivro;
    private String titulo;
    private String autor;
    private boolean Disponivel;

    public Livros(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.Disponivel = true;
    }

    public Livros() {
    }

    public void emprestarLivro() {
        if (!Disponivel) {
            System.out.println("Livro já está emprestado");
            return;
        }

        Disponivel = false;
        System.out.println("Livro emprestado com sucesso");
    }

    public void devolverLivro() {
        if (Disponivel) {
            System.out.println("O livro já está disponível");
            return;
        }

        Disponivel = true;
        System.out.println("Livro devolvido com sucesso!");
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