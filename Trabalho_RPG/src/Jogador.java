//Classe Personagem

public abstract class Jogador {
    protected String nome;
    protected Double condicionamento;
    protected int finalizacao;
    protected int defesa;
    protected int nivel;
    protected Inventario habilidades;
    protected int chanceDeChutar;

    public Jogador(String nome, Double condicionamento, int finalizacao, int defesa, int nivel){
        this.nome = nome;
        this.condicionamento = condicionamento;
        this.finalizacao = finalizacao;
        this.defesa = defesa;
        this.nivel = 1; //nivel sempre comecar em 1
        this.habilidades = new Inventario();

        this.chanceDeChutar = 0;
        this.habilidades.addHabilidade(
                new Habilidade("drible",
                        "Passa pelo adversário",
                        "Ignora a defesa do adversário",
                        1
                )
        );
    }

    public String getNome() {
        return this.nome;
    }
    public double getCondicionamento() {
        return this.condicionamento;
    }
    public int getFinalizacao() {
        return this.finalizacao;
    }
    public int getDefesa() {
        return this.defesa;
    }
    public int getNivel() {
        return this.nivel;
    }
    public Inventario getHabilidades() {
        return this.habilidades;
    }


    public void aumentarChanceDeChutar(int aumento){
        this.chanceDeChutar += aumento;
    }

    public void zerarChanceDeChutar(){
        this.chanceDeChutar = 0;
    }

    public String enfrentar(Jogador defensor) {
        System.out.println(this.nome + " está com a bola enfrentando " + defensor.getNome() + "!");
        boolean tentaFinalizar = false;

        if(this instanceof Adversario){
            tentaFinalizar = this.dado() < this.finalizacao;
        }else{
            tentaFinalizar = (this.finalizacao + this.chanceDeChutar) > (10 + defensor.dado());
        }

        if (tentaFinalizar) {
            int ataque = this.finalizacao + chanceDeChutar + dado();
            int defesa = defensor.getDefesa() + defensor.dado();
            System.out.println(this.nome + " tenta finalizar ao gol!");
            System.out.println("Força do chute: " + ataque + " | Defesa do adversário: " + defesa);

            this.zerarChanceDeChutar();

            if (ataque > defesa) {
                int margemDeVitoria = ataque - defesa;

                if(this instanceof Atacante && margemDeVitoria > 2){
                    this.nivel +=1;
                    return "Gol de " + this.nome + "! Um chute preciso no canto!";
                }

                if (this instanceof MeioCampo && margemDeVitoria > 4) {
                    this.nivel += 1;
                    return "Gol de " + this.nome + "! Um belo chute de média distância!";
                }

                // DEFENSOR: Precisa de uma grande vantagem (sorte), pois não é sua função.
                if (this instanceof Defensor && margemDeVitoria > 6) {
                    this.nivel += 1;
                    return "GOL INACREDITÁVEL DE " + this.nome + "! Pegou todo mundo de surpresa!";
                }

                // Se venceu a disputa, mas não o suficiente para a sua posição marcar, a bola vai na trave.
                return "Na trave! O goleiro nem viu a cor da bola!";
            }else{
                return "Chutou para fora! A defesa pressionou bem,";
            }
        }else{
            System.out.println(this.nome + " tenta passar pelo defensor...");
            int poderOfensivo = this.nivel + this.finalizacao + this.dado();
            int poderDefensivo = defensor.getNivel() + defensor.getDefesa() + defensor.dado();

            if (poderOfensivo > poderDefensivo){
                return "Drible bem-sucedido";
            }else{
                return "";
            }
        }
    }

    //BASE METODO TOCAR!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    public String tocar(Jogador companheiro, Jogador adversario) {
        System.out.println(this.nome + " olha para " + companheiro.getNome() + " e tenta fazer o passe...");

        // Chance base de sucesso aumenta com condicionamento e nível
        double chance =  this.condicionamento + this.nivel + dado();

        // Chance de erro aleatória do adversário (simula interceptação)
        int defesaAdversaria = (adversario.getDefesa()) + adversario.dado() + adversario.getNivel();

        System.out.println("Precisão do passe: " + chance + " | Pressão adversária: " + defesaAdversaria);

        if (chance > defesaAdversaria) {
            if (dado() > 7) {
                // Passe excelente gera bônus de moral ou experiência
                this.nivel++;
                return "Passe perfeito para " + companheiro.getNome();
            } else {
                return "Passe completo! " + companheiro.getNome() + " domina a bola.";
            }
        } else if (chance + dado() > defesaAdversaria) {
            return "O passe foi meio arriscado, mas " + companheiro.getNome() + " conseguiu recuperar!";
        } else {
            return "Passe interceptado! O adversário rouba a bola!";
        }
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
