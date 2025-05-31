package trabalho.duelodepersonagens;

public class Guerreiro extends Personagem {
    public Guerreiro(String nome) {
        super(nome,"Guerreiro", 15, 10, 1);
    }

    @Override
        public void AtivarPoderEspecial(Personagem inimigo){
            this.forcaDeAtaque = 30;
            System.out.println("CARGA BRUTAL! Guerreiro "+ nome+ "aumenta temporariamnte seu ataque para 30!!");
    }


}
