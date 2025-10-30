package EternalIdle.model.shop;

public enum ShopCategory {
    WEAPONS("Armas", "⚔️"),
    ARMOR("Armaduras", "🛡️"),
    POTIONS("Poções", "🧪"),
    MATERIALS("Materiais", "📦"),
    UPGRADES("Melhorias", "⭐");

    private final String displayName;
    private final String emoji;

    ShopCategory(String displayName, String emoji) {
        this.displayName = displayName;
        this.emoji = emoji;
    }

    public String getDisplayName() { return displayName; }
    public String getEmoji() { return emoji; }
}