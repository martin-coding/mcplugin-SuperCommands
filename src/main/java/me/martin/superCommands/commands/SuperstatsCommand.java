package me.martin.superCommands.commands;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import me.martin.superCommands.SuperAttributes;
import me.martin.superCommands.SuperCommands;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NullMarked;

import java.util.Collection;

@NullMarked
public class SuperstatsCommand implements BasicCommand {
    private final SuperCommands plugin;

    public SuperstatsCommand(SuperCommands plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSourceStack source, String[] args) {
        final CommandSender sender = source.getExecutor() != null
                ? source.getExecutor()
                : source.getSender();

        if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);

            if (target == null) {
                sender.sendMessage("§4Player §6" + args[0] + " §4not found!");
                return;
            }

            SuperAttributes superAttributes = plugin.superPlayers.getOrDefault(target.getUniqueId(), new SuperAttributes());
            final Component statsMessage = MiniMessage.miniMessage().deserialize(
                    "<rainbow><bold>SUPER STATS</rainbow> of <gold><name></gold> <light_purple> \n Immortal: <immortal> \n Invulnerable: <invulnerable> \n No hunger: <nohunger>",
                    Placeholder.unparsed("name", target.getName()),
                    Placeholder.unparsed("immortal", String.valueOf(superAttributes.isImmortal())),
                    Placeholder.unparsed("invulnerable", String.valueOf(superAttributes.isInvulnerable())),
                    Placeholder.unparsed("nohunger", String.valueOf(superAttributes.isNoHunger()))
            );

            sender.sendMessage(statsMessage);
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
