package trabalho.duelodepersonagens;

public class Mago extends Personagem {
    public Mago(String nome) {
        super(nome,"Mago", 10, 7, 3);
    }

    @Override
    public void AtivarPoderEspecial(Personagem inimigo){
        int temp = this.PontosDeVida;
        this.PontosDeVida = inimigo.PontosDeVida;
        inimigo.PontosDeVida = temp;
        System.out.println("TROCAR VIDA! O mago "+ nome +" troca os seus pontos de vida com seu adversário!!");
    }

}
