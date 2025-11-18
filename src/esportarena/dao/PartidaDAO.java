package esportarena.dao;

import esportarena.model.Partida;
import java.util.List;

public interface PartidaDAO {
    
 Partida buscarPorId(int id);

    void salvar(Partida partida);

    void atualizar(Partida partida);

    void deletar(int id);

    List<Partida> listarPorTorneio(int idTorneio);
}
