package esportarena.service;

import esportarena.dao.RankingDAO;
import esportarena.dao.mysql.RankingDAOMySQL;
import esportarena.model.Ranking;

import java.util.List;

public class RankingService {

    private final RankingDAO rankingDAO;

    public RankingService() { this.rankingDAO = new RankingDAOMySQL(); }

    public void salvar(Ranking r) { rankingDAO.salvar(r); }

    public void atualizar(Ranking r) { rankingDAO.atualizar(r); }

    public void deletar(int id) { rankingDAO.deletar(id); }

    public Ranking buscarPorId(int id) { return rankingDAO.buscarPorId(id); }

    public List<Ranking> listarPorTorneio(int idTorneio) {
        return rankingDAO.listarPorTorneio(idTorneio);
    }
}