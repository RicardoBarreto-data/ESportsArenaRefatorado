package esportarena.dao;

import esportarena.model.Torneio;
import java.util.List;

public interface TorneioDAO {
    
Torneio buscarPorId(int id);

    void salvar(Torneio torneio);

    void atualizar(Torneio torneio);

    void deletar(int id);

    List<Torneio> listarTodos();

    List<Torneio> listarPorOrganizador(int idOrganizador);
}
