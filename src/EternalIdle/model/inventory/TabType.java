package EternalIdle.model.inventory;

public enum TabType {
    ALL("Todos", "📦", "Todos os itens"),
    WEAPONS("Armas", "⚔️", "Armas e equipamentos de combate"),
    ARMOR("Armaduras", "🛡️", "Armaduras e proteções"),
    CONSUMABLES("Consumíveis", "🧪", "Poções e itens de uso"),
    MATERIALS("Materiais", "📚", "Materiais de crafting"),
    SPECIAL("Especial", "🌟", "Itens raros e lendários");

    private final String name;
    private final String emoji;
    private final String description;

    TabType(String name, String emoji, String description) {
        this.name = name;
        this.emoji = emoji;
        this.description = description;
    }

    public String getName() { return name; }
    public String getEmoji() { return emoji; }
    public String getDescription() { return description; }
}