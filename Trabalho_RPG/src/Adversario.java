//Classe Inimigo

public class Adversario extends Jogador {
    public Adversario(String nome){
        super(nome, 7.0, 9, 8);
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

    public void aumentarStatsAdversario(){
        this.condicionamento ++;
        this.finalizacao ++;
        this.defesa ++;
        this.nivel ++;
        System.out.println("Os adversários estão mais fortes!");
    }

    @Override
    public String toString(){
        return "Adversario: \n" +
                super.toString() +
                "|";
    }
}
