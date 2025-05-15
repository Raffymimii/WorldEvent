package com.raffymimi.worldEvent.event;

import org.bukkit.plugin.Plugin;

/**
 * Abstract base class for all events.
 * Each event must implement start() and end() methods.
 */
public abstract class BaseEvent {
    protected final Plugin plugin;
    public BaseEvent(Plugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Starts the custom logic for the event (e.g., registering listeners, world changes, etc.).
     */
    public abstract void start();

    /**
     * Restores/closes what was modified by start().
     */
    public abstract void end();

    /**
     * User-friendly name of the event (for chat).
     */
    public abstract String getDisplayName();
}
