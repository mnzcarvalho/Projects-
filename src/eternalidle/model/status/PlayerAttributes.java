package eternalidle.model.status;

public class PlayerAttributes {
    private int strength;      // Aumenta dano físico
    private int dexterity;     // Aumenta velocidade de ataque e crit
    private int intelligence;  // Aumenta dano mágico
    private int vitality;      // Aumenta vida máxima
    private int luck;          // Aumenta chance de crit e drops

    public PlayerAttributes() {
        this.strength = 1;
        this.dexterity = 1;
        this.intelligence = 1;
        this.vitality = 1;
        this.luck = 1;
    }

    // Getters
    public int getStrength() { return strength; }
    public int getDexterity() { return dexterity; }
    public int getIntelligence() { return intelligence; }
    public int getVitality() { return vitality; }
    public int getLuck() { return luck; }

    // Setters
    public void setStrength(int strength) { this.strength = strength; }
    public void setDexterity(int dexterity) { this.dexterity = dexterity; }
    public void setIntelligence(int intelligence) { this.intelligence = intelligence; }
    public void setVitality(int vitality) { this.vitality = vitality; }
    public void setLuck(int luck) { this.luck = luck; }

    // Increment methods
    public void addStrength(int amount) { this.strength += amount; }
    public void addDexterity(int amount) { this.dexterity += amount; }
    public void addIntelligence(int amount) { this.intelligence += amount; }
    public void addVitality(int amount) { this.vitality += amount; }
    public void addLuck(int amount) { this.luck += amount; }

    public void displayAttributes() {
        System.out.println("\n=== ATRIBUTOS ===");
        System.out.println("💪 Força: " + strength + " (+" + (strength * 2) + " dano físico)");
        System.out.println("🎯 Destreza: " + dexterity + " (+" + (dexterity * 0.5) + "% velocidade)");
        System.out.println("📚 Inteligência: " + intelligence + " (+" + (intelligence * 3) + " dano mágico)");
        System.out.println("❤️ Vitalidade: " + vitality + " (+" + (vitality * 10) + " vida)");
        System.out.println("🍀 Sorte: " + luck + " (+" + (luck * 0.5) + "% crit e drops)");
        System.out.println("=================\n");
    }
}