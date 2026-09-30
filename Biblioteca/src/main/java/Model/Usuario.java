package Model;


import java.util.ArrayList;
import java.util.List;

public class Usuario {


    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private List<Livros> livros = new ArrayList<>();

    public Usuario() {}

    public Usuario( String nome, String cpf, String email, String telefone, List<Livros> livros) {

        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException("CPF inválido");
        }

        cpf = cpf.replaceAll("\\D", ""); //isso daqui limpa todos os espaço entre letras ou numeros
        if (cpf.length() != 11) { // aqui estou dizendo que se o cpf for diferente de 11 digito e invalido
            System.out.println("CPF inválido");
        }


        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.livros = new ArrayList<>();

    }

    public Usuario(String nome, String cpf, String email, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
    }


    public boolean adicionarLivro(Livros livro){
        if(livros.size()>=3){
            System.out.println("O usuário já possui 3 livros emprestado");
            return false;
        }
        livros.add(livro);
        return true;
    }
    public boolean devolverLivro(Livros livro){
        if(livros.contains(livro)){
            System.out.println("Este livro não está emprestado para este usuário");
            return false;
        }
        livros.remove(livro);
        return true;
    }

    public Long getId(){
        return id;

    }
    public void setID(long id) {
        this.id = id;
    }

    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public String getCpf(){
        return cpf;
    }
    public void setCpf(String cpf){
        this.cpf = cpf;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getTelefone(){
        return telefone;
    }
    public void setTelefone(String telefone){
        this.telefone = telefone;
    }
    public List<Livros> listarLivros(){
        return livros;
    }

    @Override
    public String toString(){
        return "ID: " + id
                + "\nNome: " + nome
                + "\nCPF: " + cpf
                + "\nEmail: " + email
                +"\nTelefone: " + telefone;
    }



}