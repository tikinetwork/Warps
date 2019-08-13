package dev.foolen.warps.warps.commands;

import dev.foolen.warps.warps.WarpsPlugin;
import dev.foolen.warps.warps.utils.Logger;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

public class Warps implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Logger.info("Only a player can execute this command!");
            return true;
        }

        Player p = (Player) sender;

        if (!p.hasPermission("warps.warps")) {
            p.sendMessage(WarpsPlugin.PREFIX + ChatColor.RED + "You do not have permission to execute this command.");
            return true;
        }

        Configuration config = WarpsPlugin.getInstance().getConfig();

        StringBuilder warps = new StringBuilder();
        config.getConfigurationSection("warps").getKeys(false).forEach(location -> {
            warps.append(location).append(", ");
        });

        if (warps.length() == 0 ) {
            p.sendMessage(WarpsPlugin.PREFIX + "No warps have been set.");
            return true;
        }

        warps.replace(warps.length()-2, warps.length(), ""); //Remove last comma and space

        p.sendMessage(WarpsPlugin.PREFIX + "Available warps: " + ChatColor.GRAY + warps);
        return true;
    }
}
