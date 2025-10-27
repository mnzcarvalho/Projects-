package EternalIdle.main;

import java.util.Random;

public class Monster {
    private String name;
    private int level;
    private int xp;

    public Monster(String name, int level, int xp) {
        this.name = name;
        this.level = level;
        this.xp = xp;
    }

    public static Monster generateMonster(int playerLevel) {
        Random rand = new Random();
        int monsterLevel = Math.max(1, playerLevel + rand.nextInt(3) - 1);
        String name = MonsterNames.getMonsterName(monsterLevel);
        int xp = 50 + monsterLevel * 10;
        return new Monster(name, monsterLevel, xp);
    }

    public String getName() { return name; }
    public int getLevel() { return level; }
    public int getXp() { return xp; }

    private static class MonsterNames {
        static String getMonsterName(int level) {
            if (level <= 10) return "Rato";
            else if (level <= 20) return "Goblin";
            else if (level <= 30) return "Demônio";
            else if (level <= 40) return "Orc";
            else if (level <= 50) return "Ciclope";
            else if (level <= 60) return "Quimera";
            else if (level <= 70) return "Wyvern";
            else if (level <= 80) return "Hydra";
            else if (level <= 90) return "Golem";
            else return "Lich";
        }
    }
}