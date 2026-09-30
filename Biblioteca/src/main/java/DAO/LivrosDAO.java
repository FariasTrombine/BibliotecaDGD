package DAO;

import Model.Livros;
import Service.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

public class LivrosDAO {
    Livros livro = new Livros();

    public void cadastrarLivros(Livros livro) {

        String sql = """
            INSERT INTO livro (titulo, autor)
            VALUES (?, ?)
            """;

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(
                     sql,
                     Statement.RETURN_GENERATED_KEYS)) {

            comando.setString(1, livro.getTitulo());
            comando.setString(2, livro.getAutor());

            comando.executeUpdate();

            try (ResultSet resultado = comando.getGeneratedKeys()) {

                if (resultado.next()) {

                    Long idGerado = resultado.getLong(1);

                    livro.setIdLivro(idGerado);


                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar livro: " + e.getMessage());
        }
    }

    public static List<Livros> listar(){
        List<Livros> livros = new ArrayList<>();

        String sql = "Select id, titulo, autor, disponivel FROM livro";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql);
             ResultSet resultado = comando.executeQuery()) {

            while (resultado.next()) {

                Livros livro = new Livros(
                        resultado.getString("titulo"),
                        resultado.getString("autor")
                );

                livro.setIdLivro(resultado.getLong("id"));
                livro.setDisponivel(resultado.getBoolean("disponivel"));

                livros.add(livro);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar livros: " + e.getMessage());
        }

        return livros;
    }

    public static Livros buscarPorId(long id){

        String sql = "Select * from livro where id = ?";

        try(Connection conexao = Conexao.conectar();
        PreparedStatement comando = conexao.prepareStatement(sql)){
            comando.setLong(1,id);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()){
                Livros livro = new Livros(
                        resultado.getString("titulo"),
                        resultado.getString("Autor")
                );

                livro.setIdLivro(resultado.getLong("id"));
                livro.setDisponivel(resultado.getBoolean("Disponivel"));

                return livro;
            }
        }catch (SQLException e){
            System.out.println("Erro ao buscar livros: " + e.getMessage());
        }
        return null;

    }
        public void emprestarLivro (Long idLivro, Long idUsuari){

        String sql = """
                UPDATE livro 
                SET disponivel = false,
                usuario_id = ?
                WHERE id = ?
                """;
        try(Connection conexao = Conexao.conectar();
        PreparedStatement comando = conexao.prepareStatement(sql)){

            comando.setLong(1,idUsuari);
            comando.setLong(2,idLivro);

            comando.executeUpdate();
            System.out.println("Livro emprestado com sucesso!");
        } catch (SQLException e){
            System.out.println("Erro ao emprestar livro: " + e.getMessage());
        }

        }

        public void devolverLivro (Long idLivro){
        String sql = """
                UPDATE livro
                SET disponivel = true
                usuario_id = null
                WHERE id = ?
                """;
        try (Connection conexao = Conexao.conectar();
        PreparedStatement comando = conexao.prepareStatement(sql)){
            comando.setLong(1,idLivro);
            comando.executeUpdate();
            System.out.println("Livro devolvido com sucesso!");
        }
        catch (SQLException e){
            System.out.println("Erro ao  devolver livro: " + e.getMessage());
        }
        }

        public void excluirLivro (Long idLivro){

        String sql = """
                DELETE FROM livro
                Where id = ?
                AND disponivel = true
                
                """;

        try (Connection conexao = Conexao.conectar();
        PreparedStatement comando = conexao.prepareStatement(sql)){
            comando.setLong(1,idLivro);

            int linhasAlteradas = comando.executeUpdate();

            if (linhasAlteradas >0 ){
                System.out.println("Livro excluio com sucesso!");
            }
            else {
                System.out.println("Livro não encontrado ou emprestado!");
            }
        } catch (SQLException e){
            System.out.println("Erro ao excluir livro: " + e.getMessage());
        }
        }

        public static void AlterarLivro(Livros livro){
        String sql = """
                UPDATE livro
                SET titulo = ?,
                Autor = ?
                Where id = ?
                """;
        try (Connection conexao = Conexao.conectar();
        PreparedStatement comando = conexao.prepareStatement(sql)){
            comando.setString(1,livro.getTitulo());
            comando.setString(2, livro.getAutor());
            comando.setLong(3, livro.getIdLivro());

            int linhasAlterada = comando.executeUpdate();
            if (linhasAlterada >0){
                System.out.println("livro alterada com sucesso!");
            } else {
                System.out.println("Livro não encontrado ou emprestado!");
            }
        }catch (SQLException e) {
            System.out.println("Erro ao alterar livro: " + e.getMessage());
        }
        }



}
