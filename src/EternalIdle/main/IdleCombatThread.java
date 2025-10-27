package EternalIdle.main;

import java.util.Random;

public class IdleCombatThread extends Thread {

    private final Player player;
    private volatile boolean running = true;
    private volatile boolean paused = false;
    private double expMultiplier = 1.0;
    private final Random rand = new Random();

    public IdleCombatThread(Player player) {
        this.player = player;
    }

    public void setExpMultiplier(double multiplier) {
        this.expMultiplier = multiplier;
    }

    public void pauseCombat() { paused = true; }

    public void resumeCombat() { paused = false; }

    public boolean isPaused() { return paused; }

    public void stopCombat() { running = false; }

    @Override
    public void run() {
        System.out.println("\n💤 Iniciando combate automático...");
        while (running) {
            if (!paused) {
                Monster monster = Monster.generateMonster(player.getLevel());
                simulateFight(monster);
            }
            try {
                Thread.sleep(2000); // tempo entre combates
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void simulateFight(Monster monster) {
        int monsterHp = 50 + monster.getLevel() * 10;
        int playerDmg = 10 + player.getLevel() * 2;

        while (monsterHp > 0 && running && !paused) {
            monsterHp -= playerDmg;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        if (!running || paused) return;

        int xp = monster.getXp();
        int finalXp = (int) (xp * expMultiplier);
        player.addExperience(finalXp);
        System.out.println("⚔️ Derrotou " + monster.getName() + " (+" + finalXp + " XP)");
    }
}
