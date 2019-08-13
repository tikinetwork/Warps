package dev.foolen.warps.warps;

import dev.foolen.warps.warps.commands.Delwarp;
import dev.foolen.warps.warps.commands.Setwarp;
import dev.foolen.warps.warps.commands.Warp;
import dev.foolen.warps.warps.commands.Warps;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class WarpsPlugin extends JavaPlugin {

    private static WarpsPlugin instance;

    public static String PREFIX;

    @Override
    public void onEnable() {
        instance = this;

        // Save default config and set PREFIX
        saveDefaultConfig();
        PREFIX = ChatColor.translateAlternateColorCodes('&', getConfig().getString("prefix"));

        // Register commands
        registerCommands();
    }

    public static WarpsPlugin getInstance() {
        return instance;
    }

    private void registerCommands() {
        getCommand("setwarp").setExecutor(new Setwarp());
        getCommand("delwarp").setExecutor(new Delwarp());
        getCommand("warps").setExecutor(new Warps());
        getCommand("warp").setExecutor(new Warp());
    }
}
