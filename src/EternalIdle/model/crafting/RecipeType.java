package EternalIdle.model.crafting;

public enum RecipeType {
    WEAPON("Arma", "⚔️"),
    ARMOR("Armadura", "🛡️"),
    POTION("Poção", "🧪"),
    UPGRADE("Melhoria", "⭐"),
    MATERIAL("Material", "🔧");

    private final String name;
    private final String emoji;

    RecipeType(String name, String emoji) {
        this.name = name;
        this.emoji = emoji;
    }

    public String getName() { return name; }
    public String getEmoji() { return emoji; }
}