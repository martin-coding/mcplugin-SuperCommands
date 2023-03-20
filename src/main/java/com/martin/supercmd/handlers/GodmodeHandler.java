package com.martin.supercmd.handlers;

import com.martin.supercmd.SuperCommands;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class GodmodeHandler implements Listener {

    private final SuperCommands plugin;

    public GodmodeHandler(SuperCommands plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
    }
    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player) {
            Player player = (Player) entity;
            if (plugin.getGodPlayers().contains(player) || plugin.getInvulnerablePlayers().contains(player)) {
                event.setCancelled(true);
                if (plugin.getGodPlayers().contains(player) && player.getFireTicks() != 0) {
                    player.setFireTicks(0);
                }
            }
        }
    }
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getPlayer();
        if (plugin.getGodPlayers().contains(player) || plugin.getImmortalPlayers().contains(player)) {
            event.setCancelled(true);
            if (plugin.getImmortalPlayers().contains(player)) {
                player.setHealth(1);
            }
        }
    }
    @EventHandler
    public void onHunger(FoodLevelChangeEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player) {
            Player player = (Player) entity;
            if (plugin.getGodPlayers().contains(player) || plugin.getInvulnerablePlayers().contains(player)) {
                event.setCancelled(true);
            }
        }
    }
}
