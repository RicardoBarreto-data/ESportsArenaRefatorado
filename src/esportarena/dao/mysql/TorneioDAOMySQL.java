package esportarena.dao.mysql;

import esportarena.dao.TorneioDAO;
import esportarena.model.Torneio;
import esportarena.database.ConexaoMySQL;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TorneioDAOMySQL implements TorneioDAO {

    @Override
    public Torneio buscarPorId(int id) {
        String sql = "SELECT * FROM Torneio WHERE id_torneio = ?";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return criarTorneio(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Torneio> listarTodos() {
        List<Torneio> lista = new ArrayList<>();
        String sql = "SELECT * FROM Torneio ORDER BY data_inicio DESC";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(criarTorneio(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public void salvar(Torneio torneio) {
        String sql = """
                INSERT INTO Torneio
                (nome, descricao, jogo, data_inicio, data_termino,
                 modalidade, participantes_min, participantes_max,
                 plataforma, regras, id_organizador)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, torneio.getNome());
            stmt.setString(2, torneio.getDescricao());
            stmt.setString(3, torneio.getJogo());
            stmt.setDate(4, Date.valueOf(torneio.getDataInicio()));
            stmt.setDate(5, Date.valueOf(torneio.getDataTermino()));
            stmt.setString(6, torneio.getModalidade());
            stmt.setInt(7, torneio.getParticipantesMin());
            stmt.setInt(8, torneio.getParticipantesMax());
            stmt.setString(9, torneio.getPlataforma());
            stmt.setString(10, torneio.getRegras());
            stmt.setInt(11, torneio.getIdOrganizador());

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Torneio torneio) {
        String sql = """
                UPDATE Torneio SET
                nome=?, descricao=?, jogo=?, data_inicio=?, data_termino=?,
                modalidade=?, participantes_min=?, participantes_max=?,
                plataforma=?, regras=?, data_edicao=NOW()
                WHERE id_torneio=?
                """;

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, torneio.getNome());
            stmt.setString(2, torneio.getDescricao());
            stmt.setString(3, torneio.getJogo());
            stmt.setDate(4, Date.valueOf(torneio.getDataInicio()));
            stmt.setDate(5, Date.valueOf(torneio.getDataTermino()));
            stmt.setString(6, torneio.getModalidade());
            stmt.setInt(7, torneio.getParticipantesMin());
            stmt.setInt(8, torneio.getParticipantesMax());
            stmt.setString(9, torneio.getPlataforma());
            stmt.setString(10, torneio.getRegras());
            stmt.setInt(11, torneio.getIdTorneio());

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletar(int id) {
        String sql = "DELETE FROM Torneio WHERE id_torneio = ?";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Torneio criarTorneio(ResultSet rs) throws SQLException {
        Torneio t = new Torneio();

        t.setIdTorneio(rs.getInt("id_torneio"));
        t.setNome(rs.getString("nome"));
        t.setDescricao(rs.getString("descricao"));
        t.setJogo(rs.getString("jogo"));

        Date di = rs.getDate("data_inicio");
        if (di != null) t.setDataInicio(di.toLocalDate());

        Date dt = rs.getDate("data_termino");
        if (dt != null) t.setDataTermino(dt.toLocalDate());

        t.setModalidade(rs.getString("modalidade"));
        t.setParticipantesMin(rs.getInt("participantes_min"));
        t.setParticipantesMax(rs.getInt("participantes_max"));
        t.setPlataforma(rs.getString("plataforma"));
        t.setRegras(rs.getString("regras"));
        t.setIdOrganizador(rs.getInt("id_organizador"));

        return t;
    }
}