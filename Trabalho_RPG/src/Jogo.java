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

    public void criarPersonagens(){
        for(;;) {
            String nome;
            do {
                System.out.println("\n\nDigite o nome do seu jogador:\n");
                nome = Teclado.getUmString().trim();
                if (nome.isEmpty()) {
                    System.out.println("O nome não pode ser vazio!");
                }
            } while (nome.isEmpty());

            String pos;
            do {
                System.out.println("Escolha a sua posição. Digite (Atacante, MeioCampo, Defensor):\n");
                pos = Teclado.getUmString().toLowerCase().trim();

                if (!pos.equals("meiocampo") && !pos.equals("defensor") && !pos.equals("atacante")) {
                    System.out.println("Posição inválida! Tente novamente.");
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

            System.out.println("\nJogador criado com sucesso:\n" + player);
            System.out.println("\nSeus companheiros de time são:\n" + c1 + "\n" + c2);
            return;
        }
    }

    public String getRandomTeam(){
        return this.equipesAdversarias.get(random.nextInt(this.equipesAdversarias.size()));
    }

    public void removerEquipe(String equipe){
        this.equipesAdversarias.remove(equipe);
        System.out.println(equipe + " foi eliminada da copa!\n");
    }

    public boolean boasVindas(){
        System.out.println("\n"+c1.getNome()+" - Bem vindo a nossa equipe, " + player.getNome() + "!\n" +
                "Conto com você para alcançarmos nosso objetivo, sermos campeões da Copa Maligna!\n");
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
        System.out.println(c2.getNome() + " - Espero que se dedique 100%. Disputaremos a taça com outras 7 equipes, o campeonato " +
                "começa já em quartas de final.");
        this.proximoAdversario = getRandomTeam();
        System.out.println("\n" + c1.getNome() + " - nosso primeiro jogo é contra: " + this.proximoAdversario + "\nEstá preparado?\nAperte enter para começar!");
        Teclado.getUmString();
        Partida p1 = new Partida(
                this.player,
                this.c1,
                this.c2,
                this.proximoAdversario
        );
        boolean r = p1.iniciar(this.proximoAdversario);
        if(r) removerEquipe(this.proximoAdversario);
        return r;
    }

    private void jogarFaseDoCampeonato(String nomeDaFase) {
        System.out.println("\n--- " + nomeDaFase + " ---");
        String adversarioDaVez = getRandomTeam();

        System.out.println(this.c2.getNome() + " - nosso próximo adversário é o " + adversarioDaVez + ". Vamos com tudo!");
        try { Thread.sleep(2000); } catch (InterruptedException e) {}

        Partida partida = new Partida(this.player, this.c1, this.c2, adversarioDaVez);
        boolean vitoria = partida.iniciar(adversarioDaVez);

        if (!vitoria) {
            eliminacao();
        }

        removerEquipe(adversarioDaVez);
    }

    public void campeonato() {
        // Boas-vindas iniciais
        System.out.println("\n" + c1.getNome() + " - Bem vindo a nossa equipe, " + player.getNome() + "!");
        System.out.println("Conto com você para alcançarmos nosso objetivo, sermos campeões da Copa Maligna!\n");
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
        System.out.println(c2.getNome() + " - Espero que se dedique 100%. Disputaremos a taça com outras 7 equipes, o campeonato começa já em quartas de final.");

        jogarFaseDoCampeonato("Quartas de Final");
        System.out.println("\n" + this.c1.getNome() + " - Boa! Vencemos a primeira, seguimos focados rumo ao título!");
        try { Thread.sleep(2000); } catch (InterruptedException e) {}

        jogarFaseDoCampeonato("Semifinal");
        System.out.println("\n" + this.c1.getNome() + " - VAMOOOOS! Estamos na final, um passo mais perto da glória!");
        try { Thread.sleep(2000); } catch (InterruptedException e) {}

        jogarFaseDoCampeonato("Final");

        System.out.println("\n" + this.c1.getNome() + " - CAMPEÕES! CAMPEÕES! É NOSSO! BORA COMEMORAR, BORA PRO BAR!");
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
        System.out.println(this.c2.getNome() + " - Manda descer gelo!!, " + this.player.getNome() + "!");
        System.out.println("\n*** FIM DE JOGO - VOCÊ VENCEU A COPA MALIGNA! ***");
    }
    public void eliminacao(){
        System.out.println("Sua equipe foi eliminada da copa. Boa sorte na proxima!");
        System.out.println(this.c2.getNome() + " pipoqueiro...");
        System.exit(0);
    }

    public static void main(String[] args) {
        Jogo jogo = new Jogo();
        jogo.criarPersonagens();
        jogo.campeonato();
    }
}
