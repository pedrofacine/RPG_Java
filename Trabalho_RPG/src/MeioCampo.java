//Classe Mago

public class MeioCampo extends Jogador {

    public MeioCampo(String nome){
        this.nome = nome;
        this.condicionamento = 7.0;
        this.finalizacao = 7;
        this.defesa = 7;
        this.nivel = 5;
        this.habilidades = new Inventario();

        this.habilidades.addHabilidade(
                new Habilidade("drible",
                        "Passa pelo adversário",
                        "Ignora a defesa do adversário"
                )
        );
    }
}
