import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Inventario {
    private List<Habilidade> habilidades;

    public Inventario(){
        this.habilidades = new ArrayList<>();
    }

    public void addHabilidade(Habilidade h){
        this.habilidades.add(h);
    }

    @Override
    public String toString(){
        return this.habilidades.toString();
    }
}
