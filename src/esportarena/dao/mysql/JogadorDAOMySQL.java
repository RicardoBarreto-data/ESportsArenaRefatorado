
package esportarena.dao.mysql;

import esportarena.dao.JogadorDAO;
import esportarena.model.Jogador;
import esportarena.database.ConexaoMySQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JogadorDAOMySQL implements JogadorDAO{
    @Override
    public Jogador buscarPorId(int id) {
        String sql = "SELECT * FROM Jogador WHERE id_jogador = ?";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return criarJogador(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Jogador buscarPorUsuario(int idUsuario) {
        String sql = "SELECT * FROM Jogador WHERE id_usuario = ?";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return criarJogador(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Jogador> listarTodos() {
        List<Jogador> lista = new ArrayList<>();
        String sql = "SELECT * FROM Jogador";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(criarJogador(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public void salvar(Jogador jogador) {
        String sql = """
                INSERT INTO Jogador (nome, id_usuario, id_time, id_ranking)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, jogador.getNome());
            stmt.setInt(2, jogador.getIdUsuario());
            stmt.setObject(3, jogador.getIdTime() > 0 ? jogador.getIdTime() : null);
            stmt.setObject(4, jogador.getIdRanking() > 0 ? jogador.getIdRanking() : null);

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Jogador jogador) {
        String sql = """
                UPDATE Jogador SET
                nome = ?, id_time = ?, id_ranking = ?
                WHERE id_jogador = ?
                """;

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, jogador.getNome());
            stmt.setObject(2, jogador.getIdTime() > 0 ? jogador.getIdTime() : null);
            stmt.setObject(3, jogador.getIdRanking() > 0 ? jogador.getIdRanking() : null);
            stmt.setInt(4, jogador.getIdJogador());

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletar(int id) {
        String sql = "DELETE FROM Jogador WHERE id_jogador = ?";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Jogador criarJogador(ResultSet rs) throws SQLException {
        Jogador j = new Jogador(
                rs.getInt("id_jogador"),
                rs.getString("nome"),
                rs.getInt("id_usuario")
        );

        j.setIdTime(rs.getInt("id_time"));
        j.setIdRanking(rs.getInt("id_ranking"));

        return j;
    }
}
