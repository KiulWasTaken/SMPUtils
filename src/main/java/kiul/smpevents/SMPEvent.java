package kiul.smpevents;

import kiul.smpevents.listeners.CrateInteract;
import kiul.smpevents.listeners.CrateInventoryClick;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class SMPEvent extends JavaPlugin {

    public static String pluginMessagePrefix = ChatColor.translateAlternateColorCodes('&',"&f[&c&lEVENT&f] &7» ");

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new CrateInteract(),this);
        getServer().getPluginManager().registerEvents(new CrateInventoryClick(),this);
        getServer().getPluginCommand("spawncrate").setExecutor(new Commands());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
