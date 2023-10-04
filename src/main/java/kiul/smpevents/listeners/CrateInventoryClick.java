package kiul.smpevents.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.net.http.WebSocket;

public class CrateInventoryClick implements Listener {

    @EventHandler
    public void crateInventoryClick (InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equalsIgnoreCase("crate")) {
            if (e.getAction().equals(InventoryAction.MOVE_TO_OTHER_INVENTORY)) {
                CrateInteract.playersWhoGotLoot.add(p.getDisplayName());
            }
        }
    }
}
