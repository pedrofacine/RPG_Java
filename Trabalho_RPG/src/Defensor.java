public class Defensor extends Jogador{

    public Defensor(String nome){
        super(nome, 6.0, 3, 8, 5);
        this.habilidades = new Inventario();

        this.habilidades.addHabilidade(
                new Habilidade(
                        "Bote",
                        "Um bote preciso e na bola",
                        "Desarme certo",
                        1
                )
        );
    }

    @Override
    public String toString(){
        return "Defensor: {" +
                super.toString() +
                "habilidades: " + this.habilidades + "}";
    }
}
