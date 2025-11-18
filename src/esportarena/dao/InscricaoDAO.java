package esportarena.dao;

import esportarena.model.Inscricao;
import java.util.List;

public interface InscricaoDAO {
  
    void salvar(Inscricao inscricao);

    void deletar(int id);

    List<Inscricao> listarPorTorneio(int idTorneio);

    List<Inscricao> listarPorJogador(int idJogador);
}
