package yusama125718_029282ihcuobust.man10announce;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Man10Announce extends JavaPlugin {

    static Man10Announce manno;
    public static List<String> commandlist = new ArrayList<>();
    public static List<List<String>> message = new ArrayList<>();
    public static int cooltime;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        manno = this;
        commandlist = manno.getConfig().getStringList("command");
        for (String s : commandlist) {
            message.add(manno.getConfig().getStringList(s));
        }
        cooltime = manno.getConfig().getInt("cooltime");
    }

    @Override
    public void onDisable() {}

    public static void configsave(){
        manno.getConfig().set("command",commandlist);
        manno.getConfig().set("cooltime",cooltime);
        int i = 0;
        for (String s : commandlist) {
            manno.getConfig().set(s,message.get(i));
            i++;
        }
        manno.saveConfig();
    }
}
