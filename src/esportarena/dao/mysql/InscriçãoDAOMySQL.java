package esportarena.dao.mysql;

import esportarena.dao.InscricaoDAO;
import esportarena.database.ConexaoMySQL;
import esportarena.model.Inscricao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InscriçãoDAOMySQL implements InscricaoDAO {
    @Override
    public void salvar(Inscricao i) {
        String sql = "INSERT INTO Inscricao (id_torneio, id_time, id_jogador, data_inscricao) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, i.getIdTorneio());
            stmt.setInt(2, i.getIdTime());
            stmt.setInt(3, i.getIdJogador());
            stmt.setDate(4, new Date(i.getDataInscricao().getTime()));

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletar(int idInscricao) {
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM Inscricao WHERE id_inscricao = ?")) {

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
    public List<Inscricao> listarRecentesPorOrganizador(int idOrganizador, int limite) {
        List<Inscricao> lista = new ArrayList<>();

        String sql = """
            SELECT i.*
            FROM Inscricao i
            JOIN Torneio t ON t.id_torneio = i.id_torneio
            WHERE t.id_organizador = ?
            ORDER BY i.data_inscricao DESC
            LIMIT ?
        """;

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idOrganizador);
            stmt.setInt(2, limite);

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
        i.setIdTime(rs.getInt("id_time"));
        i.setIdJogador(rs.getInt("id_jogador"));
        i.setDataInscricao(rs.getDate("data_inscricao"));

        return i;
    }
}
