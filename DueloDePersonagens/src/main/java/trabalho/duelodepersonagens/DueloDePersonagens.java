/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package trabalho.duelodepersonagens;


import java.util.Scanner;

/**
 *
 * @author Heitor Coelho, Pedro Nalon
 */
public class DueloDePersonagens {

    public static void main(String[] args) {

        /// Função principal, que inicia o jogo e mantém o loop vivo.

        boolean jogar = true;
        Scanner teclado = new Scanner(System.in);
        do{
            Jogo jogo = new Jogo(teclado);
            jogo.iniciar_jogo(true);

            System.out.println("Deseja jogar novamente? \n (s) para sim\n (n) para nao");
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
        System.out.println("A poeira da arena baixa... por enquanto. Volte sempre que quiser testar sua coragem!\nAdemais,");
        System.out.println("Obrigado por dedicar seu tempo a este duelo! Espero que tenha se divertido. Desenvolvido por Heitor Coelho e Pedro Nalon.");
    }
}