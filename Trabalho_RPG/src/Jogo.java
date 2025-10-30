import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Jogo{
    private Jogador player = null;
    private Jogador c1 = null;
    private Jogador c2 = null;
    private String proximoAdversario = null;
    private List<String> equipesAdversarias = new ArrayList<String>(List.of(
            "CFC", "Ponte Branca", "GPS FC", "BT FC",
            "Linux FC", "POO FC", "Primavera Black",
            "Rio Preto"));
    private Random random = new Random();

    public Jogo(){}

    public Jogo(Jogador player, Jogador c1, Jogador c2) {
        this.player = player;
        this.c1 = c1;
        this.c2 = c2;
    }

    public Jogador getPlayer(){
        return this.player;
    }
    public Jogador getC1() {
        return c1;
    }
    public Jogador getC2() {
        return c2;
    }
    public String getProximoAdversario() {
        return proximoAdversario;
    }
    public List<String> getEquipesAdversarias() {
        return new ArrayList<>(equipesAdversarias); // cópia defensiva
    }


    public void criarPersonagens(){
        for(;;) {
            String nome;
            do {
                System.out.println("\n\nDigite o nome do seu jogador:\n");
                nome = Teclado.getUmString().trim();
                if (nome.isEmpty()) {
                    imprimirComAnimacao("O nome não pode ser vazio!");
                }
            } while (nome.isEmpty());

            String pos;
            do {
                System.out.println("Escolha a sua posição. Digite (Atacante, MeioCampo, Defensor):\n");
                pos = Teclado.getUmString().toLowerCase().trim();

                if (!pos.equals("meiocampo") && !pos.equals("defensor") && !pos.equals("atacante")) {
                    imprimirComAnimacao("Posição inválida! Tente novamente.");
                    pos = ""; // força repetir o loop
                }
            } while (pos.isEmpty());

            if (pos.equals("meiocampo")) {
                this.player = new MeioCampo(nome);
                this.c1 = new Defensor("David Luiz");
                this.c2 = new Atacante("Jeh");
            }
            if (pos.equals("defensor")) {
                this.player = new Defensor(nome);
                this.c1 = new Atacante("Jeh");
                this.c2 = new MeioCampo("Neymar");
            }
            if (pos.equals("atacante")) {
                this.player = new Atacante(nome);
                this.c1 = new Defensor("David Luiz");
                this.c2 = new MeioCampo("Neymar");
            }

            imprimirComAnimacao("\nJogador criado com sucesso:\n" + player);
            imprimirComAnimacao("\nSeus companheiros de time são:\n" + c1 + "\n" + c2);
            return;
        }
    }

    public String getRandomTeam(){
        return this.equipesAdversarias.get(random.nextInt(this.equipesAdversarias.size()));
    }

    public void removerEquipe(String equipe){
        this.equipesAdversarias.remove(equipe);
        imprimirComAnimacao(equipe + " foi eliminada da copa!\n");
    }


    private void jogarFaseDoCampeonato(String nomeDaFase) {
        imprimirComAnimacao("\n--- " + nomeDaFase.toUpperCase() + " ---");
        String adversarioDaVez = getRandomTeam();

        imprimirComAnimacao(this.c2.getNome() + " - nosso próximo adversário é o " + adversarioDaVez + ". Vamos com tudo!");
        try { Thread.sleep(2000); } catch (InterruptedException e) {}

        Partida partida = new Partida(this.player, this.c1, this.c2, adversarioDaVez);
        boolean vitoria = partida.iniciar();

        if (!vitoria) {
            eliminacao();
        }

        removerEquipe(adversarioDaVez);
    }

    public void campeonato() {
        // Boas-vindas iniciais
        imprimirComAnimacao("\n" + c1.getNome() + " - Bem vindo a nossa equipe, " + player.getNome() + "!");
        imprimirComAnimacao("Conto com você para alcançarmos nosso objetivo, sermos campeões da Copa Maligna!\n");
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
        imprimirComAnimacao(c2.getNome() + " - Espero que se dedique 100%. Disputaremos a taça com outras 7 equipes, o campeonato começa já em quartas de final.");

        jogarFaseDoCampeonato("Quartas de Final");
        imprimirComAnimacao("\n" + this.c1.getNome() + " - Boa! Vencemos a primeira, seguimos focados rumo ao título!");
        try { Thread.sleep(2000); } catch (InterruptedException e) {}

        jogarFaseDoCampeonato("Semifinal");
        imprimirComAnimacao("\n" + this.c1.getNome() + " - VAMOOOOS! Estamos na final, um passo mais perto da glória!");
        try { Thread.sleep(2000); } catch (InterruptedException e) {}

        jogarFaseDoCampeonato("Final");

        imprimirComAnimacao("\n" + this.c1.getNome() + " - CAMPEÕES! CAMPEÕES! É NOSSO! BORA COMEMORAR, BORA PRO BAR!");
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
        imprimirComAnimacao(this.c2.getNome() + " - Manda descer gelo!!, " + this.player.getNome() + "!");
        imprimirComAnimacao("\n*** FIM DE JOGO - VOCÊ VENCEU A COPA MALIGNA! ***");
        printTaca();
    }
    public void eliminacao(){
        imprimirComAnimacao("Sua equipe foi eliminada da copa. Boa sorte na proxima!");
        imprimirComAnimacao(this.c2.getNome() + " pipoqueiro...");
        System.exit(0);
    }

    public void printTaca(){
        System.out.println(
                """
                        ────────────█▄─█─▄▀▀▄─────▄█────────────
                        ────────────█─▀█─█──█──────█────────────
                        ────────────▀──▀──▀▀──▀────▀────────────
                        ████████████████████████████████████████
                        ████████████████████████████████████████
                        ████████████████████████████████████████
                        ███████████████████▒────██▓█████████████
                        ██████████████═──────────█▓▓████████████
                        ███████████▓───────────████══██████─████
                        ██████████────────────══─██──████─────██
                        █████████─────▒────██────█─█─███──██──▓█
                        █████▓▓██─────█──▓███────────▓██─████──█
                        ████───██───█▒█──█──█▓───────▓█─▒████──█
                        ███──▓███──███▓─═█──█▓───────██▓█████──█
                        ███──████─═███▒─█▒──██───────████████──█
                        ██──▓████──███──███─▓█───────████████──█
                        ██──█████─██═█─█▒═█─██───────████████─██
                        ██──█████─═█─█────█─█▓───────███████──██
                        ██──█████─────────█─█▒───────███████─███
                        ██──█████─────────█─█────────██████──███
                        ██──▓████─────────█─█───────═█████──████
                        ██───████▒────────█─█───────▓████──█████
                        ██───████▓───────▒█─█───────████──██████
                        ██▒───████───────▒█─█───────███──███████
                        ███────███───────▓█─█───────██──████████
                        ███═───███───────██─█──────▒█▓─█████████
                        ████────██▒──────█▒─███────██─██████████
                        █████────██───▒▓▓█───██────█▒─██████████
                        ██████────█───███▓───██────█─▓██████████
                        ███████───██──▓█─────█▓───██─███████████
                        ████████───█──═█─█████═───█──▓██████████
                        ████████───██──██████▒────█──────███████
                        █████████──██──▒─────────██──────███████
                        █████████──▓█▓───────────███────████████
                        █████████═─▓██──────────█████─▒█████████
                        █████████▓─████────────▓████████████████
                        █████████──████▓──────═█████████████████
                        ████████──██████▓────▓██████████████████
                        ███████▒─███████████████████████████████
                        █████████████████▒▒▓──██████████████████
                        █████████████████▒───▓██████████████████
                        ██████████████████───███████████████████
                        ██████████████████──▒███████████████████
                        ████████████████████████████████████████
                        ██████████████████▓▒────████████████████
                        ████████████████─────────▒██████████████
                        ███████████████══█▓█████████████████████
                        ██████████████████████████████▓█████████
                        █████████████──────────────▓█▒▒█████████
                        ██████████▒██─────═─══════─██▒▓█████████
                        ██████████▓▓█──══════════──██▒██████████
                        ███████████▒█▒─══─═────────█▓▒██████████
                        ███████████▒██──────────▒─▒█▓▓██████████
                        ███████████▒▓█─▒████████████▒▓██████████
                        ███████████▓▒███████▓▓▓▓▓▒▒▒▒███████████
                        ████████████▒█▓▓▒▒▒▒▓▓▓▓▓███████████████
                        ████████████▓▓▓▓████████████████████████
                        ████████████████████████████████████████
                        """);
    }

    @Override
    public String toString() {
        return "Jogo{" +
                "player=" + player +
                ", c1=" + c1 +
                ", c2=" + c2 +
                ", equipesAdversarias=" + equipesAdversarias +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if(getClass() != obj.getClass()) return false;

        Jogo jogo = (Jogo) obj;
        return player.equals(jogo.player)
                && c1.equals(jogo.c1)
                && c2.equals(jogo.c2)
                && equipesAdversarias.equals(jogo.equipesAdversarias);
    }

    @Override
    public int hashCode() {
        int retorno = 1;
        if(this.player != null) retorno = retorno * 3 + this.player.hashCode();
        if(this.c1 != null) retorno = retorno * 3 + this.c1.hashCode();
        if(this.c2 != null) retorno = retorno * 3 + c2.hashCode();
        retorno = retorno * 3 + equipesAdversarias.hashCode();

        if(retorno < 0) retorno = -retorno;
        return retorno;
    }

    public static void main(String[] args) {
        Jogo jogo = new Jogo();
        jogo.criarPersonagens();
        jogo.campeonato();
    }

    private void imprimirComAnimacao(String texto) {
        for (char c : texto.toCharArray()) {
            System.out.print(c);
            System.out.flush();

            try {
                if (".!?".indexOf(c) >= 0) {
                    Thread.sleep(200); // pausa leve no fim da frase
                } else {
                    Thread.sleep(2); // super rápido entre letras
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }

}