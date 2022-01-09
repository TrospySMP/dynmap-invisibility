package hyro.lib;

import hyro.lib.utils.Message;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin implements Listener {
    public static List<Player> list = new ArrayList<Player>();
    @Override
    public void onEnable() {
        Message.consoleSend("&8----------------------");
        Message.consoleSend("&dInvisfix");
        Message.consoleSend("&aLoaded");
        Message.consoleSend("&8----------------------");

        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for (Player p : list) {
                    if (!p.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
                        list.remove(p);
                        getServer().dispatchCommand(getServer().getConsoleSender(), "dynmap show "+p.getName());
                    }
                }
            }
        }, 0L, 100L);

        getServer().getPluginManager().registerEvents(this, this);
    }

    public void onDisable() {
        Message.consoleSend("&8----------------------");
        Message.consoleSend("&dInvisfix");
        Message.consoleSend("&cUnloaded");
        Message.consoleSend("&8----------------------");
    }

    @EventHandler
    public void invis(PlayerItemConsumeEvent event) {
        if (event.getItem().getType() == Material.POTION && event.getItem().hasItemMeta()) {
            if (event.getItem().getItemMeta() instanceof PotionMeta) {
                final PotionMeta meta = (PotionMeta) event.getItem().getItemMeta();
                final PotionData data = meta.getBasePotionData();
                if(data.getType() == PotionType.INVISIBILITY) {
                    getServer().dispatchCommand(getServer().getConsoleSender(), "dynmap hide "+event.getPlayer().getName());
                    list.add(event.getPlayer());
                }
            }
        }
    }
}
