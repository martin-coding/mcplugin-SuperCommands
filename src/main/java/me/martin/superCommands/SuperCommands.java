package me.martin.superCommands;

import me.martin.superCommands.commands.*;
import me.martin.superCommands.listeners.GodmodeListener;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class SuperCommands extends JavaPlugin {

    private final List<Player> GodPlayers = new ArrayList<>();
    private final List<Player> ImmortalPlayers = new ArrayList<>();
    private final List<Player> InvulnerablePlayers = new ArrayList<>();

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
    }

    // add
    public void addGodPlayer(Player player) {
        GodPlayers.add(player);
        ImmortalPlayers.remove(player);
        InvulnerablePlayers.remove(player);
    }
    public void addImmortalPlayer(Player player) {
        GodPlayers.remove(player);
        ImmortalPlayers.add(player);
        InvulnerablePlayers.remove(player);
    }
    public void addInvulnerablePlayer(Player player) {
        GodPlayers.remove(player);
        ImmortalPlayers.remove(player);
        InvulnerablePlayers.add(player);
    }

    // remove
    public void removeGodPlayer(Player player) {
        GodPlayers.remove(player);
    }
    public void removeImmortalPlayer(Player player) {
        ImmortalPlayers.remove(player);
    }
    public void removeInvulnerablePlayer(Player player) {
        InvulnerablePlayers.remove(player);
    }

    // get list
    public List<Player> getGodPlayers() {
        return GodPlayers;
    }
    public List<Player> getImmortalPlayers() {
        return ImmortalPlayers;
    }
    public List<Player> getInvulnerablePlayers() {
        return InvulnerablePlayers;
    }

    // check list
    public boolean hasGodPlayers() {
        return !GodPlayers.isEmpty();
    }
    public boolean hasImmortalPlayers() {
        return !ImmortalPlayers.isEmpty();
    }
    public boolean hasInvulnerablePlayers() {
        return !InvulnerablePlayers.isEmpty();
    }
}
