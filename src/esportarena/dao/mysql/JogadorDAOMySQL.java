
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
                INSERT INTO Jogador (nome, perfil, dados_contato, id_time, id_ranking, id_usuario)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, jogador.getNome());
            stmt.setString(2, jogador.getPerfil() != null ? jogador.getPerfil() : "");
            stmt.setString(3, jogador.getDadosContato() != null ? jogador.getDadosContato() : "");
            // id_time é obrigatório no banco; envia 0 ou valor apropriado
            stmt.setObject(4, jogador.getIdTime() > 0 ? jogador.getIdTime() : null);
            stmt.setObject(5, jogador.getIdRanking() != null ? jogador.getIdRanking() : null);
            stmt.setObject(6, jogador.getIdUsuario() != null ? jogador.getIdUsuario() : null);

            stmt.executeUpdate();

            // opcional: pegar id gerado
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                jogador.setIdJogador(keys.getInt(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Jogador jogador) {
        String sql = """
                UPDATE Jogador SET
                nome = ?, perfil = ?, dados_contato = ?, id_time = ?, id_ranking = ?, id_usuario = ?
                WHERE id_jogador = ?
                """;

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, jogador.getNome());
            stmt.setString(2, jogador.getPerfil() != null ? jogador.getPerfil() : "");
            stmt.setString(3, jogador.getDadosContato() != null ? jogador.getDadosContato() : "");
            stmt.setObject(4, jogador.getIdTime() > 0 ? jogador.getIdTime() : null);
            stmt.setObject(5, jogador.getIdRanking() != null ? jogador.getIdRanking() : null);
            stmt.setObject(6, jogador.getIdUsuario() != null ? jogador.getIdUsuario() : null);
            stmt.setInt(7, jogador.getIdJogador());

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
        Jogador j = new Jogador();
        j.setIdJogador(rs.getInt("id_jogador"));
        j.setNome(rs.getString("nome"));
        j.setPerfil(rs.getString("perfil"));
        j.setDadosContato(rs.getString("dados_contato"));
        j.setIdTime(rs.getInt("id_time"));
        int idRanking = rs.getInt("id_ranking");
        if (!rs.wasNull()) j.setIdRanking(idRanking);
        int idUsuario = rs.getInt("id_usuario");
        if (!rs.wasNull()) j.setIdUsuario(idUsuario);

        return j;
    }

    @Override
    public List<Jogador> listarPorTime(int idTime) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Jogador> listarPorUsuario(int idUsuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}