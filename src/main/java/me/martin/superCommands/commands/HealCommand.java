package me.martin.superCommands.commands;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class HealCommand implements BasicCommand {

    @Override
    public void execute(CommandSourceStack source, String[] args) {
        final CommandSender sender = source.getExecutor() != null
                ? source.getExecutor()
                : source.getSender();

        if (args.length == 0 && sender instanceof Player player) {
            if (player.getGameMode () != GameMode.SURVIVAL) {
                player.sendMessage("§4You have to be in survival mode!");
                return;
            }
            heal(player);
            return;
        }

        if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage("§4Player §6" + args[0] + " §4not found!");
                return;
            }
            if (target.getGameMode () != GameMode.SURVIVAL) {
                sender.sendMessage("§6"+target.getName() + " §dis in §6" + target.getGameMode().toString().toLowerCase() + "§d. Has to be survival!");
                return;
            }
            heal(target);
            sender.sendMessage("§dYou successfully healed §6" +target.getName());
            return;
        }
        sender.sendMessage("§cusage: /heal <player>");
    }

    private void heal(Player player) {
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setSaturation(12.8f);
        player.sendMessage("§eYou've been healed!");
    }
}
