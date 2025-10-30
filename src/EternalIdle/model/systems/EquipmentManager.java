package EternalIdle.model.systems;

import EternalIdle.model.items.equipment.Equipment;
import EternalIdle.model.items.equipment.Weapon;
import EternalIdle.model.items.equipment.Armor;
import java.util.HashMap;
import java.util.Map;

public class EquipmentManager {
    private Map<String, Equipment> equippedItems;
    private Weapon currentWeapon;

    public EquipmentManager() {
        this.equippedItems = new HashMap<>();
        this.currentWeapon = null;
    }

    public boolean equip(Equipment equipment, int playerLevel) {
        if (!equipment.canEquip(playerLevel)) {
            System.out.println("❌ Nível muito baixo para equipar " + equipment.getName() +
                    " (Requer nível " + equipment.getRequiredLevel() + ")");
            return false;
        }

        // Se já tem algo equipado no slot, desequipa primeiro
        if (equipment instanceof Weapon) {
            if (currentWeapon != null) {
                unequip(currentWeapon);
            }
            currentWeapon = (Weapon) equipment;
            equippedItems.put("WEAPON", equipment);
        } else if (equipment instanceof Armor) {
            Armor armor = (Armor) equipment;
            equippedItems.put(armor.getArmorType(), equipment);
        }

        equipment.setEquipped(true);
        System.out.println("✅ " + equipment.getName() + " equipado!");
        return true;
    }

    public void unequip(Equipment equipment) {
        if (equipment instanceof Weapon) {
            equippedItems.remove("WEAPON");
            currentWeapon = null;
        } else if (equipment instanceof Armor) {
            Armor armor = (Armor) equipment;
            equippedItems.remove(armor.getArmorType());
        }

        equipment.setEquipped(false);
        System.out.println("📦 " + equipment.getName() + " desequipado!");
    }

    public int getTotalDefense() {
        int totalDefense = 0;
        for (Equipment equipment : equippedItems.values()) {
            if (equipment instanceof Armor) {
                totalDefense += ((Armor) equipment).getDefense();
            }
        }
        return totalDefense;
    }

    public int getTotalHealthBonus() {
        int totalHealth = 0;
        for (Equipment equipment : equippedItems.values()) {
            if (equipment instanceof Armor) {
                totalHealth += ((Armor) equipment).getHealthBonus();
            }
        }
        return totalHealth;
    }

    public double getWeaponDPS() {
        return currentWeapon != null ? currentWeapon.getDPS() : 5.0; // Dano base sem arma
    }

    public int getWeaponDamage() {
        return currentWeapon != null ? currentWeapon.getDamage() : 5;
    }

    public void displayEquippedItems() {
        System.out.println("\n=== EQUIPAMENTO ===");

        if (equippedItems.isEmpty()) {
            System.out.println("  Nenhum item equipado");
        } else {
            for (Map.Entry<String, Equipment> entry : equippedItems.entrySet()) {
                System.out.println("  " + getSlotEmoji(entry.getKey()) + " " +
                        entry.getValue().getDisplayName());
            }
        }

        System.out.println("Bônus totais:");
        System.out.println("  🛡️ Defesa: " + getTotalDefense());
        System.out.println("  ❤️ Vida: +" + getTotalHealthBonus());
        System.out.println("  ⚔️ Dano da Arma: " + getWeaponDamage() +
                " (DPS: " + String.format("%.1f", getWeaponDPS()) + ")");
        System.out.println("==================\n");
    }

    private String getSlotEmoji(String slot) {
        return switch(slot) {
            case "WEAPON" -> "⚔️";
            case "HELMET" -> "⛑️";
            case "CHEST" -> "👕";
            case "BOOTS" -> "👢";
            default -> "📦";
        };
    }

    // Getters
    public Weapon getCurrentWeapon() { return currentWeapon; }
    public boolean hasWeapon() { return currentWeapon != null; }
}