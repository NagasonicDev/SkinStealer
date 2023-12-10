package ca.nagasonic.skinstealer.util;

import ca.nagasonic.skinstealer.SkinStealer;
import org.apache.commons.io.FileUtils;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.profile.PlayerProfile;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static ca.nagasonic.skinstealer.util.Utils.*;

public class SkinUtils {
    public static void saveSkin(CommandSender sender, Player player){
        PlayerProfile profile = player.getPlayerProfile();
        URL url = profile.getTextures().getSkin();
        File file = new File(SkinStealer.getInstance().getDataFolder().getPath() + "/skins/" + player.getName() + "/", "skins_" + player.getName() + "_" + getDateAsString(getDate()).replaceAll(" ", "") + SkinStealer.getInstance().getConfig().getString("image type"));
        if (!file.exists()){
            file.mkdirs();
        }
        try {
            FileUtils.copyURLToFile(url, file);
            sender.sendMessage(color("&7[&aSkin&9Stealer&7] &eAttempting to download skin..."));
        } catch (IOException ex) {
            sender.sendMessage(color("&7[&aSkin&9Stealer&7] &cFailed to download skin."));
            throw new RuntimeException(ex);
        }
        sender.sendMessage(color("&7[&aSkin&9Stealer&7] &aSuccessfully downloaded skin! Check out the skin in " + file.getPath()));
    }
}
