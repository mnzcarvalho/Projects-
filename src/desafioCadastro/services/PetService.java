package desafioCadastro.services;

import desafioCadastro.entities.Endereco;
import desafioCadastro.entities.Pet;
import desafioCadastro.entities.SexoPet;
import desafioCadastro.entities.TipoPet;
import desafioCadastro.utils.Constantes;
import desafioCadastro.utils.ValidacaoUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PetService {
    private List<Pet> pets;
    private Scanner scanner;

    public PetService() {
        this.pets = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        carregarPets();
    }

    private void carregarPets() {
        // Implementar carregamento dos pets salvos
    }

    public void cadastrarPet() throws IOException {
        System.out.println("\n=== CADASTRO DE NOVO PET ===");
        FormularioService.exibirFormulario();

        List<String> respostas = new ArrayList<>();
        List<String> perguntas = FormularioService.getPerguntas();

        try {
            for (int i = 0; i < perguntas.size(); i++) {
                System.out.print("Resposta: ");
                String resposta = scanner.nextLine().trim();

                // Validações específicas para cada pergunta
                switch (i) {
                    case 0: // Nome
                        validarNome(resposta);
                        break;
                    case 1: // Tipo
                        validarTipo(resposta);
                        break;
                    case 2: // Sexo
                        validarSexo(resposta);
                        break;
                    case 4: // Idade (índice 4 porque começa em 0)
                        resposta = validarIdade(resposta);
                        break;
                    case 5: // Peso
                        resposta = validarPeso(resposta);
                        break;
                    case 6: // Raça
                        validarRaca(resposta);
                        break;
                }

                if (resposta.isEmpty()) {
                    resposta = Constantes.NAO_INFORMADO;
                }
                respostas.add(resposta);
            }

            // Criar objeto Pet
            Pet pet = criarPetFromRespostas(respostas);
            salvarPet(pet);
            pets.add(pet);

            System.out.println("Pet cadastrado com sucesso!");

        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validação: " + e.getMessage());
            System.out.println("Cadastro cancelado. Tente novamente.");
        }
    }

    private void validarNome(String nome) {
        if (nome.isEmpty()) {
            throw new IllegalArgumentException("Nome completo é obrigatório");
        }
        if (!ValidacaoUtils.isValidNome(nome)) {
            throw new IllegalArgumentException("Nome não pode conter caracteres especiais ou números");
        }
    }

    private void validarTipo(String tipo) {
        if (!tipo.equalsIgnoreCase("Cachorro") && !tipo.equalsIgnoreCase("Gato")) {
            throw new IllegalArgumentException("Tipo deve ser Cachorro ou Gato");
        }
    }

    private void validarSexo(String sexo) {
        if (!sexo.equalsIgnoreCase("Macho") && !sexo.equalsIgnoreCase("Femea")) {
            throw new IllegalArgumentException("Sexo deve ser Macho ou Femea");
        }
    }

    private String validarIdade(String idadeStr) {
        if (idadeStr.isEmpty()) return Constantes.NAO_INFORMADO;

        if (!ValidacaoUtils.isNumero(idadeStr)) {
            throw new IllegalArgumentException("Idade deve conter apenas números");
        }

        double idade = ValidacaoUtils.parseDouble(idadeStr);
        if (!ValidacaoUtils.isValidIdade(idade)) {
            throw new IllegalArgumentException("Idade deve ser entre 0 e 20 anos");
        }

        return String.valueOf(idade);
    }

    private String validarPeso(String pesoStr) {
        if (pesoStr.isEmpty()) return Constantes.NAO_INFORMADO;

        if (!ValidacaoUtils.isNumero(pesoStr)) {
            throw new IllegalArgumentException("Peso deve conter apenas números");
        }

        double peso = ValidacaoUtils.parseDouble(pesoStr);
        if (!ValidacaoUtils.isValidPeso(peso)) {
            throw new IllegalArgumentException("Peso deve ser entre 0.5kg e 60kg");
        }

        return String.valueOf(peso);
    }

    private void validarRaca(String raca) {
        if (!raca.isEmpty() && !ValidacaoUtils.isValidRaca(raca)) {
            throw new IllegalArgumentException("Raça não pode conter números ou caracteres especiais");
        }
    }

    private Pet criarPetFromRespostas(List<String> respostas) {
        System.out.print("Número da casa: ");
        String numero = scanner.nextLine().trim();
        System.out.print("Cidade: ");
        String cidade = scanner.nextLine().trim();
        System.out.print("Rua: ");
        String rua = scanner.nextLine().trim();

        Endereco endereco = new Endereco(numero, cidade, rua);


        double idade = respostas.get(4).equals(Constantes.NAO_INFORMADO) ? 0.0 :
                Double.parseDouble(respostas.get(4));
        double peso = respostas.get(5).equals(Constantes.NAO_INFORMADO) ? 0.0 :
                Double.parseDouble(respostas.get(5));

        return new Pet(
                respostas.get(0), // nome
                TipoPet.fromString(respostas.get(1)), // tipo
                SexoPet.fromString(respostas.get(2)), // sexo
                endereco,
                idade,
                peso,
                respostas.get(6) // raça
        );
    }

    private void salvarPet(Pet pet) throws IOException {
        ArquivoService.criarPastaSeNaoExistir();
        String caminho = Constantes.PASTA_PET + pet.getNomeArquivo();

        List<String> conteudo = new ArrayList<>();
        conteudo.add("1 - " + pet.getNomeCompleto());
        conteudo.add("2 - " + pet.getTipo().getDescricao());
        conteudo.add("3 - " + pet.getSexo().getDescricao());
        conteudo.add("4 - " + pet.getEndereco().toString());
        conteudo.add("5 - " + pet.getIdade() + " anos");
        conteudo.add("6 - " + pet.getPeso() + "kg");
        conteudo.add("7 - " + pet.getRaca());

        ArquivoService.escreverArquivo(caminho, conteudo);
    }

    public void listarTodosPets() {
        System.out.println("\n=== TODOS OS PETS CADASTRADOS ===");
        if (pets.isEmpty()) {
            System.out.println("Nenhum pet cadastrado.");
            return;
        }

        for (int i = 0; i < pets.size(); i++) {
            System.out.println((i + 1) + ". " + pets.get(i));
        }
    }

    public void close() {
        scanner.close();
    }
}