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
                player = new Defensor(nome);
                c1 = new Atacante("Jeh");
                c2 = new MeioCampo("Neymar");
            }
            if (pos.equals("atacante")) {
                player = new Atacante(nome);
                c1 = new Defensor("David Luiz");
                c2 = new MeioCampo("Neymar");
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
        iniciarPartida(primeiroAdversario);
    }

    public boolean iniciarPartida(String nomeTimeAdversario) {
        System.out.println("\n--- A PARTIDA CONTRA " + nomeTimeAdversario +" VAI COMEÇAR! ---");
        System.out.println("Você está no centro do campo. O juiz apita!");

        // Loop principal do jogo
        boolean partidaEmAndamento = true;
        int golsA = 0;
        int golsC = 0;
        int turno = 1;

        while (partidaEmAndamento) {
            System.out.println("\n--- TURNO " + turno + " ---");
            System.out.println("Você tem a posse de bola. O que você faz?");

            // O jogador pode tomar decisões na história
            System.out.println("1. Explorar o campo (Avançar com a bola)"); //
            System.out.println("2. Usar uma Habilidade (Ver inventário)"); //
            System.out.println("3. Tentar recuar e analisar o jogo (Fugir)"); //
            System.out.println("4. Passar a bola");

            System.out.print("Escolha sua ação: ");
            String escolha = Teclado.getUmString();

            switch (escolha) {
                case "1":
                   //avancarComABola();
                    break;
                case "2":
                    //usarHabilidade();
                    break;
                case "3":
                    //tentarRecuar();
                    break;
                case "4":
                    //passar();
                    break;
                default:
                    System.out.println("Opção inválida! Você se atrapalhou com a bola e não fez nada.");
                    break;
            }

            // Lógica simples para terminar o jogo
            if (turno >= 8) {
                System.out.println("\n--- FIM DE JOGO ---");
                System.out.println("O juiz apita o final da partida!\nResultado: " + golsA + "x" + golsC);
                partidaEmAndamento = false;
            }

            turno++;
        }
        if(golsA<golsC) return false;
        else if(golsA == golsC) {
            //penaltis()
        }
        return true;
    }

    private void avancarComABola() {
        System.out.println(player.getNome() + " avança pelo campo...");

        if (Math.random() > 0.3) { // 70% de chance de encontrar um adversário
            this.adversarioAtual = new Adversario("Zagueiro Genérico");
            System.out.println("Um adversário aparece na sua frente!");
            System.out.println(this.adversarioAtual);

            player.enfrentar(this.adversarioAtual);

        } else {
            System.out.println("Você correu por um espaço vazio e ganhou terreno!");
        }
    }

    public static void main(String[] args) {
        Jogo jogo = new Jogo();
        jogo.criarPersonagens();
        jogo.boasVindas();
    }
}