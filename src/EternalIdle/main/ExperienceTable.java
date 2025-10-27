package EternalIdle.main;

public class ExperienceTable {
    public static int getExpForLevel(int level) {
        if (level < 10) return 1000;
        else if (level < 20) return 2500;
        else if (level < 30) return 4000;
        else if (level < 50) return 10000;
        else if (level < 70) return 20000;
        else return 40000;
    }
}