package esportarena.model;

import java.util.Objects;

public class Time {
    private int idTime;
    private String nomeTime;
    private String historicoCompeticoes;
    private int idTorneio;

    public Time() {}

    public Time(int idTime, String nomeTime, int idTorneio) {
        this.idTime = idTime;
        this.nomeTime = nomeTime;
        this.idTorneio = idTorneio;
    }

    public int getIdTime() { return idTime; }
    public void setIdTime(int idTime) { this.idTime = idTime; }

    public String getNomeTime() { return nomeTime; }
    public void setNomeTime(String nomeTime) { this.nomeTime = nomeTime; }

    public String getHistoricoCompeticoes() { return historicoCompeticoes; }
    public void setHistoricoCompeticoes(String historicoCompeticoes) { this.historicoCompeticoes = historicoCompeticoes; }

    public int getIdTorneio() { return idTorneio; }
    public void setIdTorneio(int idTorneio) { this.idTorneio = idTorneio; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Time)) return false;
        Time t = (Time) o;
        return idTime == t.idTime;
    }

    @Override
    public int hashCode() { return Objects.hash(idTime); }
}
