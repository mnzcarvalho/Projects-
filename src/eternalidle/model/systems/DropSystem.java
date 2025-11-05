package eternalidle.model.systems;

import eternalidle.model.items.Item;
import eternalidle.model.items.currency.Gold;
import eternalidle.model.items.equipment.Weapon;
import eternalidle.model.items.equipment.Armor;
import eternalidle.model.items.consumable.Potion;
import eternalidle.model.items.ItemRarity;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DropSystem {
    private Random random;

    public DropSystem() {
        this.random = new Random();
    }

    public List<Item> generateMonsterDrops(String monsterName, int monsterLevel) {
        List<Item> drops = new ArrayList<>();

        // Ouro sempre dropa (quantidade baseada no nível)
//        int goldAmount = monsterLevel * 5 + random.nextInt(monsterLevel * 3);
//        drops.add(new Gold(goldAmount));

        // Chance de drop de itens baseada no nível do monstro
        double itemDropChance = 0.3 + (monsterLevel * 0.03); // 🔥 30% + 3% por nível

        if (random.nextDouble() < itemDropChance) {
            drops.add(generateRandomItem(monsterLevel));
        }

        // Chance menor para poção
        if (random.nextDouble() < 0.0001) { // 🔥 20% de chance (era 30%)
            drops.add(generatePotion(monsterLevel));
        }
        System.out.println("🎲 Gerando drops para " + monsterName + " nível " + monsterLevel);
        System.out.println("   Chance de item: " + String.format("%.1f", itemDropChance * 100) + "%");

        return drops;
    }

    private Item generateRandomItem(int monsterLevel) {
        int itemType = random.nextInt(3);

        switch (itemType) {
            case 0: // Arma
                return generateWeapon(monsterLevel);
            case 1: // Armadura
                return generateArmor(monsterLevel);
            default:
                return generateWeapon(monsterLevel);
        }
    }

    private Weapon generateWeapon(int monsterLevel) {
        String[] weaponTypes = {"Espada", "Machado", "Arco", "Cajado"};
        String[] weaponNames = {
                "Lâmina do Caçador", "Machado do Trovão", "Arco da Floresta",
                "Cajado da Sabedoria", "Adaga Sombria", "Martelo do Poder"
        };

        String weaponType = weaponTypes[random.nextInt(weaponTypes.length)];
        String weaponName = weaponNames[random.nextInt(weaponNames.length)];
        int baseDamage = 5 + (monsterLevel * 2);
        double attackSpeed = 0.8 + (random.nextDouble() * 0.7);
        ItemRarity rarity = calculateRarity(monsterLevel);
        int requiredLevel = Math.max(1, monsterLevel - 2); // Nível requerido baseado no monstro

        return new Weapon(weaponName, baseDamage, attackSpeed, weaponType, rarity, requiredLevel);
    }

    private Armor generateArmor(int monsterLevel) {
        String[] armorTypes = {"HELMET", "CHEST", "BOOTS"};
        String[] armorNames = {
                "Elmo do Dragão", "Peitoral de Aço", "Grevas da Agilidade",
                "Botas do Vento", "Manto da Invisibilidade"
        };

        String armorType = armorTypes[random.nextInt(armorTypes.length)];
        String armorName = armorNames[random.nextInt(armorNames.length)];
        int defense = 2 + (monsterLevel * 1);
        int healthBonus = 10 + (monsterLevel * 3);
        ItemRarity rarity = calculateRarity(monsterLevel);
        int requiredLevel = Math.max(1, monsterLevel - 2); // Nível requerido baseado no monstro

        return new Armor(armorName, defense, healthBonus, armorType, rarity, requiredLevel);
    }

    private Potion generatePotion(int monsterLevel) {
        int healAmount = 20 + (monsterLevel * 5);
        return new Potion("Poção de Cura", healAmount, "HEAL", ItemRarity.COMMON);
    }

    private Potion generateSpecialPotion(int monsterLevel) {
        String[] specialNames = {"Poção do Poder", "Elixir da Força", "Poção Mágica"};
        int healAmount = 50 + (monsterLevel * 10);
        return new Potion(specialNames[random.nextInt(specialNames.length)],
                healAmount, "SPECIAL", ItemRarity.UNCOMMON);
    }

    private ItemRarity calculateRarity(int monsterLevel) {
        double roll = random.nextDouble();

        if (roll < 0.01) return ItemRarity.LEGENDARY;    // 1%
        if (roll < 0.05) return ItemRarity.EPIC;         // 4%
        if (roll < 0.15) return ItemRarity.RARE;         // 10%
        if (roll < 0.40) return ItemRarity.UNCOMMON;     // 25%
        return ItemRarity.COMMON;                        // 60%
    }
}