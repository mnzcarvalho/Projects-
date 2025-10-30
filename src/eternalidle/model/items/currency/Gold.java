package eternalidle.model.items.currency;

import eternalidle.model.items.Item;
import eternalidle.model.items.ItemRarity;

public class Gold extends Item {
    private int amount;

    public Gold(int amount) {
        super("Saco de Ouro", "Uma bolsa cheia de moedas brilhantes", amount, ItemRarity.COMMON);
        this.amount = amount;
    }

    @Override
    public void use() {
        System.out.println("💰 Usou " + amount + " de ouro!");
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public void displayInfo() {
        System.out.println(getDisplayName() + " x" + amount);
        System.out.println("  " + description);
    }
}