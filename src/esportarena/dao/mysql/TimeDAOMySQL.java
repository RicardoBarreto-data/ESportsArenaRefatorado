package esportarena.dao.mysql;

import esportarena.dao.TimeDAO;
import esportarena.model.Time;
import esportarena.database.ConexaoMySQL;

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
    public List<Time> listarTodos() {
        List<Time> lista = new ArrayList<>();
        String sql = "SELECT * FROM Time ORDER BY nome_time";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

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
        String sql = "INSERT INTO Time (nome_time, id_torneio) VALUES (?, ?)";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, time.getNome());
            stmt.setInt(2, time.getIdTorneio());

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Time time) {
        String sql = "UPDATE Time SET nome_time = ?, id_torneio = ? WHERE id_time = ?";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, time.getNome());
            stmt.setInt(2, time.getIdTorneio());
            stmt.setInt(3, time.getId());

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

    private Time criarTime(ResultSet rs) throws SQLException {
        Time t = new Time(
                rs.getInt("id_time"),
                rs.getString("nome_time")
        );

        t.setIdTorneio(rs.getInt("id_torneio"));
        return t;
    }
}
