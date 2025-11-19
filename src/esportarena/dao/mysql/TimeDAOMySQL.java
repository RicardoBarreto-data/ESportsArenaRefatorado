package esportarena.dao.mysql;

import esportarena.dao.TimeDAO;
import esportarena.database.ConexaoMySQL;
import esportarena.model.Time;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TimeDAOMySQL implements TimeDAO {

    @Override
    public Time buscarPorId(int id) {
        String sql = "SELECT * FROM Time WHERE id_time = ?";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return criarTime(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Time> listarTodos(int idTorneio) {
        List<Time> lista = new ArrayList<>();
        String sql = "SELECT * FROM Time WHERE id_torneio = ? ORDER BY nome_time";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idTorneio);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(criarTime(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public void salvar(Time time) {
        String sql = "INSERT INTO Time (nome_time, historico_competicoes, id_torneio) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, time.getNomeTime());
            stmt.setString(2, time.getHistoricoCompeticoes() != null ? time.getHistoricoCompeticoes() : "");
            stmt.setInt(3, time.getIdTorneio());

            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) time.setIdTime(keys.getInt(1));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Time time) {
        String sql = "UPDATE Time SET nome_time = ?, historico_competicoes = ?, id_torneio = ? WHERE id_time = ?";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, time.getNomeTime());
            stmt.setString(2, time.getHistoricoCompeticoes() != null ? time.getHistoricoCompeticoes() : "");
            stmt.setInt(3, time.getIdTorneio());
            stmt.setInt(4, time.getIdTime());

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletar(int id) {
        String sql = "DELETE FROM Time WHERE id_time = ?";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Time> listarPorTorneio(int idTorneio) {
        List<Time> lista = new ArrayList<>();
        String sql = "SELECT * FROM Time WHERE id_torneio = ? ORDER BY nome_time";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idTorneio);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(criarTime(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    private Time criarTime(ResultSet rs) throws SQLException {
        Time t = new Time();

        t.setIdTime(rs.getInt("id_time"));
        t.setNomeTime(rs.getString("nome_time"));
        t.setIdTorneio(rs.getInt("id_torneio"));
        t.setHistoricoCompeticoes(rs.getString("historico_competicoes"));

        return t;
    }
}