package Controller;

import Model.Usuario;
import Service.UsuarioService;

import java.util.List;

public class UsuarioController {
    private final UsuarioService usuarioService;
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public void cadastrarUsuario(Usuario usuario) {
        usuarioService.cadastrar(usuario);
    }
    public Usuario buscarUsuario(String cpf) {
        return usuarioService.buscarPorId(cpf);
    }
    public List<Usuario> buscarTodosUsuarios() {
        return usuarioService.buscarTodos();
    }
    public void Alterar(String cpfAtual, Usuario usuario) {
        usuarioService.alterar(cpfAtual, usuario);
    }
    public void excluir(Long id) {
        usuarioService.Excluir(id);
    }
}
