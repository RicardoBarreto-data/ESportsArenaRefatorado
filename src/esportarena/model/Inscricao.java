package esportarena.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Inscricao {

    private int idInscricao;
    private int idTorneio;
    private int idJogador;
    private LocalDateTime dataInscricao;

    public Inscricao() {}

    public Inscricao(int idInscricao, int idTorneio, int idJogador, LocalDateTime dataInscricao) {
        this.idInscricao = idInscricao;
        this.idTorneio = idTorneio;
        this.idJogador = idJogador;
        this.dataInscricao = dataInscricao;
    }

    public int getIdInscricao() { return idInscricao; }
    public void setIdInscricao(int idInscricao) { this.idInscricao = idInscricao; }

    public int getIdTorneio() { return idTorneio; }
    public void setIdTorneio(int idTorneio) { this.idTorneio = idTorneio; }

    public int getIdJogador() { return idJogador; }
    public void setIdJogador(int idJogador) { this.idJogador = idJogador; }

    public LocalDateTime getDataInscricao() { return dataInscricao; }
    public void setDataInscricao(LocalDateTime dataInscricao) { this.dataInscricao = dataInscricao; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Inscricao)) return false;
        Inscricao i = (Inscricao) o;
        return idInscricao == i.idInscricao;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idInscricao);
    }
}
