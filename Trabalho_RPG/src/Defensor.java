public class Defensor extends Jogador{

    public Defensor(String nome){
        super(nome, 6.0, 3, 8);

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

    public Defensor(Defensor d){
        super(d);
        this.habilidades = (Inventario) d.habilidades.clone();
    }

    @Override
    public Object clone(){
        try{
            Defensor copia = (Defensor) super.clone();
            copia.habilidades = (Inventario) this.habilidades.clone();
            return copia;
        } catch (Exception e) {
            return null;
        }
    }
}
