//Classe Mago

public class MeioCampo extends Jogador {

    public MeioCampo(String nome){
        super(nome, 7.0, 6, 5, 5);
        this.habilidades.addHabilidade(
                new Habilidade("passe teleguiado",
                        "Um passe com enorme precisão",
                        "Passe certo"
                )
        );
    }

    @Override
    public String toString(){
        return "MeioCampo: {" +
                super.toString() + '|' +
                "habilidades: " + '|' + this.habilidades + '|' + "}";
    }
}
