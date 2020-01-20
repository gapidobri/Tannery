package gportals.tannery;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static final Material CRAFT_BLOCK = Material.CAULDRON;
    public static final ItemStack HIDE_BLOCK = new ItemStack(Material.LEATHER, 1);

    public static Main main;

    @Override
    public void onEnable() {
        main = this;

        saveResource("recipes.yml", false);
        saveResource("stations", false);

        getServer().getPluginManager().registerEvents(new Listeners(), this);

        getLogger().info(ChatColor.GREEN + "Tannery has enabled successfully");
    }

    @Override
    public void onDisable() {
        main = null;
        getLogger().info(ChatColor.RED + "Tannery has disabled successfully");
    }
}
