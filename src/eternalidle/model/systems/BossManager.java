package eternalidle.model.systems;

public class BossManager {
    private boolean bossSpawned;

    public BossManager() {
        this.bossSpawned = false;
    }

    public void showBosses() {
        System.out.println("\n🐉 === SISTEMA DE BOSSES ===");
        System.out.println("🚧 Sistema em desenvolvimento!");
        System.out.println("📊 Bosses disponíveis em breve:");
        System.out.println("• Dragão Ancestral (Nível 50)");
        System.out.println("• Rei Lich (Nível 45)");
        System.out.println("• Titã de Pedra (Nível 40)");
        System.out.println("• Fênix Renascida (Nível 42)");
        System.out.println("============================\n");
    }

    public boolean isBossSpawned() { return bossSpawned; }
}