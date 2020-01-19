package gportals.tannery;

import gportals.tannery.craft.ItemCraft;
import gportals.tannery.craft.PreventPickup;
import gportals.tannery.station.ItemHang;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static final Material CRAFT_BLOCK = Material.CAULDRON;
    public static final Material HIDE_BLOCK = Material.LEATHER;
    public static final Material HANG_BLOCK = Material.CRAFTING_TABLE;

    public static Main main;
    Listener[] listeners = {new ItemCraft(), new ItemHang(), new PreventPickup()};

    @Override
    public void onEnable() {
        main = this;

        saveResource("recipes.yml", false);
        registerEvents(listeners);

        getLogger().info(ChatColor.GREEN + "Tannery has enabled successfully");
    }

    @Override
    public void onDisable() {
        main = null;
        getLogger().info(ChatColor.RED + "Tannery has disabled successfully");
    }

    public void registerEvents(Listener[] listeners) {
        for (Listener listener : listeners)
            getServer().getPluginManager().registerEvents(listener, this);
    }
}
