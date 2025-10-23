//Classe Guerreiro

public class Atacante extends Jogador {

    public Atacante(String nome){
        super(nome, 8.0, 8, 3, 5);
        this.habilidades = new Inventario();

        this.habilidades.addHabilidade(
                new Habilidade(
                        "SuperKick",
                        "Um super chute fortissimo!",
                        "Aumento da finalizção"
                )
        );
    }
    @Override
    public String toString(){
        return "Atacante: {" +
                super.toString() + '|'  +
                "habilidades: " + this.habilidades + "}";
    }
}
