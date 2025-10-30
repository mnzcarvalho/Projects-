package eternalidle;

import eternalidle.model.systems.GameManager;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("🎮 Iniciando Eternal Idle...");

        GameManager game = new GameManager();
        Scanner scanner = new Scanner(System.in);

        Thread gameThread = new Thread(() -> game.startGame());
        gameThread.start();

        // 🔥 ATUALIZAR COMANDOS COM SISTEMA DE SALVAMENTO
        System.out.println("\n=== COMANDOS DISPONÍVEIS ===");
        System.out.println("'shop'     - 🏪 Abrir loja");
        System.out.println("'stash'    - 🏠 Abrir stash");
        System.out.println("'crafting' - 🔨 Abrir crafting");
        System.out.println("'bosses'   - 🐉 Ver bosses");
        System.out.println("'status'   - 📊 Ver status");
        System.out.println("'stats'    - 📈 Ver estatísticas");
        System.out.println("'saves'    - 💾 Gerenciar saves"); // 🔥 NOVO
        System.out.println("'save'     - 💾 Salvar jogo");     // 🔥 NOVO
        System.out.println("'skills'   - 🌳 Ver habilidades");
        System.out.println("'equip'    - ⚔️ Ver equipamento");
        System.out.println("'inventory'- 🎒 Ver inventário");
        System.out.println("'stop'     - ⏹️ Parar jogo");
        System.out.println("============================\n");

        while (true) {
            System.out.print("🎯 Digite um comando: ");
            String input = scanner.nextLine().trim().toLowerCase();

            switch (input) {
                case "shop":
                    game.openShop();
                    break;
                case "stash":
                    game.openStash();
                    break;
                case "crafting":
                    game.openCrafting();
                    break;
                case "bosses":
                    game.showBosses();
                    break;
                case "status":
                    game.showPlayerStatus();
                    break;
                case "stats":
                    game.showStatistics();
                    break;
                case "detailedstats":
                    game.showDetailedStatistics();
                    break;
                // 🔥 NOVOS COMANDOS DE SALVAMENTO
                case "saves":
                    game.showSaveFiles();
                    break;
                case "save":
                    System.out.print("💾 Nome do save: ");
                    String saveName = scanner.nextLine().trim();
                    if (!saveName.isEmpty()) {
                        game.saveGame(saveName);
                    } else {
                        game.saveGame("manual_save");
                    }
                    break;
                case "load":
                    System.out.print("💾 Nome do save para carregar: ");
                    String loadName = scanner.nextLine().trim();
                    if (!loadName.isEmpty()) {
                        game.loadGame(loadName);
                    }
                    break;
                case "skills":
                    game.showSkills();
                    break;
                case "equip":
                    game.showEquipment();
                    break;
                case "inventory":
                    game.showInventory();
                    break;
                case "stop":
                    game.stopGame();
                    scanner.close();
                    System.out.println("👋 Jogo finalizado!");
                    return;
                default:
                    System.out.println("❌ Comando inválido! Digite 'help' para ver comandos.");
                    break;
            }
        }
    }
}