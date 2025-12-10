package desafioCadastro;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

    }

    private static void exibirMenuPrincipal() {
        Scanner scanner = new Scanner(System.in);
        boolean executando = true;


        while (executando) {
            System.out.println("===MENU PRINCIPLA===");
            System.out.println("1. Cadastrar um novo pet");
            System.out.println("2. Alterar os dados do pet cadastrado");
            System.out.println("3. Deletar um pet cadastrado");
            System.out.println("4. Listar todos os pets cadastrados");
            System.out.println("5. Listar pets por algum critério (idade, nome, raça)");
            System.out.println("6. Sair");

            int opcao;

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite apenas números");
                continue;
            }

            switch (opcao) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    System.out.println("Saindo do sistema...");
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida, por favor digite um número entre 1 e 6.");
                    break;
            }
        }
        scanner.close();

    }

    private static void cadastrarNovoPet() {

    }

}
