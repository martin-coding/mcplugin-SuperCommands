package com.martin.supercmd.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Fly implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can run this command");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            toggleFLight(player);
        } else if(args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target != null) {
                toggleFLight(target);
            } else {
                player.sendMessage("§4Player §6" + args[0] + " §4not found!");
            }
        } else {
            player.sendMessage("§4usage: &6/fly <player>§4!");
        }
        return true;
    }

    void toggleFLight(Player player) {
        if (player.getAllowFlight()){
            player.setAllowFlight(false);
            player.sendMessage("§bFlying disabled");
        } else {
            player.setAllowFlight(true);
            player.sendMessage("§bFlying enabled");
        }
    }
}
