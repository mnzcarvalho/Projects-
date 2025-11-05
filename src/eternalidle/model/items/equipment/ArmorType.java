package eternalidle.model.items.equipment;

public enum ArmorType {
    HELMET("Capacete"),
    CHEST("Armadura"),
    GLOVES("Luvas"),
    BOOTS("Botas");

    private final String displayName;

    ArmorType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmoji() {
        return switch(this) {
            case HELMET -> "⛑️";
            case CHEST -> "👕";
            case GLOVES -> "🧤";
            case BOOTS -> "👢";
        };
    }
}