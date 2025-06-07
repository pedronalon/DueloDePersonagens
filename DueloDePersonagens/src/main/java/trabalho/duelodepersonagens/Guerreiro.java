package trabalho.duelodepersonagens;

public class Guerreiro extends Personagem {
    public Guerreiro(String nome) {
        super(nome,"Guerreiro", 15, 10, 1);
    }

    @Override
        public void AtivarPoderEspecial(Personagem inimigo){
            setForcaDeAtaque(30);
            System.out.println("CARGA BRUTAL! Guerreiro "+ getNome()+ " aumenta permanentemente seu ataque para 30!!");
    }


}
