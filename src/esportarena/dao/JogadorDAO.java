package esportarena.dao;

import esportarena.model.Jogador;
import java.util.List;

public interface JogadorDAO {
    
Jogador buscarPorId(int id);

    void salvar(Jogador jogador);

    void atualizar(Jogador jogador);

    void deletar(int id);

    List<Jogador> listarPorTime(int idTime);

    List<Jogador> listarPorUsuario(int idUsuario);

    List<Jogador> listarTodos();
}
