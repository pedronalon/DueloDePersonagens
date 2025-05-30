package trabalho.duelodepersonagens;

public class Mago extends Personagem {
    public Mago(String nome) {
        super(nome, 10, 7, 3);
    }

    @Override
    public void AtivarPoderEspecial(Personagem inimigo){
        this.PontosDeVida = inimigo.PontosDeVida;
        System.out.println("TROCAR VIDA! O mago "+ nome +" troca os seus pontos de vida com seu adversário!!");
    }

}
