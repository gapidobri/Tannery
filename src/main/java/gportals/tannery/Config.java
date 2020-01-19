package gportals.tannery;

import gportals.tannery.recipe.Hide;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.ArrayList;

import static gportals.tannery.Main.main;
import static gportals.tannery.StaticMethods.toDyeColor;

public class Config {

    static File recipesFile = new File(main.getDataFolder(), "recipes.yml");
    static FileConfiguration recipeConf = YamlConfiguration.loadConfiguration(recipesFile);

    public static ArrayList<Hide> getRecipes() {
        ArrayList<Hide> recipes = new ArrayList<>();

        for (String key : recipeConf.getKeys(false)) {
            ConfigurationSection conf = recipeConf.getConfigurationSection(key);

            ItemStack item = new ItemStack(Material.getMaterial(conf.getString("item").toUpperCase()));
            DyeColor.valueOf(conf.getString("color").toUpperCase());

            ArrayList<ItemStack> ingredients = new ArrayList<>();
            for (String ingredient : conf.getStringList("ingredients")) {
                ingredients.add(new ItemStack(Material.getMaterial(ingredient.toUpperCase())));
            }

            Hide recipe = new Hide(
                    key, //Id
                    conf.getString("name"), //Name
                    item, //Item
                    conf.getInt("dry-time"), //Dry time
                    toDyeColor(conf.getString("color")), //Color
                    ingredients); //Ingredients

            recipes.add(recipe);
        }
        return recipes;
    }
}
