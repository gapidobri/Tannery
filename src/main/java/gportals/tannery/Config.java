package gportals.tannery;

import gportals.tannery.recipe.Hide;
import gportals.tannery.station.Station;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static gportals.tannery.Main.main;
import static gportals.tannery.StaticMethods.toDyeColor;

public class Config {

    //Recipes config file
    static File recipesFile = new File(main.getDataFolder(), "recipes.yml");
    static FileConfiguration recipesConf = YamlConfiguration.loadConfiguration(recipesFile);

    //Stations config file
    static File stationsFile = new File(main.getDataFolder(), "stations");
    static FileConfiguration stationsConf = YamlConfiguration.loadConfiguration(stationsFile);

    public static ArrayList<Hide> getRecipes() {
        ArrayList<Hide> recipes = new ArrayList<>();

        for (String key : recipesConf.getKeys(false)) {
            ConfigurationSection conf = recipesConf.getConfigurationSection(key);

            ItemStack item = new ItemStack(Material.getMaterial(conf.getString("item").toUpperCase()));

            ArrayList<ItemStack> ingredients = new ArrayList<>();
            for (String ingredient : conf.getStringList("ingredients")) {
                ingredients.add(new ItemStack(Material.getMaterial(ingredient.toUpperCase())));
            }

            ConfigurationSection confCraft = conf.getConfigurationSection("crafting");

            Hide recipe = new Hide(
                    key, //Id
                    ChatColor.translateAlternateColorCodes('&', conf.getString("name")), //Name
                    item, //Item
                    toDyeColor(conf.getString("color")).getColor(), //Color
                    conf.getInt("dry-time"), //Dry time
                    ingredients, //Ingredients
                    confCraft.getBoolean("helmet.enabled"),
                    confCraft.getBoolean("chestplate.enabled"),
                    confCraft.getBoolean("leggings.enabled"),
                    confCraft.getBoolean("boots.enabled"));

            recipes.add(recipe);
        }
        return recipes;
    }

    public static ItemStack enchantArmor(ItemStack item, Hide recipe) {

        ConfigurationSection conf = recipesConf.getConfigurationSection(recipe.getId());

        main.getLogger().info(String.valueOf(recipesConf.getKeys(true)));

        ItemMeta meta = item.getItemMeta();

        switch (item.getType()) {
            case LEATHER_HELMET:
                ConfigurationSection confHelmet = conf.getConfigurationSection("crafting.helmet.enchantments");
                for (String key : confHelmet.getKeys(false)) {
                    meta.addEnchant(Enchantment.getByKey(NamespacedKey.minecraft(key)), confHelmet.getInt(key), true);
                }
                break;
            case LEATHER_CHESTPLATE:
                ConfigurationSection confChest = conf.getConfigurationSection("crafting.chestplate.enchantments");
                for (String key : confChest.getKeys(false)) {
                    meta.addEnchant(Enchantment.getByKey(NamespacedKey.minecraft(key)), confChest.getInt(key), true);
                }
                break;
            case LEATHER_LEGGINGS:
                ConfigurationSection confLeg = conf.getConfigurationSection("crafting.leggings.enchantments");
                for (String key : confLeg.getKeys(false)) {
                    meta.addEnchant(Enchantment.getByKey(NamespacedKey.minecraft(key)), confLeg.getInt(key), true);
                }
                break;
            case LEATHER_BOOTS:
                ConfigurationSection confBoots = conf.getConfigurationSection("crafting.boots.enchantments");
                for (String key : confBoots.getKeys(false)) {
                    meta.addEnchant(Enchantment.getByKey(NamespacedKey.minecraft(key)), confBoots.getInt(key), true);
                }
                break;
        }

        item.setItemMeta(meta);
        return item;
    }

    public static void setStation(Station station) {
        int id = stationsConf.getKeys(false).size() + 1;

        for (String key : stationsConf.getKeys(false)) {
            if (stationsConf.get(key + ".location").equals(station.getLocation())) {
                id = Integer.parseInt(key);
            }
        }

        stationsConf.set(id + ".location", station.getLocation());
        stationsConf.set(id + ".isEmpty", station.isEmpty());
        stationsConf.set(id + ".hide", station.getHide().getId());
        stationsConf.set(id + ".hangTime", station.getHangTime());
        stationsConf.set(id + ".dryTime", station.getDryTime());

        saveConfig(stationsFile);
    }

    public static void clearStation(Station station) {
        for (String key : stationsConf.getKeys(false)) {
            if (stationsConf.get(key + ".location").equals(station.getLocation())) {
                stationsConf.set(key + ".isEmpty", true);
                stationsConf.set(key + ".hide", null);
                stationsConf.set(key + ".hangTime", null);
                stationsConf.set(key + ".dryTime", null);
            }
        }

        saveConfig(stationsFile);
    }

    public static void optimizeStations() {
        int count = 0;
        for (Station station : getStations()) {
            count++;
            if (station.isEmpty()) {
                stationsConf.set(String.valueOf(count), null);
            }
        }
    }

    public static ArrayList<Station> getStations() {
        ArrayList<Station> stations = new ArrayList<>();

        for (String key : stationsConf.getKeys(false)) {
            Hide hide = null;

            for (Hide recipe : getRecipes()) {
                if (recipe.getId().equals(stationsConf.getString(key + ".hide"))) {
                    hide = recipe;
                }
            }

            Station station = new Station((Location) stationsConf.get(key + ".location"),
                    stationsConf.getBoolean(key + ".isEmpty"),
                    hide,
                    stationsConf.getLong(key + ".hangTime"),
                    stationsConf.getInt(key + ".dryTime"));
            stations.add(station);
        }

        return stations;
    }

    public static Station getStation(Block block) {
        Station get = null;
        for (Station station : getStations()) {
            if (station.getLocation().equals(block.getLocation()))
                get = station;
        }

        return get;
    }

    static void saveConfig(File configFile) {
        try {
            stationsConf.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
