package com.martin.supercmd.commands;

import com.martin.supercmd.SuperCommands;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Invulnerable implements CommandExecutor {

    private final SuperCommands plugin;

    public Invulnerable(SuperCommands plugin) {
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
            toggleInvulnerability(player);
        } else if(args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target != null) {
                toggleInvulnerability(target);
            } else {
                player.sendMessage("§4Player §6" + args[0] + " §4not found!");
            }
        } else {
            player.sendMessage("§4usage: &6/godmode <player>§4!");
        }
        return true;
    }

    private void toggleInvulnerability(Player player) {
        if (plugin.hasInvulnerablePlayers()) {
            if (plugin.getInvulnerablePlayers().contains(player)) {
                plugin.removeInvulnerablePlayer(player);
                player.sendMessage("§cYou are no longer invulnerable!");
                return;
            }
        }
        plugin.addInvulnerablePlayer(player);
        player.sendMessage("§cYou are now invulnerable!");
    }
}
