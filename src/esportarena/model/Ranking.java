package esportarena.model;

import java.util.Objects;

public class Ranking {
    private int idRanking;
    private int classificacao;
    private int pontuacao;
    private int idTorneio;
    private int idTime;

    public Ranking() {}

    public Ranking(int idRanking, int classificacao, int pontuacao, int idTorneio, int idTime) {
        this.idRanking = idRanking;
        this.classificacao = classificacao;
        this.pontuacao = pontuacao;
        this.idTorneio = idTorneio;
        this.idTime = idTime;
    }

    public int getIdRanking() { return idRanking; }
    public void setIdRanking(int idRanking) { this.idRanking = idRanking; }

    public int getClassificacao() { return classificacao; }
    public void setClassificacao(int classificacao) { this.classificacao = classificacao; }

    public int getPontuacao() { return pontuacao; }
    public void setPontuacao(int pontuacao) { this.pontuacao = pontuacao; }

    public int getIdTorneio() { return idTorneio; }
    public void setIdTorneio(int idTorneio) { this.idTorneio = idTorneio; }

    public int getIdTime() { return idTime; }
    public void setIdTime(int idTime) { this.idTime = idTime; }

    @Override
    public int hashCode() { return Objects.hash(idRanking); }
}
