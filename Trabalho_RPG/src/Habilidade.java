public class Habilidade{
    private String nome;
    private String desc;
    private String efeito;
    private int qtd;

    public Habilidade(String nome, String desc, String efeito){
        this.nome = nome;
        this.desc = desc;
        this.efeito = efeito;
        this.qtd = 1;
    }

    @Override
    public String toString(){
        return "{"+this.nome + '|' + this.desc + '|' +this.efeito + '|' + this.qtd + "}";
    }
}
