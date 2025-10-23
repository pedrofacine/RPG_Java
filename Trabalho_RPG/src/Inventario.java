import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private List<Habilidade> habilidades;

    public Inventario(){
        this.habilidades = new ArrayList<>();
    }

    public void addHabilidade(Habilidade h){
        this.habilidades.add(h);
    }
}
