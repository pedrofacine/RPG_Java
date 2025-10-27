//Classe Mago

public class MeioCampo extends Jogador {

    public MeioCampo(String nome){
        super(nome, 7.0, 6, 5, 5);
        this.habilidades.addHabilidade(
                new Habilidade("passe teleguiado",
                        "Um passe com enorme precisão",
                        "Passe certo",
                        1
                )
        );
    }

    @Override
    public String toString(){
        return "MeioCampo: {" +
                super.toString() + '|' +
                "habilidades: " + '|' + this.habilidades + '|' + "}";
    }

    public MeioCampo(MeioCampo m){
        super(m);
        this.habilidades = (Inventario) m.habilidades.clone();
    }

    @Override
    public Object clone(){
        try{
            MeioCampo m = (MeioCampo) super.clone();
            m.habilidades = (Inventario) this.habilidades.clone();
            return m;
        }catch (Exception e) {
            return null;
        }
    }
}
