package kiul.smpevents;

import kiul.smpevents.methods.SpawnCrate;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command cmd, String label, String[] args) {
        Player p = (Player) commandSender;

        switch (label) {
            case "spawncrate":
                if (args[0].equalsIgnoreCase("nether") || args[0].equalsIgnoreCase("end")) {
                    SpawnCrate.createNewCrate(args[0].toUpperCase(), Integer.parseInt(args[1]), p.getWorld());
                } else {
                    p.sendMessage(ChatColor.RED + "please use a valid crate type");
                }


        }

        return false;
    }
}
