package eternalidle.model.entity;

import eternalidle.model.inventory.Inventory;
import eternalidle.model.statistics.PlayerStatistics;
import eternalidle.model.systems.EquipmentManager;
import eternalidle.model.items.equipment.Equipment;
import eternalidle.model.skills.SkillTree;
import eternalidle.model.status.PlayerAttributes;

public class Player {
    private String name;
    private int level;
    private long experience;
    private long gold;
    private EquipmentManager equipmentManager;
    private SkillTree skillTree;
    private PlayerAttributes attributes;
    private Inventory playerInventory; // 🔥 ADICIONE ESTA VARIÁVEL


    public Player(String name) {
        this.name = name;
        this.level = 1;
        this.experience = 0;
        this.gold = 0;
        this.equipmentManager = new EquipmentManager();
        this.skillTree = new SkillTree();
        this.attributes = new PlayerAttributes();
        this.playerInventory = new Inventory(20); // 🔥 CERTIFIQUE-SE QUE EXISTE ESTA LINHA
    }

    // 🔥 MÉTODOS GETTERS BÁSICOS
    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public long getExperience() {
        return experience;
    }

    public long getGold() {
        return gold;
    }

    // 🔥 MÉTODOS PARA JAVAFX - NOVOS!
    public int getExpToNextLevel() {
        return level * 100;
    }

    public long getCurrentExp() {
        return experience;
    }

    public double getExpProgress() {
        return (double) experience / getExpToNextLevel();
    }

    // 🔥 MÉTODOS PARA ADICIONAR RECURSOS
    public void addExperience(long exp) {
        this.experience += exp;
        checkLevelUp();
    }

    public void addGold(long gold) {
        this.gold += gold;
        if (this.gold < 0) {
            this.gold = 0;
        }
    }

    private void checkLevelUp() {
        long expNeeded = level * 100L;
        if (experience >= expNeeded) {
            levelUp();
        }
    }

    private void levelUp() {
        this.level++;
        this.experience = 0;

        if (skillTree != null) {
            skillTree.addSkillPoints(1);
        }

        System.out.println("🎉 " + name + " subiu para o nível " + level + "!");
        System.out.println("✨ Ganhou 1 ponto de habilidade!");
    }

    // 🔥 MÉTODOS DE EQUIPAMENTO
    public EquipmentManager getEquipmentManager() {
        return equipmentManager;
    }

    // 🔥 MÉTODO CORRIGIDO PARA EQUIPAR ITENS
    public boolean equipItem(Equipment equipment) {
        // Verifica se o item está no inventário
        if (playerInventory.getItems().contains(equipment)) {
            boolean equipped = equipmentManager.equip(equipment, level);
            if (equipped) {
                // 🔥 REMOVE O ITEM DO INVENTÁRIO APÓS EQUIPAR
                playerInventory.removeItem(equipment);
                System.out.println("✅ " + equipment.getName() + " equipado e removido do inventário!");
                return true;
            }
        } else {
            System.out.println("❌ Item não encontrado no inventário: " + equipment.getName());
        }
        return false;
    }

    public void displayEquipment() {
        System.out.println("\n⚔️ === EQUIPAMENTO ===");
        equipmentManager.displayEquippedItems();
        System.out.println("======================\n");
    }

    public int getTotalDefense() {
        return equipmentManager.getTotalDefense();
    }

    public double getWeaponDPS() {
        return equipmentManager.getWeaponDPS();
    }

    public int getWeaponDamage() {
        int baseDamage = equipmentManager.getWeaponDamage();
        int strengthBonus = attributes.getStrength() * 2;
        return baseDamage + strengthBonus;
    }

    // 🔥 MÉTODOS DE SKILL TREE
    public SkillTree getSkillTree() {
        return skillTree;
    }

    public PlayerAttributes getAttributes() {
        return attributes;
    }

    public void displaySkills() {
        System.out.println("\n🌳 === HABILIDADES ===");
        skillTree.displaySkillTree();
        attributes.displayAttributes();
        System.out.println("=====================\n");
    }

    public void displayStatus() {
        System.out.println("\n📊 === STATUS ===");
        System.out.println("Nome: " + name);
        System.out.println("Nível: " + level);
        System.out.println("Experiência: " + experience + "/" + (level * 100));
        System.out.println("Ouro: " + gold);
        System.out.println("🛡️ Defesa: " + getTotalDefense());
        System.out.println("⚔️ Dano: " + getWeaponDamage() + " (DPS: " + String.format("%.1f", getWeaponDPS()) + ")");
        System.out.println("🌳 Pontos de Habilidade: " + skillTree.getAvailableSkillPoints());
        System.out.println("=================\n");
    }

    // 🔥 ADICIONE ESTE MÉTODO AO Player.java
    public Inventory getInventory() {
        return playerInventory;
    }

    public void setInventory(Inventory playerInventory) {
        this.playerInventory = playerInventory;
    }
    // 🔥 ADICIONE ESTE MÉTODO AO Player.java (no final da classe)
    public PlayerStatistics getStatistics() {
        // Por enquanto, vamos retornar null ou criar uma instância se necessário
        // Você precisará integrar o sistema de estatísticas ao Player
        return null;
    }
}