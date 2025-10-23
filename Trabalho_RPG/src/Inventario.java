import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Inventario implements Cloneable{
    private List<Habilidade> habilidades;

    public Inventario(){
        this.habilidades = new ArrayList<>();
    }

    public void addHabilidade(Habilidade h){
        for(Habilidade habilidade : this.habilidades){
            if(habilidade.equals(h)){
                this.habilidades.add(habilidade);
                habilidade.addQtd(h.getQtd());
                return;
            }
        }
        this.habilidades.add(h.clone());
    }

    public void removeHabilidade(Habilidade h, int qtd){
        for(int i = 0; i < qtd; i++){
            Habilidade habilidade = this.habilidades.get(i);
            if(habilidade.getNome().equals(h.getNome())){
                h.rmQtd(qtd);
                if(h.getQtd() == 0){
                    this.habilidades.remove(i);
                }
                return;
            }
        }
    }

    @Override
    public String toString(){
        return this.habilidades.toString();
    }

    public Inventario(Inventario i){
        this.habilidades = new ArrayList<>();
        for(Habilidade habilidade : i.habilidades){
            this.habilidades.add(habilidade.clone());
        }
    }

    @Override
    public Inventario clone(){
        return new Inventario(this);
    }
}
