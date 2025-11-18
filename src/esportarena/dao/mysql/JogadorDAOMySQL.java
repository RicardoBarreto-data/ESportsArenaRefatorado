
package esportarena.dao.mysql;

import esportarena.dao.JogadorDAO;
import esportarena.database.ConexaoMySQL;
import esportarena.model.Jogador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JogadorDAOMySQL implements JogadorDAO {

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

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void salvar(Jogador jogador) {
        String sql = """
            INSERT INTO Jogador (nome, perfil, dados_contato, id_usuario, id_time, id_ranking)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, jogador.getNome());
            stmt.setString(2, jogador.getPerfil());
            stmt.setString(3, jogador.getDadosContato());
            stmt.setObject(4, jogador.getIdUsuario());
            stmt.setObject(5, jogador.getIdTime());
            stmt.setObject(6, jogador.getIdRanking());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Jogador jogador) {
        String sql = """
            UPDATE Jogador SET
                nome = ?, 
                perfil = ?, 
                dados_contato = ?, 
                id_time = ?, 
                id_ranking = ?
            WHERE id_jogador = ?
        """;

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, jogador.getNome());
            stmt.setString(2, jogador.getPerfil());
            stmt.setString(3, jogador.getDadosContato());
            stmt.setObject(4, jogador.getIdTime());
            stmt.setObject(5, jogador.getIdRanking());
            stmt.setInt(6, jogador.getIdJogador());

            stmt.executeUpdate();

        } catch (SQLException e) {
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

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Jogador> listarPorTime(int idTime) {
        List<Jogador> lista = new ArrayList<>();

        String sql = "SELECT * FROM Jogador WHERE id_time = ?";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idTime);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(criarJogador(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public List<Jogador> listarPorUsuario(int idUsuario) {
        List<Jogador> lista = new ArrayList<>();

        String sql = "SELECT * FROM Jogador WHERE id_usuario = ?";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(criarJogador(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    private Jogador criarJogador(ResultSet rs) throws SQLException {
        Jogador j = new Jogador();

        j.setIdJogador(rs.getInt("id_jogador"));
        j.setNome(rs.getString("nome"));
        j.setPerfil(rs.getString("perfil"));
        j.setDadosContato(rs.getString("dados_contato"));

        Integer idUsuario = rs.getInt("id_usuario");
        j.setIdUsuario(rs.wasNull() ? null : idUsuario);

        Integer idTime = rs.getInt("id_time");
        j.setIdTime(rs.wasNull() ? null : idTime);

        Integer idRanking = rs.getInt("id_ranking");
        j.setIdRanking(rs.wasNull() ? null : idRanking);

        return j;
    }
}