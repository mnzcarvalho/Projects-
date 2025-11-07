package desafioCadastro.ui;

import java.util.Scanner;

public class MenuInicial {

    public static void menuInicial() {
        Scanner sc = new Scanner(System.in);
        String opcao = "";

        while (true) {
            System.out.println("\n== MENU INICIAL ===");
            System.out.println("1. Cadastrar novo pet");
            System.out.println("2. Alterar os dados do pet cadastrado");
            System.out.println("3. Deletar um pet cadastrado");
            System.out.println("4. Listar todos os pets cadastrados");
            System.out.println("5. Listar pets por algum critério");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = sc.nextLine().trim();

            try {
                int opcaoNum = Integer.parseInt(opcao);

                switch (opcaoNum) {
                    case 1:  break;
                    case 2:  break;
                    case 3:  break;
                    case 4:  break;
                    case 5:  break;
                    case 6:
                        System.out.println("Saindo...");
                        sc.close();
                        return;
                    default:
                        System.out.println("Opção deve ser entre 1 e 6");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido!");
            }
        }
    }
}
