package com.martin.supercmd.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Heal implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can run this command");
            return true;
        }

        Player player = (Player) sender;

        if(args.length == 0) {
            if(player.getGameMode () == GameMode.SURVIVAL) {
                player.setHealth(20);
                player.setFoodLevel(20);
                player.sendMessage("§eYou've been healed!");
            }else {
                player.sendMessage("§4You have to be in survival mode!");
            }
        } else if(args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if(target != null) {
                if(target.getGameMode () == GameMode.SURVIVAL) {
                    target.setHealth(20);
                    target.setFoodLevel(20);
                    target.sendMessage("§eSomeone healed you!");
                    player.sendMessage("§dYou successfully healed §6" +target.getName());
                }else
                    player.sendMessage("§6"+target.getName() + " §dis in §6" + target.getGameMode().toString().toLowerCase() + "§d. Has to be survival!");
            } else
                player.sendMessage("§4Player §6" + args[0] + " §4not found!");
        } else
            player.sendMessage("§4usage: §6/heal <player>§4!");
        return true;
    }
}
