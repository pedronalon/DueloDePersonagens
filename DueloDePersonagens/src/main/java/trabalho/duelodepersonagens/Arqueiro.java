package trabalho.duelodepersonagens;

public class Arqueiro extends Personagem {
    /// Classe arqueiro que herda os atributos de Personagem.
    public Arqueiro(String nome) {
        super(nome,"Arqueiro", 8, 5, 5, false);
    }

    @Override
    public void AtivarPoderEspecial(Personagem jogador){
        setAlcanceDeAtaque(getAlcanceDeAtaque()+ 1);
        System.out.println("FLECHA PRECISA! Arqueiro "+ getNome() + " incrementa permanentemente 1 em seu alcance de ataque!!");
        System.out.println("Alcance de ataque atual: " + getAlcanceDeAtaque());
    }


}
