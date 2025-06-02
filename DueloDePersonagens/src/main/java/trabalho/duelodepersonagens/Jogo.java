package trabalho.duelodepersonagens;

import java.awt.*;
import java.util.Random;
import java.util.Scanner;

public class Jogo {
    private Personagem Player_1;
    private Personagem Player_2;
    private boolean ehPVE;
    private Scanner teclado;
    private Menus menu;

    public Jogo() {
        teclado = new Scanner(System.in);
        menu = new Menus();
        this.Player_1 = menu.getPlayer_1();
        this.Player_2 = menu.getPlayer_2();
        this.ehPVE = menu.isEhPVE();
        geraPosicaoInicial(Player_1, Player_2);
        iniciar_jogo();
    }

    private void iniciar_jogo() {
        boolean turnoPlayer1 = true;

        while (Player_1.esta_vivo() && Player_2.esta_vivo()) {
            if (turnoPlayer1) {
                turnoJogador(Player_1, Player_2, turnoPlayer1);
            } else {
                if (ehPVE) {
                    turnoBot(Player_2, Player_1);
                } else  {
                    turnoJogador(Player_2, Player_1, turnoPlayer1);
                }
            }
            turnoPlayer1 = !turnoPlayer1;
        }

        // Verifica o vencedor
        if (Player_1.esta_vivo()) {
            System.out.println(Player_1.nome + " venceu o duelo!");
        } else {
            System.out.println(Player_2.nome + " venceu o duelo!");
        }
    }

    private void turnoJogador(Personagem jogador, Personagem inimigo, boolean turnoPlayer1) {
        System.out.println("\n--- Turno de " + jogador.nome + " ---");
        System.out.println("PV: " + jogador.PontosDeVida + " | Defesa: " + jogador.DefesaAtual);
        System.out.println("Posição: [" + jogador.linha + "," + jogador.coluna + "]");
        System.out.println("Posição do inimigo: [" + inimigo.linha + "," + inimigo.coluna + "]");

        if(turnoPlayer1) {
            menu.AtualizarArena(jogador, inimigo);
        }
        else{
            menu.AtualizarArena(inimigo, jogador);
        }

        System.out.println("\nEscolha sua ação:");
        System.out.println("1 - Mover");
        System.out.println("2 - Atacar");
        System.out.println("3 - Defender");
        System.out.println("4 - Poder Especial");

        int acao = teclado.nextInt();
        while (acao < 1 || acao > 4) {
            System.out.println("Opção inválida! Escolha novamente:");
            acao = teclado.nextInt();
        }

        switch (acao) {
            case 1:
                System.out.println("Digite a direção (C - Cima, B - Baixo, E - Esquerda, D - Direita):");
                char direcao = teclado.next().charAt(0);
                jogador.Andar(direcao);
                break;
            case 2:
                jogador.atacar(inimigo);
                break;
            case 3:
                jogador.Defender();
                break;
            case 4:
                jogador.AtivarPoderEspecial(inimigo);
                break;
        }
    }

    private void turnoBot(Personagem bot, Personagem jogador) {
        System.out.println("\n--- Turno do BOT " + bot.nome + " ---");
        System.out.println("PV: " + bot.PontosDeVida + " | Defesa: " + bot.DefesaAtual);
        System.out.println("Posição: [" + bot.linha + "," + bot.coluna + "]");
        System.out.println("Jogador posição: [" + jogador.linha + "," + jogador.coluna + "]");
        menu.AtualizarArena(jogador, bot);

        Random random = new Random();
        int acao;


        // Lógica simples para o bot
        if (bot.EstaNoAlcance(jogador)) {
            // Se está no alcance, 70% de chance de atacar, 20% de defender, 10% de usar poder especial
            int chance = random.nextInt(100);
            if (chance < 70) {
                acao = 2; // Atacar
            } else if (chance < 90) {
                acao = 3; // Defender
            } else {
                acao = 4; // Poder especial
            }
        } else {
            // Se não está no alcance, 60% de tentar se mover para perto do jogador, 30% de defender, 10% de usar poder especial
            int chance = random.nextInt(100);
            if (chance < 60) {
                acao = 1; // Mover
            } else if (chance < 90) {
                acao = 3; // Defender
            } else {
                acao = 4; // Poder especial
            }
        }

        // Executa a ação escolhida
        switch (acao) {
            case 1: // Mover
                moverBotParaJogador(bot, jogador);
                break;
            case 2: // Atacar
                bot.atacar(jogador);
                System.out.println("O BOT atacou!");
                break;
            case 3: // Defender
                bot.Defender();
                System.out.println("O BOT se defendeu!");
                break;
            case 4: // Poder especial
                bot.AtivarPoderEspecial(jogador);
                System.out.println("O BOT usou seu poder especial!");
                break;
        }
    }

    private void moverBotParaJogador(Personagem bot, Personagem jogador) {
        int deltaX = jogador.linha - bot.linha;
        int deltaY = jogador.coluna - bot.coluna;

        char[] direcoesPrioritarias = new char[2];

        // Define as direções prioritárias baseadas na maior diferença
        if (Math.abs(deltaX) > Math.abs(deltaY)) {
            direcoesPrioritarias[0] = deltaX > 0 ? 'B' : 'C';
            direcoesPrioritarias[1] = deltaY > 0 ? 'D' : 'E';
        } else {
            direcoesPrioritarias[0] = deltaY > 0 ? 'D' : 'E';
            direcoesPrioritarias[1] = deltaX > 0 ? 'B' : 'C';
        }

        // Tenta as direções prioritárias
        for (char direcao : direcoesPrioritarias) {
            if (bot.Andar(direcao)) {
                System.out.println("O BOT se moveu para " + direcao);
                return;
            }
        }

        // Se não conseguiu com as prioritárias, tenta outras direções aleatórias
        char[] todasDirecoes = {'C', 'B', 'E', 'D'};
        for (char direcao : todasDirecoes) {
            if (bot.Andar(direcao)) {
                System.out.println("O BOT se moveu para " + direcao);
                return;
            }
        }

        System.out.println("O BOT tentou se mover mas não conseguiu");
    }

    public static void geraPosicaoInicial(Personagem player_1, Personagem player_2) {
        Random random = new Random();
        EscolheValoresPosicao(random, player_1);

        // Garante que o player 2 não fique muito perto do player 1
        do {
            EscolheValoresPosicao(random, player_2);
        } while (Math.abs(player_1.linha - player_2.linha) < 3 &&
                Math.abs(player_1.coluna - player_2.coluna) < 3);
    }

    private static void EscolheValoresPosicao(Random random, Personagem player) {
        int posicao_x = random.nextInt(10);
        int posicao_y = random.nextInt(10);
        player.linha = posicao_x;
        player.coluna = posicao_y;
    }
}