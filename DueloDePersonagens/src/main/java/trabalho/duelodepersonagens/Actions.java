package trabalho.duelodepersonagens;

import java.util.Scanner;

public class Actions {

    private Personagem personagem;
    private int linha;
    private int coluna;

    public Actions(Personagem personagem){
        this.personagem = personagem;
        this.linha = personagem.getLinha();
        this.coluna = personagem.getColuna();
    }

    public boolean Andar(Character direcional){ // utiliza C/B/E/D como os direicionais para movimentar o jogador no tabuleiro
        direcional = getDirecao(direcional);
        if(verificaDirecional(direcional)){
            personagem.linha = this.linha;
            personagem.coluna = this.coluna;
            return true;
        }
    return false;
    }


    private static Character getDirecao(Character direcional) { //metodo para movimentação do jogador
          while(direcional != 'C' && direcional != 'c' &&
                direcional != 'E' && direcional != 'e' &&
                direcional != 'd' && direcional != 'D' &&
                direcional != 'B' && direcional != 'b'){
            System.out.println("Opção inválida! \n Por favor, digite um direcional válido.");
            Scanner verificaDirecional = new Scanner(System.in);
            direcional = verificaDirecional.nextLine().charAt(0);
        }
        return direcional;
    }


    private boolean verificaDirecional(Character direcional) {
        direcional = Character.toUpperCase(direcional);

        switch(direcional) {
            case 'C':
                if ((this.linha - 1) < 0) return false;
                this.linha--;
                break;
            case 'E':
                if (this.coluna - 1 < 0) return false;
                this.coluna--;
                break;
            case 'D':
                if ((this.coluna + 1) > 9) return false;
                this.coluna++;
                break;
            case 'B':
                if ((this.linha + 1) > 9) return false;
                this.linha++;
                break;
        }
        return true;
    }


    public void atacar(Personagem Inimigo){
        if(EstaNoAlcance(Inimigo)){ //max(0, forcaDeAtaque do atacante - forcaDeDefesa do alvo).
            int dano = Math.max(0, personagem.forcaDeAtaque - Inimigo.DefesaAtual);

            if(dano >0){
                Inimigo.PontosDeVida -= dano;
                System.out.println("O ataque foi bem sucedido!!");
                System.out.println("O "+ personagem.getClasse() +" desfere seu golpe e causa "+ dano + " de dano no "+ Inimigo.classe +" "+ Inimigo.nome+"!");
            }
            else{
                System.out.println("Ataque mal-sucedido! A defesa de "+ Inimigo.nome+ " bloqueou completamente o ataque!");
            }

            Inimigo.DefesaAtual  = Math.max(0, Inimigo.DefesaAtual - personagem.forcaDeAtaque);
        }
        else{
            System.out.println("ERROU! O alvo estava fora do alcance!");

        }
    }

    public void Defender(){
        personagem.DefesaAtual = personagem.forcaDeDefesa;
        System.out.println("O "+personagem.getClasse() +" " +personagem.getNome() +" se defendeu! Sua defesa foi restaurada para " + personagem.DefesaAtual + "!");
    }

    public boolean esta_vivo(){
        return personagem.PontosDeVida>0;
    }


    public boolean EstaNoAlcance(Personagem inimigo){ //calcula a distancia entre os dois personagens utilizando a Distância de Chebyshev
        int Distancia = Math.max(Math.abs(this.linha - inimigo.linha), Math.abs(this.coluna - inimigo.coluna));
        return Distancia <= personagem.AlcanceDeAtaque;
    }

}
