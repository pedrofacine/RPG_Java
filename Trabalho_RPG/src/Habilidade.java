public class Habilidade extends Jogador{
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

}
