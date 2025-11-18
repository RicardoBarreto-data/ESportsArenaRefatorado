package esportarena.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Partida {
    private int idPartida;
    private LocalDateTime data;
    private String resultado;
    private String status; // agendada, em andamento, concluida
    private int idTorneio;
    private int idTime1;
    private int idTime2;

    public Partida() {}

    public Partida(int idPartida, LocalDateTime data, int idTorneio, int idTime1, int idTime2) {
        this.idPartida = idPartida;
        this.data = data;
        this.idTorneio = idTorneio;
        this.idTime1 = idTime1;
        this.idTime2 = idTime2;
        this.status = "agendada";
    }

    public int getIdPartida() { return idPartida; }
    public void setIdPartida(int idPartida) { this.idPartida = idPartida; }

    public LocalDateTime getData() { return data; }
    public void setData(LocalDateTime data) { this.data = data; }

    public String getResultado() { return resultado; }
    public void setResultado(String resultado) { this.resultado = resultado; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getIdTorneio() { return idTorneio; }
    public void setIdTorneio(int idTorneio) { this.idTorneio = idTorneio; }

    public int getIdTime1() { return idTime1; }
    public void setIdTime1(int idTime1) { this.idTime1 = idTime1; }

    public int getIdTime2() { return idTime2; }
    public void setIdTime2(int idTime2) { this.idTime2 = idTime2; }

    @Override
    public String toString() {
        return "Partida{" + "idPartida=" + idPartida + ", data=" + data + ", status='" + status + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Partida)) return false;
        Partida partida = (Partida) o;
        return idPartida == partida.idPartida;
    }

    @Override
    public int hashCode() { return Objects.hash(idPartida);
  }
}
