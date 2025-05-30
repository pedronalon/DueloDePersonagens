package trabalho.duelodepersonagens;


import java.util.Scanner;

public abstract class Personagem {
    protected String nome;
    protected int PontosDeVida;
    protected int forcaDeAtaque;
    protected int forcaDeDefesa;
    protected int AlcanceDeAtaque;
    protected int DefesaAtual;
    protected int linha;
    protected int coluna;


    public Personagem(String nome, int forcaDeAtaque, int forcaDeDefesa, int AlcanceDeAtaque) { //construtor da classe personagem
        this.nome = nome;
        this.forcaDeAtaque = forcaDeAtaque;
        this.forcaDeDefesa = forcaDeDefesa;
        this.AlcanceDeAtaque = AlcanceDeAtaque;
        this.PontosDeVida = 100;
    }

    public void Andar(Character direcional){ // utiliza C/B/E/D como os direicionais para movimentar o jogador no tabuleiro
        direcional = getDirecao(direcional);
        verificaDirecional(direcional);
    }

    private static Character getDirecao(Character direcional) { //método utilizado pela funcao andar
        while(direcional != 'C' || direcional != 'c' || direcional != 'E' || direcional != 'e' || direcional != 'd' || direcional != 'D' || direcional != 'B' || direcional != 'b'){
            System.out.println("Opção inválida! \n Por favor, digite um direcional válido.");
            Scanner verificaDirecional = new Scanner(System.in);
            direcional = verificaDirecional.nextLine().charAt(0);
        }
        return direcional;
    }
    private void verificaDirecional(Character direcional) {

        if(direcional == 'C' || direcional == 'c') {
            while((this.coluna + 1) > 9){
                Scanner teclado = new Scanner(System.in);
                System.out.println("Não é possível se mover nessa direção, por favor tente outra!");
                direcional = teclado.nextLine().charAt(0);
            }
             this.coluna += 1;
        }

        else if(direcional == 'E' || direcional == 'e') {

            this.linha--;
        }
        else if(direcional == 'D' || direcional == 'd') {
            this.linha++;
        }
        else if(direcional == 'B' || direcional == 'b') {
            while((this.coluna - 1) < 0){
                Scanner teclado = new Scanner(System.in);
                System.out.println("Não é possível se mover nessa direção, por favor tente outra!");
                direcional = teclado.nextLine().charAt(0);
            }
            this.coluna -= 1;
        }




    }


    public boolean EstaNoAlcance(Personagem inimigo){ //calcula a distancia entre os dois personagens utilizando a Distância de Chebyshev
        int Distancia = Math.max(Math.abs(this.linha - inimigo.linha), Math.abs(this.coluna - inimigo.coluna));
        return Distancia <= AlcanceDeAtaque;
    }


    public void atacar(Personagem Inimigo){
        if(EstaNoAlcance(Inimigo)){

        }
    }

    public void Defender(String nome){
        DefesaAtual = forcaDeDefesa;
    }

    public abstract void AtivarPoderEspecial(Personagem Inimigo);

}

