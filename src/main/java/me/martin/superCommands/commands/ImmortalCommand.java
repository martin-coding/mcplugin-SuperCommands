package me.martin.superCommands.commands;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import me.martin.superCommands.SuperAttributes;
import me.martin.superCommands.SuperCommands;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NullMarked;

import java.util.Collection;

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

        // Toggle immortality for player type senders without arguments
        if (args.length == 0 && sender instanceof Player player) {
            toggleImmortality(player);
            return;
        }

        if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage("§4Player §6" + args[0] + " §4not found!");
                return;
            }
            toggleImmortality(target);
            return;
        }
        sender.sendMessage("§4usage: &6/godmode <player>§4!");
    }

    private void toggleImmortality(Player player) {
        if (!plugin.superPlayers.containsKey(player.getUniqueId())) {
            plugin.superPlayers.put(player.getUniqueId(), new SuperAttributes());
        }
        SuperAttributes superAttributes = plugin.superPlayers.get(player.getUniqueId());
        if (superAttributes.isImmortal()) {
            superAttributes.setImmortal(false);
            player.sendMessage("§cYou are no longer immortal!");
        } else {
            superAttributes.setImmortal(true);
            player.sendMessage("§cYou are now immortal!");
        }
    }

    @Override
    public Collection<String> suggest(CommandSourceStack source, String[] args) {
        if (args.length == 0) {
            return Bukkit.getOnlinePlayers().stream().map(Player::getName).toList();
        }

        return Bukkit.getOnlinePlayers().stream()
                .map(Player::getName)
                .filter(name -> name.toLowerCase().startsWith(args[args.length - 1].toLowerCase()))
                .toList();
    }
}
