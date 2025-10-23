
public class Main {
    public static void main(String[] args) {
        System.out.printf("Bem vindo ao RPG de Futebol!");
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

            Jogador player = null;
            Jogador c1 = null;
            Jogador c2 = null;
            if (pos.equals("meiocampo")) {
                player = new MeioCampo(nome);
                c1 = new Defensor("Davi Luiz");
                c2 = new Atacante("Jeh");
            }
            if (pos.equals("defensor")) {
                player = new Defensor(nome);
                c1 = new Atacante("Jeh");
                c2 = new MeioCampo("Neymar");
            }
            if (pos.equals("atacante")) {
                player = new Atacante(nome);
                c1 = new Defensor("Davi Luiz");
                c2 = new MeioCampo("Neymar");
            }

            System.out.println("\nJogador criado com sucesso:\n" + player);
            System.out.println("\nSeus companheiros de time são:\n" + c1 + "\n" + c2);
            return;
        }
    }
}