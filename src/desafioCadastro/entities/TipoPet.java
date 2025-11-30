package desafioCadastro.entities;

public enum TipoPet {
    CACHORRO("Cachorro"),
    GATO("Gato");

    private final String descricao;

    TipoPet(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoPet fromString(String texto) {
        for (TipoPet tipo : TipoPet.values()) {
            if (tipo.descricao.equalsIgnoreCase(texto)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo inválido: " + texto);
    }
}