package com.raffymimi.worldEvent.util;

import com.raffymimi.worldEvent.WorldEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for reading, formatting and broadcasting plugin messages with placeholders.
 */
public class MessageUtil {
    private final FileConfiguration messages;

    public MessageUtil(WorldEvent plugin) {
        File file = new File(plugin.getDataFolder(), "messages.yml");
        if (!file.exists()) plugin.saveResource("messages.yml", false);
        messages = YamlConfiguration.loadConfiguration(file);
    }

    /**
     * Get the message and replace generic placeholders.
     */
    public String get(String key, String... replacements) {
        String msg = messages.getString(key, "&7Missing message: " + key);
        msg = msg.replace("{prefix}", messages.getString("prefix", "[WorldEvents]"));
        // Custom placeholders
        for (int i = 0; i + 1 < replacements.length; i += 2)
            msg = msg.replace(replacements[i], replacements[i+1]);
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    /**
     * Send to a player/admin
     */
    public void send(CommandSender to, String key, String... replacements) {
        to.sendMessage(get(key, replacements));
    }

    /**
     * Global broadcast
     */
    public void broadcast(String key, String... replacements) {
        Bukkit.broadcastMessage(get(key, replacements));
    }
}
