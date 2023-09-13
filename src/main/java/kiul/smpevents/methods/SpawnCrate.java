package kiul.smpevents.methods;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.Date;
import java.util.Random;

public class SpawnCrate {

    public void spawnCrate (String type, int minutesUntilSpawn, int unlockMinutes, World world) {
        Random random = new Random();
        int x = random.nextInt(0,7900);
        int z = random.nextInt(0,7900);
        int y = world.getHighestBlockYAt(x,z);
        Location crateSpawnLocation = new Location(world,x,y,z);
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage(type + " crate will drop at " + x + " " + y + " " + z + " in " + minutesUntilSpawn + " minutes.");
        Bukkit.broadcastMessage("");
        System.currentTimeMillis();
    }
}
