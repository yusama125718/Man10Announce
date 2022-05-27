package yusama125718_029282ihcuobust.man10announce;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import static yusama125718_029282ihcuobust.man10announce.Man10Announce.*;

public class Command implements CommandExecutor {
    static boolean ct = false;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, org.bukkit.command.@NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.hasPermission("mano.player")){
            sender.sendMessage("§c[Man10Announce]You don't have Permission!");
            return true;
        }
        for (int i = 0;i < commandlist.size();i++){
            if (command.getLabel().equals(commandlist.get(i))){
                if (ct){
                    sender.sendMessage("[§bMan10Announce§r]&r現在クールタイム中です");
                    return true;
                }
                ct = true;
                for (String s : message.get(i)){
                    for (Player player:Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(s);
                    }
                }
                try {
                    Thread.sleep(cooltime * 1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ct = false;
                return true;
            }
        }
        if (command.getLabel().equals("mannounce")){
            if (!sender.hasPermission("mano.op")){
                sender.sendMessage("§c[Man10Announce]You don't have Permission!");
                return true;
            }
            if (args.length == 0){
                sender.sendMessage("[§b&lMan10Announce§r]&r/mannounce list : 現在登録されてるコマンドのリストを表示します。");
                sender.sendMessage("[§b&lMan10Announce§r]&r/mannounce add [コマンド名] : アナウンスを追加します。コマンドを打つと「null」とアナウンスします");
                sender.sendMessage("[§b&lMan10Announce§r]&r/mannounce delete [コマンド名] : アナウンスを削除します");
                sender.sendMessage("[§b&lMan10Announce§r]&r/mannounce edit [コマンド] [行数] [内容] : 指定した行の内容を書き換えます。");
                return true;
            }
            if (args.length == 1){
                if (args[0].equals("list")) {
                    sender.sendMessage("[§b&lMan10Announce§r]&r===コマンドリスト===");
                    for (String s : commandlist) {
                        sender.sendMessage(s);
                    }
                    return true;
                }
            }
            if (args.length == 2){
                if (args[0].equals("add")){
                    if (commandlist.contains(args[1])) {
                        sender.sendMessage("§c[Man10Announce]そのコマンドはすでに登録されています");
                        return true;
                    }
                    commandlist.add(args[1]);
                    message.get(commandlist.size() - 1).add("nil");
                    configsave();
                }
                if (args[0].equals("delete")){
                    if (!commandlist.contains(args[1])){
                        sender.sendMessage("§c[Man10Announce]そのコマンドは登録されていません");
                        return true;
                    }
                    commandlist.remove(args[1]);
                    for (int i = 0;i < commandlist.size();i++){
                        if (commandlist.contains(args[1])){
                            for (int j = 0;j < message.get(i).size();j++){
                                message.get(i).remove(i);
                            }
                        }
                    }
                    configsave();
                }
            }
        }
        return true;
    }
}
