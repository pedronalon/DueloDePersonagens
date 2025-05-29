package trabalho.duelodepersonagens;

public class Arqueiro {
    private String nome;
    private int PontosDeVida = 100;
    private final int forcaDeAtaque = 8;
    private final int forcaDeDefesa = 5;
    private final int AlcanceDeAtaque = 5;

    public Arqueiro(String nome_jogador1){
        nome = nome_jogador1;
    }
}
