package kiul.smpevents;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command cmd, String label, String[] args) {

        switch (label) {
            case "spawncrate":
                switch (args[0]) {
                    case "nether":

                    case "end":
                }
                break;

        }

        return false;
    }
}
