package eternalidle.model.inventory;

public enum TabType {
    WEAPONS,
    ARMOR,
    MATERIALS,
    POTIONS,
    GEMS,
    RUNES,
    SPECIAL,
    ALL;

    // 🔥 MÉTODO ADICIONADO: getName()
    public String getName() {
        return switch(this) {
            case WEAPONS -> "Armas";
            case ARMOR -> "Armaduras";
            case MATERIALS -> "Materiais";
            case POTIONS -> "Poções";
            case GEMS -> "Gemas";
            case RUNES -> "Runas";
            case SPECIAL -> "Especial";
            case ALL -> "Todos Itens";
        };
    }

    // 🔥 MÉTODO ADICIONADO: getEmoji()
    public String getEmoji() {
        return switch(this) {
            case WEAPONS -> "⚔️";
            case ARMOR -> "🛡️";
            case MATERIALS -> "📦";
            case POTIONS -> "🧪";
            case GEMS -> "💎";
            case RUNES -> "🔮";
            case SPECIAL -> "🌟";
            case ALL -> "📋";
        };
    }

    // 🔥 MÉTODO ADICIONADO: isUnlocked() - lógica básica
    public boolean isUnlocked() {
        // Tabs básicas sempre desbloqueadas, outras precisam ser desbloqueadas
        return switch(this) {
            case WEAPONS, ARMOR, MATERIALS, ALL -> true;
            case POTIONS, GEMS, RUNES, SPECIAL -> false; // Precisam ser desbloqueadas
        };
    }
}