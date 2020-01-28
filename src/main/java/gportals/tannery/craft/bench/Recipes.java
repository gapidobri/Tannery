package gportals.tannery.craft.bench;

import gportals.tannery.recipe.Hide;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import static gportals.tannery.Config.enchantArmor;
import static gportals.tannery.Config.getRecipes;
import static gportals.tannery.Main.main;

public class Recipes {

    public static void addCraftingRecipes() {
        for (Hide recipe : getRecipes()) {
            if (recipe.isHelmet()) addHelmetRecipe(recipe);
            if (recipe.isChestplate()) addChestplateRecipe(recipe);
            if (recipe.isLeggings()) addLeggingsRecipe(recipe);
            if (recipe.isBoots()) addBootsRecipe(recipe);
        }
    }

    static void addHelmetRecipe(Hide hide) {

        ItemStack result = new ItemStack(Material.LEATHER_HELMET);
        LeatherArmorMeta meta = (LeatherArmorMeta) result.getItemMeta();
        meta.setDisplayName(hide.getName() + " Hat");
        meta.setColor(hide.getColor());
        result.setItemMeta(meta);
        result = enchantArmor(result, hide);

        NamespacedKey key = new NamespacedKey(main, hide.getId() + "_helmet");
        ShapedRecipe recipe = new ShapedRecipe(key, result);
        recipe.shape("aaa", "axa", "xxx");
        recipe.setIngredient('a', hide.getFinalItem());
        recipe.setIngredient('x', Material.AIR);

        main.getLogger().info("Added helmet recipe for " + hide.getName());
        Bukkit.addRecipe(recipe);

    }

    static void addChestplateRecipe(Hide hide) {
        ItemStack result = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta meta = (LeatherArmorMeta) result.getItemMeta();
        meta.setDisplayName(hide.getName() + " Tunic");
        meta.setColor(hide.getColor());
        result.setItemMeta(meta);
        result = enchantArmor(result, hide);

        NamespacedKey key = new NamespacedKey(main, hide.getId() + "_chestplate");
        ShapedRecipe recipe = new ShapedRecipe(key, result);
        recipe.shape("axa", "aaa", "aaa");
        recipe.setIngredient('a', hide.getFinalItem());
        recipe.setIngredient('x', Material.AIR);

        main.getLogger().info("Added chestplate recipe for " + hide.getName());
        Bukkit.addRecipe(recipe);
    }

    static void addLeggingsRecipe(Hide hide) {
        ItemStack result = new ItemStack(Material.LEATHER_LEGGINGS);
        LeatherArmorMeta meta = (LeatherArmorMeta) result.getItemMeta();

        meta.setDisplayName(hide.getName() + " Pants");
        meta.setColor(hide.getColor());

        result.setItemMeta(meta);
        result = enchantArmor(result, hide);

        NamespacedKey key = new NamespacedKey(main, hide.getId() + "_leggings");
        ShapedRecipe recipe = new ShapedRecipe(key, result);
        recipe.shape("aaa", "axa", "axa");
        recipe.setIngredient('a', hide.getFinalItem());
        recipe.setIngredient('x', Material.AIR);

        main.getLogger().info("Added leggings recipe for " + hide.getName());
        Bukkit.addRecipe(recipe);
    }

    static void addBootsRecipe(Hide hide) {
        ItemStack result = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta meta = (LeatherArmorMeta) result.getItemMeta();

        meta.setDisplayName(hide.getName() + " Boots");
        meta.setColor(hide.getColor());

        result.setItemMeta(meta);
        result = enchantArmor(result, hide);

        NamespacedKey key = new NamespacedKey(main, hide.getId() + "_boots");
        ShapedRecipe recipe = new ShapedRecipe(key, result);
        recipe.shape("axa", "axa", "xxx");
        recipe.setIngredient('a', hide.getFinalItem());
        recipe.setIngredient('x', Material.AIR);

        main.getLogger().info("Added boots recipe for " + hide.getName());
        Bukkit.addRecipe(recipe);
    }

}
