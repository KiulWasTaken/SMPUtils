package kiul.smpevents.methods;

import kiul.smpevents.SMPEvent;
import kiul.smpevents.config.TimeData;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Date;
import java.util.Random;

public class SpawnCrate {



    public void createNewCrate (String type, int minutesUntilSpawn, World world) {
        if (TimeData.get().get("crate.number") == null) {
            TimeData.get().set("crate.number", 0);
        }
        int crateNumber = TimeData.get().getInt("crate.number");
        long crateSpawnTime = (System.currentTimeMillis() + minutesUntilSpawn*60*1000);
        long remainingTime = (crateSpawnTime - System.currentTimeMillis());
        Random random = new Random();

        int x = random.nextInt(0,7900);
        int z = random.nextInt(0,7900);
        int y = world.getHighestBlockYAt(x,z);
        Location crateSpawnLocation = new Location(world,x,y,z);
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage(type + " crate will drop at " + x + " " + y + " " + z + " in " + minutesUntilSpawn + " minutes.");
        Bukkit.broadcastMessage("");
        System.currentTimeMillis();
        new BukkitRunnable() {
            int tick = 1;
            @Override
            public void run() {
                if (System.currentTimeMillis() > crateSpawnTime) {
                    spawnCrate(type,20,world,crateSpawnLocation);
                }
                    tick++;
                if (tick >= (((remainingTime/4)/1000)/60)) {
                    Bukkit.broadcastMessage("");
                    Bukkit.broadcastMessage(type + " drop will arrive at " + x + " " + y + " " + z + " in " + (((crateSpawnTime - System.currentTimeMillis())/1000)/60) + " minutes.");
                    Bukkit.broadcastMessage("");
                    tick = 1;
                }



            }
        }.runTaskTimer(SMPEvent.getPlugin(SMPEvent.class), 0L, 1200L);
    }

    public void spawnCrate (String type, int unlockMinutes, World world,Location spawnlocation) {

    }
}
