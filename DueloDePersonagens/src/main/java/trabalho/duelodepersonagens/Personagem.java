package trabalho.duelodepersonagens;
import java.util.Scanner;


public abstract class Personagem {
    protected String nome;
    protected String classe;
    protected int PontosDeVida;
    protected int forcaDeAtaque;
    protected int forcaDeDefesa;
    protected int AlcanceDeAtaque;
    protected int DefesaAtual;
    protected int linha;
    protected int coluna;


    public Personagem(String nome,String classe, int forcaDeAtaque, int forcaDeDefesa, int AlcanceDeAtaque) { //construtor da classe personagem
        this.nome = nome;
        this.classe = classe;
        this.forcaDeAtaque = forcaDeAtaque;
        this.DefesaAtual = forcaDeDefesa;
        this.forcaDeDefesa = forcaDeDefesa;
        this.AlcanceDeAtaque = AlcanceDeAtaque;
        this.PontosDeVida = 100;
    }

    public abstract void AtivarPoderEspecial(Personagem Inimigo);

    public int getLinha() { return linha;}
    public int getColuna() { return coluna;}
    public String getNome() { return nome;}
    public String getClasse() { return classe;}
}
