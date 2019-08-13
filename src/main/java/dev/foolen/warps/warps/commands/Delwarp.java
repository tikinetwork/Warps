package dev.foolen.warps.warps.commands;

import dev.foolen.warps.warps.WarpsPlugin;
import dev.foolen.warps.warps.utils.Logger;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

public class Delwarp implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Logger.info("Only a player can execute this command!");
            return true;
        }

        Player p = (Player) sender;

        if (!p.hasPermission("warps.delwarp")) {
            p.sendMessage(WarpsPlugin.PREFIX + ChatColor.RED + "You do not have permission to execute this command.");
            return true;
        }

        if (args.length == 0) {
            p.sendMessage(WarpsPlugin.PREFIX + "Please specify a name.");
            return true;
        }

        WarpsPlugin plugin = WarpsPlugin.getInstance();
        Configuration config = plugin.getConfig();

        config.set("warps." + args[0], null);

        plugin.saveConfig();

        p.sendMessage(WarpsPlugin.PREFIX + "Warp " + ChatColor.GRAY + args[0] + ChatColor.RESET + " has been removed!");
        return true;
    }
}
