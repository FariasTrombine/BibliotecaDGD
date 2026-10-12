package Repository;

import Model.Usuario;
import Config.Conexao;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
public class UsuarioRepository {

    public void cadastrarUsuari(Usuario usuario){

        String sql = """
                INSERT INTO usuario (nome, cpf, email, telefone)
                VALUES (?,?,?,?)
                """;
        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            comando.setString(1, usuario.getNome());
            comando.setString(2, usuario.getCpf());
            comando.setString(3, usuario.getEmail());
            comando.setString(4, usuario.getTelefone());

            comando.executeUpdate();

            ResultSet resultado = comando.getGeneratedKeys();

            if(resultado.next()){
                usuario.setID(resultado.getLong(1));
            }

        } catch (SQLException e){
            throw  new RuntimeException(
                    "Erro ao cadastrar usuario" + e.getMessage());

        }
    }

    public Usuario buscarPorCPF(String cpf){

        String sql = """
                SELECT * FROM usuario 
                WHERE cpf = ?;
                """;

        try(Connection conexao = Conexao.conectar();
        PreparedStatement comando = conexao.prepareStatement(sql)){

            comando.setString(1, cpf);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()){
                return new Usuario(
                        resultado.getLong("id"),
                        resultado.getString("Nome"),
                        resultado.getString("cpf"),
                        resultado.getString("Email"),
                        resultado.getString("Telefone")
                );
            }
            return null;
        } catch (SQLException e){
            throw  new RuntimeException(
                    "Erro ao buscar usuario" + e.getMessage()
            );
        }

    }

    public List<Usuario> listarTodos(){
        String sql = """
                SELECT * FROM usuario
                ORDER BY id DESC;                
                """;

        List<Usuario> usuarios = new ArrayList<>();

        try(Connection conexao = Conexao.conectar();
           PreparedStatement comando = conexao.prepareStatement(sql);
           ResultSet resultado = comando.executeQuery()){

               while(resultado.next()){

                   Usuario usuario = new Usuario(
                           resultado.getLong("id"),
                           resultado.getString("Nome"),
                           resultado.getString("cpf"),
                           resultado.getString("email"),
                           resultado.getString("telefone")
                   );
                   usuarios.add(usuario);
               }
        } catch (SQLException e){
               throw  new RuntimeException(
                       "Erro ao buscar usuario selecionado" + e.getMessage()
               );
        }
        return  usuarios;
    }

    public void Atualizar(Usuario usuario){
        String sql = """
                UPDATE usuario
                SET nome = ?,
                cpf = ?,
                email = ?,
                telefone = ?
                WHERE id = ?
                """;

        try(Connection conexao = Conexao.conectar();
        PreparedStatement comando = conexao.prepareStatement(sql)){

            comando.setString(1,usuario.getNome());
            comando.setString(2, usuario.getCpf());
            comando.setString(3, usuario.getEmail());
            comando.setString(4, usuario.getTelefone());
            comando.setLong(5, usuario.getId());

            comando.executeUpdate();
        } catch (SQLException e){
            throw  new RuntimeException(
                    "Erro ao atualizar usuario" + e.getMessage()
            );
        }
    }
    public void Excluir (Long id){
        String sql = """
                DELETE FROM usuario
                Where id = ?
                """;

        try(Connection conexao = Conexao.conectar();
        PreparedStatement comando = conexao.prepareStatement(sql)){

            comando.setLong(1,id);

            comando.executeUpdate();
        } catch (SQLException e){
            throw  new RuntimeException(
                    "Erro ao excluir usuario" + e.getMessage()
            );
        }
    }


}
