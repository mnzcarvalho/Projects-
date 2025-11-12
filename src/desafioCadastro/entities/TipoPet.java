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

    public static TipoPet fromString (String text){
for (TipoPet tipo : TipoPet.values()){
    if (tipo.descricao.equalsIgnoreCase(text)){
        return tipo;
    }
}
throw new IllegalArgumentException("Tipo de pet inválido: " + text);
    }
}
