package esportarena.service;

import esportarena.dao.TorneioDAO;
import esportarena.dao.mysql.TorneioDAOMySQL;
import esportarena.model.Torneio;
import java.util.List;

public class TorneioService {
    private final TorneioDAO torneioDAO;

    public TorneioService() {
        this.torneioDAO = new TorneioDAOMySQL();
    }

    public void salvar(Torneio t) { torneioDAO.salvar(t); }

    public void atualizar(Torneio t) { torneioDAO.atualizar(t); }

    public void deletar(int id) { torneioDAO.deletar(id); }

    public Torneio buscarPorId(int id) { return torneioDAO.buscarPorId(id); }

    public List<Torneio> listarTodos() { return torneioDAO.listarTodos(); 
    }
}

