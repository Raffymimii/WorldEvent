package com.raffymimi.worldEvent.command;

import com.raffymimi.worldEvent.manager.EventManager;
import com.raffymimi.worldEvent.util.MessageUtil;
import com.raffymimi.worldEvent.util.VaultHook;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class WorldEventsCommand implements CommandExecutor {
    private final EventManager eventManager;
    private final MessageUtil messages;
    private final VaultHook vaultHook;

    public WorldEventsCommand(EventManager eventManager, MessageUtil messages, VaultHook vaultHook) {
        this.eventManager = eventManager;
        this.messages = messages;
        this.vaultHook = vaultHook;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // /worldevents start <event>
        if (args.length == 2 && args[0].equalsIgnoreCase("start")) {
            if (!vaultHook.hasPermission(sender, "worldevents.admin")) {
                messages.send(sender, "no_permission");
                return true;
            }
            String eventKey = args[1].toLowerCase();
            boolean found = eventManager.getAllEventKeys().contains(eventKey)
                    && eventManager.getEnabledEventKeys().contains(eventKey);
            if (!found) {
                messages.send(sender, "event_not_found", "{event}", eventKey);
                return true;
            }
            boolean ok = eventManager.startEvent(eventKey, true);
            if (!ok) {
                sender.sendMessage("§cAn event is already running!");
            }
            return true;
        }
        // Simple help output
        sender.sendMessage("§bWorldEvents§7: /worldevents start <event>");
        sender.sendMessage("§7Available events: §e" + String.join(", ", eventManager.getAllEventKeys()));
        return true;
    }
}
