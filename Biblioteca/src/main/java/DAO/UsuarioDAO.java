package DAO;

import Model.Usuario;
import Service.Conexao;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
public class UsuarioDAO {

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

            comando.execute();

            try (ResultSet resultado = comando.getGeneratedKeys()) {
                if (resultado.next()){
                    Long idgerado = resultado.getLong(1);
                    usuario.setID(idgerado);
                }
            }




        } catch (SQLException e){
            System.out.println("Erro ao cadastrar usuario." + e.getMessage());

        }
    }

    public static List<Usuario> listarUsuario(){
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario";

        try (Connection conexao = Conexao.conectar();
        PreparedStatement comando = conexao.prepareStatement(sql);
        ResultSet resultado = comando.executeQuery()){

            while (resultado.next()){
                Usuario usuario = new Usuario(
                resultado.getString("nome"),
                resultado.getString("cpf"),
                resultado.getString("email"),
                resultado.getString("telefone")
                );
                usuario.setID(resultado.getLong("id"));

                usuarios.add(usuario);

            }

        } catch (SQLException e){
            System.out.println("Erro ao listar usuario." + e.getMessage());
        }
        return usuarios;
    }

    public Usuario buscarUsuario(long id){
        String sql = "SELECT * FROM usuario WHERE id = ?";

        try(Connection conexao = Conexao.conectar();
        PreparedStatement comando = conexao.prepareStatement(sql)){
            comando.setLong(1,id);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()){
                Usuario usuario = new Usuario(
                        resultado.getString("Nome"),
                        resultado.getString("Cpf"),
                        resultado.getString("Email"),
                        resultado.getString("Telefone")
                );

                usuario.setID(resultado.getLong("id"));
                return usuario;
            }

        } catch (SQLException e){
            System.out.println("Erro ao buscar usuario." + e.getMessage());
        }
        return null;
    }

    public void excluirUsuario(String cpf){
        String sql = """
                DELETE FROM usuario
                Where cpf  = ?
                """;

        try (Connection conexao = Conexao.conectar();
        PreparedStatement comando = conexao.prepareStatement(sql)){
            comando.setString(1, cpf);

            int linhasAlteradas = comando.executeUpdate();
            if (linhasAlteradas > 0){
                System.out.println("Usuario excluido com sucesso!");
            }
            else {
                System.out.println("Usuario não encontrado");
            }

        } catch (SQLException e){
            System.out.println("Erro ao excluir usuario." + e.getMessage());
        }
    }
    public void AlterarUsuario(String cpfAntigo, Usuario usuario  ){
        String sql = """
                UPDATE usuario
                Set nome = ?,
                cpf = ?,
                email = ?,
                telefone = ?
                where cpf = ?
                
                """;

        try (Connection conexao = Conexao.conectar();
        PreparedStatement comando = conexao.prepareStatement(sql)){

            comando.setString(1, usuario.getNome());
            comando.setString(2, usuario.getCpf());
            comando.setString(3, usuario.getEmail());
            comando.setString(4, usuario.getTelefone());
            comando.setString(5, cpfAntigo);


            int linhasAlteradas = comando.executeUpdate();
            if(linhasAlteradas > 0){
                System.out.println("Usuario Alterado com sucesso.");

            } else {
                System.out.println("Usuario não encontrado.");
            }
        } catch (SQLException e){
            System.out.println("Erro ao alterar usuario." + e.getMessage());
        }


    }
}
