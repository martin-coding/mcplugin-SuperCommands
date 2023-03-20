package com.martin.supercmd;

import com.martin.supercmd.commands.*;
import com.martin.supercmd.handlers.GodmodeHandler;
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
        // Plugin startup logic
        getLogger().info("SuperCommands plugin enabled!");

        registerCommands();
        registerHandlers();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("SuperCommands plugin disabled!");
    }

    void registerCommands() {
        getCommand("fly").setExecutor(new Fly());
        getCommand("heal").setExecutor(new Heal());
        getCommand("food").setExecutor(new Food());
        getCommand("sethealth").setExecutor(new Sethealth());
        getCommand("godmode").setExecutor(new Godmode(this));
        getCommand("immortal").setExecutor(new Immortal(this));
        getCommand("invulnerable").setExecutor(new Invulnerable(this));
    }

    void registerHandlers() {
        new GodmodeHandler(this);
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
