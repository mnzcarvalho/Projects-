package EternalIdle.main;

import EternalIdle.ascii.AsciiUI;
import java.util.Scanner;

public class Game {

    private static double globalExpMultiplier = 1.0;
    private static boolean running = true;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AsciiUI.showTitle();

        Player player = new Player("Hero");
        IdleCombatThread idleCombat = new IdleCombatThread(player);
        idleCombat.start();

        while (running) {
            AsciiUI.showPlayerStatus(player);
            System.out.println("\n=== MENU ===");
            System.out.println("1. Ver status detalhado");
            System.out.println("2. Alterar multiplicador global de XP");
            System.out.println("3. Pausar/retomar combate automático");
            System.out.println("4. Sair do jogo");

            int choice = Utils.readInt(sc);

            switch (choice) {
                case 1 -> AsciiUI.showStats(player);
                case 2 -> {
                    System.out.print("Novo multiplicador de XP: ");
                    globalExpMultiplier = Utils.readDouble(sc);
                    idleCombat.setExpMultiplier(globalExpMultiplier);
                    System.out.println("✅ Multiplicador global alterado para " + globalExpMultiplier + "x");
                }
                case 3 -> {
                    if (idleCombat.isPaused()) {
                        idleCombat.resumeCombat();
                        System.out.println("⚔️ Combate retomado!");
                    } else {
                        idleCombat.pauseCombat();
                        System.out.println("⏸️ Combate pausado!");
                    }
                }
                case 4 -> {
                    running = false;
                    idleCombat.stopCombat();
                    System.out.println("Saindo do jogo...");
                }
                default -> System.out.println("Opção inválida.");
            }
        }

        sc.close();
    }
}