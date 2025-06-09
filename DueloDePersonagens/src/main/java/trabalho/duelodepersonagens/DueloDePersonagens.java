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
        Scanner teclado = new Scanner(System.in);
        do{
            Jogo jogo = new Jogo(teclado);
            jogo.iniciar_jogo();

            System.out.println("Deseja jogar novamente? \n (s) para sim\n 22(n) para nao");
            String resposta = teclado.next();
            resposta = resposta.toUpperCase();
            while(!resposta.equals("S") && !resposta.equals("N")){
                System.out.println("Por favor, escolha com (s) ou (n).");
                resposta = teclado.next();
                resposta = resposta.toUpperCase();
                teclado.nextLine();
            }
            jogar = resposta.equals("S");
        }while(jogar);
        System.out.println("obrigado por testar o jogo.");
    }


}


