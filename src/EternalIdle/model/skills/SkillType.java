package EternalIdle.model.skills;

public enum SkillType {
    // Atributos básicos
    STRENGTH_BOOST("Força Aprimorada", "Aumenta a Força em 1", "💪"),
    DEXTERITY_BOOST("Destreza Aprimorada", "Aumenta a Destreza em 1", "🎯"),
    INTELLIGENCE_BOOST("Inteligência Aprimorada", "Aumenta a Inteligência em 1", "📚"),
    VITALITY_BOOST("Vitalidade Aprimorada", "Aumenta a Vitalidade em 1", "❤️"),
    LUCK_BOOST("Sorte Aprimorada", "Aumenta a Sorte em 1", "🍀"),

    // Habilidades de combate
    CRITICAL_STRIKE("Golpe Crítico", "Aumenta chance de acerto crítico", "⚡"),
    DOUBLE_STRIKE("Golpe Duplo", "Chance de atacar duas vezes", "✨"),
    LIFE_STEAL("Roubo de Vida", "Rouba vida dos inimigos", "🩸"),

    // Habilidades passivas
    GOLD_FIND("Caça ao Ouro", "Aumenta ouro encontrado", "💰"),
    EXP_BOOST("Experiência Acumulada", "Aumenta experiência ganha", "🌟"),
    ITEM_FIND("Caçador de Tesouros", "Aumenta chance de encontrar itens", "🎁");

    private final String name;
    private final String description;
    private final String emoji;

    SkillType(String name, String description, String emoji) {
        this.name = name;
        this.description = description;
        this.emoji = emoji;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getEmoji() { return emoji; }
}