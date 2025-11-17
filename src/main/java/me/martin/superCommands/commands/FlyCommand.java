package me.martin.superCommands.commands;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NullMarked;

import java.util.Arrays;
import java.util.Collection;

@NullMarked
public class FlyCommand implements BasicCommand {
    @Override
    public void execute(CommandSourceStack source, String[] args) {
        final CommandSender sender = source.getExecutor() != null
                ? source.getExecutor()
                : source.getSender();

        // Set flight for player specified in argument
        if (args.length == 2) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage("§cPlayer §e" + args[0] + " §cnot found!");
                return;
            }
            if (args[1].equals("true")) {
                setFlight(target, sender, true);
            } else if (args[1].equals("false")) {
                setFlight(target, sender, false);
            } else {
                sender.sendMessage("§cInvalid argument");
            }
            return;
        }

        // Toggle flight for player type senders without arguments
        if (args.length == 0 && sender instanceof Player target) {
            toggleFlight(target);
            return;
        }

        sender.sendMessage("§cusage: /fly <player> <boolean>");
    }

    void toggleFlight(Player player) {
        if (player.getAllowFlight()){
            player.setAllowFlight(false);
            player.sendMessage("§bFlying disabled");
        } else {
            player.setAllowFlight(true);
            player.sendMessage("§bFlying enabled");
        }
    }

    void setFlight(Player target, CommandSender sender, boolean allow) {
        target.setAllowFlight(allow);
        if (allow) {
            sender.sendMessage("Flying §aenabled§r for §e" + target.getName());
            target.sendMessage("§bFlying enabled");
        } else {
            sender.sendMessage("Flying §cdisabled§r for §e" + target.getName());
            target.sendMessage("§bFlying disabled");
        }
    }

    @Override
    public Collection<String> suggest(CommandSourceStack source, String[] args) {
        if (args.length == 0) {
            return Bukkit.getOnlinePlayers().stream().map(Player::getName).toList();
        }

        if (args.length == 1) {
            return Bukkit.getOnlinePlayers().stream()
                    .map(Player::getName)
                    .filter(name -> name.toLowerCase().startsWith(args[args.length - 1].toLowerCase()))
                    .toList();
        }

        if (args.length == 2) {
            return Arrays.asList("true", "false");
        }

        return java.util.List.of();
    }
}
