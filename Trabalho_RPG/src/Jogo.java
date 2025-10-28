import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Jogo{
    private Jogador player = null;
    private Jogador c1 = null;
    private Jogador c2 = null;
    private Adversario adversarioAtual;
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
        System.out.println(equipe + "foi eliminada da copa!\n");
    }

    public void boasVindas(){
        System.out.println("\n"+c1.getNome()+" - Bem vindo a nossa equipe, " + player.getNome() + "!\n" +
                "Conto com você para alcançarmos nosso objetivo, sermos campeões da Copa Maligna!\n" +
                c2.getNome() + " - Espero que se dedique 100%. Enfrentaremos outras 8 equipes, o campeonato " +
                "começa já em quartas de final.");
        String primeiroAdversario = getRandomTeam();
        System.out.println("\n" + c1.getNome() + " - nosso primeiro jogo é contra: " + primeiroAdversario + "\nEstá preparado?");
        Partida p1 = new Partida(
                this.player,
                this.c1,
                this.c2,
                primeiroAdversario
        );
        boolean resultado = p1.iniciar(primeiroAdversario);
    }



    public static void main(String[] args) {
        Jogo jogo = new Jogo();
        jogo.criarPersonagens();
        jogo.boasVindas();
    }
}
