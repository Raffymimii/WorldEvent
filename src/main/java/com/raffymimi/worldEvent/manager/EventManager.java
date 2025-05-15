package com.raffymimi.worldEvent.manager;

import com.raffymimi.worldEvent.WorldEvent;
import com.raffymimi.worldEvent.event.BaseEvent;
import com.raffymimi.worldEvent.event.MagicStormEvent;
import com.raffymimi.worldEvent.event.EternalNightEvent;
import com.raffymimi.worldEvent.event.LootRainEvent;
import com.raffymimi.worldEvent.event.TreasureHuntEvent;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * Manages the event cycle and executes a random enabled/configured event.
 */
public class EventManager {
    private final Plugin plugin;
    private final Map<String, BaseEvent> eventMap = new LinkedHashMap<>();
    private BukkitRunnable cycleTask;
    private BukkitRunnable eventEndTask;
    private BaseEvent currentEvent;

    public EventManager(Plugin plugin) {
        this.plugin = plugin;
        registerEvents();
    }

    private void registerEvents() {
        // Register events with keys (used in config, command, messages)
        eventMap.put("magic_storm", new MagicStormEvent(plugin));
        eventMap.put("eternal_night", new EternalNightEvent(plugin));
        eventMap.put("loot_rain", new LootRainEvent(plugin));
        eventMap.put("treasure_hunt", new TreasureHuntEvent(plugin));
    }

    public Set<String> getEnabledEventKeys() {
        Set<String> enabledEvents = plugin.getConfig().getConfigurationSection("enabled-events")
                .getKeys(false);
        return enabledEvents.stream()
                .filter(e -> plugin.getConfig().getBoolean("enabled-events." + e, false))
                .collect(Collectors.toSet());
    }

    public BaseEvent getEventByKey(String key) {
        return eventMap.get(key);
    }

    public BaseEvent getCurrentEvent() {
        return currentEvent;
    }

    public boolean isRunningEvent() {
        return currentEvent != null;
    }

    /**
     * Starts the periodic random event cycle.
     */
    public void startCycle() {
        int interval = plugin.getConfig().getInt("event-interval-minutes", 10);
        cycleTask = new BukkitRunnable() {
            @Override
            public void run() {
                if (isRunningEvent()) return;
                List<String> available = new ArrayList<>(getEnabledEventKeys());
                if (available.isEmpty()) return;
                String key = available.get(ThreadLocalRandom.current().nextInt(available.size()));
                startEvent(key, false);
            }
        };
        cycleTask.runTaskTimer(plugin, interval * 60 * 20L, interval * 60 * 20L);
    }

    /**
     * Stops the periodic event cycle.
     */
    public void stopCycle() {
        if (cycleTask != null) cycleTask.cancel();
        if (eventEndTask != null) eventEndTask.cancel();
        if (currentEvent != null) currentEvent.end();
        currentEvent = null;
    }

    /**
     * Start a specific event by key, optionally as forced by admin.
     */
    public boolean startEvent(String key, boolean forced) {
        if (isRunningEvent()) return false;
        BaseEvent event = eventMap.get(key);
        if (event == null) return false;
        if (!getEnabledEventKeys().contains(key)) return false;
        currentEvent = event;
        currentEvent.start();
        // Announce in chat
        String msgType = forced ? "event_forced" : "event_start";
        WorldEvent.getInstance().getMessages().broadcast(msgType,
                "{event}", event.getDisplayName());
        // Schedule end of event task
        int duration = plugin.getConfig().getInt("event-duration-minutes", 3);
        eventEndTask = new BukkitRunnable() {
            @Override
            public void run() {
                endCurrentEvent();
            }
        };
        eventEndTask.runTaskLater(plugin, duration * 60 * 20L);
        return true;
    }

    /**
     * Ends the current event and announces its conclusion.
     */
    public void endCurrentEvent() {
        if (currentEvent != null) {
            currentEvent.end();
            WorldEvent.getInstance().getMessages().broadcast("event_end",
                    "{event}", currentEvent.getDisplayName());
            currentEvent = null;
        }
        if (eventEndTask != null) eventEndTask.cancel();
    }

    /**
     * Returns a list of all available event keys.
     */
    public Set<String> getAllEventKeys() {
        return eventMap.keySet();
    }
}
