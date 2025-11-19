package esportarena.dao.mysql;

import esportarena.dao.InscricaoDAO;
import esportarena.database.ConexaoMySQL;
import esportarena.model.Inscricao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InscricaoDAOMySQL implements InscricaoDAO {

    @Override
    public void salvar(Inscricao i) {
        String sql = "INSERT INTO Inscricao (id_torneio, id_jogador, data_inscricao) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, i.getIdTorneio());
            stmt.setInt(2, i.getIdJogador());

            if (i.getDataInscricao() != null) {
                stmt.setTimestamp(3, Timestamp.valueOf(i.getDataInscricao()));
            } else {
                stmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            }

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletar(int idInscricao) {
        String sql = "DELETE FROM Inscricao WHERE id_inscricao = ?";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idInscricao);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Inscricao> listarPorTorneio(int idTorneio) {
        List<Inscricao> lista = new ArrayList<>();

        String sql = "SELECT * FROM Inscricao WHERE id_torneio = ? ORDER BY data_inscricao DESC";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idTorneio);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(criarInscricao(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public List<Inscricao> listarPorJogador(int idJogador) {
        List<Inscricao> lista = new ArrayList<>();

        String sql = "SELECT * FROM Inscricao WHERE id_jogador = ? ORDER BY data_inscricao DESC";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idJogador);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(criarInscricao(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    private Inscricao criarInscricao(ResultSet rs) throws SQLException {
        Inscricao i = new Inscricao();

        i.setIdInscricao(rs.getInt("id_inscricao"));
        i.setIdTorneio(rs.getInt("id_torneio"));
        i.setIdJogador(rs.getInt("id_jogador"));

        Timestamp ts = rs.getTimestamp("data_inscricao");
        if (ts != null) {
            i.setDataInscricao(ts.toLocalDateTime());
        }

        return i;
    }
}