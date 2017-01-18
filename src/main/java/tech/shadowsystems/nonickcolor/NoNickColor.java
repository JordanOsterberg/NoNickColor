package tech.shadowsystems.nonickcolor;

import net.ess3.api.events.NickChangeEvent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class NoNickColor extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
        this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();

        if (this.getConfig().getConfigurationSection("errorMessage") == null) {
            this.getConfig().set("errorMessage", "&cYou can only change the color of your nickname.");
            this.saveConfig();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onNickChange(NickChangeEvent event) {
        Player affected = event.getAffected().getBase();
        if (!ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', event.getValue())).toLowerCase().equals(affected.getName().toLowerCase())) {
            event.setCancelled(true);
            affected.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("errorMessage")));
        }
    }

}
