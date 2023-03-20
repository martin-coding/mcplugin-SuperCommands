package com.martin.supercmd.commands;

import com.martin.supercmd.SuperCommands;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Godmode implements CommandExecutor {

    private final SuperCommands plugin;

    public Godmode(SuperCommands plugin) {
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
            toggleGodmode(player);
        } else if(args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target != null) {
                toggleGodmode(target);
            } else {
                player.sendMessage("§4Player §6" + args[0] + " §4not found!");
            }
        } else {
            player.sendMessage("§4usage: &6/godmode <player>§4!");
        }
        return true;
    }

    private void toggleGodmode(Player player) {
        if (plugin.hasGodPlayers()) {
            if (plugin.getGodPlayers().contains(player)) {
                plugin.removeGodPlayer(player);
                player.sendMessage("§cYou are no longer in God mode!");
                return;
            }
        }
        plugin.addGodPlayer(player);
        player.sendMessage("§cYou are now in God mode!");
    }
}
