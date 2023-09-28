package kiul.smpevents.methods;

import kiul.smpevents.SMPEvent;
import kiul.smpevents.config.TimeData;
import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SpawnCrate {

    public interface MyCallback {
        void call(Entity entity);
    }



    public static Location returnCrateLocation (World world) {
        Random random = new Random();
        int x = random.nextInt(0,7900);
        int z = random.nextInt(0,7900);
        int y = world.getHighestBlockYAt(x,z);
        Location crateSpawnLocation = new Location(world,x,y,z);
        return crateSpawnLocation;}



    public static void createNewCrate (String type, int minutesUntilSpawn, World world) {
        long crateSpawnTime = (System.currentTimeMillis() + minutesUntilSpawn*60*1000);
        long remainingTime = (crateSpawnTime - System.currentTimeMillis());


        new BukkitRunnable() {
            Location crateSpawnLocation = returnCrateLocation(world);
            @Override
            public void run() {
                if (crateSpawnLocation.getBlock().getType() == Material.WATER) {
                    crateSpawnLocation = returnCrateLocation(world);
                } else {
                    int x = (int) crateSpawnLocation.getX();
                    int y = (int) crateSpawnLocation.getY();
                    int z = (int) crateSpawnLocation.getZ();
                    Bukkit.broadcastMessage("");
                    Bukkit.broadcastMessage(type + " crate will drop at " + x + " " + y + " " + z + " in " + minutesUntilSpawn + " minutes.");
                    Bukkit.broadcastMessage("");
                    new BukkitRunnable() {
                        int tick = 1;
                        @Override
                        public void run() {
                            if (System.currentTimeMillis() > crateSpawnTime) {
                                spawnCrate(type,20,world,x,z);
                                cancel();
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
                    cancel();
                }
            }
        } .runTaskTimer(SMPEvent.getPlugin(SMPEvent.class), 0L, 1L);
        System.currentTimeMillis();

    }

    public static void spawnCrate (String type, int unlockMinutes, World world,int x, int z) {
        int y = world.getHighestBlockYAt(x,z);
        Location crateSpawnLocation = new Location(world,x,y,z);
        ArmorStand crate = (ArmorStand) world.spawnEntity(crateSpawnLocation.add(0.5,-0.05,0.5), EntityType.ARMOR_STAND);
        crate.setInvulnerable(true);
        crate.setVisible(false);
        crate.setGravity(false);
        ChatColor color;
        switch (type) {
            case "NETHER":
                crate.setHelmet(new ItemStack(Material.RED_NETHER_BRICKS));
                color = ChatColor.RED;
                break;
            case "END":
                crate.setHelmet(new ItemStack(Material.ENDER_CHEST));
                color = ChatColor.LIGHT_PURPLE;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        crate.setCustomNameVisible(true);
        crate.setMetadata("locked", new FixedMetadataValue(SMPEvent.getPlugin(SMPEvent.class), "uruguay"));
        long unlockTime = System.currentTimeMillis()+(unlockMinutes*60*1000);
        long remainingTime = (unlockTime - System.currentTimeMillis());
        new BukkitRunnable() {
            int tick = 1;
            @Override
            public void run() {
                crate.setCustomName(color + String.format("%02d : %02d",
                        TimeUnit.MILLISECONDS.toMinutes(remainingTime),
                        TimeUnit.MILLISECONDS.toSeconds(remainingTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(remainingTime))
                ));
                if (System.currentTimeMillis() >= unlockTime) {
                    crate.removeMetadata("locked",SMPEvent.getPlugin(SMPEvent.class));
                    crate.setCustomName(color + "CLICK TO OPEN");
                    cancel();
                }
            }
        }.runTaskTimer(SMPEvent.getPlugin(SMPEvent.class), 0L, 20L);


    }
}
