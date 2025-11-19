package esportsarenarefatorado;

import esportarena.model.*;
import esportarena.service.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ESportsArenaRefatorado {

    public static void main(String[] args) {
        System.out.println("=== Início dos testes básicos (main) ===");

        UsuarioService usuarioService = new UsuarioService();
        TorneioService torneioService = new TorneioService();
        TimeService timeService = new TimeService();
        JogadorService jogadorService = new JogadorService();
        RankingService rankingService = new RankingService();
        PartidaService partidaService = new PartidaService();
        InscricaoService inscricaoService = new InscricaoService();

        try {
            // 1) Criar um usuário organizador
            Usuario organizador = new Usuario();
            organizador.setNomeUsuario("Organizador Test");
            organizador.setEmail("orgtest@example.com");
            organizador.setSenha("senha123");
            organizador.setTipo("organizador");
            usuarioService.salvar(organizador);
            System.out.println("Usuário criado com id = " + organizador.getId());

            // 2) Criar um torneio
            Torneio torneio = new Torneio();
            torneio.setNome("Torneio Teste Main");
            torneio.setJogo("TestGame");
            torneio.setDataInicio(LocalDate.now().plusDays(7));
            torneio.setDescricao("Torneio criado no main para testes");
            torneio.setModalidade("1v1");
            torneio.setParticipantesMin(2);
            torneio.setParticipantesMax(16);
            torneio.setPlataforma("PC");
            torneio.setRegras("Regras de exemplo...");
            torneio.setIdOrganizador(organizador.getId());
            torneioService.salvar(torneio);
            System.out.println("Torneio criado com id = " + torneio.getIdTorneio());

            // 3) Criar dois times para o torneio
            Time timeA = new Time();
            timeA.setNomeTime("Time A Test");
            timeA.setIdTorneio(torneio.getIdTorneio());
            timeService.salvar(timeA);
            System.out.println("Time A criado id = " + timeA.getIdTime());

            Time timeB = new Time();
            timeB.setNomeTime("Time B Test");
            timeB.setIdTorneio(torneio.getIdTorneio());
            timeService.salvar(timeB);
            System.out.println("Time B criado id = " + timeB.getIdTime());

            // 4) Criar um jogador e associar ao timeA
            Jogador jogador = new Jogador();
            jogador.setNome("Jogador Test");
            jogador.setPerfil("Atirador");
            jogador.setDadosContato("jogadortest@example.com");
            jogador.setIdTime(timeA.getIdTime());
            jogador.setIdUsuario(organizador.getId()); // só para teste
            jogadorService.salvar(jogador);
            System.out.println("Jogador criado id = " + jogador.getIdJogador());

            // 5) Criar ranking para timeA
            Ranking ranking = new Ranking();
            ranking.setPontuacao(100);
            ranking.setClassificacao(1);
            ranking.setIdTorneio(torneio.getIdTorneio());
            ranking.setIdTime(timeA.getIdTime());
            rankingService.salvar(ranking);
            System.out.println("Ranking salvo (pontuação=" + ranking.getPontuacao() + ")");

            // 6) Criar uma partida entre timeA e timeB
            Partida partida = new Partida();
            partida.setData(LocalDateTime.now().plusDays(8));
            partida.setStatus("agendada");
            partida.setIdTorneio(torneio.getIdTorneio());
            partida.setIdTime1(timeA.getIdTime());
            partida.setIdTime2(timeB.getIdTime());
            partidaService.salvar(partida);
            System.out.println("Partida agendada id = " + partida.getIdPartida());

            // 7) Fazer inscrição do jogador no torneio
            Inscricao inscr = new Inscricao();
            inscr.setIdTorneio(torneio.getIdTorneio());
            inscr.setIdJogador(jogador.getIdJogador());
            inscr.setDataInscricao(LocalDateTime.now());
            inscricaoService.salvar(inscr);
            System.out.println("Inscrição criada para jogador id = " + jogador.getIdJogador());

            // 8) Listar torneios e imprimir resumo
            List<Torneio> torneios = torneioService.listarTodos();
            System.out.println("\n--- Lista de torneios (resumo) ---");
            for (Torneio t : torneios) {
                System.out.println(t);
            }

            // 9) Atualizar torneio (nome) e salvar
            torneio.setNome(torneio.getNome() + " (editado)");
            torneioService.atualizar(torneio);
            System.out.println("Torneio atualizado: " + torneio.getNome());

            // 10) Buscar partida por id e atualizar resultado
            Partida partidaBuscada = partidaService.buscarPorId(partida.getIdPartida());
            if (partidaBuscada != null) {
                partidaBuscada.setResultado("Time A venceu por 2x0");
                partidaBuscada.setStatus("concluida");
                partidaService.atualizar(partidaBuscada);
                System.out.println("Partida atualizada com resultado: " + partidaBuscada.getResultado());
            }

            // 11) Limpeza: deletar inscrição, partida, ranking, jogador, time, torneio, usuário
            inscricaoService.deletar(inscr.getIdInscricao());
            System.out.println("Inscrição deletada id = " + inscr.getIdInscricao());

            partidaService.deletar(partida.getIdPartida());
            System.out.println("Partida deletada id = " + partida.getIdPartida());

            // supondo seu DAO/Service usa generated keys e popula id no objeto
            rankingService.deletar(ranking.getIdRanking());
            System.out.println("Ranking deletado (se aplicável)");

            jogadorService.deletar(jogador.getIdJogador());
            System.out.println("Jogador deletado id = " + jogador.getIdJogador());

            timeService.deletar(timeA.getIdTime());
            timeService.deletar(timeB.getIdTime());
            System.out.println("Times deletados");

            torneioService.deletar(torneio.getIdTorneio());
            System.out.println("Torneio deletado id = " + torneio.getIdTorneio());

            usuarioService.deletar(organizador.getId());
            System.out.println("Usuário deletado id = " + organizador.getId());

            System.out.println("\n=== Testes concluídos com sucesso ===");

        } catch (Exception ex) {
            System.err.println("Erro durante testes no main: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
