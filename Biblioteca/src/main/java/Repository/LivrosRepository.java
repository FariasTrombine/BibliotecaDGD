package Repository;

import Model.Livros;
import Config.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

public class LivrosRepository {
    static Livros livro = new Livros();

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
            comando.setBoolean(3,livro.isDisponivel());

            comando.executeUpdate();

            ResultSet resultado = comando.getGeneratedKeys();

                if (resultado.next()) {

                    livro.setIdLivro(resultado.getLong(1));
               }

        } catch (SQLException e) {
            throw new RuntimeException(
            "Erro ao cadastrar livro: " + e.getMessage());
        }
    }


    public static Livros buscarPorId(long id){

        String sql = "Select * from livro where id = ?";

        try(Connection conexao = Conexao.conectar();
        PreparedStatement comando = conexao.prepareStatement(sql)){

            comando.setLong(1,id);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {

                return new Livros(
                        resultado.getLong("id"),
                        resultado.getString("Titulo"),
                        resultado.getString("Autor"),
                        resultado.getBoolean("disponivel")
                );
            }

                return null;

        }catch (SQLException e){
           throw new RuntimeException(
                   "Erro ao buscar livro: " + e.getMessage()
           );
        }
        }

        public void Atualizar(Livros livro){

            String sql = """
                    UPDATE livro
                    SET titulo = ?,
                    autor = ?,
                    disponivel = ?
                    WHERE id = ?                    
                    """;

            try (Connection conexao = Conexao.conectar();
                PreparedStatement comando = conexao.prepareStatement(sql)){

                comando.setString(1, livro.getTitulo());
                comando.setString(2,livro.getAutor());
                comando.setBoolean(3, livro.isDisponivel());
                comando.setLong(4, livro.getIdLivro());

                comando.executeUpdate();

            } catch (SQLException e){
                throw new RuntimeException(
                        "Erro ao atualizar livro: " + e.getMessage()
                );
            }


    }
         public List<Livros> listarTodos(){

            String sql = """
                        Select * from livro
                        ORDER BY idLivro
                        """;

            List<Livros> lista = new ArrayList<>();

            try (Connection conexao = Conexao.conectar();
                 PreparedStatement comando = conexao.prepareStatement(sql);
                 ResultSet resultado = comando.executeQuery()){

                while(resultado.next()) {
                    Livros livro = new Livros(
                            resultado.getLong("id"),
                            resultado.getString("Titulo"),
                            resultado.getString("Autor"),
                            resultado.getBoolean("Disponivel"));

                    lista.add(livro);
                }
                } catch (SQLException e){
                throw new RuntimeException(
                        "Erro ao listar livros: " + e.getMessage()
                );
            }
            return lista;

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

            comando.executeUpdate();

        } catch (SQLException e){
            throw new RuntimeException(
                    "Erro ao excluir livro: " + e.getMessage()
            );
        }
        }






}
