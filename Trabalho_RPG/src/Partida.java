import java.util.Random;

public class Partida extends Jogo{
    private int golsA;
    private int golsC;
    private Jogador player;
    private Jogador c1;
    private Jogador c2;
    private Jogador jogadorComBola;
    private String nomeTimeAdversario;
    private Adversario adversarioAtual;
    private Random random;

    public Partida(Jogador player, Jogador c1, Jogador c2, String nomeTimeAdversario) {
        golsA = 0;
        golsC = 0;
        this.player = player;
        this.c1 = c1;
        this.c2 = c2;
        this.jogadorComBola = player;
        this.nomeTimeAdversario = nomeTimeAdversario;
        random = new Random();
    }

    public boolean iniciar(String nomeTimeAdversario) {
        System.out.println("\n--- A PARTIDA CONTRA " + nomeTimeAdversario +" VAI COMEÇAR! ---");
        System.out.println("Você está no centro do campo. O juiz apita!");

        // Loop principal do jogo
        boolean partidaEmAndamento = true;
        int turno = 1;

        while (partidaEmAndamento) {
            System.out.println("\n--- TURNO " + turno + " ---");
            System.out.println(this.jogadorComBola.getNome() + " está com a bola.");

            if(this.jogadorComBola == this.player) {
                System.out.println("1. Explorar o campo (Avançar com a bola)"); //
                System.out.println("2. Usar uma Habilidade (Ver inventário)"); //
                System.out.println("3. Tentar recuar e analisar o jogo (Fugir)"); //
                System.out.println("4. Passar a bola");

                System.out.print("Escolha sua ação: ");
                String escolha = Teclado.getUmString();

                switch (escolha) {
                    case "1":
                        avancarComABola();
                        break;
                    case "2":
                        usarHabilidade();
                        break;
                    case "3":
                        //tentarRecuar();
                        break;
                    case "4":
                        decidirPasse();
                        break;
                    default:
                        System.out.println("Opção inválida! Você se atrapalhou com a bola e não fez nada.");
                        break;
                }
            }else if(this.jogadorComBola == this.c1 || this.jogadorComBola == this.c2) {
                System.out.println("O seu companheiro " + this.jogadorComBola.getNome() + " está com a bola.");
                turnoControladoPorIA();
            }else{
                System.out.println("A bola está com o adversário.");
                turnoAdversario();
            }

            // Lógica simples para terminar o jogo
            if (turno >= 15) {
                System.out.println("\n--- FIM DE JOGO ---");
                System.out.println("O juiz apita o final da partida!\nResultado: " + golsA + "x" + golsC);
                partidaEmAndamento = false;
            }

            turno++;
        }
        if(this.golsA<this.golsC) {
            System.out.println("Você perdeu a partida.");
            return false;
        }
        else if(this.golsC == this.golsC) {
            //penaltis()
        }
        System.out.println("Voce venceu a partida!");
        return true;
    }

    private boolean chancePerderBola(){
        int chance = 0;
        if(this.player instanceof Defensor) chance = 30;
        else if (this.player instanceof MeioCampo) chance = 10 ;
        else if (this.player instanceof Atacante) chance = 15;

        int ajusteCondicionamento = 0;
        if(this.player.getCondicionamento() < 4.0) ajusteCondicionamento = 20;
        else if(this.player.getCondicionamento() < 6.5) ajusteCondicionamento = 10;

        chance = chance + ajusteCondicionamento;
        int rolagem = random.nextInt(100);
        if(rolagem < chance) return true;
        return false;
    }

    private void avancarComABola() {
        System.out.println(this.jogadorComBola.getNome() + " avança pelo campo...");

        if (Math.random() > 0.3) { // 70% de chance de encontrar um adversário
            this.adversarioAtual = new Adversario("Fernandinho");
            System.out.println("Um adversário (" + adversarioAtual.getNome() + ") aparece!");
            System.out.println(this.adversarioAtual.toString());

            // Enfrentar o adversário e obter o resultado (pode ser um gol)
            String resultado = this.jogadorComBola.enfrentar(this.adversarioAtual);

            if (!resultado.isEmpty()) {
                System.out.println("\n*** " + resultado + " ***\n");
                if (resultado.startsWith("Gol")) {
                    System.out.println(this.jogadorComBola.getNome() + " MARCOU!");
                    this.golsA ++;
                    this.jogadorComBola = adversarioAtual;
                }else if(resultado.startsWith("Drible")){
                    this.jogadorComBola.aumentarChanceDeChutar(5);
                }
                else{
                    System.out.println("A defesa adversária recupera a posse de bola.");
                    this.jogadorComBola = adversarioAtual;
                }
            } else {
                System.out.println(this.jogadorComBola.getNome() + " driblou o adversário e se aproximou do gol!");
                this.jogadorComBola.aumentarChanceDeChutar(5);
            }

        }else if(chancePerderBola()){
            System.out.println(this.jogadorComBola.getNome() + " se atrapalhou e perdeu a bola.");
            this.jogadorComBola.zerarChanceDeChutar();
            this.jogadorComBola = this.adversarioAtual;
        }else {
            System.out.println(this.jogadorComBola.getNome() + " correu por um espaço vazio e ganhou terreno!");
            this.jogadorComBola.aumentarChanceDeChutar(3);
        }
    }


    private void decidirPasse() {
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
            this.jogadorComBola = new Adversario("Confuso"); // Perde a posse
            return;
        }

        // Agora, chame o método de lógica com o alvo escolhido
        executarPasse(this.jogadorComBola, companheiro);
    }

    private void executarPasse(Jogador passador, Jogador receptor) {
        String resultadoPasse = passador.tocar(receptor, new Adversario("Halland") );
        System.out.println(resultadoPasse);

        if (resultadoPasse.contains("interceptado")) {
            System.out.println("Adversário está com a bola!");
            this.jogadorComBola = new Adversario("Ladrão de Bolas");
        } else {
            System.out.println(receptor.getNome() + " agora está com a bola.");
            this.jogadorComBola = receptor; // A posse é transferida corretamente
        }
    }

    private void turnoControladoPorIA() {
        // Pausa para o jogador poder ler o que está acontecendo
        try { Thread.sleep(2000); } catch (InterruptedException e) {}

        Jogador alvoDoPasse;
        if (this.jogadorComBola == this.c1) {
            // Se c1 tem a bola, ele pode tocar para o player ou para c2
            alvoDoPasse = (random.nextBoolean()) ? this.player : this.c2;
        } else { // Assumindo que jogadorComBola só pode ser c2 neste caso
            // Se c2 tem a bola, ele pode tocar para o player ou para c1
            alvoDoPasse = (random.nextBoolean()) ? this.player : this.c1;
        }

        if (this.jogadorComBola instanceof Atacante) {
            System.out.println(this.jogadorComBola.getNome() + " está no ataque...");
            if (random.nextInt(100) < 20) { // 20% de chance de um atacante tocar
                executarPasse(this.jogadorComBola, alvoDoPasse);
            } else {
                System.out.println("Ele decide partir pra cima!");
                avancarComABola();
            }
        } else if (this.jogadorComBola instanceof Defensor) {
            System.out.println(this.jogadorComBola.getNome() + " prefere a segurança e toca a bola.");
            executarPasse(this.jogadorComBola, alvoDoPasse);
        } else if (this.jogadorComBola instanceof MeioCampo) {
            System.out.println(this.jogadorComBola.getNome() + " analisa o jogo no meio-campo...");
            if (random.nextInt(100) < 60) { // 60% de chance de tocar e armar o jogo
                executarPasse(this.jogadorComBola, alvoDoPasse);
            } else {
                System.out.println("Ele vê uma brecha e decide avançar!");
                avancarComABola();
            }
        }
    }

    public Jogador escolherDefensor(){
        int escolha = random.nextInt(3);
        return switch (escolha) {
            case 0 -> this.player;
            case 1 -> this.c1;
            default -> this.c2;
        };
    }

    public void turnoAdversario() {
        try { Thread.sleep(2000); } catch (InterruptedException e) {}

        System.out.println(this.jogadorComBola.getNome() + " avança com a bola para o ataque!");

        Jogador defensorEscolhido = escolherDefensor();
        System.out.println("Ele vai pra cima de " + defensorEscolhido.nome);

        String resultadoAtaque = this.jogadorComBola.enfrentar(defensorEscolhido);
        if (resultadoAtaque.isEmpty()) {
            // RESULTADO VAZIO = DESARME!
            System.out.println(defensorEscolhido.getNome() + " fez um belo desarme!");
            this.jogadorComBola = defensorEscolhido;
        } else {
            // QUALQUER OUTRO RESULTADO = TENTATIVA DE CHUTE OU DRIBLE BEM-SUCEDIDO
            System.out.println("\n*** " + resultadoAtaque + " ***\n");
            if (resultadoAtaque.startsWith("Gol")) {
                System.out.println("GOL DO ADVERSÁRIO!");
                this.golsC++;
                this.jogadorComBola = this.player;
            } else if (resultadoAtaque.startsWith("Drible")) {
                System.out.println("O adversário continua com a posse, se aproximando da área!");
            } else {
                System.out.println("A posse é retomada!");
                this.jogadorComBola = escolherDefensor();
            }
        }
    }

    private void usarHabilidade() {
        if (this.jogadorComBola != this.player){
            System.out.println("Apenas o jogador pode usar habilidades do menu");
        }

        System.out.println("\n--- SUAS HABILIDADES ---");
        System.out.println(this.player.getHabilidades());

        System.out.print("Digite o NOME da habilidade que deseja USAR (ou ENTER para cancelar): ");
        String nomeHabilidade = Teclado.getUmString().trim();

        if (nomeHabilidade.isEmpty()) {
            System.out.println("Nenhuma habilidade utilizada.");
            return;
        }

        Habilidade habilidadeEscolhida = this.player.getHabilidades().getHabilidadePorNome(nomeHabilidade);
        if (habilidadeEscolhida == null) {
            System.out.println("Habilidade não encontrada!");
            return;
        }
        if(habilidadeEscolhida.getQtd() <= 0){
            System.out.println("Você não tem mais unidades de " + habilidadeEscolhida.getNome());
        }

        System.out.println(this.player.getNome() + " usa " + habilidadeEscolhida.getNome() + "!");

        switch (habilidadeEscolhida.getNome().toLowerCase()){
            case "superkick":
                System.out.println("Seu próximo chute terá uma força extra!");
                this.player.aumentarChanceDeChutar(10);
                this.player.habilidades.removeHabilidade(habilidadeEscolhida, 1);
                break;
            case "passe teleguiado":
                System.out.println("Seu proximo passe terá precisão máxima!");
                this.player.habilidades.removeHabilidade(habilidadeEscolhida, 1);
                // implementar chance de passe
                break;
            case "bote":
                System.out.println("Seu proximo desarme será preciso!");
                this.player.habilidades.removeHabilidade(habilidadeEscolhida, 1);
                // implementar desarme
                break;
        }
    }

    // ALGO COM POTENCIAL DE COZINHAMENTO JEH
/*


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


* */
}
