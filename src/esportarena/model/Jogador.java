package esportarena.model;

import java.util.Objects;

public class Jogador {
    private int idJogador;
    private String nome;
    private String perfil;
    private String dadosContato;
    private int idTime;
    private Integer idRanking;
    private Integer idUsuario;

    public Jogador() {}

    public Jogador(int idJogador, String nome, String perfil, int idTime) {
        this.idJogador = idJogador;
        this.nome = nome;
        this.perfil = perfil;
        this.idTime = idTime;
    }
   public int getIdJogador() { return idJogador; }
    public void setIdJogador(int idJogador) { this.idJogador = idJogador; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getPerfil() { return perfil; }
    public void setPerfil(String perfil) { this.perfil = perfil; }

    public String getDadosContato() { return dadosContato; }
    public void setDadosContato(String dadosContato) { this.dadosContato = dadosContato; }

    public int getIdTime() { return idTime; }
    public void setIdTime(int idTime) { this.idTime = idTime; }

    public Integer getIdRanking() { return idRanking; }
    public void setIdRanking(Integer idRanking) { this.idRanking = idRanking; }

    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }

    @Override
    public String toString() { return "Jogador{" + "idJogador=" + idJogador + ", nome='" + nome + '\'' + ", perfil='" + perfil + '\'' + '}'; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Jogador)) return false;
        Jogador jogador = (Jogador) o;
        return idJogador == jogador.idJogador;
    }
    @Override
    public int hashCode() { return Objects.hash(idJogador);
  }
}
