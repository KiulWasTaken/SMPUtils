package kiul.smpevents;

import kiul.smpevents.listeners.CrateInteract;
import org.bukkit.plugin.java.JavaPlugin;

public final class SMPEvent extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new CrateInteract(),this);
        getServer().getPluginCommand("spawncrate").setExecutor(new Commands());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
