package esportarena.service;

import esportarena.dao.PartidaDAO;
import esportarena.dao.mysql.PartidaDAOMySQL;
import esportarena.model.Partida;

import java.util.List;

public class PartidaService {
  
    private final PartidaDAO partidaDAO;
   
    public PartidaService() { this.partidaDAO = new PartidaDAOMySQL(); }
   
    public void salvar(Partida p) { partidaDAO.salvar(p); }
   
    public void atualizar(Partida p) { partidaDAO.atualizar(p); }
    
    public void deletar(int id) { partidaDAO.deletar(id); }

    public Partida buscarPorId(int id) { return partidaDAO.buscarPorId(id); }

    public List<Partida> listarPorTorneio(int idTorneio) {
        return partidaDAO.listarPorTorneio(idTorneio);
    }
    
};