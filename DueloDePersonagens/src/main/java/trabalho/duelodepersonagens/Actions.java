package trabalho.duelodepersonagens;

import java.util.Scanner;

public class Actions {

    private final Personagem personagem;

    public Actions(Personagem personagem){
        this.personagem = personagem;
    }

    public boolean Andar(Character direcional){ // utiliza C/B/E/D como os direicionais para movimentar o jogador no tabuleiro
        direcional = getDirecao(direcional);
        return (verificaDirecional(direcional));

    }


    private static Character getDirecao(Character direcional) { //Metodo para movimentação do jogador

        direcional = Character.toUpperCase(direcional);
          while(direcional != 'C' && direcional != 'E' && direcional != 'D' && direcional != 'B' ){
            System.out.println("Opção inválida! \n Por favor, digite um direcional válido.");
            Scanner verificaDirecional = new Scanner(System.in);
            direcional = verificaDirecional.nextLine().charAt(0);
            direcional = Character.toUpperCase(direcional);
        }
        return direcional;
    }


    private boolean verificaDirecional(Character direcional) {
        direcional = Character.toUpperCase(direcional);

        switch(direcional) {
            case 'C':
                if ((personagem.getLinha() - 1) < 0)
                    return false;
                personagem.setLinha(personagem.getLinha() - 1);
                break;
            case 'E':
                if (personagem.getColuna() - 1 < 0)
                    return false;
                personagem.setColuna(personagem.getColuna() - 1);
                break;
            case 'D':
                if ((personagem.getColuna() + 1) > 9)
                    return false;
                personagem.setColuna(personagem.getColuna() + 1);
                break;
            case 'B':
                if ((personagem.getLinha() + 1) > 9)
                    return false;
                personagem.setLinha(personagem.getLinha() + 1);
                break;
        }
        return true;
    }


    public void atacar(Personagem Inimigo){
        if(EstaNoAlcance(Inimigo)){ //max(0, forcaDeAtaque do atacante - forcaDeDefesa do alvo).
            int dano = Math.max(0, personagem.getForcaDeAtaque() - Inimigo.getDefesaAtual());

            if(dano > 0){
                Inimigo.setPontosDeVida(Inimigo.getPontosDeVida() - dano);
                System.out.println("O ataque foi bem sucedido!!");
                System.out.println("O "+ personagem.getClasse() +" "+personagem.getNome()+" desfere seu golpe e causa "+ dano + " de dano no "+ Inimigo.getClasse() +" "+ Inimigo.getNome()+"!");
            }
            else{
                System.out.println("Ataque mal-sucedido! A defesa de "+ Inimigo.getNome()+ " bloqueou completamente o ataque!");
            }

            Inimigo.setDefesaAtual(Math.max(0, Inimigo.getDefesaAtual() - personagem.getForcaDeAtaque()));
        }
        else{
            System.out.println("ERROU! O alvo estava fora do alcance!");

        }
    }

    public void Defender(){
        personagem.setDefesaAtual(personagem.getForcaDeDefesa());
        System.out.println("O "+personagem.getClasse() +" " +personagem.getNome() +" se defendeu! Sua defesa foi restaurada para " + personagem.getDefesaAtual() + "!");
    }



    public boolean EstaNoAlcance(Personagem inimigo){ //calcula a distância entre os dois personagens utilizando a Distância de Chebyshev
        int Distancia = Math.max(Math.abs(personagem.getLinha() - inimigo.getLinha()), Math.abs(personagem.getColuna() - inimigo.getColuna()));
        return Distancia <= personagem.getAlcanceDeAtaque();
    }

}
