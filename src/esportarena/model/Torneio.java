package esportarena.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Torneio {
    private int idTorneio;
    private String nome;
    private String jogo;
    private LocalDate dataInicio;
    private LocalDate dataTermino;
    private String descricao;
    private String modalidade;
    private Integer participantesMin;
    private Integer participantesMax;
    private String plataforma;
    private String regras;
    private int idOrganizador;
    private LocalDateTime dataEdicao;

    private List<Partida> partidas = new ArrayList<>();

    public Torneio() {}

    public Torneio(int idTorneio, String nome, String jogo, LocalDate dataInicio, int idOrganizador) {
        this.idTorneio = idTorneio;
        this.nome = nome;
        this.jogo = jogo;
        this.dataInicio = dataInicio;
        this.idOrganizador = idOrganizador;
    }
    // getters / setters
    public int getIdTorneio() { return idTorneio; }
    public void setIdTorneio(int idTorneio) { this.idTorneio = idTorneio; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getJogo() { return jogo; }
    public void setJogo(String jogo) { this.jogo = jogo; }

    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }

    public LocalDate getDataTermino() { return dataTermino; }
    public void setDataTermino(LocalDate dataTermino) { this.dataTermino = dataTermino; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getModalidade() { return modalidade; }
    public void setModalidade(String modalidade) { this.modalidade = modalidade; }

    public Integer getParticipantesMin() { return participantesMin; }
    public void setParticipantesMin(Integer participantesMin) { this.participantesMin = participantesMin; }

    public Integer getParticipantesMax() { return participantesMax; }
    public void setParticipantesMax(Integer participantesMax) { this.participantesMax = participantesMax; }

    public String getPlataforma() { return plataforma; }
    public void setPlataforma(String plataforma) { this.plataforma = plataforma; }

    public String getRegras() { return regras; }
    public void setRegras(String regras) { this.regras = regras; }

    public int getIdOrganizador() { return idOrganizador; }
    public void setIdOrganizador(int idOrganizador) { this.idOrganizador = idOrganizador; }

    public LocalDateTime getDataEdicao() { return dataEdicao; }
    public void setDataEdicao(LocalDateTime dataEdicao) { this.dataEdicao = dataEdicao; }

    public List<Partida> getPartidas() { return partidas; }
    public void adicionarPartida(Partida p) { this.partidas.add(p); }

    @Override
    public String toString() {
        return "Torneio{" + "idTorneio=" + idTorneio + ", nome='" + nome + '\'' + ", jogo='" + jogo + '\'' + ", dataInicio=" + dataInicio + '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Torneio)) return false;
        Torneio torneio = (Torneio) o;
        return idTorneio == torneio.idTorneio;
    }
    @Override
    public int hashCode() { return Objects.hash(idTorneio); 
  }
}
