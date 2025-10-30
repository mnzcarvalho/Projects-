package EternalIdle.model.items;

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