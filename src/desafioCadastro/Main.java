package desafioCadastro;

import desafioCadastro.services.PetService;
import desafioCadastro.services.FormularioService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PetService petService = new PetService();

        // Carregar pets existentes ao iniciar
        System.out.println("Carregando pets cadastrados...");

        try {
            // Menu inicial com as duas opções (Passo EXTRA)
            exibirMenuInicial(scanner, petService);
        } finally {
            petService.close();
            scanner.close();
        }
    }

    private static void exibirMenuInicial(Scanner scanner, PetService petService) {
        while (true) {
            System.out.println("\n=== SISTEMA DE ADOÇÃO DE PETS ===");
            System.out.println("1 - Iniciar o sistema para cadastro de PETS");
            System.out.println("2 - Iniciar o sistema para alterar formulário");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    exibirMenuPrincipal(scanner, petService);
                    break;
                case 2:
                    exibirMenuFormulario(scanner);
                    break;
                case 3:
                    System.out.println("Saindo do sistema...");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void exibirMenuPrincipal(Scanner scanner, PetService petService) {
        while (true) {
            System.out.println("\n=== SISTEMA DE ADOÇÃO DE PETS ===");
            System.out.println("1. Cadastrar um novo pet");
            System.out.println("2. Alterar os dados do pet cadastrado");
            System.out.println("3. Deletar um pet cadastrado");
            System.out.println("4. Listar todos os pets cadastrados");
            System.out.println("5. Listar pets por algum critério");
            System.out.println("6. Voltar ao menu anterior");
            System.out.print("Escolha uma opção: ");

            int opcao = Integer.parseInt(scanner.nextLine());

            try {
                switch (opcao) {
                    case 1:
                        petService.cadastrarPet();
                        break;
                    case 2:
                        petService.alterarPet();
                        break;
                    case 3:
                        petService.deletarPet();
                        break;
                    case 4:
                        petService.listarTodosPets();
                        break;
                    case 5:
                        petService.buscarPetsComDestaque();
                        break;
                    case 6:
                        System.out.println("Voltando ao menu anterior...");
                        return;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private static void buscarPets(Scanner scanner, PetService petService) {
        System.out.println("\n=== BUSCAR PET ===");

        // Critério obrigatório: Tipo
        System.out.println("Selecione o tipo para busca:");
        System.out.println("1. Cachorro");
        System.out.println("2. Gato");
        System.out.print("Opção: ");
        int opcaoTipo = Integer.parseInt(scanner.nextLine());

        java.util.Map<String, Object> criterios = new java.util.HashMap<>();
        desafioCadastro.entities.TipoPet tipo = (opcaoTipo == 1) ?
                desafioCadastro.entities.TipoPet.CACHORRO :
                desafioCadastro.entities.TipoPet.GATO;
        criterios.put("tipo", tipo);

        // Critérios adicionais
        System.out.println("\nSelecione critério de busca adicional:");
        System.out.println("1. Nome");
        System.out.println("2. Raça");
        System.out.println("3. Sexo");
        System.out.println("4. Endereço");
        System.out.println("5. Sem critério adicional");
        System.out.print("Opção: ");
        int opcaoBusca = Integer.parseInt(scanner.nextLine());

        switch (opcaoBusca) {
            case 1:
                System.out.print("Digite o nome ou parte do nome: ");
                criterios.put("nome", scanner.nextLine());
                break;
            case 2:
                System.out.print("Digite a raça: ");
                criterios.put("raca", scanner.nextLine());
                break;
            case 3:
                System.out.println("Selecione o sexo:");
                System.out.println("1. Macho");
                System.out.println("2. Femea");
                System.out.print("Opção: ");
                int opcaoSexo = Integer.parseInt(scanner.nextLine());
                desafioCadastro.entities.SexoPet sexo = (opcaoSexo == 1) ?
                        desafioCadastro.entities.SexoPet.MACHO :
                        desafioCadastro.entities.SexoPet.FEMEA;
                criterios.put("sexo", sexo);
                break;
            case 4:
                System.out.print("Digite parte do endereço: ");
                criterios.put("endereco", scanner.nextLine());
                break;
        }

        // Executar busca
        java.util.List<desafioCadastro.entities.Pet> resultados = petService.buscarPets(criterios);

        // Exibir resultados
        if (resultados.isEmpty()) {
            System.out.println("Nenhum pet encontrado com os critérios informados.");
        } else {
            System.out.println("\n=== RESULTADOS DA BUSCA ===");
            for (int i = 0; i < resultados.size(); i++) {
                System.out.println((i + 1) + ". " + resultados.get(i));
            }
        }
    }

    private static void exibirMenuFormulario(Scanner scanner) {
        while (true) {
            System.out.println("\n=== GERENCIAR FORMULÁRIO ===");
            System.out.println("1. Criar nova pergunta");
            System.out.println("2. Alterar pergunta existente");
            System.out.println("3. Excluir pergunta existente");
            System.out.println("4. Voltar para o menu inicial");
            System.out.print("Escolha uma opção: ");

            int opcao = Integer.parseInt(scanner.nextLine());

            try {
                switch (opcao) {
                    case 1:
                        System.out.print("Digite a nova pergunta: ");
                        String novaPergunta = scanner.nextLine();
                        FormularioService.adicionarPergunta(novaPergunta);
                        System.out.println("Pergunta adicionada com sucesso!");
                        break;
                    case 2:
                        System.out.print("Digite o número da pergunta a alterar: ");
                        int numeroAlterar = Integer.parseInt(scanner.nextLine());
                        System.out.print("Digite a nova pergunta: ");
                        String perguntaAlterada = scanner.nextLine();
                        FormularioService.alterarPergunta(numeroAlterar, perguntaAlterada);
                        System.out.println("Pergunta alterada com sucesso!");
                        break;
                    case 3:
                        System.out.print("Digite o número da pergunta a excluir: ");
                        int numeroExcluir = Integer.parseInt(scanner.nextLine());
                        FormularioService.excluirPergunta(numeroExcluir);
                        System.out.println("Pergunta excluída com sucesso!");
                        break;
                    case 4:
                        System.out.println("Voltando ao menu inicial...");
                        return;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }
}