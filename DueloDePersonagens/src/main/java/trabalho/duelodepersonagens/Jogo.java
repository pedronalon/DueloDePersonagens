package trabalho.duelodepersonagens;

import java.util.Scanner;

public class Jogo {
    public Jogo() {
        MenuIniciar();
        MenuSelecionar();
    }

    private static void MenuSelecionar() {
        Scanner teclado = new Scanner(System.in);
        int  ModoDeJogo ;
        boolean a  = true;
        System.out.println("Selecione o modo de jogo!\n 1: PvP \n 2:PvE");
        ModoDeJogo = teclado.nextInt();
        while (a) {
            switch (ModoDeJogo) {
                case 1: //seleciona o pvp
                    a = false;
                    break;
                case 2:   // seleciona o pve
                    a = false;
                    break;
                default:
                    System.out.println("Selecione um modo de jogo válido");
            }
        }
    }

    private static void MenuIniciar() {
        System.out.println("Bem vindo ao Masmorras e Dragões!");
        System.out.println("Masmorras e Dragões é um jogo de estratégia por turnos onde apenas os mais habilidosos e destemidos sobreviverão!");
        System.out.println("Neste combate tático, dois oponentes se enfrentam em uma Arena 2D. \nExistem três tipos de classe: Arqueiro, Guerreiro e Mago.");
    }
}