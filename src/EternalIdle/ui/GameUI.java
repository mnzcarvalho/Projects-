package EternalIdle.ui;

import EternalIdle.main.Player;
import EternalIdle.main.Monster;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameUI extends JFrame {

    private final Player player;
    private Monster currentMonster;
    private JLabel lblPlayerHP, lblPlayerXP, lblGold;
    private JLabel lblMonsterName, lblMonsterHP;
    private JTextArea logArea;
    private int monsterIndex = 0;

    private static final Color GOLD = new Color(255, 170, 0);

    public GameUI(Player player) {
        this.player = player;
        this.currentMonster = Monster.generateMonster(player.getLevel());
        setupUI();
        setupIdleCombat();
    }

    private void setupUI() {
        setTitle("⚔️ Eternal Idle RPG");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.BLACK);

        // Monstro
        lblMonsterName = makeLabel(currentMonster.getName(), 60, 40, 200, 30);
        lblMonsterHP = makeLabel("HP: 100", 60, 70, 200, 30);

        // Player
        lblPlayerHP = makeLabel("HP: 100", 60, 400, 200, 30);
        lblPlayerXP = makeLabel("XP: 0 / " + player.getNextLevelExp(), 60, 430, 200, 30);
        lblGold = makeLabel("Gold: 999", 700, 30, 150, 30);

        // Log
        logArea = new JTextArea();
        logArea.setBounds(300, 100, 550, 350);
        logArea.setEditable(false);
        logArea.setBackground(Color.BLACK);
        logArea.setForeground(GOLD);
        logArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        add(logArea);

        // Botões
        JButton btnNext = makeButton("próximo monstro", 400, 470);
        btnNext.addActionListener(e -> changeMonster(1));

        JButton btnPrev = makeButton("monstro anterior", 600, 470);
        btnPrev.addActionListener(e -> changeMonster(-1));

        JButton btnShop = makeSideButton("shop", 800, 100);
        JButton btnInventory = makeSideButton("inventory", 800, 150);
        JButton btnStatus = makeSideButton("status", 800, 200);

        add(lblMonsterName);
        add(lblMonsterHP);
        add(lblPlayerHP);
        add(lblPlayerXP);
        add(lblGold);
        add(btnNext);
        add(btnPrev);
        add(btnShop);
        add(btnInventory);
        add(btnStatus);

        setVisible(true);
    }

    private void setupIdleCombat() {
        new Thread(() -> {
            while (true) {
                try {
                    // combate automático em loop
                    fightMonster();
                    Thread.sleep(1500); // tempo entre um monstro e outro
                } catch (InterruptedException e) {
                    return;
                }
            }
        }).start();
    }

    private void fightMonster() {
        int monsterHp = 50 + currentMonster.getLevel() * 10;
        int playerDmg = 5 + player.getLevel() * 2;

        log("🐉 Enfrentando " + currentMonster.getName() + " (HP: " + monsterHp + ")");
        lblMonsterName.setText(currentMonster.getName());
        lblMonsterHP.setText("HP: " + monsterHp);

        while (monsterHp > 0) {
            monsterHp -= playerDmg;
            int dmg = Math.max(playerDmg, 1);
            lblMonsterHP.setText("HP: " + Math.max(monsterHp, 0));
            log("⚔️ Causou " + dmg + " de dano");

            try {
                Thread.sleep(300); // intervalo entre golpes
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // monstro derrotado
        int xpGain = currentMonster.getXp();
        player.addExperience(xpGain);
        log("💀 Derrotou " + currentMonster.getName() + " (+ " + xpGain + " XP)");

        updateStats();
        currentMonster = Monster.generateMonster(player.getLevel());
    }

    private void attackMonster() {
        int damage = 10 + player.getLevel() * 2;
        int hp = Math.max(0, currentMonster.getXp() - damage);
        if (hp == 0) {
            int xpGain = currentMonster.getXp();
            player.addExperience(xpGain);
            log("⚔️ Derrotou " + currentMonster.getName() + " (+ " + xpGain + " XP)");
            currentMonster = Monster.generateMonster(player.getLevel());
            lblMonsterName.setText(currentMonster.getName());
        } else {
            lblMonsterHP.setText("HP: " + hp);
        }
        updateStats();
    }

    private void changeMonster(int direction) {
        monsterIndex = Math.max(0, monsterIndex + direction);
        currentMonster = Monster.generateMonster(player.getLevel());
        lblMonsterName.setText(currentMonster.getName());
        lblMonsterHP.setText("HP: " + currentMonster.getXp());
        log("➡️ Novo monstro: " + currentMonster.getName());
    }

    private void updateStats() {
        lblPlayerXP.setText("XP: " + player.getExperience() + " / " + player.getNextLevelExp());
        lblPlayerHP.setText("HP: " + (100 + player.getLevel() * 10));
    }

    private JLabel makeLabel(String text, int x, int y, int w, int h) {
        JLabel lbl = new JLabel(text);
        lbl.setBounds(x, y, w, h);
        lbl.setForeground(GOLD);
        lbl.setFont(new Font("Consolas", Font.BOLD, 16));
        return lbl;
    }

    private JButton makeButton(String text, int x, int y) {
        JButton btn = new JButton(text);
        btn.setBounds(x, y, 160, 30);
        styleButton(btn);
        return btn;
    }

    private JButton makeSideButton(String text, int x, int y) {
        JButton btn = new JButton(text);
        btn.setBounds(x, y, 90, 30);
        styleButton(btn);
        return btn;
    }

    private void styleButton(JButton btn) {
        btn.setBackground(Color.BLACK);
        btn.setForeground(GOLD);
        btn.setFont(new Font("Consolas", Font.PLAIN, 14));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(GOLD));
    }

    private void log(String msg) {
        logArea.append(msg + "\n");
        logArea.setCaretPosition(logArea.getDocument().getLength());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameUI(new Player("Hero")));
    }
}
