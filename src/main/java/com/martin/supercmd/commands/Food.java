package com.martin.supercmd.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Food implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can run this command");
            return true;
        }

        Player player = (Player) sender;

        if(args.length == 0) {
            if(player.getGameMode () == GameMode.SURVIVAL) {
                player.setFoodLevel(20);
                player.sendMessage("§eYour hunger has been satisfied!");
            }else {
                player.sendMessage("§4You have to be in survival mode!");
            }
        } else if(args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if(target != null) {
                if(target.getGameMode () == GameMode.SURVIVAL) {
                    target.setHealth(20);
                    target.setFoodLevel(20);
                    target.sendMessage("§eSomeone satisfied your hunger!");
                    player.sendMessage("§dYou successfully filled §6" +target.getName() + "§d's hunger");
                }else
                    player.sendMessage("§6"+target.getName() + " §dis in §6" + target.getGameMode().toString().toLowerCase() + "§d. has to be survival!");
            } else
                player.sendMessage("§4Player §6" + args[0] + " §4not found!");
        } else
            player.sendMessage("§4usage: §6/food <player>§4!");
        return true;
    }
}
