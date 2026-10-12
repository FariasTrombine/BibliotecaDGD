package Service;

import Model.Usuario;
import Repository.UsuarioRepository;

import java.util.List;

public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService (UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public void cadastrar (Usuario usuario){

        validarUsario(usuario);



        String cpf = usuario.getCpf().replaceAll("\\D", "");

        if (cpf.length() != 11 ) {
            throw new IllegalArgumentException(
                    "Cpf deve possuir 11 digitos"
            );
        }


        usuario.setCpf(cpf);
        usuarioRepository.cadastrarUsuari(usuario);
    }

    //continua com a classe de buscar por id

    public Usuario buscarPorId(String cpf) {

        if (cpf == null){
            throw new IllegalArgumentException(
                    "CPF inválido."
            );
        }
        return usuarioRepository.buscarPorCPF(cpf);

    }

    public List<Usuario> buscarTodos(){
        return usuarioRepository.listarTodos();
    }

    public void alterar (String cpfAtual, Usuario usuario){
        validarUsario(usuario);

        if (cpfAtual == null || cpfAtual.isBlank()) {
            throw new IllegalArgumentException(
                    "CPF atual é obrigatório."
            );
        }

        // Limpa o CPF antigo, deixando somente números
        cpfAtual = cpfAtual.replaceAll("\\D", "");

        // Busca pelo CPF antigo
        Usuario existente = usuarioRepository.buscarPorCPF(cpfAtual);

        if (existente == null) {
            throw new IllegalArgumentException(
                    "Usuário não encontrado."
            );
        }

        // Valida o novo CPF
        String novoCpf = usuario.getCpf().replaceAll("\\D", "");

        if (novoCpf.length() != 11) {
            throw new IllegalArgumentException(
                    "CPF deve possuir 11 dígitos."
            );
        }

        usuario.setCpf(novoCpf);

        // Preserva o ID do cadastro encontrado
        usuario.setID(existente.getId());

        // Atualiza os dados no banco
        usuarioRepository.Atualizar(usuario);


        }
        public void Excluir (Long id){
        Usuario usuario = usuarioRepository.buscarPorCPF(id.toString());

        if (usuario == null){
            throw new IllegalArgumentException(
                    "Usuário não encontrado."
            );
        }
        usuarioRepository.Excluir(usuario.getId());
    }

    private void validarUsario(Usuario usuario) {

        if (usuario == null ){
            throw new IllegalArgumentException(
                    "Usuário não pode ser nulo."
            );
        }
        if (usuario.getNome() == null || usuario.getNome().isBlank()){
            throw new IllegalArgumentException(
                    "Nome do usuario não pode ser nulo ou em branco."
            );
        }
        if (usuario.getCpf() == null || usuario.getCpf().isBlank()){
            throw  new IllegalArgumentException(
                    "Cpf do usuario não pode ser nulo ou em branco."
            );
        }

        if (usuario.getEmail() == null || usuario.getEmail().isBlank()){
            throw new IllegalArgumentException(
                    "Email do usuario não pode ser nulo ou em branco."
            );
        }
        if (usuario.getTelefone() == null || usuario.getTelefone().isBlank()){
            throw new IllegalArgumentException(
                    "Telefone do usuario não pode ser nulo ou em branco."
            );
        }

    }
}
