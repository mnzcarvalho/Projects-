package EternalIdle.entity;

import java.util.ArrayList;
import java.util.List;

public class MonsterFactory {

    public static List<Monster> createAllMonsters() {
        List<Monster> monsters = new ArrayList<>();

        // Tier 1 - Básicos
        monsters.add(createMonster("Rat", 1, "100", "5", 1.5, 1500, 10, 5));
        monsters.add(createMonster("Gnome", 2, "150", "8", 1.2, 1800, 15, 8));
        monsters.add(createMonster("Goblin", 3, "200", "12", 1.0, 2000, 20, 12));

        // Tier 2 - Intermediários
        monsters.add(createMonster("Orc", 5, "350", "18", 0.8, 2200, 35, 20));
        monsters.add(createMonster("Troll", 7, "500", "25", 0.6, 2500, 50, 30));
        monsters.add(createMonster("Gargoyle", 9, "400", "30", 1.3, 1800, 45, 35));

        // Tier 3 - Avançados
        monsters.add(createMonster("Wraith", 12, "300", "35", 1.8, 1600, 60, 40));
        monsters.add(createMonster("Harpy", 14, "350", "40", 1.5, 1700, 70, 45));
        monsters.add(createMonster("Cyclops", 16, "800", "45", 0.5, 3000, 80, 50));

        // Tier 4 - Élites
        monsters.add(createMonster("Minotaur", 18, "1000", "55", 0.7, 2800, 100, 60));
        monsters.add(createMonster("Griffin", 20, "900", "60", 1.2, 2000, 120, 70));
        monsters.add(createMonster("Chimera", 22, "1100", "65", 0.9, 2300, 140, 80));

        // Tier 5 - Raros
        monsters.add(createMonster("Basilisk", 25, "1200", "75", 0.8, 2400, 180, 100));
        monsters.add(createMonster("Wyvern", 28, "1300", "85", 1.1, 2100, 220, 120));
        monsters.add(createMonster("Golem", 30, "2000", "70", 0.4, 3500, 250, 130));

        // Tier 6 - Lendários
        monsters.add(createMonster("Hydra", 35, "2500", "100", 0.6, 2800, 400, 200));
        monsters.add(createMonster("Dragon", 40, "3000", "120", 0.7, 2600, 500, 250));
        monsters.add(createMonster("Lich", 45, "1800", "150", 1.4, 1800, 600, 300));

        // Tier 7 - Final Boss
        monsters.add(createMonster("Ancient Dragon", 50, "5000", "200", 0.5, 3000, 1000, 500));

        return monsters;
    }

    public static Monster createMonsterByName(String name) {
        return switch(name.toLowerCase()) {
            case "rat" -> createMonster("Rat", 1, "100", "5", 1.5, 1500, 10, 5);
            case "gnome" -> createMonster("Gnome", 2, "150", "8", 1.2, 1800, 15, 8);
            case "goblin" -> createMonster("Goblin", 3, "200", "12", 1.0, 2000, 20, 12);
            case "orc" -> createMonster("Orc", 5, "350", "18", 0.8, 2200, 35, 20);
            case "troll" -> createMonster("Troll", 7, "500", "25", 0.6, 2500, 50, 30);
            case "gargoyle" -> createMonster("Gargoyle", 9, "400", "30", 1.3, 1800, 45, 35);
            case "wraith" -> createMonster("Wraith", 12, "300", "35", 1.8, 1600, 60, 40);
            case "harpy" -> createMonster("Harpy", 14, "350", "40", 1.5, 1700, 70, 45);
            case "cyclops" -> createMonster("Cyclops", 16, "800", "45", 0.5, 3000, 80, 50);
            case "minotaur" -> createMonster("Minotaur", 18, "1000", "55", 0.7, 2800, 100, 60);
            case "griffin" -> createMonster("Griffin", 20, "900", "60", 1.2, 2000, 120, 70);
            case "chimera" -> createMonster("Chimera", 22, "1100", "65", 0.9, 2300, 140, 80);
            case "basilisk" -> createMonster("Basilisk", 25, "1200", "75", 0.8, 2400, 180, 100);
            case "wyvern" -> createMonster("Wyvern", 28, "1300", "85", 1.1, 2100, 220, 120);
            case "golem" -> createMonster("Golem", 30, "2000", "70", 0.4, 3500, 250, 130);
            case "hydra" -> createMonster("Hydra", 35, "2500", "100", 0.6, 2800, 400, 200);
            case "dragon" -> createMonster("Dragon", 40, "3000", "120", 0.7, 2600, 500, 250);
            case "lich" -> createMonster("Lich", 45, "1800", "150", 1.4, 1800, 600, 300);
            case "ancient dragon" -> createMonster("Ancient Dragon", 50, "5000", "200", 0.5, 3000, 1000, 500);
            default -> createMonster("Rat", 1, "100", "5", 1.5, 1500, 10, 5);
        };
    }

    public static Monster getMonsterByTier(int tier) {
        List<Monster> allMonsters = createAllMonsters();
        int index = Math.min(tier, allMonsters.size() - 1);
        return allMonsters.get(index);
    }

    private static Monster createMonster(String name, int level, String health, String damage,
                                         double attackSpeed, int spawnTime,
                                         long expReward, long goldReward) {
        Monster monster = new Monster(name, level, health, damage, attackSpeed, spawnTime);
        // Configurar recompensas específicas
        monster.setExpReward(expReward);
        monster.setGoldReward(goldReward);
        return monster;
    }
}
