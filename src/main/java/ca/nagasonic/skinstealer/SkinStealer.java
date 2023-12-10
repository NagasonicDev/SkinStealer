package ca.nagasonic.skinstealer;

import ca.nagasonic.skinstealer.commands.SkinCommand;
import ca.nagasonic.skinstealer.util.SkinUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Logger;

public final class SkinStealer extends JavaPlugin implements Listener {
    private static SkinStealer instance;
    private static Logger logger;
    private FileConfiguration config = this.getConfig();
    @Override
    public void onEnable() {
        instance = this;
        logger = this.getLogger();
        info("SkinStealer has been enabled");
        saveDefaultConfig();
        config.options().copyDefaults(true);
        saveConfig();
        getServer().getPluginManager().registerEvents(this, this);
        getCommand("skinstealer").setExecutor(new SkinCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static SkinStealer getInstance() {
        return instance;
    }
    public static void info(String message){
        logger.info(message);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (config.getBoolean("save on join")){
            SkinUtils.saveSkin(Bukkit.getConsoleSender(), player);
        }
    }
}
