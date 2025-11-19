
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

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
            stmt.setString(2, jogador.getPerfil());
            stmt.setString(3, jogador.getDadosContato());

            // id_time é NOT NULL → enviar sempre um valor válido
            stmt.setInt(4, jogador.getIdTime());
            
            // podem ser nulos
            if (jogador.getIdRanking() != null)
                stmt.setInt(5, jogador.getIdRanking());
            else
                stmt.setNull(5, Types.INTEGER);

            if (jogador.getIdUsuario() != null)
                stmt.setInt(6, jogador.getIdUsuario());
            else
                stmt.setNull(6, Types.INTEGER);

            stmt.executeUpdate();

            // pegar ID gerado
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
            stmt.setString(2, jogador.getPerfil());
            stmt.setString(3, jogador.getDadosContato());
            stmt.setInt(4, jogador.getIdTime());

            if (jogador.getIdRanking() != null)
                stmt.setInt(5, jogador.getIdRanking());
            else
                stmt.setNull(5, Types.INTEGER);

            if (jogador.getIdUsuario() != null)
                stmt.setInt(6, jogador.getIdUsuario());
            else
                stmt.setNull(6, Types.INTEGER);

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

        } catch (Exception e) {
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

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
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
}
