package Model;

public class Livros {

    private static Long proximID= 1L;
    private Long idLivro;
    private String titulo;
    private String autor;
    private boolean Disponivel;

    public Livros(String titulo, String autor) {
        this.idLivro =proximID++;
        this.titulo = titulo;
        this.autor = autor;
        this.Disponivel = true;
    }

    public void emprestarLivro(){
        if(!Disponivel){
            System.out.println("Livro já está emprestado");
            return;
        }
        Disponivel = false;
        System.out.println("Livro emprestado com sucesso");
    }
    public void devolverLivro(){
        if(Disponivel){
            System.out.println("O livro já está disponivel");
        }
        Disponivel = true;
        System.out.println("Livro devolvido com sucesso!");
    }


    public long getIdLivro() {
        return idLivro;
    }


    public String getTitulo (){
        return titulo;
    }


    public String getAutor(){
        return autor;
    }
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }
    public void setAutor(String autor){
        this.autor = autor;
    }


    public boolean isDisponivel(){
        return Disponivel;
    }
    public void setDisponivel(boolean Disponivel){
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