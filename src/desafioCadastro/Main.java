package desafioCadastro;

import desafioCadastro.services.FormularioService;
import desafioCadastro.services.PetService;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static PetService petService = new PetService();

    public static void main(String[] args) {
        System.out.println("🐾 SISTEMA DE ADOÇÃO PARA PETS 🐾");
        exibirMenuPrincipal();
    }

    private static void exibirMenuPrincipal() {
        int opcao;

        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Cadastrar um novo pet");
            System.out.println("2. Alterar os dados do pet cadastrado");
            System.out.println("3. Deletar um pet cadastrado");
            System.out.println("4. Listar todos os pets cadastrados");
            System.out.println("5. Listar pets por algum critério (idade, nome, raça)");
            System.out.println("6. Sistema de Formulário");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        petService.cadastrarPet();
                        break;
                    case 2:
                        System.out.println("Funcionalidade em desenvolvimento...");
                        break;
                    case 3:
                        System.out.println("Funcionalidade em desenvolvimento...");
                        break;
                    case 4:
                        petService.listarTodosPets();
                        break;
                    case 5:
                        System.out.println("Funcionalidade em desenvolvimento...");
                        break;
                    case 6:
                        exibirMenuFormulario();
                        break;
                    case 7:
                        System.out.println("Saindo do sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida! Digite um número entre 1 e 7.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite apenas números!");
                opcao = 0;
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
                opcao = 0;
            }

        } while (opcao != 7);

        petService.close();
        scanner.close();
    }

    private static void exibirMenuFormulario() {
        int opcao;

        do {
            System.out.println("\n=== SISTEMA DE FORMULÁRIO ===");
            System.out.println("1. Criar nova pergunta");
            System.out.println("2. Alterar pergunta existente");
            System.out.println("3. Excluir pergunta existente");
            System.out.println("4. Voltar para o menu inicial");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        System.out.print("Digite a nova pergunta: ");
                        String novaPergunta = scanner.nextLine();
                        FormularioService.adicionarPergunta(novaPergunta);
                        System.out.println("Pergunta adicionada com sucesso!");
                        break;
                    case 2:
                        System.out.print("Digite o número da pergunta a alterar: ");
                        int numAlterar = Integer.parseInt(scanner.nextLine());
                        System.out.print("Digite a nova pergunta: ");
                        String perguntaAlterada = scanner.nextLine();
                        FormularioService.alterarPergunta(numAlterar, perguntaAlterada);
                        break;
                    case 3:
                        System.out.print("Digite o número da pergunta a excluir: ");
                        int numExcluir = Integer.parseInt(scanner.nextLine());
                        FormularioService.excluirPergunta(numExcluir);
                        break;
                    case 4:
                        System.out.println("Voltando ao menu principal...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite apenas números!");
                opcao = 0;
            } catch (IOException e) {
                System.out.println("Erro ao manipular arquivo: " + e.getMessage());
                opcao = 0;
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
                opcao = 0;
            }

        } while (opcao != 4);
    }
}

