package esportarena.dao;

import esportarena.model.Time;
import java.util.List;

public interface TimeDAO {
   
    Time buscarPorId(int id);

    void salvar(Time time);

    void atualizar(Time time);

    void deletar(int id);

    List<Time> listarPorTorneio(int idTorneio);
}
