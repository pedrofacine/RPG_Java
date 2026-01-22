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
                habilidade.addQtd(h.getQtd());
                return;
            }
        }
        this.habilidades.add(h.clone());
    }

    public void removeHabilidade(Habilidade h, int qtd){
        for(int i = 0; i < this.habilidades.size(); i++){
            Habilidade habilidade = this.habilidades.get(i);
            if(habilidade.getNome().equals(h.getNome())){
                h.rmQtd(qtd);
                if(h.getQtd() <= 0){
                    this.habilidades.remove(i);
                }
                return;
            }
        }
    }

    public Habilidade getHabilidadePorNome(String nome){
        for(Habilidade h : this.habilidades){
            if(h.getNome().equalsIgnoreCase(nome)){
                return h;
            }
        }
        return null;
    }

    @Override
    public String toString(){
        return this.habilidades.toString();
    }

    @Override
    public int hashCode(){
        int retorno = 1;

        for(int i = 0; i<this.habilidades.size(); i++){
            if(this.habilidades.get(i) != null){
                retorno = retorno * 2 + this.habilidades.get(i).hashCode();
            }
        }

        if(retorno < 0) retorno = -retorno;
        return retorno;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == this) return true;
        if(obj == null) return false;
        if(obj.getClass() != this.getClass()) return false;
        Inventario i = (Inventario) obj;
        if(i.habilidades.size() != this.habilidades.size()) return false;
        for(int j = 0; j<i.habilidades.size(); j++){
            if(!i.habilidades.containsAll(this.habilidades)
                    && this.habilidades.containsAll(i.habilidades)) return false;
        }
        return true;
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
