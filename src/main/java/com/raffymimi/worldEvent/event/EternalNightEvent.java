package com.raffymimi.worldEvent.event;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.TimeSkipEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.plugin.Plugin;

import java.util.Random;

/**
 * Event: Eternal Night. Freezes the day/night cycle to night.
 */
public class EternalNightEvent extends BaseEvent implements Listener {
    private BukkitRunnable forceNightTask;

    public EternalNightEvent(Plugin plugin) {
        super(plugin);
    }

    @Override
    public void start() {
        for (World w : Bukkit.getWorlds()) {
            w.setTime(14000);
        }
        // Prevents skipping the night
        Bukkit.getPluginManager().registerEvents(this, plugin);
        // Forces night every 10 seconds
        forceNightTask = new BukkitRunnable() {
            @Override
            public void run() {
                for (World w : Bukkit.getWorlds()) w.setTime(14000);
                spawnRareMobs();
            }
        };
        forceNightTask.runTaskTimer(plugin, 0L, 200L);
    }

    @Override
    public void end() {
        if (forceNightTask != null) forceNightTask.cancel();
        TimeSkipEvent.getHandlerList().unregister(this);
        // Optional: allow normal cycle to resume (some servers may set AlwaysDay/AlwaysNight elsewhere)
    }

    @Override
    public String getDisplayName() {
        return "Eternal Night";
    }

    @EventHandler
    public void onTimeSkip(TimeSkipEvent e) {
        e.setCancelled(true); // Prevents day skipping
    }

    // Spawns rare mobs at night
    private void spawnRareMobs() {
        for (World w : Bukkit.getWorlds()) {
            Random r = new Random();
            if (r.nextDouble() < 0.25) {
                EntityType[] rareTypes = {EntityType.CREEPER, EntityType.ENDERMAN, EntityType.WITCH, EntityType.SKELETON_HORSE};
                EntityType mob = rareTypes[r.nextInt(rareTypes.length)];
                int radius = 120;
                double x = w.getSpawnLocation().getX() + (r.nextDouble() - 0.5) * radius;
                double z = w.getSpawnLocation().getZ() + (r.nextDouble() - 0.5) * radius;
                double y = w.getHighestBlockYAt((int) x, (int) z);
                w.spawnEntity(new org.bukkit.Location(w, x, y, z), mob);
            }
        }
    }
}
