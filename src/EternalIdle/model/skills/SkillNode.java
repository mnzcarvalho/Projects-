package EternalIdle.skills;

public class SkillNode {
    private SkillType skillType;
    private int level;
    private int maxLevel;
    private int requiredLevel;
    private int skillPointsCost;
    private boolean unlocked;

    public SkillNode(SkillType skillType, int maxLevel, int requiredLevel, int skillPointsCost) {
        this.skillType = skillType;
        this.level = 0;
        this.maxLevel = maxLevel;
        this.requiredLevel = requiredLevel;
        this.skillPointsCost = skillPointsCost;
        this.unlocked = false;
    }

    public boolean canUnlock(int playerLevel, int availableSkillPoints) {
        return !unlocked && playerLevel >= requiredLevel && availableSkillPoints >= skillPointsCost;
    }

    public boolean canUpgrade(int availableSkillPoints) {
        return unlocked && level < maxLevel && availableSkillPoints >= skillPointsCost;
    }

    public boolean unlock(int playerLevel, int availableSkillPoints) {
        if (canUnlock(playerLevel, availableSkillPoints)) {
            this.unlocked = true;
            this.level = 1;
            return true;
        }
        return false;
    }

    public boolean upgrade(int availableSkillPoints) {
        if (canUpgrade(availableSkillPoints)) {
            this.level++;
            return true;
        }
        return false;
    }

    // Getters
    public SkillType getSkillType() { return skillType; }
    public int getLevel() { return level; }
    public int getMaxLevel() { return maxLevel; }
    public int getRequiredLevel() { return requiredLevel; }
    public int getSkillPointsCost() { return skillPointsCost; }
    public boolean isUnlocked() { return unlocked; }

    public String getDisplayInfo() {
        String status = unlocked ? "✅ Nível " + level + "/" + maxLevel : "🔒 Travada";
        return skillType.getEmoji() + " " + skillType.getName() + " - " + status +
                "\n   " + skillType.getDescription() +
                "\n   Custo: " + skillPointsCost + " pontos | Nível req: " + requiredLevel;
    }
}