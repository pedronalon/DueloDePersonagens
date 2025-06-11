package trabalho.duelodepersonagens;


import java.util.List;
import java.util.Scanner;

public class Menus {
    private final String[][] arena = new String[10][10];
    private final Personagem Player_1;
    private final Personagem Player_2;
    private boolean ehPVE;


    public Menus(Scanner teclado){
        InicializaArena();
        MenuInicial();
        Seleciona_Modo(teclado);
        CriaPersonagem criaPersonagens = new CriaPersonagem();

        List<Personagem> jogadoresCriados = criaPersonagens.criarJogadores(teclado, ehPVE);
        this.Player_1 = jogadoresCriados.get(0);
        this.Player_2 = jogadoresCriados.get(1);
    }


    public void InicializaArena(){
        ///...preenche a matriz de strings 10x10 com "[  ]" em cada string.
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                arena[j][i] = "[  ]";
            }
        }
    }

    ///Metodo que imprime a arena
    public void ImprimeArena(){
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                System.out.print(arena[i][j]+ " ");
            }
            System.out.println();
        }
    }

    /// Metodo que atualiza a arena com a posição atual dos jogadores
    /// Antes de posicionar os jogadores no mapa, limpa a arena, para não haver sobreposição de posições

    public void AtualizarArena(Personagem Player_1, Personagem Player_2){
        InicializaArena();
        arena[Player_1.getLinha()][Player_1.getColuna()] = ("[P1]");
        arena[Player_2.getLinha()][Player_2.getColuna()] = ("[P2]");
        ImprimeArena();
    }

    ///Metodo que imprime todos os dados do jogador, utilizando os métodos get para cada atributo
    public void ImprimeDados(Personagem jogador, Personagem inimigo){
        String[] DadosJogador = {
                "Status "+ jogador.getNome()+": ",
                "   Classe:  "+ jogador.getClasse(),
                "   Pontos de vida: "+ jogador.getPontosDeVida(),
                "   Dano de ataque: "+ jogador.getForcaDeAtaque(),
                "   Defesa Atual: "+ jogador.getDefesaAtual(),
                "   Alcance: "+ jogador.getAlcanceDeAtaque(),
                "   Posicao: ["+jogador.getLinha() +", "+jogador.getColuna() +"]"
        };

        String[] DadosInimigo = {
                "Status "+ inimigo.getNome()+": ",
                "   Classe:  "+ inimigo.getClasse(),
                "   Pontos de vida: "+ inimigo.getPontosDeVida(),
                "   Dano de ataque: "+ inimigo.getForcaDeAtaque(),
                "   Defesa Atual: "+ inimigo.getDefesaAtual(),
                "   Alcance: "+ inimigo.getAlcanceDeAtaque(),
                "   Posicao: ["+inimigo.getLinha() +", "+inimigo.getColuna() +"]"
        };

        for(int i = 0; i < 7; i++)
            System.out.printf("%-40s %-40s\n", DadosJogador[i], DadosInimigo[i]);

    }

    /// Mensagem inicial
    private void MenuInicial() {
        System.out.println("Bem vindo ao Masmorras e Dragões!\n");
        System.out.println("Masmorras e Dragões é um jogo de estratégia por turnos onde apenas os mais habilidosos e destemidos sobreviverão!");
        System.out.println("Neste combate tático, dois oponentes se enfrentam em uma Arena 2D, travando duelos entre arqueiros, guerreiros e magos!\n");
    }

    /// Escolha do modo de jogo (difícil de ser quebrada.);
    private void Seleciona_Modo(Scanner teclado) {
        String ModoDeJogo;
        System.out.println("Por favor, selecione o modo de jogo!\n (1): PvP \n (2): PvE");
        ModoDeJogo = teclado.next();

        while (!ModoDeJogo.equals("1") && !ModoDeJogo.equals("2")) {
            System.out.println("Por favor, selecione uma opcao valida!\n (1): PvP \n (2): PvE");
            ModoDeJogo = teclado.next();
        }

        teclado.nextLine();
        ehPVE = ModoDeJogo.equals("2");
    }


    /// Imprime as opções de ação do jogador
    public int Menu_de_Combate(Personagem jogador, Personagem inimigo, Scanner teclado) {
        System.out.println("\nEscolha sua ação:");
        System.out.println("1 - Mover");
        System.out.println("2 - Atacar");
        System.out.println("3 - Defender");
        System.out.println("4 - Poder Especial");
        System.out.println("5 - Desistir do jogo");

        String acao = teclado.next();
        while (!acao.equals("1")  && !acao.equals("2") && !acao.equals("3") && !acao.equals("4") && !acao.equals("5")) {
            System.out.println("Opção inválida! Escolha novamente:");
            acao = teclado.next();
        }
        teclado.nextLine();
        return Integer.parseInt(acao);
    }

    /// Retorna a informação de que o jogo é pvp ou pve
    public boolean isEhPVE() {
        return ehPVE;
    }

    public Personagem getPlayer_1() {
        return Player_1;
    }

    public Personagem getPlayer_2() {
        return Player_2;
    }
}

