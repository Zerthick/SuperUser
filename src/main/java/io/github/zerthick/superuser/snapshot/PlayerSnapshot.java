package io.github.zerthick.superuser.snapshot;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


public class PlayerSnapshot {

    private final Player player;
    private final ItemStack[] inventory;
    private final ItemStack[] armor;
    private final int level;
    private final float exp;
    private final double health;
    private final float saturation;
    private final Location location;

    public PlayerSnapshot(Player player) {
        this.player = player;

        inventory = copyInventory(player.getInventory().getContents());
        armor = copyInventory(player.getInventory().getArmorContents());
        level = player.getLevel();
        exp = player.getExp();
        health = player.getHealth();
        saturation = player.getSaturation();
        location = player.getLocation();
    }

    public void restore() {
        player.getInventory().setContents(inventory);
        player.getInventory().setArmorContents(armor);
        player.setLevel(level);
        player.setExp(exp);
        player.setHealth(health);
        player.setSaturation(saturation);
        player.teleport(location);
    }

    public ItemStack[] copyInventory(ItemStack[] inventory) {
        ItemStack[] copy = new ItemStack[inventory.length];
        for (int i = 0; i < inventory.length; i++) {
            copy[i] = new ItemStack(inventory[i]);
        }
        return copy;
    }
}
