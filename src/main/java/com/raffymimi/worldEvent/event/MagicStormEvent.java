package com.raffymimi.worldEvent.event;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Event: Magic Storm. Random lightning, constant rain, stronger mobs.
 */
public class MagicStormEvent extends BaseEvent implements Listener {
    private final Set<UUID> buffedMobs = new HashSet<>();
    private BukkitRunnable lightningTask;
    private boolean isActive = false;
    
    // Usare valueOf per compatibilità con ogni versione/mapping
    private static final Attribute GENERIC_MAX_HEALTH = Attribute.valueOf("GENERIC_MAX_HEALTH");
    private static final Attribute GENERIC_ATTACK_DAMAGE = Attribute.valueOf("GENERIC_ATTACK_DAMAGE");

    public MagicStormEvent(Plugin plugin) {
        super(plugin);
    }

    @Override
    public void start() {
        if (isActive) return;
        isActive = true;
        
        // Start thunderstorm in all worlds
        for (World w : Bukkit.getWorlds()) {
            w.setStorm(true);
            w.setThundering(true);
        }
        // Periodic lightning strikes
        lightningTask = new BukkitRunnable() {
            @Override
            public void run() {
                for (World w : Bukkit.getWorlds()) {
                    int times = 4 + (int) (Math.random() * 6); // 4-10 strikes
                    for (int i = 0; i < times; i++) {
                        double x = w.getSpawnLocation().getX() + (Math.random() - 0.5) * 200;
                        double z = w.getSpawnLocation().getZ() + (Math.random() - 0.5) * 200;
                        double y = w.getHighestBlockYAt((int)x, (int)z);
                        w.strikeLightningEffect(new org.bukkit.Location(w, x, y, z));
                    }
                }
            }
        };
        lightningTask.runTaskTimer(plugin, 0L, 40L); // every 2 seconds

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public void end() {
        if (!isActive) return;
        isActive = false;
        
        for (World w : Bukkit.getWorlds()) {
            w.setStorm(false);
            w.setThundering(false);
        }
        // Clear buffed mobs
        buffedMobs.clear();
        if (lightningTask != null) lightningTask.cancel();
        HandlerList.unregisterAll(this); // Properly unregister all handlers
    }

    @Override
    public String getDisplayName() {
        return "Magic Storm";
    }

    // Buff mobs when they spawn
    @EventHandler
    public void onMobSpawn(CreatureSpawnEvent e) {
        if (!isActive) return;
        if (e.getEntity().getWorld().isThundering()) {
            LivingEntity mob = e.getEntity();
            UUID mobId = mob.getUniqueId();
            
            // Prevent double buffing
            if (buffedMobs.contains(mobId)) return;
            
            // Safe attribute handling
            AttributeInstance healthAttr = mob.getAttribute(GENERIC_MAX_HEALTH);
            AttributeInstance dmgAttr = mob.getAttribute(GENERIC_ATTACK_DAMAGE);
            
            if (healthAttr != null) {
                double newHealth = healthAttr.getBaseValue() * 1.8;
                healthAttr.setBaseValue(newHealth);
                mob.setHealth(newHealth);
            }
            
            if (dmgAttr != null) {
                dmgAttr.setBaseValue(dmgAttr.getBaseValue() * 1.6);
            }
            
            mob.setGlowing(true);
            buffedMobs.add(mobId);
        }
    }
}
