package trabalho.duelodepersonagens;

import java.util.Random;
import java.util.Scanner;

public class Jogo {
    private Personagem Player_1;
    private Personagem Player_2;

    public Jogo() {
        Menus menu = new Menus();
        this.Player_1 = menu.getPlayer_1();
        this.Player_2 = menu.getPlayer_2();
        menu.ImprimeDados(Player_1);
        iniciar_jogo();
    }


    private void iniciar_jogo(){

    }


    public static void geraPosicaoInicial(Personagem  player_1, Personagem player_2){
        Random random = new Random();
        EscolheValoresPosicao(random, player_1);
        EscolheValoresPosicao(random, player_2);
    }

    private static void EscolheValoresPosicao(Random random, Personagem player) {
        int posicao_x = random.nextInt(10);
        int posicao_y = random.nextInt(10);
        player.linha = posicao_x;
        player.coluna = posicao_y;
    }

}