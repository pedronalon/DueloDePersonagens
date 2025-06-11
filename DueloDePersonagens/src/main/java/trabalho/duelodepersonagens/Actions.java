package trabalho.duelodepersonagens;

import java.util.Scanner;

public class Actions {

    private final Personagem personagem;

    public Actions(Personagem personagem){
        this.personagem = personagem;
    }

    /// Chamada das funções que garantem a movimentação correta do jogador
    public boolean Andar(String direcionalString){ /// Utiliza C/B/E/D como os direicionais para movimentar o jogador no tabuleiro

        Scanner leDirecao = new Scanner(System.in);
        char direcional = VerificaDirecao(direcionalString);
        boolean movimento;

        /// Verifica a cada tentativa se a direção é válida, permitindo que o usuário troque de ideia.
            do {
                movimento = EscolheDirecional(direcional);
                if(!movimento){
                    System.out.println("Direção inválida! por favor, escolha outra.");
                    String loop = leDirecao.next();
                    direcional = VerificaDirecao(loop);
                }
            }while(!movimento);

        return movimento;
    }


    /// Verifica se o índice passado pelo usuário é válido
    private static Character VerificaDirecao(String direcional) {
        Scanner EscolheDirecional = new Scanner(System.in);

        do{
            direcional = direcional.toUpperCase();
            if(!direcional.equals("C") &&  !direcional.equals("D") && !direcional.equals("B") &&  !direcional.equals("E")){
                System.out.println("Opção inválida! \nPor favor, digite um direcional válido.");
                direcional = EscolheDirecional.next();
                direcional = direcional.toUpperCase();
            }
        } while(!direcional.equals("C") &&  !direcional.equals("D") && !direcional.equals("B") &&  !direcional.equals("E"));

        return direcional.charAt(0);
    }


    /// Realiza as alterações na posição do jogador no mapa, em seu índice linha ou coluna com base no caractere escolhido.
    private boolean EscolheDirecional(Character direcional) {
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


    /// Utiliza o metodo que verifica se o inimigo está dentro do seu alcance, e então, ataca.
    public void atacar(Personagem Inimigo){ /// Distância de Chebyshev
        if(personagem.EstaNoAlcance(Inimigo)){ //max(0, forcaDeAtaque do atacante - forcaDeDefesa do alvo).
            int dano = Math.max(0, personagem.getForcaDeAtaque() - Inimigo.getDefesaAtual());

            if(dano > 0){
                Inimigo.setPontosDeVida(Inimigo.getPontosDeVida() - dano);
                System.out.println("O ataque foi bem sucedido!!");
                System.out.println("O "+ personagem.getClasse() +" "+personagem.getNome()+" desfere seu golpe e causa "+ dano + " de dano no "+ Inimigo.getClasse() +" "+ Inimigo.getNome()+"!");
            }
            else{
                System.out.println("O golpe acertou!! Porém, a defesa de "+ Inimigo.getNome()+ " bloqueou completamente!!");
            }

            Inimigo.setDefesaAtual(Math.max(0, Inimigo.getDefesaAtual() - personagem.getForcaDeAtaque()));
        }
        else{
            System.out.println("ERROU! O alvo estava fora do alcance!");
        }
    }


    /// Restaura a defesa atual para seu valor inicial/máximo.
    public void Defender(){
        personagem.setDefesaAtual(personagem.getForcaDeDefesa());
        System.out.println("O "+personagem.getClasse() +" " +personagem.getNome() +" se defendeu! Sua defesa foi restaurada para " + personagem.getDefesaAtual() + "!");
    }
}
