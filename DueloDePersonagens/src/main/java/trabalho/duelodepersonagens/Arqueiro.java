package trabalho.duelodepersonagens;

public class Arqueiro extends Personagem {
    public Arqueiro(String nome) {
        super(nome,"Arqueiro", 8, 5, 5);
    }

    @Override
    public void AtivarPoderEspecial(Personagem inimigo){
        this.AlcanceDeAtaque += 1;
        System.out.println("FLECHA PRECISA! Arqueiro "+ nome + " incrementa permanentemente 1 em seu alcance de ataque!!");
    }


}
