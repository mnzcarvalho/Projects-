package eternalidle.model.bosses;

import eternalidle.model.entity.Monster;
import eternalidle.model.rewards.BossReward;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Boss extends Monster {
    private BossType bossType;
    private List<String> abilities;
    private int phase;
    private boolean isInCombat;
    private BossReward reward;
    private long spawnTime;
    private long timeLimit; // tempo limite para derrotar (ms)

    public Boss(BossType bossType) {
        super(bossType.getName(),
                bossType.getLevel(),
                String.valueOf(bossType.getHealth()),
                String.valueOf(bossType.getDamage()),
                bossType.getAttackSpeed(),
                0); // Bosses não spawnam, são invocados

        this.bossType = bossType;
        this.abilities = new ArrayList<>();
        this.phase = 1;
        this.isInCombat = false;
        this.timeLimit = 300000; // 5 minutos por padrão
        initializeBoss();
    }

    private void initializeBoss() {
        // Adicionar habilidades baseadas no tipo de boss
        switch (bossType) {
            case ANCIENT_DRAGON:
                abilities.add("🔥 Sopro de Fogo - Dano em área");
                abilities.add("🌪️ Vendaval - Reduz velocidade de ataque");
                abilities.add("💥 Rugido Atordoante - Atordoa o jogador");
                break;
            case LICH_KING:
                abilities.add("💀 Drenar Vida - Rouba vida do jogador");
                abilities.add("☠️ Névoa Venenosa - Dano contínuo");
                abilities.add("⚰️ Invocar Esqueletos - Aliados menores");
                break;
            case TITAN_STONE:
                abilities.add("🗿 Armadura de Pedra - Aumenta defesa");
                abilities.add("💥 Terremoto - Dano massivo");
                abilities.add("🛡️ Reflexo - Reflete parte do dano");
                break;
            case PHOENIX:
                abilities.add("🔥 Renascer - Revive com 50% de vida");
                abilities.add("✨ Plumas Flamejantes - Dano múltiplo");
                abilities.add("🌅 Canto Solar - Cura própria");
                break;
        }

        // Criar recompensa do boss
        this.reward = new BossReward(this);
    }

    public void startCombat() {
        this.isInCombat = true;
        this.spawnTime = System.currentTimeMillis();
        System.out.println("⚔️ 🚨 " + bossType.getEmoji() + " " + getName() + " entrou em combate! 🚨");
        System.out.println("   " + bossType.getDescription());
        displayAbilities();
    }

    public void useAbility() {
        if (abilities.isEmpty() || !isInCombat) return;

        String ability = abilities.get((int)(Math.random() * abilities.size()));
        System.out.println("💢 " + getName() + " usa: " + ability);

        // Efeitos especiais baseados na habilidade
        if (ability.contains("Renascer") && getHealth().compareTo(BigDecimal.valueOf(getMaxHealth().doubleValue() * 0.2)) < 0) {
            System.out.println("✨ " + getName() + " RENASCEU com 50% de vida!");
            // Lógica de renascimento seria implementada aqui
        }
    }

    public void checkPhaseChange() {
        double healthPercent = getHealth().doubleValue() / getMaxHealth().doubleValue();

        if (phase == 1 && healthPercent <= 0.5) {
            phase = 2;
            System.out.println("🚨 " + getName() + " entrou na FASE 2! Poder aumentado!");
        } else if (phase == 2 && healthPercent <= 0.2) {
            phase = 3;
            System.out.println("💀 " + getName() + " entrou na FASE 3! DESESPERADO!");
        }
    }

    public boolean isTimeUp() {
        return isInCombat && (System.currentTimeMillis() - spawnTime) > timeLimit;
    }

    public void displayAbilities() {
        System.out.println("📖 Habilidades do Boss:");
        for (String ability : abilities) {
            System.out.println("   • " + ability);
        }
    }

    public void displayBossInfo() {
        System.out.println("\n" + bossType.getEmoji() + " === " + getName().toUpperCase() + " ===");
        System.out.println("Nível: " + getLevel());
        System.out.println("Fase: " + phase + "/3");
        System.out.println("Vida: " + getHealth() + "/" + getMaxHealth());
        System.out.println("Dano: " + getDamage());
        System.out.println("Descrição: " + bossType.getDescription());
        displayAbilities();
        System.out.println("============================\n");
    }

    // Getters
    public BossType getBossType() { return bossType; }
    public List<String> getAbilities() { return abilities; }
    public int getPhase() { return phase; }
    public boolean isInCombat() { return isInCombat; }
    public BossReward getReward() { return reward; }
    public long getTimeLimit() { return timeLimit; }
    public long getRemainingTime() {
        return isInCombat ? timeLimit - (System.currentTimeMillis() - spawnTime) : 0;
    }
}