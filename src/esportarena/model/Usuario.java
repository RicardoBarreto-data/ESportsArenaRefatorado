package esportarena.model;

import java.util.Objects;

public class Usuario {
    private int id;
    private String nomeUsuario;
    private String email;
    private String senha;
    private String tipo;

    public Usuario() {}

    public Usuario(int id, String nomeUsuario, String email, String senha, String tipo) {
        this.id = id;
        this.nomeUsuario = nomeUsuario;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNomeUsuario() { return nomeUsuario; }
    public void setNomeUsuario(String nomeUsuario) { this.nomeUsuario = nomeUsuario; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario u = (Usuario) o;
        return id == u.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
