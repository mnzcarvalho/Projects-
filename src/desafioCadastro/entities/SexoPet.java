package desafioCadastro.entities;

public enum SexoPet {
    MACHO("Macho"),
    FEMEA("Femea");

    private final String descricao;

    SexoPet(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static SexoPet fromString (String text){
        for (SexoPet sexo : SexoPet.values()){
            if (sexo.descricao.equalsIgnoreCase(text)){
                return sexo;
            }
        }
        throw new IllegalArgumentException("Sexo do pet inválido: " + text);
    }
}
