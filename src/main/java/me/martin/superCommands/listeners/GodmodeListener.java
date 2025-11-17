package me.martin.superCommands.listeners;

import me.martin.superCommands.SuperCommands;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class GodmodeListener implements Listener {
    private final SuperCommands plugin;

    public GodmodeListener(SuperCommands plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player player) {
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
        if (event.getEntity() instanceof Player player) {
            if (plugin.getGodPlayers().contains(player)) {
                event.setCancelled(true);
            }
        }
    }
}
