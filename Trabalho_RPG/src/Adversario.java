//Classe Inimigo

public class Adversario extends Jogador {
    public Adversario(String nome){
        super(nome, 7.0, 7, 7, 5);
        this.habilidades.addHabilidade(
                new Habilidade(
                        "Bote",
                        "Um bote preciso e na bola",
                        "Desarme certo",
                        1
                )
        );
        this.habilidades.addHabilidade(
                new Habilidade(
                        "SuperKick",
                        "Um super chute fortissimo!",
                        "Aumento da finalizção",
                        1
                )
        );
        this.habilidades.addHabilidade(
                new Habilidade("passe teleguiado",
                        "Um passe com enorme precisão",
                        "Passe certo",
                        1
                )
        );
    }

    public String getNome(){
        return this.nome;
    }
    public double getCondicionaemento(){
        return this.condicionamento;
    }
    public int getFinalizacao() {
        return this.finalizacao;
    }
    public int getDefesa(){
        return this.defesa;
    }
    public int getNivel(){
        return this.nivel;
    }

    @Override
    public String toString(){
        return "Adversario: {" +
                super.toString() +
                "habilidades: " + this.habilidades + "}";
    }
}
