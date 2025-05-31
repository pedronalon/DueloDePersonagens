package trabalho.duelodepersonagens;


import java.util.Random;
import java.util.Scanner;

public class Menus {
    private String[][] arena;
    private Personagem Player_1;
    private Personagem Player_2;
    private boolean ehPVE;


    public Menus(){
        CriarArena();
        MenuInicial();
        Seleciona_Modo();
        Personaliza_Personagem();
        Jogo.geraPosicaoInicial(Player_1, Player_2);
    }

    public Personagem getPlayer_1() {
        return Player_1;
    }

    public Personagem getPlayer_2() {
        return Player_2;
    }

    public void CriarArena(){
            arena = new String [10][10];   //...Cria uma matriz de strings 10x10, inicializada por "[ ]"em cada string.
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                arena[j][i] = "[ ]";
            }
        }
    }

    public void ImprimeArena(){
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                System.out.print(arena[i][j]+ " ");
            }
            System.out.println();
        }
    }

    public void ImprimeDados(Personagem Player){
        System.out.println("O jogador "+ Player.nome+ "eh um "+ Player.classe +". Atributos:");
        System.out.println("Pontos de vida: "+ Player.PontosDeVida);
        System.out.println("Dano de ataque: "+ Player.forcaDeAtaque);
        System.out.println("Defesa atual: "+Player.DefesaAtual);
        System.out.println("Alcance: " + Player.AlcanceDeAtaque);
        System.out.println("Posicao: ["+Player.linha +"], ["+Player.coluna +"]" );
    }

    private void MenuInicial() {
        System.out.println("Bem vindo ao Masmorras e Dragões!\n");
        System.out.println("Masmorras e Dragões é um jogo de estratégia por turnos onde apenas os mais habilidosos e destemidos sobreviverão!");
        System.out.println("Neste combate tático, dois oponentes se enfrentam em uma Arena 2D, travando duelos entre arqueiros, guerreiros e magos!\n");
    }

    private void Seleciona_Modo() { //escolha do modo de jogo à prova de burro!
        Scanner teclado = new Scanner(System.in);
        String ModoDeJogo;
        System.out.println("Por favor, selecione o modo de jogo!\n (1): PvP \n (2): PvE");
        ModoDeJogo = teclado.next();


        while (!ModoDeJogo.equals("1") && !ModoDeJogo.equals("2")) {
            System.out.println("Por favor, selecione uma opcao valida!\n (1): PvP \n (2): PvE");
            ModoDeJogo = teclado.next();
        }

        ehPVE = ModoDeJogo.equals("2");


    }

    private void Personaliza_Personagem() {
        Player_1 = getInfo(1);
        Player_2 = getInfo(2);
    }

    private Personagem getInfo(int num){
        if(num == 1 || !ehPVE){     // dupla verificação para que não sejam criados dois bots.
                                    //caso o modo de jogo escolhido seja PvP

            Scanner teclado = new Scanner(System.in);
            System.out.println("Jogador " + num + ", digite seu nome: ");
            String nome = teclado.nextLine();
            System.out.println("Ótimo. Seja bem-vindo, " + nome + "! Agora, escolha uma classe! As opçoes são: ");
            imprimeClasses();

            String classe = teclado.next();
            while (!classe.equals("1") && !classe.equals("2") && !classe.equals("3")){
                System.out.println("Por favor, escolha uma classe válida!");
                classe = teclado.next();
            }

            int escolhaClasse = Integer.parseInt(classe);
            return Seleciona_Personagem(escolhaClasse, nome);
        }

        else{
            // caso o modo de jogo escolhido seja PvE
            int classe_bot = new Random().nextInt(3) + 1;
            return Seleciona_Personagem(classe_bot, "BOT");
        }

    }


    private Personagem Seleciona_Personagem(int classe, String Nome) {

        // eu tinha colocado case:1 , case:2 . a IDE sugeriu trocar pra ->
        return switch (classe) {
            case 1 -> new Guerreiro(Nome);
            case 2 -> new Mago(Nome);
            case 3 -> new Arqueiro(Nome);
            default -> null;
        };
    }


    private static void Menu_de_Combate(){}
    // ta foda, ainda n fiz



    private static void imprimeClasses() { //forma mais amigável ao usuário de visualizar as informações
        String[] guerreiro = {
                "(1) Guerreiro:",
                "     Vida: 100",
                "     Ataque: 15",
                "     Defesa: 10",
                "     Alcance: 1"
        };

        String[] mago = {
                "(2) Mago:",
                "     Vida: 100",
                "     Ataque: 10",
                "     Defesa: 7",
                "     Alcance: 3"
        };

        String[] arqueiro = {
                "(3) Arqueiro:",
                "     Vida: 100",
                "     Ataque: 8",
                "     Defesa: 5",
                "     Alcance: 5"
        };
        for (int i = 0; i < 5; i++) {
            System.out.printf("%-25s %-25s %-25s\n", guerreiro[i], mago[i], arqueiro[i]);
            // %s é para puxar a string
            // -25 é para alinhar à esquerda, 25 caracteres
        }
    }
}

