/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package trabalho.duelodepersonagens;


import java.util.Scanner;

/**
 *
 * @author Heitor
 */
public class DueloDePersonagens {

    public static void main(String[] args) {
        boolean jogar = true;
        do{
            Jogo jogo = new Jogo();
            jogo.iniciar_jogo();

            Scanner teclado = new Scanner(System.in);
            System.out.println("Deseja jogar novamente? \n (s) para sim\n(n) para nao");
            String resposta = teclado.next();
            resposta = resposta.toUpperCase();
            while(!resposta.equals("S") && !resposta.equals("N")){
                System.out.println("Por favor, escolha com (s) ou (n).");
                resposta = teclado.next();
            }
            jogar = resposta.equals("S");
        }while(jogar);
        System.out.println("obrigado por testar o jogo.");
    }


}


