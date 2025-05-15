package com.raffymimi.worldEvent;

import com.raffymimi.worldEvent.manager.EventManager;
import com.raffymimi.worldEvent.command.WorldEventsCommand;
import com.raffymimi.worldEvent.util.MessageUtil;
import com.raffymimi.worldEvent.util.VaultHook;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main plugin class for WorldEvents.
 * Handles initialization, shutdown, and provides access to core components.
 */
public final class WorldEvent extends JavaPlugin {
    private static WorldEvent instance;
    private EventManager eventManager;
    private MessageUtil messages;
    private VaultHook vaultHook;

    /**
     * Returns the singleton instance of the plugin.
     * 
     * @return The plugin instance
     */
    public static WorldEvent getInstance() {
        return instance;
    }

    /**
     * Called when the plugin is enabled.
     * Initializes configuration, hooks, and starts the event cycle.
     */
    @Override
    public void onEnable() {
        instance = this;
        // Save default configuration and messages
        saveDefaultConfig();
        saveResource("messages.yml", false);

        // Load messages and Vault permissions hook
        messages = new MessageUtil(this);
        vaultHook = new VaultHook(this);
        vaultHook.hook();

        // Start event manager
        eventManager = new EventManager(this);
        eventManager.startCycle();

        // Register command
        getCommand("worldevents").setExecutor(
            new WorldEventsCommand(eventManager, messages, vaultHook)
        );

        getLogger().info("WorldEvents enabled!");
    }

    /**
     * Called when the plugin is disabled.
     * Stops the event cycle and unhooks from Vault.
     */
    @Override
    public void onDisable() {
        eventManager.stopCycle();
        vaultHook.unhook();
        getLogger().info("WorldEvents disabled!");
    }

    /**
     * Gets the event manager instance.
     * 
     * @return The event manager
     */
    public EventManager getEventManager() {
        return eventManager;
    }

    /**
     * Gets the message utility instance.
     * 
     * @return The message utility
     */
    public MessageUtil getMessages() {
        return messages;
    }
    
    /**
     * Gets the Vault hook instance.
     * 
     * @return The Vault hook
     */
    public VaultHook getVaultHook() {
        return vaultHook;
    }
}
