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
        // Implementar aqui:
        // - Tela de boas-vindas
        // - Escolha dos personagens
        // - Posicionamento inicial
        // - Loop de turnos alternados
        // - Menu de ações: mover, atacar, defender, poder especial
        // - Verificar condição de vitória
        String [][] arena = new String [10][10];
        for(int i = 9; i > 0; i--){
            for(int j = 0; j < 10; j++){
                arena[j][i] = "[ ]";
            }
        }

        arena[3][ 5] = "["+ 'x' + "]";
        for(int i = 0; i < arena.length; i++){
            for(int j = 0; j < arena[i].length; j++){
                System.out.print(arena[i][j]+ " ");
            }
            System.out.println();
        }
    }
}
