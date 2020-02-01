package gportals.tannery;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import static gportals.tannery.craft.bench.Recipes.addCraftingRecipes;

public final class Tannery extends JavaPlugin {

  public static final Material CRAFT_BLOCK = Material.CAULDRON;
  public static final ItemStack HIDE_BLOCK = new ItemStack(Material.LEATHER, 1);

  public static Tannery tannery;

  @Override
  public void onEnable() {
    tannery = this;

    addCraftingRecipes();

    saveResource("recipes.yml", false);
    saveResource("stations", false);

    getServer().getPluginManager().registerEvents(new Listeners(), this);

    getLogger().info(ChatColor.GREEN + "Tannery has been enabled successfully");
  }

  @Override
  public void onDisable() {
    tannery = null;
    getLogger().info(ChatColor.RED + "Tannery has been disabled successfully");
  }
}
