package gportals.tannery.recipe;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;

public class Hide {

    String id;
    String name;
    ItemStack item;
    int dryTime;
    ArrayList<ItemStack> ingredients;

    public Hide(String id, String name, ItemStack item, int dryTime, ArrayList<ItemStack> ingredients) {
        this.id = id;
        this.name = name;
        this.item = item;
        this.dryTime = dryTime;
        this.ingredients = ingredients;
    }

    public ItemStack giveItem() {
        ItemStack item = getItem();
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setDisplayName(ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', getName()));
        if (getDryTime() > 0) {
            itemMeta.setDisplayName(ChatColor.AQUA + "Wet " + ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', getName()));
            itemMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            itemMeta.setLore(Collections.singletonList(ChatColor.GOLD + "Seconds to dryness: " + getDryTime()));
        }
        item.setItemMeta(itemMeta);

        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setString("id", getId());
        nbtItem.setString("name", getName());
        nbtItem.setInteger("dryTime", getDryTime());

        return nbtItem.getItem();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemStack getItem() {
        return item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

    public int getDryTime() {
        return dryTime;
    }

    public void setDryTime(int dryTime) {
        this.dryTime = dryTime;
    }

    public ArrayList<ItemStack> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<ItemStack> ingredients) {
        this.ingredients = ingredients;
    }

}
