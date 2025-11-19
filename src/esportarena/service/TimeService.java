package esportarena.service;

import esportarena.dao.TimeDAO;
import esportarena.dao.mysql.TimeDAOMySQL;
import esportarena.model.Time;
import java.util.List;

public class TimeService {
    private final TimeDAO timeDAO;

    public TimeService() {
        this.timeDAO = new TimeDAOMySQL();
    }

    public void salvar(Time t) { timeDAO.salvar(t); }

    public void atualizar(Time t) { timeDAO.atualizar(t); }

    public void deletar(int id) { timeDAO.deletar(id); }

    public Time buscarPorId(int id) { return timeDAO.buscarPorId(id); }

    public List<Time> listarTodos() { return timeDAO.listarTodos(); }
}