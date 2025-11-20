package me.martin.superCommands.commands;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import me.martin.superCommands.SuperAttributes;
import me.martin.superCommands.SuperCommands;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NohungerCommand implements BasicCommand {
    private final SuperCommands plugin;

    public NohungerCommand(SuperCommands plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSourceStack source, String[] args) {
        final CommandSender sender = source.getExecutor() != null
                ? source.getExecutor()
                : source.getSender();

        // Toggle no hunger for player type senders without arguments
        if (args.length == 0 && sender instanceof Player player) {
            toggleNohunger(player);
            return;
        }

        if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage("§4Player §6" + args[0] + " §4not found!");
                return;
            }
            toggleNohunger(target);
            return;
        }
        sender.sendMessage("§4usage: &6/nohunger <player>§4!");
    }

    private void toggleNohunger(Player player) {
        if (!plugin.superPlayers.containsKey(player.getUniqueId())) {
            plugin.superPlayers.put(player.getUniqueId(), new SuperAttributes());
        }
        SuperAttributes superAttributes = plugin.superPlayers.get(player.getUniqueId());
        if (superAttributes.isNoHunger()) {
            superAttributes.setNoHunger(false);
            player.sendMessage("§cHunger enabled!");
        } else {
            superAttributes.setNoHunger(true);
            player.sendMessage("§cHunger disabled!");
        }
    }
}
