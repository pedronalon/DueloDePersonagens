package trabalho.duelodepersonagens;

public class Mago extends Personagem {
    /// Classe mago que herda os atributos de Personagem.
    public Mago(String nome) {
        super(nome,"Mago", 10, 7, 3, false);
    }

    @Override
    public void AtivarPoderEspecial(Personagem inimigo){
        if(EstaNoAlcance(inimigo)){
            int temp = this.getPontosDeVida();
            this.setPontosDeVida(inimigo.getPontosDeVida());
            inimigo.setPontosDeVida(temp);
            System.out.println("TROCAR VIDA! O mago "+ getNome() +" troca os seus pontos de vida com seu adversário!!");
        }
        else
            System.out.println("TROCAR VIDA! O mago "+ getNome()+ " tenta trocar de vida e falha miseravelmente!");
    }

}
