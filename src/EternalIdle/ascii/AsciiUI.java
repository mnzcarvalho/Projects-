package EternalIdle.ascii;

import EternalIdle.main.Player;

public class AsciiUI {

    public static void showTitle() {
        System.out.println("====================================");
        System.out.println("     ⚔️  ETERNAL IDLE RPG ⚔️");
        System.out.println("====================================\n");
    }

    public static void showPlayerStatus(Player p) {
        System.out.println("\n==== STATUS ====");
        System.out.println("Nome: " + p.getName());
        System.out.println("Nível: " + p.getLevel());
        System.out.println("XP: " + p.getExperience() + " / " + p.getNextLevelExp());
    }

    public static void showStats(Player p) {
        System.out.println("\n--- Atributos ---");
        System.out.println("Força: " + (5 + p.getLevel()));
        System.out.println("Vitalidade: " + (5 + p.getLevel() / 2));
        System.out.println("Inteligência: " + (5 + p.getLevel() / 3));
        System.out.println("-----------------");
    }
}