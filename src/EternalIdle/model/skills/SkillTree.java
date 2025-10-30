package EternalIdle.model.skills;

import EternalIdle.model.status.PlayerAttributes;
import java.util.ArrayList;
import java.util.List;

public class SkillTree {
    private List<SkillNode> skills;
    private int availableSkillPoints;

    public SkillTree() {
        this.skills = new ArrayList<>();
        this.availableSkillPoints = 0;
        initializeSkills();
    }

    private void initializeSkills() {
        // Tier 1 - Habilidades básicas (nível 1+)
        skills.add(new SkillNode(SkillType.STRENGTH_BOOST, 10, 1, 1));
        skills.add(new SkillNode(SkillType.DEXTERITY_BOOST, 10, 1, 1));
        skills.add(new SkillNode(SkillType.VITALITY_BOOST, 10, 1, 1));

        // Tier 2 - Habilidades intermediárias (nível 5+)
        skills.add(new SkillNode(SkillType.INTELLIGENCE_BOOST, 10, 5, 2));
        skills.add(new SkillNode(SkillType.LUCK_BOOST, 10, 5, 2));
        skills.add(new SkillNode(SkillType.GOLD_FIND, 5, 5, 3));

        // Tier 3 - Habilidades avançadas (nível 10+)
        skills.add(new SkillNode(SkillType.CRITICAL_STRIKE, 5, 10, 5));
        skills.add(new SkillNode(SkillType.EXP_BOOST, 5, 10, 4));
        skills.add(new SkillNode(SkillType.ITEM_FIND, 5, 10, 4));

        // Tier 4 - Habilidades élite (nível 15+)
        skills.add(new SkillNode(SkillType.DOUBLE_STRIKE, 3, 15, 8));
        skills.add(new SkillNode(SkillType.LIFE_STEAL, 3, 15, 8));
    }

    public boolean unlockSkill(SkillType skillType, int playerLevel) {
        SkillNode skill = findSkill(skillType);
        if (skill != null && skill.canUnlock(playerLevel, availableSkillPoints)) {
            if (skill.unlock(playerLevel, availableSkillPoints)) {
                availableSkillPoints -= skill.getSkillPointsCost();
                return true;
            }
        }
        return false;
    }

    public boolean upgradeSkill(SkillType skillType) {
        SkillNode skill = findSkill(skillType);
        if (skill != null && skill.canUpgrade(availableSkillPoints)) {
            if (skill.upgrade(availableSkillPoints)) {
                availableSkillPoints -= skill.getSkillPointsCost();
                return true;
            }
        }
        return false;
    }

    public void addSkillPoints(int points) {
        this.availableSkillPoints += points;
    }

    private SkillNode findSkill(SkillType skillType) {
        for (SkillNode skill : skills) {
            if (skill.getSkillType() == skillType) {
                return skill;
            }
        }
        return null;
    }

    public void applySkillEffects(PlayerAttributes attributes) {
        for (SkillNode skill : skills) {
            if (skill.isUnlocked()) {
                applySkillEffect(skill, attributes);
            }
        }
    }

    private void applySkillEffect(SkillNode skill, PlayerAttributes attributes) {
        switch (skill.getSkillType()) {
            case STRENGTH_BOOST:
                attributes.setStrength(1 + skill.getLevel());
                break;
            case DEXTERITY_BOOST:
                attributes.setDexterity(1 + skill.getLevel());
                break;
            case INTELLIGENCE_BOOST:
                attributes.setIntelligence(1 + skill.getLevel());
                break;
            case VITALITY_BOOST:
                attributes.setVitality(1 + skill.getLevel());
                break;
            case LUCK_BOOST:
                attributes.setLuck(1 + skill.getLevel());
                break;
            // Outras habilidades teriam efeitos mais complexos
        }
    }

    public void displaySkillTree() {
        System.out.println("\n🌳 === ÁRVORE DE HABILIDADES ===");
        System.out.println("Pontos disponíveis: " + availableSkillPoints);
        System.out.println("--------------------------------");

        for (SkillNode skill : skills) {
            System.out.println(skill.getDisplayInfo());
            System.out.println();
        }
        System.out.println("==============================\n");
    }

    // Getters
    public int getAvailableSkillPoints() { return availableSkillPoints; }
    public List<SkillNode> getSkills() { return skills; }

    // Calcular bônus totais para display
    public double getTotalGoldBonus() {
        return getSkillLevel(SkillType.GOLD_FIND) * 0.1; // +10% por nível
    }

    public double getTotalExpBonus() {
        return getSkillLevel(SkillType.EXP_BOOST) * 0.15; // +15% por nível
    }

    private int getSkillLevel(SkillType skillType) {
        SkillNode skill = findSkill(skillType);
        return skill != null && skill.isUnlocked() ? skill.getLevel() : 0;
    }
}