//Classe Personagem

public abstract class Jogador {
    protected String nome;
    protected int condicionamento;
    protected int finalizacao;
    protected int defesa;
    protected int nivel;
    protected Inventario habilidades;


    @Override
    public String toString(){ return "Jogador: " + nome + "Nível: " + nivel};

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(this.getClass() != obj.getClass) return false;
        Jogador j = (Jogador) obj;
        if(!this.nome.equals(((Jogador)obj).nome) || this.condicionamento != j.condicionamento || this.finalizacao != j.finalizacao ||
        this.defesa != j.defesa || this.nivel != j.nivel || !this.habilidades.equals(((Jogador)obj).habilidades)) return false;
    }

    @Override
    public int hashCode(){
        int r = 1;
        r = r * 2 + this.nome.hashCode();
        r = r  * 2 + ((Integer)this.condicionamento).hashCode();
        r = r  * 2 + ((Integer)this.finalizacao).hashCode();
        r = r  * 2 + ((Integer)this.defesa).hashCode();
        r = r  * 2 + ((Integer)this.nivel).hashCode();
        r = r * 2 + this.habilidades.hashCode();

        if(r<0) return r = -r;
        return r;
    }


}
