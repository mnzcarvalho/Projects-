package EternalIdle.main;

public class Player {
    private String name;
    private int level;
    private int experience;
    private int nextLevelExp;

    public Player(String name) {
        this.name = name;
        this.level = 1;
        this.experience = 0;
        this.nextLevelExp = ExperienceTable.getExpForLevel(level);
    }

    public synchronized void addExperience(int xp) {
        experience += xp;
        while (experience >= nextLevelExp && level < 100) {
            experience -= nextLevelExp;
            level++;
            System.out.println("⭐ Subiu para o nível " + level + "!");
            nextLevelExp = ExperienceTable.getExpForLevel(level);
        }
        if (level >= 100) {
            System.out.println("🔥 Nível máximo alcançado!");
        }
    }

    public String getName() { return name; }
    public int getLevel() { return level; }
    public int getExperience() { return experience; }
    public int getNextLevelExp() { return nextLevelExp; }
}