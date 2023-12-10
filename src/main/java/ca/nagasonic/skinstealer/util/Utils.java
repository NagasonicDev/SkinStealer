package ca.nagasonic.skinstealer.util;

import net.md_5.bungee.api.ChatColor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    private static final Pattern HEX_PATTERN = Pattern.compile("#[a-fA-F0-9]{6}");

    public static Date getDate(){
        Date date = new Date();
        return date;
    }
    public static String getDateAsString(Date date){
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
        return format.format(date);
    }
    public static String color(String msg) {
        Matcher match = HEX_PATTERN.matcher(msg);
        while (match.find()){
            String color = msg.substring(match.start(), match.end());
            msg = msg.replace(color, ChatColor.of(color) + "");
            match = HEX_PATTERN.matcher(msg);
        }
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
