import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import Jogador.*;

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
                    tocar();
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
// ALGO COM POTENCIAL DE COZINHAMENTO JEH
/*
* // Método para o jogador avançar com a bola e potencialmente enfrentar um adversário.
    private void avancarComABola() {
        System.out.println(player.getNome() + " avança pelo campo...");

        if (Math.random() > 0.3) { // 70% de chance de encontrar um adversário
            this.adversarioAtual = new Adversario("Zagueiro Genérico");
            System.out.println("Um adversário (" + adversarioAtual.getNome() + ") aparece na sua frente!");
            System.out.println(this.adversarioAtual.toString());

            // Enfrentar o adversário e obter o resultado (pode ser um gol)
            String resultado = player.enfrentar(this.adversarioAtual);

            if (!resultado.isEmpty()) {
                System.out.println("\n*** " + resultado + " ***\n");
                if (resultado.startsWith("Gol")) {
                    // Se a implementação de 'enfrentar' for modificada para retornar o placar,
                    // esta lógica seria ajustada. Por agora, apenas imprime o resultado.
                    // Para o placar funcionar, a lógica de golsA e golsC precisará ser ajustada no loop principal.
                }
            } else {
                System.out.println("Você driblou o adversário, mas perdeu um pouco de velocidade.");
            }

        } else {
            System.out.println("Você correu por um espaço vazio e ganhou terreno!");
        }
    }

    // Método para usar uma das habilidades do jogador.
    private void usarHabilidade() {
        System.out.println("\n--- SUAS HABILIDADES ---");
        System.out.println(player.getHabilidades());

        // A lógica do jogo não define o efeito real, então apenas simula o uso.
        System.out.print("Digite o NOME da habilidade que deseja USAR (ou ENTER para cancelar): ");
        String nomeHabilidade = Teclado.getUmString().trim();

        if (nomeHabilidade.isEmpty()) {
            System.out.println("Nenhuma habilidade utilizada.");
            return;
        }

        // Simulação de uso (o Inventario não tem um método getHabilidade(String nome))
        System.out.println(player.getNome() + " usa a habilidade **" + nomeHabilidade + "**!");
        System.out.println("Você sente um aumento de poder! (Efeito de jogo não implementado)");
        // Lógica para remover 1 da quantidade de habilidade deveria ser aqui.
    }

    // Método para recuar e analisar o jogo (simula defesa ou 'cura')
    private void tentarRecuar() {
        System.out.println(player.getNome() + " recua a bola para tentar analisar o jogo...");
        if (player.dado() > 5) { // 50% de chance de sucesso
            System.out.println("Recuo bem-sucedido! Você conseguiu ganhar mais fôlego e reanalisar a jogada.");
            // Poderia aumentar condicionamento ou reduzir o risco de interceptação.
        } else {
            System.out.println("O adversário pressiona! Você tem que se livrar da bola rapidamente!");
        }
    }

    // Método para passar a bola para um dos companheiros de time.
    private void tocar() {
        System.out.println("\nPara quem você deseja tocar a bola?");
        System.out.println("1. " + c1.getNome() + " (" + c1.getClass().getSimpleName() + ")");
        System.out.println("2. " + c2.getNome() + " (" + c2.getClass().getSimpleName() + ")");
        System.out.print("Escolha (1 ou 2): ");
        String escolha = Teclado.getUmString();

        Jogador companheiro = null;
        if (escolha.equals("1")) {
            companheiro = c1;
        } else if (escolha.equals("2")) {
            companheiro = c2;
        } else {
            System.out.println("Escolha inválida! A posse de bola foi perdida.");
            return;
        }

        String resultadoPasse = player.tocar(companheiro);
        System.out.println(resultadoPasse);

        // Se o passe foi interceptado, a posse de bola muda.
        if (resultadoPasse.contains("interceptado")) {
            System.out.println("Adversário está com a bola!");
            // Simular ataque do adversário
            if (this.adversarioAtual == null) {
                this.adversarioAtual = new Adversario("Meia Ofensivo");
            }
            String resultadoAdversario = companheiro.serEnfrentado(this.adversarioAtual);
            if (!resultadoAdversario.isEmpty()) {
                System.out.println("\n*** " + resultadoAdversario + " ***\n");
                // A lógica de placar precisaria ser inserida aqui.
            }
        }
    }
* */