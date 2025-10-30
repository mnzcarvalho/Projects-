package EternalIdle.model.crafting;

public enum MaterialType {
    FRAGMENT("Fragmento", "🧩"),
    ESSENCE("Essência", "💎"),
    CORE("Núcleo", "🔮"),
    SHARD("Estilhaço", "✨");

    private final String name;
    private final String emoji;

    MaterialType(String name, String emoji) {
        this.name = name;
        this.emoji = emoji;
    }

    public String getName() { return name; }
    public String getEmoji() { return emoji; }
}