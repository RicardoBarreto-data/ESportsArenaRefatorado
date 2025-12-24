package esportarena.service;

public class CalculadoraPontuacao {

    public int calcularPontuacao(int vitorias, int empates, int derrotas) {
        if (vitorias < 0 || empates < 0 || derrotas < 0) {
            throw new IllegalArgumentException("Valores não podem ser negativos");
        }
        return (vitorias * 3) + empates;
    }
}