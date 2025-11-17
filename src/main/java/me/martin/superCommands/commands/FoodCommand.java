package me.martin.superCommands.commands;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class FoodCommand implements BasicCommand {
    @Override
    public void execute(CommandSourceStack source, String[] args) {
        final CommandSender sender = source.getExecutor() != null
                ? source.getExecutor()
                : source.getSender();

        if(args.length == 0 && sender instanceof Player player) {
            if (player.getGameMode () != GameMode.SURVIVAL) {
                player.sendMessage("§4You have to be in survival mode!");
                return;
            }
            fillHunger(player);
            return;
        }

        if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage("§cPlayer §e" + args[0] + " §cnot found!");
                return;
            }
            if (target.getGameMode() != GameMode.SURVIVAL) {
                sender.sendMessage("§6" + target.getName() + " §dis in §6" + target.getGameMode().toString().toLowerCase() + "§d. has to be survival!");
                return;
            }
            fillHunger(target);
            sender.sendMessage("§dYou successfully filled §6" + target.getName() + "§d's hunger");
            return;
        }
        sender.sendMessage("§4usage: §6/food <player>§4!");
    }

    private void fillHunger(Player player) {
        player.setFoodLevel(20);
        player.setSaturation(12.8f);
        player.sendMessage("§eYour hunger has been satisfied!");
    }
}
