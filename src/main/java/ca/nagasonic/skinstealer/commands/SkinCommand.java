package ca.nagasonic.skinstealer.commands;

import ca.nagasonic.skinstealer.SkinStealer;
import ca.nagasonic.skinstealer.util.SkinUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import static ca.nagasonic.skinstealer.util.Utils.color;

public class SkinCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args[0].equals("reload")){
            if (sender.hasPermission(SkinStealer.getInstance().getConfig().getString("permissions.reload permission")))
                sender.sendMessage(color("&7[&aSkin&9Stealer&7] &eReloading config..."));
                SkinStealer.getInstance().reloadConfig();
                sender.sendMessage(color("&7[&aSkin&9Stealer&7] &aReloaded config!"));
        }else if (args[0].equals("download") || args[0].equals("save")){
            if (args.length == 2){
                if (sender.hasPermission(SkinStealer.getInstance().getConfig().getString("permissions.steal permission"))){
                    Player player = Bukkit.getPlayer(args[1]);
                    if (player != null){
                        SkinUtils.saveSkin(sender, player);
                    }else sender.sendMessage(color("&7[&aSkin&9Stealer&7] &cCouldn't find a player named " + ChatColor.RED + args[0] + "&c, make sure that the player has joined the server."));
                }
            }else sender.sendMessage(color("77[&aSkin&9Stealer&7] &cNot enough arguments to run this command"));
        }
        return true;
    }
}
