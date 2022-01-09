package hyro.lib.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Message {
    public static void consoleSend(String msg) {
        msg = ChatColor.translateAlternateColorCodes('&', msg);
        Bukkit.getServer().getConsoleSender().sendMessage(msg);
    }

    public static void sendToPlayer(Player p, String msg) {
        String fmsg = ChatColor.translateAlternateColorCodes('&', msg);
        p.sendMessage(fmsg);
    }
}
