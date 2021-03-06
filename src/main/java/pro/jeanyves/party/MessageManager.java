package pro.jeanyves.party;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import pro.jeanyves.party.config.Config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageManager {

    private Config config;
    private String prefix;
    private Map<String, String> cache;

    public MessageManager(Config config) {
        cache = new HashMap<>();
        this.config = config;
        prefix = config.getConfig().getString("Prefix");
    }

    public TextComponent getString(String path) {
        if(cache.containsKey(path)) {
            return new TextComponent(ChatColor.translateAlternateColorCodes('&', (prefix + cache.get(path))));
        }else {
            cache.put(path, config.getConfig().getString(path));
            return new TextComponent(ChatColor.translateAlternateColorCodes('&', (prefix + config.getConfig().getString(path))));
        }
    }

    public TextComponent getString(String path, List<String> toReplace, List<String> replace) {
        if(!cache.containsKey(path)) {
            cache.put(path, config.getConfig().getString(path));
        }

        String str = prefix + cache.get(path);

        for(int i = 0; i < toReplace.size(); i++) {
            str = str.replaceAll(toReplace.get(i), replace.get(i));
        }

        return new TextComponent(ChatColor.translateAlternateColorCodes('&', str));
    }

    public String getRawString(String path) {
        if(cache.containsKey(path)) {
            return ChatColor.translateAlternateColorCodes('&', (cache.get(path)));
        }else {
            cache.put(path, config.getConfig().getString(path));
            return ChatColor.translateAlternateColorCodes('&', (config.getConfig().getString(path)));
        }
    }

    public String getRawString(String path, List<String> toReplace, List<String> replace) {
        if(!cache.containsKey(path)) {
            cache.put(path, config.getConfig().getString(path));
        }

        String str = cache.get(path);

        for(int i = 0; i < toReplace.size(); i++) {
            str = str.replaceAll(toReplace.get(i), replace.get(i));
        }

        return ChatColor.translateAlternateColorCodes('&', str);
    }

}
