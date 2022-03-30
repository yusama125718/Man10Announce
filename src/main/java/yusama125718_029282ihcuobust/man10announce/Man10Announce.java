package yusama125718_029282ihcuobust.man10announce;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Man10Announce extends JavaPlugin {

    JavaPlugin manno;
    public static List<String> commandlist = new ArrayList<>();
    public static List<List<String>> message = new ArrayList<>();
    public static int cooltime;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        this.manno = this;
        commandlist = getConfig().getStringList("command");
        for (String s : commandlist) {
            message.add(getConfig().getStringList(s));
        }
        cooltime = getConfig().getInt("cooltime");
    }

    @Override
    public void onDisable() {}
}
