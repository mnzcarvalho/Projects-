package eternalidle.model.items.equipment;

import eternalidle.model.items.ItemRarity;

public class Armor extends Equipment {
    private int defense;
    private ArmorType armorType;

    // 🔥 CONSTRUTOR NOVO (com ArmorType)
    public Armor(String name, String description, int value, ItemRarity rarity,
                 int requiredLevel, int defense, ArmorType armorType) {
        super(name, description, value, rarity, requiredLevel);
        this.defense = defense;
        this.armorType = armorType;
    }

    // 🔥 CONSTRUTOR COMPATÍVEL (para código existente)
    public Armor(String name, int defense, int value, String description,
                 ItemRarity rarity, int requiredLevel) {
        super(name, description, value, rarity, requiredLevel);
        this.defense = defense;
        // 🔥 DETERMINAR ArmorType BASEADO NO NOME OU CRIAR UM PADRÃO
        this.armorType = determineArmorTypeFromName(name);
    }

    // 🔥 MÉTODO AUXILIAR: Determinar ArmorType baseado no nome
    private ArmorType determineArmorTypeFromName(String name) {
        String lowerName = name.toLowerCase();

        if (lowerName.contains("helm") || lowerName.contains("cap") || lowerName.contains("crown")) {
            return ArmorType.HELMET;
        } else if (lowerName.contains("chest") || lowerName.contains("plate") || lowerName.contains("armor")) {
            return ArmorType.CHEST;
        } else if (lowerName.contains("glove") || lowerName.contains("hand") || lowerName.contains("gauntlet")) {
            return ArmorType.GLOVES;
        } else if (lowerName.contains("boot") || lowerName.contains("foot") || lowerName.contains("greave")) {
            return ArmorType.BOOTS;
        } else {
            // Padrão: usar CHEST como fallback
            return ArmorType.CHEST;
        }
    }

    public int getDefense() {
        return defense;
    }

    // 🔥 CORREÇÃO: getArmorType() deve retornar ArmorType, não String
    public ArmorType getArmorType() {
        return armorType;
    }


    // 🔥 MÉTODO ADICIONAL: Para compatibilidade com EquipmentManager
    public String getArmorTypeString() {
        return armorType.name(); // Retorna "HELMET", "CHEST", etc.
    }

    @Override
    public String getDisplayName() {
        return armorType.getEmoji() + " " + getName();
    }

    @Override
    public void use() {
        System.out.println("Equipando " + getDisplayName());
    }

    // 🔥 MÉTODO ADICIONAL: Se quiser adicionar bônus de vida
    public int getHealthBonus() {
        // Defina a lógica para bônus de vida baseado na raridade ou tipo
        return switch(getRarity()) {
            case COMMON -> 5;
            case UNCOMMON -> 10;
            case RARE -> 20;
            case EPIC -> 35;
            case LEGENDARY -> 50;
            default -> 0;
        };
    }
}