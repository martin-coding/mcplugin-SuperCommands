package me.martin.superCommands.commands;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import me.martin.superCommands.SuperCommands;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class ImmortalCommand implements BasicCommand {
    private final SuperCommands plugin;

    public ImmortalCommand(SuperCommands plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSourceStack source, String[] args) {
        final CommandSender sender = source.getExecutor() != null
                ? source.getExecutor()
                : source.getSender();

        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can run this command");
            return;
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
