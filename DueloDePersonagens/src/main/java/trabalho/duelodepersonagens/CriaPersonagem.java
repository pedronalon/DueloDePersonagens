package trabalho.duelodepersonagens;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CriaPersonagem {

    public List<Personagem> criarJogadores(Scanner teclado, boolean ehPVE) {
        List<Personagem> jogadores = new ArrayList<>();

        Personagem p1 = getInfo(1, teclado, ehPVE);
        Personagem p2 = getInfo(2, teclado, ehPVE);

        jogadores.add(p1);
        jogadores.add(p2);

        return jogadores;
    }


    /// Cria, conforme o modo de jogo, dois personages
    private Personagem getInfo(int num, Scanner teclado, boolean ehPVE) {
        if(num == 1 || !ehPVE){     // Dupla verificação para não serem criados dois bots.
            //Caso o modo de jogo escolhido seja PvP
            System.out.println("Jogador " + num + ", digite seu nome: ");
            String nome = teclado.nextLine();
            System.out.println("Ótimo. Seja bem-vindo, " + nome + "! Agora, escolha uma classe! As opçoes são: ");
            imprimeClasses();

            String classe = teclado.next();
            while (!classe.equals("1") && !classe.equals("2") && !classe.equals("3")){
                System.out.println("Por favor, escolha uma classe válida!");
                classe = teclado.next();
            }

            teclado.nextLine();
            int escolhaClasse = Integer.parseInt(classe);
            return Seleciona_Personagem(escolhaClasse, nome);
        }

        else{
            // caso o modo de jogo escolhido seja PvE
            int classe_bot = new Random().nextInt(3) + 1;
            String NomeBot;
            switch (classe_bot) {
                case 1-> NomeBot = "Ragnar";
                case 2-> NomeBot = "Gandalf";
                case 3-> NomeBot = "Légolas";
                default-> NomeBot = "BOT";
            }
            return Seleciona_Personagem(classe_bot, NomeBot);
        }

    }

    /// Cria o personagem selecionado
    private Personagem Seleciona_Personagem(int classe, String Nome) {

        return switch (classe) {
            case 1 -> new Guerreiro(Nome);
            case 2 -> new Mago(Nome);
            case 3 -> new Arqueiro(Nome);
            default -> null;
        };
    }

    /// Primeira impressão das classes, para escolha do jogador
    public static void imprimeClasses() { ///Forma mais amigável ao usuário de visualizar as informações
        String[] guerreiro = {
                "(1) Guerreiro:",
                "     Vida: 100",
                "     Ataque: 15",
                "     Defesa: 10",
                "     Alcance: 1",
                "     Ataque Especial:",
                "     Carga Brutal:",
                "     Aumenta seu ataque para 30"
        };

        String[] mago = {
                "(2) Mago:",
                "     Vida: 100",
                "     Ataque: 10",
                "     Defesa: 7",
                "     Alcance: 3",
                "     Ataque Especial:",
                "     Trocar de Vida:",
                "     Troca de vida com o inimigo"
        };

        String[] arqueiro = {
                "(3) Arqueiro:",
                "     Vida: 100",
                "     Ataque: 8",
                "     Defesa: 5",
                "     Alcance: 5",
                "     Ataque Especial:",
                "     Flecha Precisa:",
                "     Incrementa 1 em seu alcance"
        };
        for (int i = 0; i < 8; i++) {
            System.out.printf("%-40s %-40s %-40s\n", guerreiro[i], mago[i], arqueiro[i]);
            // %s é para puxar a string
            // -25 é para alinhar à esquerda, 25 caracteres
        }
    }
}
