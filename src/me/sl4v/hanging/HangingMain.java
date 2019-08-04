package me.sl4v.hanging;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerLeashEntityEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class HangingMain extends JavaPlugin implements Listener
{
    @Override
    public void onEnable()
    {
        getLogger().info("Hanging plugin has been enabled");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onEntityHang(PlayerLeashEntityEvent event)
    {
        Player playerThatHangedEntity = event.getPlayer();
        LivingEntity hangedEntity = (LivingEntity) event.getEntity();
        Location hangedLocation = hangedEntity.getLocation();
        Location highestBlockAtLocation = hangedLocation.getWorld().getHighestBlockAt(hangedLocation).getLocation();
        boolean isAnimalHanging = hangedLocation.getY() - highestBlockAtLocation.getY() >= 1;
        if (isAnimalHanging)
        {
            hangedEntity.setHealth(0);

            Bukkit.broadcastMessage(
                    ChatColor.YELLOW
                            + playerThatHangedEntity.getDisplayName()
                            + ChatColor.AQUA
                            + " Has hanged a "
                            + hangedEntity.getName()
            );
        }
    }
}
