package me.martin.superCommands.listeners;

import me.martin.superCommands.SuperAttributes;
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
            SuperAttributes superAttributes = plugin.superPlayers.get(player.getUniqueId());
            if (superAttributes != null && superAttributes.isInvulnerable()) {
                event.setCancelled(true);
                if (player.getFireTicks() != 0) {
                    player.setFireTicks(0);
                }
            }
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getPlayer();
        SuperAttributes superAttributes = plugin.superPlayers.get(player.getUniqueId());
        if (superAttributes != null && superAttributes.isImmortal()) {
            event.setCancelled(true);
            player.setHealth(1);
        }
    }

    @EventHandler
    public void onHunger(FoodLevelChangeEvent event) {
        if (event.getEntity() instanceof Player player) {
            SuperAttributes superAttributes = plugin.superPlayers.get(player.getUniqueId());
            if (superAttributes != null && superAttributes.isNoHunger()) {
                event.setCancelled(true);
            }
        }
    }
}
