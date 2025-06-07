package trabalho.duelodepersonagens;
import java.util.Scanner;


public abstract class Personagem {
    private final String nome;
    private final String classe;
    private int PontosDeVida;
    private int forcaDeAtaque;
    private final int forcaDeDefesa;
    private int AlcanceDeAtaque;
    private int DefesaAtual;
    private int linha;
    private int coluna;


    public Personagem(String nome,String classe, int forcaDeAtaque, int forcaDeDefesa, int AlcanceDeAtaque) { //construtor da classe personagem
        this.nome = nome;
        this.classe = classe;
        this.forcaDeDefesa = forcaDeDefesa;
        setForcaDeAtaque(forcaDeAtaque);
        setDefesaAtual(forcaDeDefesa);
        setAlcanceDeAtaque(AlcanceDeAtaque);
        setPontosDeVida(100);
    }

    public boolean esta_vivo(){
        return getPontosDeVida()>0;
    }
    public abstract void AtivarPoderEspecial(Personagem Inimigo);

    public int getLinha() { return linha;}
    public int getColuna() { return coluna;}
    public String getNome() { return nome;}
    public String getClasse() { return classe;}
    public int getForcaDeAtaque() { return forcaDeAtaque;}
    public int getDefesaAtual() { return DefesaAtual;}
    public int getForcaDeDefesa() { return forcaDeDefesa;}
    public int getAlcanceDeAtaque() { return AlcanceDeAtaque;}
    public int getPontosDeVida() { return PontosDeVida;}


    public void setPontosDeVida(int PontosDeVida) {
        if(PontosDeVida < 0)
            this.PontosDeVida = 0;
        else
            this.PontosDeVida = PontosDeVida;
    }

    public void setForcaDeAtaque(int forcaDeAtaque) {
        this.forcaDeAtaque = forcaDeAtaque;
    }

    public void setDefesaAtual(int DefesaAtual) {
        this.DefesaAtual = DefesaAtual;
    }

    public void setAlcanceDeAtaque(int AlcanceDeAtaque) {
        this.AlcanceDeAtaque = AlcanceDeAtaque;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }
}
