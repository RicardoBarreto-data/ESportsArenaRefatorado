
package esportarena.dao;

import esportarena.model.Usuario;
import java.util.List;

public interface UsuarioDAO {
    Usuario buscarPorId(int id);

    Usuario buscarPorEmailSenha(String email, String senha);

    void salvar(Usuario usuario);

    void atualizar(Usuario usuario);

    void deletar(int id);

    List<Usuario> listarTodos();
}
