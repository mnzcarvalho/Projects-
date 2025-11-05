package eternalidle.model.items;

public abstract class Item {
    protected String name;
    protected String description;
    protected int value;
    protected ItemRarity rarity;

    public Item(String name, String description, int value, ItemRarity rarity) {
        this.name = name;
        this.description = description;
        this.value = value;
        this.rarity = rarity;
    }
    // 🔥 MÉTODO ADICIONADO: getSellValue()
    public int getSellValue() {
        // Itens vendem por 50% do valor de compra
        return Math.max(1, value / 2);
    }

    // 🔥 MÉTODO ADICIONADO: getSellValue com bônus
    public int getSellValue(double bonusMultiplier) {
        int baseValue = getSellValue();
        return (int) Math.max(1, baseValue * bonusMultiplier);
    }

    // Getters
    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getValue() { return value; }
    public ItemRarity getRarity() { return rarity; }

    public abstract void use();

    public String getDisplayName() {
        return name + " [" + rarity.getDisplayName() + "]"; // 🔥 Sem códigos de cor
    }

    public void displayInfo() {
        System.out.println(getDisplayName());
        System.out.println("  " + description);
        System.out.println("  Valor: " + value + " ouro");
    }
}