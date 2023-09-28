package kiul.smpevents.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;


public class ChunkLoad implements Listener {

    @EventHandler
    public void spawnArmorStandOnChunkLoad (ChunkLoadEvent e) {
        e.getChunk();
    }

    @EventHandler
    public void deleteArmorStandOnChunkUnload (ChunkUnloadEvent e) {

    }
}
