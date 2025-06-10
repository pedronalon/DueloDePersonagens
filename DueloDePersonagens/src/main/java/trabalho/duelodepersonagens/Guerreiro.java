package trabalho.duelodepersonagens;

public class Guerreiro extends Personagem {
    /// Classe guerreiro que herda os atributos de Personagem.
    public Guerreiro(String nome) {
        super(nome,"Guerreiro", 15, 10, 1, false);
    }

    @Override
        public void AtivarPoderEspecial(Personagem inimigo){
        if(!getEspecialAtivado()){
            setForcaDeAtaque(30);
            System.out.println("CARGA BRUTAL! Guerreiro "+ getNome()+ " aumenta permanentemente seu ataque para 30!!");
            setEspecialAtivado(true);
        }
        else
            System.out.println("ERRO! O guerreiro "+ getNome()+" já utlizou sua habilidade especial");
    }


}
