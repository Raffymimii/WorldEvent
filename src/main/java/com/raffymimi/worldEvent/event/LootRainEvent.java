package com.raffymimi.worldEvent.event;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

/**
 * Event: Loot Rain. Drops random loot chests from the sky.
 */
public class LootRainEvent extends BaseEvent implements Listener {
    private BukkitRunnable lootRainTask;

    public LootRainEvent(Plugin plugin) {
        super(plugin);
    }

    @Override
    public void start() {
        lootRainTask = new BukkitRunnable() {
            @Override
            public void run() {
                for (World w : Bukkit.getWorlds()) {
                    dropRandomChest(w);
                }
            }
        };
        lootRainTask.runTaskTimer(plugin, 0L, 100L); // every 5s
    }

    @Override
    public void end() {
        if (lootRainTask != null) lootRainTask.cancel();
    }

    @Override
    public String getDisplayName() {
        return "Loot Rain";
    }

    private void dropRandomChest(World w) {
        Random r = new Random();
        double x = w.getSpawnLocation().getX() + (r.nextDouble() - 0.5) * 160;
        double z = w.getSpawnLocation().getZ() + (r.nextDouble() - 0.5) * 160;
        double y = w.getHighestBlockYAt((int) x, (int) z) + 32; // well above ground

        Block block = w.getBlockAt((int) x, (int) y, (int) z);
        block.setType(Material.CHEST);

        Inventory inv = ((org.bukkit.block.Chest) block.getState()).getBlockInventory();
        inv.addItem(new ItemStack(Material.DIAMOND, r.nextInt(2) + 1));
        inv.addItem(new ItemStack(Material.GOLDEN_APPLE, r.nextInt(3) + 1));
        inv.addItem(new ItemStack(Material.EXPERIENCE_BOTTLE, 32 + r.nextInt(64)));

        // Global notification
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage("§eDid you see a loot chest falling from the sky?");
        }
    }
}
