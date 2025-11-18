package esportarena.dao;

import esportarena.model.Ranking;
import java.util.List;

public interface RankingDAO {
   
    Ranking buscarPorId(int id);

    void salvar(Ranking ranking);

    void atualizar(Ranking ranking);

    void deletar(int id);

    List<Ranking> listarPorTorneio(int idTorneio);
}
 
