package esportarena.service;

import esportarena.dao.JogadorDAO;
import esportarena.dao.mysql.JogadorDAOMySQL;
import esportarena.model.Jogador;

import java.util.List;

public class JogadorService {

    private final JogadorDAO jogadorDAO;

    public JogadorService() {
        this.jogadorDAO = new JogadorDAOMySQL();
    }

    public void salvar(Jogador j) { jogadorDAO.salvar(j); }

    public void atualizar(Jogador j) { jogadorDAO.atualizar(j); }

    public void deletar(int id) { jogadorDAO.deletar(id); }

    public Jogador buscarPorId(int id) { return jogadorDAO.buscarPorId(id); }

    public List<Jogador> listarPorTime(int idTime) {
        return jogadorDAO.listarPorTime(idTime);
    }

    public List<Jogador> listarPorUsuario(int idUsuario) {
        return jogadorDAO.listarPorUsuario(idUsuario);
    }

    public List<Jogador> listarTodos() {
        return jogadorDAO.listarTodos();
    }
}
