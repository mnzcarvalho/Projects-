package EternalIdle.rewards;

import EternalIdle.bosses.Boss;
import EternalIdle.entity.Player;
import EternalIdle.items.Item;
import EternalIdle.items.ItemRarity;
import EternalIdle.items.equipment.Weapon;
import EternalIdle.items.equipment.Armor;
import EternalIdle.items.currency.Gold;
import java.util.ArrayList;
import java.util.List;

public class BossReward {
    private Boss boss;
    private List<Item> guaranteedRewards;
    private List<Item> chanceRewards;

    public BossReward(Boss boss) {
        this.boss = boss;
        this.guaranteedRewards = new ArrayList<>();
        this.chanceRewards = new ArrayList<>();
        initializeRewards();
    }

    private void initializeRewards() {
        int bossLevel = boss.getLevel();

        // Recompensas garantidas
        guaranteedRewards.add(new Gold(bossLevel * 500)); // Ouro baseado no nível
        guaranteedRewards.add(new Gold(bossLevel * 200)); // Ouro extra

        // Recompensas com chance baseadas no tipo de boss
        switch (boss.getBossType()) {
            case ANCIENT_DRAGON:
                guaranteedRewards.add(new Weapon("Espada do Dragão", 35, 1.0, "Espada", ItemRarity.EPIC, bossLevel));
                chanceRewards.add(new Weapon("Arco das Escamas", 28, 1.4, "Arco", ItemRarity.LEGENDARY, bossLevel + 5));
                break;
            case LICH_KING:
                guaranteedRewards.add(new Armor("Manto do Lich", 15, 50, "CHEST", ItemRarity.EPIC, bossLevel));
                chanceRewards.add(new Weapon("Cajado da Alma", 40, 0.8, "Cajado", ItemRarity.LEGENDARY, bossLevel + 5));
                break;
            case TITAN_STONE:
                guaranteedRewards.add(new Armor("Armadura de Titã", 25, 80, "CHEST", ItemRarity.EPIC, bossLevel));
                chanceRewards.add(new Weapon("Martelo do Trovão", 45, 0.6, "Machado", ItemRarity.LEGENDARY, bossLevel + 5));
                break;
        }

        // Materiais de crafting especiais
        // (você precisaria criar materiais especiais de boss)
    }

    public void giveReward(Player player) {
        System.out.println("🎁 === RECOMPENSAS DO BOSS ===");

        // Dar recompensas garantidas
        for (Item reward : guaranteedRewards) {
            System.out.println("✅ " + reward.getName());
            // Aqui você daria o item para o jogador
        }

        // Chance para recompensas especiais
        for (Item reward : chanceRewards) {
            if (Math.random() < 0.3) { // 30% de chance
                System.out.println("🎊 " + reward.getName() + " [RARO!]");
                // Dar item raro
            }
        }

        // Experiência baseada no nível do boss
        long expReward = boss.getLevel() * 1000L;
        player.addExperience(expReward);
        System.out.println("🌟 +" + expReward + " EXP");

        System.out.println("============================\n");
    }

    public void displayPotentialRewards() {
        System.out.println("\n🎁 Recompensas de " + boss.getName() + ":");
        System.out.println("Garantidas:");
        for (Item reward : guaranteedRewards) {
            System.out.println("   ✅ " + reward.getName());
        }

        if (!chanceRewards.isEmpty()) {
            System.out.println("Chance (30% cada):");
            for (Item reward : chanceRewards) {
                System.out.println("   🎲 " + reward.getName());
            }
        }
    }
}