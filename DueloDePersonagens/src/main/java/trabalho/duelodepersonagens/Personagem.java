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

    public boolean Andar(Character direcional){ // utiliza C/B/E/D como os direicionais para movimentar o jogador no tabuleiro
        direcional = getDirecao(direcional);
        return verificaDirecional(direcional);
    }

    private static Character getDirecao(Character direcional) { //metodo para movimentação do jogador
        while(direcional != 'C' && direcional != 'c' && direcional != 'E' && direcional != 'e' && direcional != 'd' && direcional != 'D' && direcional != 'B' && direcional != 'b'){
            System.out.println("Opção inválida! \n Por favor, digite um direcional válido.");
            Scanner verificaDirecional = new Scanner(System.in);
            direcional = verificaDirecional.nextLine().charAt(0);
        }
        return direcional;
    }

    private boolean verificaDirecional(Character direcional) {

        if (direcional == 'C' || direcional == 'c') {
            if ((this.linha - 1) < 0) {
                return false;
            }
            this.linha--;
        }
        else if (direcional == 'E' || direcional == 'e') {
            if (this.coluna - 1 < 0) {
                return false;
            }
            this.coluna--;
        }
        else if (direcional == 'D' || direcional == 'd') {
            if ((this.coluna + 1) > 9) {
                return false;
            }
            this.coluna++;
        }
        else if (direcional == 'B' || direcional == 'b') {
            if ((this.linha + 1) > 9) {
                return false;
            }
            this.linha++;
        }
        return true;
    }


    public boolean EstaNoAlcance(Personagem inimigo){ //calcula a distancia entre os dois personagens utilizando a Distância de Chebyshev
        int Distancia = Math.max(Math.abs(this.linha - inimigo.linha), Math.abs(this.coluna - inimigo.coluna));
        return Distancia <= AlcanceDeAtaque;
    }


    public void atacar(Personagem Inimigo){
        if(EstaNoAlcance(Inimigo)){ //max(0, forcaDeAtaque doatacante - forcaDeDefesa do alvo).
            int dano = Math.max(0, this.forcaDeAtaque - Inimigo.DefesaAtual);

            if(dano >0){
                Inimigo.PontosDeVida -= dano;
                System.out.println("O ataque foi bem sucedido!!");
                System.out.println("O "+ this.classe +" desfere seu golpe e causa "+ dano + " de dano no "+ Inimigo.classe +" "+ Inimigo.nome+"!");
            }
            else{
                System.out.println("Ataque mal-sucedido! A defesa de "+ Inimigo.nome+ " bloqueou completamente o ataque!");
            }

            Inimigo.DefesaAtual  = Math.max(0, Inimigo.DefesaAtual - this.forcaDeAtaque);
        }
        else{
            System.out.println("ERROU! O alvo estava fora do alcance!");

        }
    }

    public void Defender(){
        this.DefesaAtual = this.forcaDeDefesa;
        System.out.println("O "+classe +" " +nome +" se defendeu! Sua defesa foi restaurada para " + DefesaAtual + "!");
    }

    public boolean esta_vivo(){
        return this.PontosDeVida>0;
    }
    public abstract void AtivarPoderEspecial(Personagem Inimigo);

}

