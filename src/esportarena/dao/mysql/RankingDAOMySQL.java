
package esportarena.dao.mysql;

import esportarena.dao.RankingDAO;
import esportarena.database.ConexaoMySQL;
import esportarena.model.Ranking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RankingDAOMySQL implements RankingDAO {

    @Override
    public Ranking buscarPorId(int id) {
        String sql = "SELECT * FROM Ranking WHERE id_ranking = ?";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return criarRanking(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Ranking> listarPorTorneio(int idTorneio) {
        List<Ranking> lista = new ArrayList<>();

        String sql = """
            SELECT *
            FROM Ranking
            WHERE id_torneio = ?
            ORDER BY pontuacao DESC
        """;

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idTorneio);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(criarRanking(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public void salvar(Ranking r) {
        String sql = """
            INSERT INTO Ranking (pontuacao, classificacao, id_torneio, id_time)
            VALUES (?, ?, ?, ?)
        """;

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, r.getPontuacao());
            stmt.setInt(2, r.getClassificacao());
            stmt.setInt(3, r.getIdTorneio());
            stmt.setInt(4, r.getIdTime());

            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                r.setIdRanking(keys.getInt(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Ranking r) {
        String sql = """
            UPDATE Ranking
            SET pontuacao = ?, 
                classificacao = ?, 
                id_torneio = ?, 
                id_time = ?
            WHERE id_ranking = ?
        """;

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, r.getPontuacao());
            stmt.setInt(2, r.getClassificacao());
            stmt.setInt(3, r.getIdTorneio());
            stmt.setInt(4, r.getIdTime());
            stmt.setInt(5, r.getIdRanking());

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletar(int id) {
        String sql = "DELETE FROM Ranking WHERE id_ranking = ?";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Ranking criarRanking(ResultSet rs) throws SQLException {
        Ranking r = new Ranking();

        r.setIdRanking(rs.getInt("id_ranking"));
        r.setPontuacao(rs.getInt("pontuacao"));
        r.setClassificacao(rs.getInt("classificacao"));
        r.setIdTorneio(rs.getInt("id_torneio"));
        r.setIdTime(rs.getInt("id_time"));

        return r;
    }
}

