//Classe Inimigo

public class Adversario extends Jogador {
    public Adversario(String nome){
        super(nome, 7.0, 7, 7, 5);
        this.habilidades.addHabilidade(
                new Habilidade(
                        "Bote",
                        "Um bote preciso e na bola",
                        "Desarme certo"
                )
        );
        this.habilidades.addHabilidade(
                new Habilidade(
                        "SuperKick",
                        "Um super chute fortissimo!",
                        "Aumento da finalizção"
                )
        );
        this.habilidades.addHabilidade(
                new Habilidade("passe teleguiado",
                        "Um passe com enorme precisão",
                        "Passe certo"
                )
        );
    }

    @Override
    public String toString(){
        return "Adversario: {" +
                super.toString() +
                "habilidades: " + this.habilidades + "}";
    }
}
