//Classe Guerreiro

public class Atacante extends Jogador {

    public Atacante(String nome){
        super(nome, 8.0, 8, 3);

        this.habilidades.addHabilidade(
                new Habilidade(
                        "SuperKick",
                        "Um super chute fortissimo!",
                        "Aumento da finalização",
                        1
                )
        );
    }
    @Override
    public String toString(){
        return "Atacante: \n" +
                super.toString() + "|\n"  +
                "habilidades: " + this.habilidades + "|";
    }

    public Atacante(Atacante a){
        super(a);
        this.habilidades = (Inventario) a.habilidades.clone();
    }

    @Override
    public Object clone(){
        try{
            Atacante copia = (Atacante) super.clone();
            copia.habilidades = (Inventario) this.habilidades.clone();
            return copia;
        }catch(Exception e){
            return null;
        }
    }
}
