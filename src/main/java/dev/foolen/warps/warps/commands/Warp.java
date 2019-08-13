package dev.foolen.warps.warps.commands;

import dev.foolen.warps.warps.WarpsPlugin;
import dev.foolen.warps.warps.utils.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

public class Warp implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Logger.info("Only a player can execute this command!");
            return true;
        }

        Player p = (Player) sender;

        if (!p.hasPermission("warps.warp")) {
            p.sendMessage(WarpsPlugin.PREFIX + ChatColor.RED + "You do not have permission to execute this command.");
            return true;
        }

        if (args.length == 0) {
            p.sendMessage(WarpsPlugin.PREFIX + "Please specify a name.");
            return true;
        }

        p.sendMessage(WarpsPlugin.PREFIX + "Warping...");

        Configuration config = WarpsPlugin.getInstance().getConfig();

        Location loc = p.getLocation();

        loc.setX(config.getDouble("warps." + args[0] + ".x"));
        loc.setY(config.getDouble("warps." + args[0] + ".y"));
        loc.setZ(config.getDouble("warps." + args[0] + ".z"));
        loc.setYaw(config.getLong("warps." + args[0] + ".yaw"));
        loc.setPitch(config.getLong("warps." + args[0] + ".pitch"));
        loc.setWorld(Bukkit.getWorld(config.getString("warps." + args[0] + ".world")));

        p.teleport(loc);
        return true;
    }
}
