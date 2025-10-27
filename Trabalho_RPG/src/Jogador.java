//Classe Personagem

public abstract class Jogador {
    protected String nome;
    protected Double condicionamento;
    protected int finalizacao;
    protected int defesa;
    protected int nivel;
    protected Inventario habilidades;

    public Jogador(String nome, Double condicionamento, int finalizacao, int defesa, int nivel){
        this.nome = nome;
        this.condicionamento = condicionamento;
        this.defesa = defesa;
        this.nivel = 1; //nivel sempre comecar em 1
        this.habilidades = new Inventario();

        this.habilidades.addHabilidade(
                new Habilidade("drible",
                        "Passa pelo adversário",
                        "Ignora a defesa do adversário",
                        1
                )
        );
    }

    public String getNome() {
        return nome;
    }

    public String enfrentar(Adversario adversario) {
        System.out.println(this.nome + " está com a bola enfrentando " + adversario.getNome() + "!");
        // jeh! se o jogador tem boa finalização,  PODE tentar afundar a rede, senão pode escolher tocar
        boolean tentaFinalizar = this.finalizacao + dado() > 13;

        if (tentaFinalizar) {
            int ataque = this.finalizacao + dado();
            int defesa = adversario.getDefesa() + adversario.dado();
            System.out.println(this.nome + " tenta finalizar ao gol!");
            System.out.println("Força do chute: " + finalizacao + " | Defesa do adversário: " + defesa);

            if (ataque > defesa) {
                if (dado() > 7) {
                    this.nivel += 1;
                    return("Gol de " + this.nome + "!");
                }
                else if(dado() > 3 && dado() <= 7){
                    return("Na trave!");
                }
                else return("Chutou para fora!");
            }
        }

        return "";
    }
    public String serEnfrentado(Adversario adversario) {
        System.out.println(this.nome + " está sendo atacado por " + adversario.getNome() + "!");

        boolean tentaFinalizar = adversario.getFinalizacao() + adversario.dado() > 13;

        if (tentaFinalizar) {
            int ataque = adversario.getFinalizacao() + adversario.dado();
            int defesa = this.defesa + dado();

            System.out.println(adversario.getNome() + " tenta finalizar!");
            System.out.println("Força do chute: " + ataque + " | Defesa de " + this.nome + ": " + defesa);

            if (ataque > defesa) {
                int resultado = dado();
                if (resultado > 7) {
                    return "Gol de " + adversario.getNome() + "!";
                } else if (resultado > 3) {
                    return "A bola bate na trave!";
                }
                else return adversario.getNome() + " chutou pra fora!";
            }
        }
        return "";
    }


    public int dado() {
        return ((int) (Math.random() * (10)))+1;
    }

    @Override
    public String toString(){ return "{" + this.nome + "|\n" + "Condicionamento: " + this.condicionamento + "|\n" +
            "Finalização: " + this.finalizacao + "|\n"
            + "Defesa: " + this.defesa + "|\n"
            +"Nível: " + this.nivel + "}";}

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(this.getClass() != obj.getClass()) return false;
        Jogador j = (Jogador) obj;
        if(!this.nome.equals(((Jogador)obj).nome) || this.condicionamento != j.condicionamento || this.finalizacao != j.finalizacao ||
        this.defesa != j.defesa || this.nivel != j.nivel || !this.habilidades.equals(((Jogador)obj).habilidades)) return false;
        return true;
    }

    @Override
    public int hashCode(){
        int r = 1;
        r = r * 2 + this.nome.hashCode();
        r = r  * 2 + ((Double) this.condicionamento).hashCode();
        r = r  * 2 + ((Integer)this.finalizacao).hashCode();
        r = r  * 2 + ((Integer)this.defesa).hashCode();
        r = r  * 2 + ((Integer)this.nivel).hashCode();
        r = r * 2 + this.habilidades.hashCode();

        if(r<0) return r = -r;
        return r;
    }


    public Jogador(Jogador j) {
        this.nome = j.nome;
        this.condicionamento = j.condicionamento;
        this.finalizacao = j.finalizacao;
        this.defesa = j.defesa;
        this.nivel = j.nivel;
        this.habilidades = (Inventario) j.habilidades.clone();
    }

    @Override
    public Object clone() {
        try {
            Jogador copia = (Jogador) super.clone();
            copia.habilidades = (Inventario) this.habilidades.clone();
            return copia;
        } catch (Exception e) {
            return null;
        }
    }

}
