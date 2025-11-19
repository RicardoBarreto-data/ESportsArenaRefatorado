
package esportarena.dao.mysql;

import esportarena.dao.PartidaDAO;
import esportarena.model.Partida;
import esportarena.database.ConexaoMySQL;

import java.sql.*;
import java.time.LocalDateTime;
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
                Partida p = new Partida();
                p.setIdPartida(rs.getInt("id_partida"));
                Timestamp ts = rs.getTimestamp("data");
                if (ts != null) p.setData(ts.toLocalDateTime());
                p.setResultado(rs.getString("resultado"));
                p.setStatus(rs.getString("status"));
                p.setIdTorneio(rs.getInt("id_torneio"));
                p.setIdTime1(rs.getInt("id_time1"));
                p.setIdTime2(rs.getInt("id_time2"));
                return p;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void salvar(Partida partida) {
        String sql = "INSERT INTO Partida (data, resultado, status, id_torneio, id_time1, id_time2) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setTimestamp(1, partida.getData() != null ? Timestamp.valueOf(partida.getData()) : new Timestamp(System.currentTimeMillis()));
            stmt.setString(2, partida.getResultado() != null ? partida.getResultado() : "");
            stmt.setString(3, partida.getStatus() != null ? partida.getStatus() : "agendada");
            stmt.setInt(4, partida.getIdTorneio());
            stmt.setInt(5, partida.getIdTime1());
            stmt.setInt(6, partida.getIdTime2());

            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) partida.setIdPartida(keys.getInt(1));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Partida partida) {
        String sql = "UPDATE Partida SET data = ?, resultado = ?, status = ?, id_torneio = ?, id_time1 = ?, id_time2 = ? WHERE id_partida = ?";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setTimestamp(1, partida.getData() != null ? Timestamp.valueOf(partida.getData()) : null);
            stmt.setString(2, partida.getResultado());
            stmt.setString(3, partida.getStatus());
            stmt.setInt(4, partida.getIdTorneio());
            stmt.setInt(5, partida.getIdTime1());
            stmt.setInt(6, partida.getIdTime2());
            stmt.setInt(7, partida.getIdPartida());

            stmt.executeUpdate();

        } catch (Exception e) {
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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Partida> listarPorTorneio(int idTorneio) {
        List<Partida> partidas = new ArrayList<>();

        String sql = "SELECT * FROM Partida WHERE id_torneio = ? ORDER BY data";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idTorneio);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Partida p = new Partida();
                p.setIdPartida(rs.getInt("id_partida"));
                Timestamp ts = rs.getTimestamp("data");
                if (ts != null) p.setData(ts.toLocalDateTime());
                p.setResultado(rs.getString("resultado"));
                p.setStatus(rs.getString("status"));
                p.setIdTorneio(rs.getInt("id_torneio"));
                p.setIdTime1(rs.getInt("id_time1"));
                p.setIdTime2(rs.getInt("id_time2"));

                partidas.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return partidas;
    }
}