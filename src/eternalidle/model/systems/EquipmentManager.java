package eternalidle.model.systems;

import eternalidle.model.items.equipment.Equipment;
import eternalidle.model.items.equipment.Weapon;
import eternalidle.model.items.equipment.Armor;
import java.util.HashMap;
import java.util.Map;

public class EquipmentManager {
    private Map<String, Equipment> equippedItems;
    private Weapon currentWeapon;

    public EquipmentManager() {
        this.equippedItems = new HashMap<>();
        this.currentWeapon = null;
    }

    // 🔥 NO EquipmentManager, ATUALIZE O MÉTODO equip:
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
            // 🔥 CORREÇÃO: Usar getArmorTypeString() em vez de getArmorType()
            equippedItems.put(armor.getArmorTypeString(), equipment);
        }

        equipment.setEquipped(true);
        System.out.println("✅ " + equipment.getName() + " equipado!");
        return true;
    }

    // 🔥 TAMBÉM ATUALIZE O MÉTODO unequip:
    public void unequip(Equipment equipment) {
        if (equipment instanceof Weapon) {
            equippedItems.remove("WEAPON");
            currentWeapon = null;
        } else if (equipment instanceof Armor) {
            Armor armor = (Armor) equipment;
            // 🔥 CORREÇÃO: Usar getArmorTypeString()
            equippedItems.remove(armor.getArmorTypeString());
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
                // 🔥 CORREÇÃO: Se não tem getHealthBonus(), usar valor padrão
                totalHealth += 0; // Ou adicione um campo healthBonus na classe Armor
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

    // 🔥 ADICIONE ESTE MÉTODO AO EquipmentManager.java
    public boolean unequipWeapon() {
        if (currentWeapon != null) {
            System.out.println("🗡️ Desequipando arma: " + currentWeapon.getName());
            currentWeapon = null;
            return true;
        }
        return false;
    }

    // 🔥 ADICIONE ESTES MÉTODOS AO SEU EquipmentManager.java
    public Armor getCurrentHelmet() {
        return (Armor) equippedItems.get("HELMET");
    }

    public Armor getCurrentChest() {
        return (Armor) equippedItems.get("CHEST");
    }

    public Armor getCurrentGloves() {
        return (Armor) equippedItems.get("GLOVES");
    }

    public Armor getCurrentBoots() {
        return (Armor) equippedItems.get("BOOTS");
    }

    public boolean hasHelmet() {
        return equippedItems.containsKey("HELMET");
    }

    public boolean hasChest() {
        return equippedItems.containsKey("CHEST");
    }

    public boolean hasGloves() {
        return equippedItems.containsKey("GLOVES");
    }

    public boolean hasBoots() {
        return equippedItems.containsKey("BOOTS");
    }

    // 🔥 MÉTODOS ESPECÍFICOS PARA DESEQUIPAR
    public boolean unequipHelmet() {
        if (hasHelmet()) {
            Equipment helmet = equippedItems.remove("HELMET");
            helmet.setEquipped(false);
            return true;
        }
        return false;
    }

    public boolean unequipChest() {
        if (hasChest()) {
            Equipment chest = equippedItems.remove("CHEST");
            chest.setEquipped(false);
            return true;
        }
        return false;
    }

    public boolean unequipGloves() {
        if (hasGloves()) {
            Equipment gloves = equippedItems.remove("GLOVES");
            gloves.setEquipped(false);
            return true;
        }
        return false;
    }

    public boolean unequipBoots() {
        if (hasBoots()) {
            Equipment boots = equippedItems.remove("BOOTS");
            boots.setEquipped(false);
            return true;
        }
        return false;
    }
}