package trabalho.duelodepersonagens;


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
    private boolean EspecialAtivado;

    /// Construtor da classe personagem.
    public Personagem(String nome,String classe, int forcaDeAtaque, int forcaDeDefesa, int AlcanceDeAtaque, boolean EspecialAtivado) { //construtor da classe personagem
        this.nome = nome;
        this.classe = classe;
        this.forcaDeDefesa = forcaDeDefesa;
        this.EspecialAtivado = EspecialAtivado;
        setForcaDeAtaque(forcaDeAtaque);
        setDefesaAtual(forcaDeDefesa);
        setAlcanceDeAtaque(AlcanceDeAtaque);
        setPontosDeVida(100);
    }


    public boolean esta_vivo(){
        return getPontosDeVida()>0;
    }

    /// Metodo que verifica se o inimigo está no alcance do jogador.
    public boolean EstaNoAlcance(Personagem inimigo){ //calcula a distância entre os dois personagens utilizando a Distância de Chebyshev
        int Distancia = Math.max(Math.abs(this.getLinha() - inimigo.getLinha()), Math.abs(this.getColuna() - inimigo.getColuna()));
        return Distancia <= this.getAlcanceDeAtaque();
    }

    public abstract void AtivarPoderEspecial(Personagem Inimigo);

    /// Métodos para acessar os atributos do personagem
    public int getLinha() { return linha;}
    public int getColuna() { return coluna;}
    public String getNome() { return nome;}
    public String getClasse() { return classe;}
    public int getForcaDeAtaque() { return forcaDeAtaque;}
    public int getDefesaAtual() { return DefesaAtual;}
    public int getForcaDeDefesa() { return forcaDeDefesa;}
    public int getAlcanceDeAtaque() { return AlcanceDeAtaque;}
    public int getPontosDeVida() { return PontosDeVida;}
    public boolean getEspecialAtivado() { return EspecialAtivado;}


    /// Metodos para alterar os atributos dos personagens
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

    public void setEspecialAtivado(boolean especialAtivado) {
        this.EspecialAtivado = especialAtivado;
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
