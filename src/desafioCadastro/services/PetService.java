package desafioCadastro.services;

import desafioCadastro.entities.Endereco;
import desafioCadastro.entities.Pet;
import desafioCadastro.entities.SexoPet;
import desafioCadastro.entities.TipoPet;
import desafioCadastro.utils.Constantes;
import desafioCadastro.utils.ValidacaoUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.Normalizer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.HashMap;



public class PetService {
    private List<Pet> pets;
    private Scanner scanner;

    public PetService() {
        this.pets = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        carregarPetsCadastrados();
    }

    public void carregarPetsCadastrados() {
        File pastaPets = new File(Constantes.PASTA_PET);
        if (!pastaPets.exists()) {
            pastaPets.mkdirs();
            return;
        }

        File[] arquivos = pastaPets.listFiles((dir, nome) ->
                nome.toLowerCase().endsWith(".txt") &&
                        nome.matches("\\d{8}T\\d{4}-[A-Z]+\\.txt")
        );

        if (arquivos != null) {
            for (File arquivo : arquivos) {
                try {
                    Pet pet = criarPetFromArquivo(arquivo);
                    if (pet != null) {
                        pets.add(pet);
                    }
                } catch (Exception e) {
                    System.out.println("Erro ao carregar pet do arquivo: " + arquivo.getName());
                }
            }
        }
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

        // Adicionar respostas extras se existirem
        List<String> respostasExtras = pet.getRespostasExtras();
        for (int i = 0; i < respostasExtras.size(); i++) {
            conteudo.add((8 + i) + " - " + respostasExtras.get(i));
        }

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
        if (scanner != null) {
            scanner.close();
        }
    }

    private Pet criarPetFromArquivo(File arquivo) throws IOException {
        List<String> linhas = Files.readAllLines(arquivo.toPath(), StandardCharsets.UTF_8);
        if (linhas.size() < 7) return null;

        String nomeArquivo = arquivo.getName();
        LocalDateTime dataCadastro = extrairDataDoNome(nomeArquivo);

        Pet pet = new Pet();
        pet.setDataCadastro(dataCadastro); // Agora este método existe

        for (int i = 0; i < linhas.size(); i++) {
            String linha = linhas.get(i).trim();
            if (linha.isEmpty()) continue;

            // Extrair resposta (remover numeração)
            String resposta = linha.replaceAll("^\\d+\\s*-\\s*", "").trim();

            switch (i) {
                case 0: // Nome completo
                    pet.setNomeCompleto(resposta);
                    break;
                case 1: // Tipo
                    try {
                        pet.setTipo(TipoPet.fromString(resposta));
                    } catch (IllegalArgumentException e) {
                        // Se não conseguir converter, tenta pelo valor em português
                        if (resposta.equalsIgnoreCase("Cachorro")) {
                            pet.setTipo(TipoPet.CACHORRO);
                        } else if (resposta.equalsIgnoreCase("Gato")) {
                            pet.setTipo(TipoPet.GATO);
                        }
                    }
                    break;
                case 2: // Sexo
                    try {
                        pet.setSexo(SexoPet.fromString(resposta));
                    } catch (IllegalArgumentException e) {
                        // Se não conseguir converter, tenta pelo valor em português
                        if (resposta.equalsIgnoreCase("Macho")) {
                            pet.setSexo(SexoPet.MACHO);
                        } else if (resposta.equalsIgnoreCase("Femea")) {
                            pet.setSexo(SexoPet.FEMEA);
                        }
                    }
                    break;
                case 3: // Endereço
                    pet.setEndereco(parseEndereco(resposta)); // Corrigido: setEndereco (sem ç)
                    break;
                case 4: // Idade
                    pet.setIdade(parseIdade(resposta));
                    break;
                case 5: // Peso
                    pet.setPeso(parsePeso(resposta));
                    break;
                case 6: // Raça
                    pet.setRaca(resposta);
                    break;
                default: // Perguntas extras
                    if (i >= 7) {
                        pet.adicionarRespostaExtra(resposta); // Agora este método existe
                    }
                    break;
            }
        }

        return pet;
    }


    private LocalDateTime extrairDataDoNome(String nomeArquivo) {
        try {
            // Formato: 20231101T1234-NOME.txt
            String dataHoraStr = nomeArquivo.substring(0, 13); // "20231101T1234"
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
            return LocalDateTime.parse(dataHoraStr, formatter);
        } catch (Exception e) {
            return LocalDateTime.now();
        }
    }

    private Endereco parseEndereco(String enderecoStr) {
        String[] partes = enderecoStr.split(",");
        Endereco endereco = new Endereco();

        if (partes.length >= 1) endereco.setRua(partes[0].trim());
        if (partes.length >= 2) endereco.setNumero(partes[1].trim());
        if (partes.length >= 3) endereco.setBairro(partes[2].trim());
        return endereco;
    }

    private double parseIdade(String idadeStr) {
        // Converter "6 anos" ou "0.5 anos" para double
        return Double.parseDouble(idadeStr.replaceAll("[^0-9.,]", "").replace(",", "."));
    }

    private double parsePeso(String pesoStr) {
        // Converter "5kg" para double
        return Double.parseDouble(pesoStr.replaceAll("[^0-9.,]", "").replace(",", "."));
    }

    public List<Pet> buscarPets(Map<String, Object> criterios) {
        return pets.stream()
                .filter(pet -> aplicarTodosCriterios(pet, criterios))
                .collect(Collectors.toList());
    }

    public void buscarPetsComDestaque() {
        Map<String, Object> criterios = realizarBuscaParaSelecao();
        List<Pet> resultados = buscarPets(criterios);
        exibirResultadosBuscaComDestaque(resultados, criterios);
    }

    private boolean aplicarTodosCriterios(Pet pet, Map<String, Object> criterios) {
        for (Map.Entry<String, Object> entry : criterios.entrySet()) {
            if (!aplicarCriterio(pet, entry.getKey(), entry.getValue())) {
                return false;
            }
        }
        return true;
    }

    private boolean aplicarCriterio(Pet pet, String chave, Object valor) {
        switch (chave) {
            case "tipo":
                return pet.getTipo() == valor;
            case "nome":
                return contemTermoIgnorandoCase(pet.getNomeCompleto(), (String) valor);
            case "sexo":
                return pet.getSexo() == valor;
            case "idade":
                return pet.getIdade() == (double) valor;
            case "peso":
                return pet.getPeso() == (double) valor;
            case "raca":
                return contemTermoIgnorandoCase(pet.getRaca(), (String) valor);
            case "endereco":
                return contemTermoIgnorandoCase(pet.getEndereco().toString(), (String) valor);
            case "dataMes":
                return pet.getDataCadastro().getMonthValue() == (int) valor;
            case "dataAno":
                return pet.getDataCadastro().getYear() == (int) valor;
            default:
                return true;
        }
    }

    private boolean contemTermoIgnorandoCase(String texto, String termo) {
        if (texto == null || termo == null) return false;
        String textoNormalizado = removerAcentos(texto.toLowerCase());
        String termoNormalizado = removerAcentos(termo.toLowerCase());
        return textoNormalizado.contains(termoNormalizado);
    }

    // Adicionar em ValidacaoUtils.java
    public static String removerAcentos(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

    public void alterarPet() throws IOException {
        System.out.println("\n=== ALTERAR DADOS DO PET ===");

        if (pets.isEmpty()) {
            System.out.println("Nenhum pet cadastrado para alterar.");
            return;
        }

        // Buscar o pet primeiro
        Map<String, Object> criterios = realizarBuscaParaSelecao();
        List<Pet> resultados = buscarPets(criterios);

        if (resultados.isEmpty()) {
            System.out.println("Nenhum pet encontrado com os critérios informados.");
            return;
        }

        // Exibir resultados para seleção
        exibirResultadosParaSelecao(resultados);

        // Selecionar pet
        System.out.print("\nDigite o número do pet que deseja alterar: ");
        int escolha = Integer.parseInt(scanner.nextLine());

        if (escolha < 1 || escolha > resultados.size()) {
            System.out.println("Número inválido!");
            return;
        }

        Pet petSelecionado = resultados.get(escolha - 1);
        alterarDadosPet(petSelecionado);
    }

    private void alterarDadosPet(Pet pet) throws IOException {
        System.out.println("\nAlterando dados do pet: " + pet.getNomeCompleto());
        System.out.println("Deixe em branco para manter o valor atual.");

        // Nome
        System.out.print("Novo nome completo [" + pet.getNomeCompleto() + "]: ");
        String novoNome = scanner.nextLine().trim();
        if (!novoNome.isEmpty()) {
            validarNome(novoNome);
            pet.setNomeCompleto(novoNome);
        }

        // Endereço
        Endereco enderecoAtual = pet.getEndereco();
        System.out.println("\n--- Endereço ---");
        System.out.print("Nova rua [" + enderecoAtual.getRua() + "]: ");
        String novaRua = scanner.nextLine().trim();
        if (!novaRua.isEmpty()) {
            enderecoAtual.setRua(novaRua);
        }

        System.out.print("Novo número [" + enderecoAtual.getNumero() + "]: ");
        String novoNumero = scanner.nextLine().trim();
        if (!novoNumero.isEmpty()) {
            enderecoAtual.setNumero(novoNumero);
        }

        System.out.print("Novo bairro [" + enderecoAtual.getBairro() + "]: ");
        String novoBairro = scanner.nextLine().trim();
        if (!novoBairro.isEmpty()) {
            enderecoAtual.setBairro(novoBairro);
        }

        // Idade
        System.out.print("Nova idade [" + pet.getIdade() + "]: ");
        String novaIdadeStr = scanner.nextLine().trim();
        if (!novaIdadeStr.isEmpty()) {
            String idadeValidada = validarIdade(novaIdadeStr);
            pet.setIdade(Double.parseDouble(idadeValidada));
        }

        // Peso
        System.out.print("Novo peso [" + pet.getPeso() + "]: ");
        String novoPesoStr = scanner.nextLine().trim();
        if (!novoPesoStr.isEmpty()) {
            String pesoValidado = validarPeso(novoPesoStr);
            pet.setPeso(Double.parseDouble(pesoValidado));
        }

        // Raça
        System.out.print("Nova raça [" + pet.getRaca() + "]: ");
        String novaRaca = scanner.nextLine().trim();
        if (!novaRaca.isEmpty()) {
            validarRaca(novaRaca);
            pet.setRaca(novaRaca);
        }

        // Salvar alterações no arquivo
        salvarAlteracoesPet(pet);
        System.out.println("Pet alterado com sucesso!");
    }

    private void salvarAlteracoesPet(Pet pet) throws IOException {
        // Deletar arquivo antigo
        String caminhoAntigo = Constantes.PASTA_PET + encontrarArquivoAntigo(pet);
        ArquivoService.deletarArquivo(caminhoAntigo);

        // Criar novo arquivo com dados atualizados
        salvarPet(pet);
    }

    private String encontrarArquivoAntigo(Pet pet) {
        // Buscar arquivo que corresponde a este pet (pode ser pelo nome ou outros critérios)
        File pasta = new File(Constantes.PASTA_PET);
        File[] arquivos = pasta.listFiles();

        if (arquivos != null) {
            for (File arquivo : arquivos) {
                try {
                    Pet petArquivo = criarPetFromArquivo(arquivo);
                    if (petArquivo != null && petArquivo.getNomeCompleto().equals(pet.getNomeCompleto())) {
                        return arquivo.getName();
                    }
                } catch (IOException e) {
                    // Continua para o próximo arquivo
                }
            }
        }
        return pet.getNomeArquivo(); // Fallback
    }

    public void deletarPet() throws IOException {
        System.out.println("\n=== EXCLUIR PET ===");

        if (pets.isEmpty()) {
            System.out.println("Nenhum pet cadastrado para excluir.");
            return;
        }

        // Buscar o pet primeiro
        Map<String, Object> criterios = realizarBuscaParaSelecao();
        List<Pet> resultados = buscarPets(criterios);

        if (resultados.isEmpty()) {
            System.out.println("Nenhum pet encontrado com os critérios informados.");
            return;
        }

        // Exibir resultados para seleção
        exibirResultadosParaSelecao(resultados);

        // Selecionar pet
        System.out.print("\nDigite o número do pet que deseja excluir: ");
        int escolha = Integer.parseInt(scanner.nextLine());

        if (escolha < 1 || escolha > resultados.size()) {
            System.out.println("Número inválido!");
            return;
        }

        Pet petSelecionado = resultados.get(escolha - 1);

        // Confirmação
        System.out.print("Tem certeza que deseja excluir " + petSelecionado.getNomeCompleto() + "? (SIM/NÃO): ");
        String confirmacao = scanner.nextLine().trim();

        if (confirmacao.equalsIgnoreCase("SIM")) {
            excluirPet(petSelecionado);
            System.out.println("Pet excluído com sucesso!");
        } else {
            System.out.println("Exclusão cancelada.");
        }
    }

    private void excluirPet(Pet pet) throws IOException {
        // Remover da lista em memória
        pets.remove(pet);

        // Deletar arquivo
        String caminho = Constantes.PASTA_PET + encontrarArquivoAntigo(pet);
        ArquivoService.deletarArquivo(caminho);
    }

    private Map<String, Object> realizarBuscaParaSelecao() {
        Map<String, Object> criterios = new HashMap<>();

        // Critério obrigatório: Tipo
        System.out.println("Selecione o tipo para busca:");
        System.out.println("1. Cachorro");
        System.out.println("2. Gato");
        System.out.print("Opção: ");
        int opcaoTipo = Integer.parseInt(scanner.nextLine());

        TipoPet tipo = (opcaoTipo == 1) ? TipoPet.CACHORRO : TipoPet.GATO;
        criterios.put("tipo", tipo);

        // Critérios adicionais
        System.out.println("\nSelecione critério de busca adicional:");
        System.out.println("1. Nome");
        System.out.println("2. Raça");
        System.out.println("3. Sem critério adicional");
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
            // Pode adicionar mais critérios aqui
        }

        return criterios;
    }

    private void exibirResultadosParaSelecao(List<Pet> resultados) {
        System.out.println("\n=== RESULTADOS ENCONTRADOS ===");
        for (int i = 0; i < resultados.size(); i++) {
            System.out.println((i + 1) + ". " + resultados.get(i));
        }
    }

    private static final String ANSI_BOLD = "\u001B[1m";
    private static final String ANSI_RESET = "\u001B[0m";

    public void exibirResultadosBuscaComDestaque(List<Pet> resultados, Map<String, Object> criterios) {
        if (resultados.isEmpty()) {
            System.out.println("Nenhum pet encontrado com os critérios informados.");
            return;
        }

        System.out.println("\n=== RESULTADOS DA BUSCA ===");
        for (int i = 0; i < resultados.size(); i++) {
            Pet pet = resultados.get(i);
            String linhaFormatada = formatarLinhaComDestaque(pet, criterios);
            System.out.println((i + 1) + ". " + linhaFormatada);
        }
        System.out.println("============================\n");
    }

    private String formatarLinhaComDestaque(Pet pet, Map<String, Object> criterios) {
        StringBuilder sb = new StringBuilder();

        // Nome com destaque
        String nome = aplicarDestaque(pet.getNomeCompleto(), criterios, "nome");
        sb.append(nome).append(" - ");

        // Tipo e Sexo
        sb.append(pet.getTipo().getDescricao()).append(" - ");
        sb.append(pet.getSexo().getDescricao()).append(" - ");

        // Endereço
        String endereco = aplicarDestaque(pet.getEndereco().toString(), criterios, "endereco");
        sb.append(endereco).append(" - ");

        // Idade e Peso
        sb.append(pet.getIdade()).append(" anos - ");
        sb.append(pet.getPeso()).append("kg - ");

        // Raça com destaque
        String raca = aplicarDestaque(pet.getRaca(), criterios, "raca");
        sb.append(raca);

        return sb.toString();
    }

    private String aplicarDestaque(String texto, Map<String, Object> criterios, String chaveCriterio) {
        if (texto == null || texto.equals(Constantes.NAO_INFORMADO)) {
            return Constantes.NAO_INFORMADO;
        }

        Object criterio = criterios.get(chaveCriterio);
        if (criterio instanceof String) {
            String termo = (String) criterio;
            if (!termo.isEmpty() && contemTermoIgnorandoCase(texto, termo)) {
                // Aplicar destaque ao termo encontrado (case-insensitive)
                String regex = "(?i)(" + Pattern.quote(termo) + ")";
                return texto.replaceAll(regex, ANSI_BOLD + "$1" + ANSI_RESET);
            }
        }
        return texto;
    }


}
