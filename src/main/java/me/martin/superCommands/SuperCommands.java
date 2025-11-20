package me.martin.superCommands;

import me.martin.superCommands.commands.*;
import me.martin.superCommands.listeners.GodmodeListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public final class SuperCommands extends JavaPlugin {

    public Map<UUID, SuperAttributes> superPlayers = new HashMap<>();

    @Override
    public void onEnable() {
        getLogger().info("SuperCommands plugin enabled!");
        getServer().getPluginManager().registerEvents(new GodmodeListener(this), this);
        registerCommands();
    }

    @Override
    public void onDisable() {
        getLogger().info("SuperCommands plugin disabled!");
    }

    void registerCommands() {
        registerCommand("fly", new FlyCommand());
        registerCommand("food", new FoodCommand());
        registerCommand("godmode", new GodmodeCommand(this));
        registerCommand("heal", new HealCommand());
        registerCommand("immortal", new ImmortalCommand(this));
        registerCommand("invulnerable", new InvulnerableCommand(this));
        registerCommand("superstats", new SuperstatsCommand(this));
        registerCommand("nohunger", new NohungerCommand(this));
    }
}
