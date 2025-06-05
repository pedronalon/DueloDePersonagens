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
    public boolean jogarNovamente;
    private Random random;
    private Actions p1_action, p2_action;

    public Jogo() {
        this.teclado = new Scanner(System.in);
        this.menu = new Menus();
        this.random = new Random();
        this.Player_1 = menu.getPlayer_1();
        this.Player_2 = menu.getPlayer_2();
        this.ehPVE = menu.isEhPVE();
        geraPosicaoInicial(Player_1, Player_2);
        p1_action = new Actions(Player_1);
        p2_action = new Actions(Player_2);
    }

    public void iniciar_jogo() {
        boolean turnoPlayer1 = true;

        while (p1_action.esta_vivo() && p2_action.esta_vivo()) {
            if (turnoPlayer1) {
                turnoJogador(Player_1, Player_2, turnoPlayer1);
            } else {
                if (ehPVE) {
                    turnoBot(Player_2, Player_1);
                } else {
                    turnoJogador(Player_2, Player_1, turnoPlayer1);
                }
            }
            turnoPlayer1 = !turnoPlayer1;
        }

        // Verifica o vencedor
        if (p1_action.esta_vivo()) {
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

        if (turnoPlayer1) {
            menu.AtualizarArena(jogador, inimigo);
        } else {
            menu.AtualizarArena(inimigo, jogador);
        }

        menu.Menu_de_Combate(jogador, inimigo);
    }

    private void turnoBot(Personagem bot, Personagem jogador) {
        System.out.println("\n--- Turno do BOT " + bot.nome + " ---");
        Actions bot_action = new Actions(bot);
        System.out.println("PV: " + bot.PontosDeVida + " | Defesa: " + bot.DefesaAtual);
        System.out.println("Posição: [" + bot.linha + "," + bot.coluna + "]");
        System.out.println("Jogador posição: [" + jogador.linha + "," + jogador.coluna + "]");
        menu.AtualizarArena(jogador, bot);

        Random random = new Random();
        int acao;

        // Lógica simples para o bot
        if (bot_action.EstaNoAlcance(jogador)) {
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
                bot_action.atacar(jogador);
                System.out.println("O BOT atacou!");
                break;
            case 3: // Defender
                bot_action.Defender();
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

        Actions bot_action = new Actions(bot);
        char[] direcoesPrioritarias = new char[2];

        // Define as direções prioritárias baseadas na maior diferença
        if (Math.abs(deltaX) > Math.abs(deltaY)) {
            // Tenta mover verticalmente primeiro
            if (deltaX > 0) {
                if (bot_action.Andar('B')) {
                    System.out.println("O BOT moveu para Baixo");
                    return;
                }
            } else {
                if (bot_action.Andar('C')) {
                    System.out.println("O BOT moveu para Cima");
                    return;
                }
            }

            // Se não conseguiu mover verticalmente, tenta horizontal
            if (deltaY > 0) {
                if (bot_action.Andar('D')) {
                    System.out.println("O BOT moveu para Direita");
                    return;
                }
            } else {
                if (bot_action.Andar('E')) {
                    System.out.println("O BOT moveu para Esquerda");
                    return;
                }
            }
        } else {
            // Tenta mover horizontalmente primeiro
            if (deltaY > 0) {
                if (bot_action.Andar('D')) {
                    System.out.println("O BOT moveu para Direita");
                    return;
                }
            } else {
                if (bot_action.Andar('E')) {
                    System.out.println("O BOT moveu para Esquerda");
                    return;
                }
            }

            // Se não conseguiu mover horizontalmente, tenta vertical
            if (deltaX > 0) {
                if (bot_action.Andar('B')) {
                    System.out.println("O BOT moveu para Baixo");
                    return;
                }
            } else {
                if (bot_action.Andar('C')) {
                    System.out.println("O BOT moveu para Cima");
                    return;
                }
            }
        }

        System.out.println("O BOT não conseguiu se mover!");
    }

    public void geraPosicaoInicial(Personagem player_1, Personagem player_2) {
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