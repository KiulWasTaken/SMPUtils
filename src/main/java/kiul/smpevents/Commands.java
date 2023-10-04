package kiul.smpevents;

import kiul.smpevents.methods.SpawnCrate;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

import java.util.Random;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command cmd, String label, String[] args) {
        Player p = (Player) commandSender;

        switch (label) {
            case "spawncrate":
                if (p.isOp()) {
                        Random random = new Random();
                        String type;
                        switch (random.nextInt(0,3)) {
                            case 0:
                                type = "END";
                                break;
                            case 1:
                                type = "NETHER";
                                break;
                            case 2:
                                type = "OVERWORLD";
                                break;
                            default:
                                type = "OVERWORLD";
                                break;
                        }
                        SpawnCrate.createNewCrate(type, Integer.parseInt(args[0]), p.getWorld());
                }
                break;


        }

        return false;
    }
}
