package com.raffymimi.worldEvent.util;

import net.milkbowl.vault.permission.Permission;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.Plugin;

public class VaultHook {
    private final Plugin plugin;
    private Permission perms;

    public VaultHook(Plugin plugin) {
        this.plugin = plugin;
    }

    public void hook() {
        if (plugin.getServer().getPluginManager().getPlugin("Vault") == null)
            return;
        RegisteredServiceProvider<Permission> rsp = plugin.getServer().getServicesManager()
                .getRegistration(Permission.class);
        perms = (rsp != null) ? rsp.getProvider() : null;
    }

    public void unhook() {
        perms = null;
    }

    /**
     * Check permission. Fallback to op if Vault is unavailable.
     */
    public boolean hasPermission(CommandSender sender, String perm) {
        if (perms != null && sender instanceof Player)
            return perms.has((Player) sender, perm);
        return sender.isOp();
    }
}
