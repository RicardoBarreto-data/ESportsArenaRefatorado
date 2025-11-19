package esportarena.service;

import esportarena.dao.InscricaoDAO;
import esportarena.dao.mysql.InscricaoDAOMySQL;
import esportarena.model.Inscricao;

import java.util.List;

public class InscricaoService {

    private final InscricaoDAO inscricaoDAO;

    public InscricaoService() {
        this.inscricaoDAO = new InscricaoDAOMySQL();
    }

    public void salvar(Inscricao i) { inscricaoDAO.salvar(i); }

    public void deletar(int id) { inscricaoDAO.deletar(id); }

    public List<Inscricao> listarPorTorneio(int idTorneio) {
        return inscricaoDAO.listarPorTorneio(idTorneio);
    }

    public List<Inscricao> listarPorJogador(int idJogador) {
        return inscricaoDAO.listarPorJogador(idJogador);
    }
}
