package trabalho.duelodepersonagens;



public abstract class Personagem {
    protected String nome;
    protected int PontosDeVida;
    protected int forcaDeAtaque;
    protected int forcaDeDefesa;
    protected int AlcanceDeAtaque;
    protected int DefesaAtual;


    public Personagem(String nome, int forcaDeAtaque, int forcaDeDefesa, int AlcanceDeAtaque) {
        this.nome = nome;
        this.forcaDeAtaque = forcaDeAtaque;
        this.forcaDeDefesa = forcaDeDefesa;
        this.AlcanceDeAtaque = AlcanceDeAtaque;
        this.PontosDeVida = 100;
    }

    public void mover(Character Direction){
        if(Direction == 'C' || Direction == 'c'){

        }
    }

}

