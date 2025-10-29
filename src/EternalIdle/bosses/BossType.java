package EternalIdle.bosses;

public enum BossType {
    // Bosses Normais
    ANCIENT_DRAGON("Dragão Ancestral", "🐉", 50, 5000, 200, 0.5, "O guardião das montanhas antigas"),
    LICH_KING("Rei Lich", "👑", 45, 3000, 150, 1.4, "Senhor dos mortos-vivos"),
    TITAN_STONE("Titã de Pedra", "🗿", 40, 8000, 120, 0.3, "Gigante elemental da terra"),
    PHOENIX("Fênix Renascida", "🔥", 42, 2500, 180, 1.2, "Criatura mítica do fogo eterno"),

    // Bosses Especiais (Evento)
    CORRUPTED_GOLEM("Golem Corrompido", "💀", 35, 6000, 140, 0.6, "Evento: Corrupção Profunda"),
    VOID_HORROR("Horror do Vácuo", "🌌", 60, 10000, 300, 0.8, "Evento: Invasão do Vácuo"),
    CRYSTAL_QUEEN("Rainha de Cristal", "💎", 38, 4000, 160, 1.1, "Evento: Mina de Cristal");

    private final String name;
    private final String emoji;
    private final int level;
    private final double health;
    private final double damage;
    private final double attackSpeed;
    private final String description;

    BossType(String name, String emoji, int level, double health, double damage, double attackSpeed, String description) {
        this.name = name;
        this.emoji = emoji;
        this.level = level;
        this.health = health;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.description = description;
    }

    // Getters
    public String getName() { return name; }
    public String getEmoji() { return emoji; }
    public int getLevel() { return level; }
    public double getHealth() { return health; }
    public double getDamage() { return damage; }
    public double getAttackSpeed() { return attackSpeed; }
    public String getDescription() { return description; }

    public boolean isEventBoss() {
        return this == CORRUPTED_GOLEM || this == VOID_HORROR || this == CRYSTAL_QUEEN;
    }
}