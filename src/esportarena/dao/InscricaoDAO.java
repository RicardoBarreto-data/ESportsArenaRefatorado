package esportarena.dao;

import esportarena.model.Inscricao;
import java.util.List;

public interface InscricaoDAO {

    void salvar(Inscricao i);

    void atualizar(Inscricao i);

    void deletar(int id);

    Inscricao buscarPorId(int id);

    List<Inscricao> listarPorTorneio(int idTorneio);

    List<Inscricao> listarPorJogador(int idJogador);
}

