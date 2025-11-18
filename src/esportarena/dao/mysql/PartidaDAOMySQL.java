
package esportarena.dao.mysql;

import esportarena.dao.PartidaDAO;
import esportarena.database.ConexaoMySQL;
import esportarena.model.Partida;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PartidaDAOMySQL implements PartidaDAO {

    @Override
    public Partida buscarPorId(int id) {
        String sql = "SELECT * FROM Partida WHERE id_partida = ?";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return criarPartida(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void salvar(Partida partida) {
        String sql = """
            INSERT INTO Partida (id_torneio, id_time1, id_time2, data, status, resultado)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, partida.getIdTorneio());
            stmt.setInt(2, partida.getIdTime1());
            stmt.setInt(3, partida.getIdTime2());
            stmt.setTimestamp(4, Timestamp.valueOf(partida.getData()));
            stmt.setString(5, partida.getStatus());
            stmt.setString(6, partida.getResultado());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Partida partida) {
        String sql = """
            UPDATE Partida
            SET id_time1 = ?, 
                id_time2 = ?, 
                data = ?, 
                status = ?, 
                resultado = ?
            WHERE id_partida = ?
        """;

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, partida.getIdTime1());
            stmt.setInt(2, partida.getIdTime2());
            stmt.setTimestamp(3, Timestamp.valueOf(partida.getData()));
            stmt.setString(4, partida.getStatus());
            stmt.setString(5, partida.getResultado());
            stmt.setInt(6, partida.getIdPartida());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletar(int id) {
        String sql = "DELETE FROM Partida WHERE id_partida = ?";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Partida> listarPorTorneio(int idTorneio) {
        List<Partida> lista = new ArrayList<>();

        String sql = """
            SELECT * FROM Partida
            WHERE id_torneio = ?
            ORDER BY data
        """;

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idTorneio);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(criarPartida(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    private Partida criarPartida(ResultSet rs) throws SQLException {
        Partida p = new Partida();

        p.setIdPartida(rs.getInt("id_partida"));
        p.setIdTorneio(rs.getInt("id_torneio"));
        p.setIdTime1(rs.getInt("id_time1"));
        p.setIdTime2(rs.getInt("id_time2"));
        p.setStatus(rs.getString("status"));
        p.setResultado(rs.getString("resultado"));

        Timestamp ts = rs.getTimestamp("data");
        if (ts != null) {
            p.setData(ts.toLocalDateTime());
        }

        return p;
    }
}

