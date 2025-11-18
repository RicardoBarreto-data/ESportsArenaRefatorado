package esportarena.model;

import java.util.Objects;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String tipo; //administrador, organizador, jogador, espectador

    public Usuario() {}

    public Usuario(int id, String nome, String email, String senha, String tipo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
    }
 public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nome='" + nome + '\'' + ", email='" + email + '\'' + ", tipo='" + tipo + '\'' + '}';
    }
  @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario usuario = (Usuario) o;
        return id == usuario.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
