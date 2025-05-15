package com.raffymimi.worldEvent.event;

import com.raffymimi.worldEvent.WorldEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.Random;

/**
 * Event: Treasure Hunt. Spawns a special chest and announces its position!
 */
public class TreasureHuntEvent extends BaseEvent {
    private Location chestLocation;

    public TreasureHuntEvent(Plugin plugin) { super(plugin); }

    @Override
    public void start() {
        World w = Bukkit.getWorlds().get(0); // First world
        Random r = new Random();
        double x = w.getSpawnLocation().getX() + (r.nextDouble() - 0.5) * 200;
        double z = w.getSpawnLocation().getZ() + (r.nextDouble() - 0.5) * 200;
        double y = w.getHighestBlockYAt((int)x, (int)z) + 1;
        chestLocation = new Location(w, x, y, z);

        Block block = w.getBlockAt(chestLocation);
        block.setType(Material.CHEST);

        Inventory inv = ((org.bukkit.block.Chest) block.getState()).getBlockInventory();
        inv.addItem(new ItemStack(Material.EMERALD, 16 + r.nextInt(24)));
        inv.addItem(new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 1));
        inv.addItem(new ItemStack(Material.NETHERITE_INGOT, 1));

        // Announce to all players
        String coords = (int)x + ", " + (int)y + ", " + (int)z;
        WorldEvent.getInstance().getMessages().broadcast("treasure_announce", "{coords}", coords);
    }

    @Override
    public void end() {
        // Nothing explicit to clean up
    }

    @Override
    public String getDisplayName() {
        return "Treasure Hunt";
    }
}
