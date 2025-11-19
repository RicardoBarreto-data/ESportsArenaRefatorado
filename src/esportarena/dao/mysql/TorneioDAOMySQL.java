package esportarena.dao.mysql;

import esportarena.dao.TorneioDAO;
import esportarena.model.Torneio;
import esportarena.database.ConexaoMySQL;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TorneioDAOMySQL implements TorneioDAO{
    
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
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, torneio.getNome());
            stmt.setString(2, torneio.getDescricao());
            stmt.setString(3, torneio.getJogo());
            stmt.setDate(4, torneio.getDataInicio() != null ? Date.valueOf(torneio.getDataInicio()) : null);
            stmt.setDate(5, torneio.getDataTermino() != null ? Date.valueOf(torneio.getDataTermino()) : null);
            stmt.setString(6, torneio.getModalidade());
            stmt.setObject(7, torneio.getParticipantesMin() != null ? torneio.getParticipantesMin() : null);
            stmt.setObject(8, torneio.getParticipantesMax() != null ? torneio.getParticipantesMax() : null);
            stmt.setString(9, torneio.getPlataforma());
            stmt.setString(10, torneio.getRegras());
            stmt.setInt(11, torneio.getIdOrganizador());

            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) torneio.setIdTorneio(keys.getInt(1));

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
            stmt.setDate(4, torneio.getDataInicio() != null ? Date.valueOf(torneio.getDataInicio()) : null);
            stmt.setDate(5, torneio.getDataTermino() != null ? Date.valueOf(torneio.getDataTermino()) : null);
            stmt.setString(6, torneio.getModalidade());
            stmt.setObject(7, torneio.getParticipantesMin() != null ? torneio.getParticipantesMin() : null);
            stmt.setObject(8, torneio.getParticipantesMax() != null ? torneio.getParticipantesMax() : null);
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
        t.setJogo(rs.getString("jogo"));
        Date dInicio = rs.getDate("data_inicio");
        if (dInicio != null) t.setDataInicio(dInicio.toLocalDate());
        Date dTermino = rs.getDate("data_termino");
        if (dTermino != null) t.setDataTermino(dTermino.toLocalDate());
        t.setDescricao(rs.getString("descricao"));
        t.setModalidade(rs.getString("modalidade"));
        t.setParticipantesMin(rs.getObject("participantes_min") != null ? rs.getInt("participantes_min") : null);
        t.setParticipantesMax(rs.getObject("participantes_max") != null ? rs.getInt("participantes_max") : null);
        t.setPlataforma(rs.getString("plataforma"));
        t.setRegras(rs.getString("regras"));
        t.setIdOrganizador(rs.getInt("id_organizador"));
        Timestamp tsEd = rs.getTimestamp("data_edicao");
        if (tsEd != null) t.setDataEdicao(tsEd.toLocalDateTime());
        return t;
    }

    @Override
    public List<Torneio> listarPorOrganizador(int idOrganizador) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}