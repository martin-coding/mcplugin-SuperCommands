package com.martin.supercmd.commands;

import com.martin.supercmd.SuperCommands;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Immortal implements CommandExecutor {

    private final SuperCommands plugin;

    public Immortal(SuperCommands plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can run this command");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            toggleImmortality(player);
        } else if(args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target != null) {
                toggleImmortality(target);
            } else {
                player.sendMessage("§4Player §6" + args[0] + " §4not found!");
            }
        } else {
            player.sendMessage("§4usage: &6/godmode <player>§4!");
        }
        return true;
    }

    private void toggleImmortality(Player player) {
        if (plugin.hasImmortalPlayers()) {
            if (plugin.getImmortalPlayers().contains(player)) {
                plugin.removeImmortalPlayer(player);
                player.sendMessage("§cYou are no longer immortal!");
                return;
            }
        }
        plugin.addImmortalPlayer(player);
        player.sendMessage("§cYou are now immortal!");
    }
}
