package EternalIdle.model.items;

public enum ItemRarity {
    COMMON("Comum"),
    UNCOMMON("Incomum"),
    RARE("Raro"),
    EPIC("Épico"),
    LEGENDARY("Lendário");

    private final String displayName;

    ItemRarity(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}