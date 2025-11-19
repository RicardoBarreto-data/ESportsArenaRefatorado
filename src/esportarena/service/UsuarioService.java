
package esportarena.service;

import esportarena.dao.UsuarioDAO;
import esportarena.dao.mysql.UsuarioDAOMySQL;
import esportarena.model.Usuario;

import java.util.List;

public class UsuarioService {

    private final UsuarioDAO usuarioDAO;

    public UsuarioService() {
        this.usuarioDAO = new UsuarioDAOMySQL();
    }

    public void salvar(Usuario u) { usuarioDAO.salvar(u); }

    public void atualizar(Usuario u) { usuarioDAO.atualizar(u); }

    public void deletar(int id) { usuarioDAO.deletar(id); }

    public Usuario buscarPorId(int id) { return usuarioDAO.buscarPorId(id); }

    public List<Usuario> listarTodos() { return usuarioDAO.listarTodos(); }

    public Usuario login(String email, String senha) {
        return usuarioDAO.buscarPorEmailSenha(email, senha);
    }
}

