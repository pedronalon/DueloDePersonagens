package trabalho.duelodepersonagens;


import java.awt.*;
import java.util.Random;
import java.util.Scanner;

public class Jogo {
    private Personagem Player_1;
    private Personagem Player_2;
    private boolean ehPVE;
    private final Scanner teclado;
    private Menus menu;
    private Random random;
    private Actions p1_action, p2_action;

    /// Inicializa todas as variaveis
    public Jogo(Scanner teclado) {
        this.teclado = teclado;
        this.menu = new Menus(teclado);
        this.random = new Random();
        this.Player_1 = menu.getPlayer_1();
        this.Player_2 = menu.getPlayer_2();
        this.ehPVE = menu.isEhPVE();
        geraPosicaoInicial(Player_1, Player_2);
        p1_action = new Actions(Player_1);
        p2_action = new Actions(Player_2);
    }

    /// Laço principal do jogo
    public void iniciar_jogo() {
        boolean turnoPlayer1 = true;

        while (Player_1.esta_vivo() && Player_2.esta_vivo()) {
            if (turnoPlayer1) {
                turnoJogador(Player_1, Player_2, turnoPlayer1);
            } else {
                if (ehPVE) {
                    turnoBot(Player_2, Player_1);
                } else {
                    turnoJogador(Player_2, Player_1, turnoPlayer1);
                }
            }
            /// Inverte o turno do jogador.
            turnoPlayer1 = !turnoPlayer1;
        }

        /// Verifica o vencedor
        if (Player_1.esta_vivo()) {
            System.out.println("PARABÉNS!! "+ Player_1.getNome() + " venceu o duelo!");
        } else {
            System.out.println("PARABÉNS!! "+ Player_2.getNome() + " venceu o duelo!");
        }
    }

    private void turnoJogador(Personagem jogador, Personagem inimigo, boolean turnoPlayer1) {
        Actions jogador_action = new Actions(jogador);
        System.out.println("\n--- Turno de " + jogador.getNome() + " ---");
        menu.ImprimeDados(jogador, inimigo);

        ///atualiza e imprime a arena
        if (turnoPlayer1) {
            menu.AtualizarArena(jogador, inimigo);
        } else {
            menu.AtualizarArena(inimigo, jogador);
        }

        int acao = menu.Menu_de_Combate(jogador, inimigo, teclado);

        /// Define a ação a ser realizada pelo jogador.
        switch (acao) {
            case 1:
                System.out.println("Digite a direção (C - Cima, B - Baixo, E - Esquerda, D - Direita):");
                String direcao = teclado.next();
                jogador_action.Andar(direcao);
                break;
            case 2:
                jogador_action.atacar(inimigo);
                break;
            case 3:
                jogador_action.Defender();
                break;
            case 4:
                jogador.AtivarPoderEspecial(inimigo);
                break;
            case 5:
                jogador.setPontosDeVida(0);

        }

    }

    private void turnoBot(Personagem bot, Personagem jogador) {
        Actions bot_action = new Actions(bot);
        System.out.println("\n--- Turno do "+bot.getNome()+" ---");
        menu.ImprimeDados(jogador, bot);
        menu.AtualizarArena(jogador, bot);

        Random random = new Random();
        int acao;

        /// Lógica para o bot
        if (bot.EstaNoAlcance(jogador)) {
            /// Se está no alcance, 70% de chance de atacar, 20% de defender (caso a defesa não tenha sido danificada, ataca.), 10% de usar poder especial
            int chance = random.nextInt(100);
            if (chance < 70) {
                acao = 2; /// Atacar
            } else if (chance < 90) {
                if(bot.getDefesaAtual() != bot.getForcaDeDefesa())
                    acao = 3; /// Defender
                else
                    acao = 2;
            } else {
                acao = 4; /// Poder especial
            }
        }
        else {
            /// Se não está no alcance, 60% de tentar se mover para perto do jogador, 30% de defender, 10% de usar poder especial
            int chance = random.nextInt(100);
            if (chance < 60) {
                acao = 1; /// Mover
            } else if (chance < 90) {
                if(bot.getDefesaAtual() != bot.getForcaDeDefesa())
                     acao = 3; /// Defender
                else
                     acao = 4;
            } else {
                acao = 4; /// Poder especial
            }
        }

        /// Executa a ação escolhida
        switch (acao) {
            case 1-> moverBotParaJogador(bot, jogador); /// Mover

            case 2->{ /// Atacar
                bot_action.atacar(jogador);
            }
            case 3->{  /// Defender
                bot_action.Defender();

            }

            case 4-> { /// Poder especial
                bot.AtivarPoderEspecial(jogador);

            }
        }
    }


    /// Função que move o bot em direção ao jogador.
    private void moverBotParaJogador(Personagem bot, Personagem jogador) {
        /// calcula a distancia entre suas linhas e colunas.
        int deltaX = jogador.getLinha() - bot.getLinha();
        int deltaY = jogador.getColuna() - bot.getColuna();

        Actions bot_action = new Actions(bot);

        /// Define as direções prioritárias baseadas na maior diferença
        if (Math.abs(deltaX) > Math.abs(deltaY)) {
            /// deltaX > deltaY = direção prioritária = vertical.
            /// Tenta mover verticalmente primeiro
            if (deltaX > 0) {
                if (bot_action.Andar("B")) {
                    System.out.println("O "+bot.getClasse()+" "+bot.getNome()+" moveu para Baixo");
                    return;
                }
            } else {
                if (bot_action.Andar("C")) {
                    System.out.println("O "+bot.getClasse()+" "+bot.getNome()+" moveu para Cima");
                    return;
                }
            }

            /// Se não conseguiu mover verticalmente, tenta horizontal
            if (deltaY > 0) {
                if (bot_action.Andar("D")) {
                    System.out.println("O "+bot.getClasse()+" "+bot.getNome()+" moveu para Direita");
                    return;
                }
            } else {
                if (bot_action.Andar("E")) {
                    System.out.println("O "+bot.getClasse()+" "+bot.getNome()+" moveu para Esquerda");
                    return;
                }
            }
        } else { /// deltaX < deltaY = direção prioritária = horizontal.
            /// Tenta mover horizontalmente primeiro
            if (deltaY > 0) {
                if (bot_action.Andar("D")) {
                    System.out.println("O "+bot.getClasse()+" "+bot.getNome()+" moveu para Direita");
                    return;
                }
            } else {
                if (bot_action.Andar("E")) {
                    System.out.println("O "+bot.getClasse()+" "+bot.getNome()+" moveu para Esquerda");
                    return;
                }
            }

            /// Se não conseguiu mover horizontalmente, tenta vertical
            if (deltaX > 0) {
                if (bot_action.Andar("B")) {
                    System.out.println("O "+bot.getClasse()+" "+bot.getNome()+" moveu para Baixo");
                    return;
                }
            } else {
                if (bot_action.Andar("C")) {
                    System.out.println("O "+bot.getClasse()+" "+bot.getNome()+" moveu para Cima");
                    return;
                }
            }
        }

        System.out.println("O "+bot.getClasse()+" "+bot.getNome()+" não conseguiu se mover!");
    }


    /// Gera novas posições para os 2 jogadores, até que haja uma distância mínima de 3 casas.
    public void geraPosicaoInicial(Personagem player_1, Personagem player_2) {
        Random random = new Random();
        EscolheValoresPosicao(random, player_1);

        /// Garante que o player 2 não fique muito perto do player 1
        do {
            EscolheValoresPosicao(random, player_2);
        } while (Math.abs(player_1.getLinha() - player_2.getLinha()) < 3 &&
                Math.abs(player_1.getColuna() - player_2.getColuna()) < 3);
    }


    /// Gera os índices das posições dos 2 jogadores e os aplica.
    private static void EscolheValoresPosicao(Random random, Personagem player) {
        int posicao_x = random.nextInt(10);
        int posicao_y = random.nextInt(10);
        player.setLinha(posicao_x);
        player.setColuna(posicao_y);
    }
}