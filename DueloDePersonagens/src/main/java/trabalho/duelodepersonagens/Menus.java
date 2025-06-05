package trabalho.duelodepersonagens;


import java.util.Random;
import java.util.Scanner;

public class Menus {
    private String[][] arena = new String[10][10];
    private Personagem Player_1;
    private Personagem Player_2;
    private boolean ehPVE;


    public Menus(){
        InicializaArena();
        MenuInicial();
        Seleciona_Modo();
        Personaliza_Personagem();
        //Jogo.geraPosicaoInicial(Player_1, Player_2);
    }

    public Personagem getPlayer_1() {
        return Player_1;
    }

    public Personagem getPlayer_2() {
        return Player_2;
    }

    public void InicializaArena(){
        //...preenche a matriz de strings 10x10 com "[  ]" em cada string.
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                arena[j][i] = "[  ]";
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


    public void AtualizarArena(Personagem Player_1, Personagem Player_2){
        arena[Player_1.linha][Player_1.coluna] = ("[P1]");
        arena[Player_2.linha][Player_2.coluna] = ("[P2]");
        ImprimeArena();
        InicializaArena();
    }

    public void ImprimeDados(Personagem Player){
        System.out.println("O jogador "+ Player.nome+ " eh um "+ Player.classe +". Atributos:");
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
        if(num == 1 || !ehPVE){     // dupla verificação para não serem criados dois bots.
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


    public void Menu_de_Combate(Personagem jogador, Personagem inimigo) {
        Actions jogador_action = new Actions(jogador);
        Scanner teclado = new Scanner(System.in);
        System.out.println("\nEscolha sua ação:");
        System.out.println("1 - Mover");
        System.out.println("2 - Atacar");
        System.out.println("3 - Defender");
        System.out.println("4 - Poder Especial");
        System.out.println("5 - Desistir do jogo");

        int acao = teclado.nextInt();
        while (acao < 1 || acao > 5) {
            System.out.println("Opção inválida! Escolha novamente:");
            acao = teclado.nextInt();
        }

        switch (acao) {
            case 1:
                System.out.println("Digite a direção (C - Cima, B - Baixo, E - Esquerda, D - Direita):");
                char direcao = teclado.next().charAt(0);
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
                jogador.PontosDeVida = 0;

        }
    }

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

    public boolean isEhPVE() {
        return ehPVE;
    }
}

