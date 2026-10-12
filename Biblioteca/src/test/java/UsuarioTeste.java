package Test;

import Model.Usuario;
import Repository.UsuarioRepository;
import Service.UsuarioService;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UsuarioTeste {

    @Test
    void deveRejeitarCpfInvalido(){
        //1. criaremos um usuario com cpf inválido
        Usuario usuario = new Usuario(
                "Guilherme",
                "12345678",
                "guilhermeT@hotmail.com",
                "14998971772"

        );

        //2. Criaremos o serviço que queremos testar
        UsuarioRepository usuarioRepository  = new UsuarioRepository();
        UsuarioService usuarioService = new UsuarioService(usuarioRepository);

        //3. Verificamos se o sistema rejeita o cpf
        assertThrows(
                IllegalArgumentException.class,
                () -> usuarioService.cadastrar(usuario)
        );
    }
    @Test
    void deveRejeitarCpfNulo() {
        UsuarioRepository repository = new UsuarioRepository();
        UsuarioService service = new UsuarioService(repository);

        assertThrows(
                IllegalArgumentException.class,
                () -> service.cadastrar(null)
        );
    }
    @Test
    void deveRejeirarCpfMaior(){

        Usuario usuario = new Usuario(
                "Guilherme",
                "4314913185543",
                "guilherme@test",
                "14996440527"
                );

        UsuarioRepository repository = new UsuarioRepository();
        UsuarioService service = new UsuarioService(repository);

        assertThrows(
                IllegalArgumentException.class,
                () -> service.cadastrar(usuario)
        );



        }
    }


