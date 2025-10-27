public class Habilidade implements Comparable<Habilidade>{
    private String nome;
    private String desc;
    private String efeito;
    private int qtd;

    public Habilidade(String nome, String desc, String efeito, int qtd){
        this.nome = nome;
        this.desc = desc;
        this.efeito = efeito;
        this.qtd = qtd;
    }

    public void addQtd(int qtd){
        this.qtd += qtd;
    }

    public void rmQtd(int qtd){
        this.qtd -= qtd;
    }

    public String getNome(){
        return nome;
    }
    public String getDesc(){
        return desc;
    }
    public String getEfeito(){
        return efeito;
    }
    public int getQtd(){
        return qtd;
    }

    @Override
    public String toString(){
        return "{"+this.nome + '|' + this.desc + '|' +this.efeito + '|' + this.qtd + "}";
    }

    @Override
    public int hashCode(){
        return this.nome.hashCode();
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        Habilidade h = (Habilidade) obj;
        return nome.equals(h.nome);
    }

    public Habilidade(Habilidade h){
        this.nome = h.nome;
        this.desc = h.desc;
        this.efeito = h.efeito;
        this.qtd = 1;
    }

    @Override
    public Habilidade clone(){
        return new Habilidade(this);
    }

    @Override
    public int compareTo(Habilidade h){
        return this.nome.compareTo(h.nome);
    }

}
