package gportals.tannery.recipe;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Hide {

    String id;
    String name;
    ItemStack item;
    Color color;
    int dryTime;
    ArrayList<ItemStack> ingredients;

    boolean helmet;
    boolean chestplate;
    boolean leggings;
    boolean boots;

    public Hide(
            String id,
            String name,
            ItemStack item,
            Color color,
            int dryTime,
            ArrayList<ItemStack> ingredients,
            boolean helmet,
            boolean chestplate,
            boolean leggings,
            boolean boots) {
        this.id = id;
        this.name = name;
        this.item = item;
        this.color = color;
        this.dryTime = dryTime;
        this.ingredients = ingredients;
        this.helmet = helmet;
        this.chestplate = chestplate;
        this.leggings = leggings;
        this.boots = boots;
    }

    public ItemStack giveItem() {
        ItemStack item = getItem();
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setDisplayName(ChatColor.RESET + getName());
        if (getDryTime() > 0) {
            itemMeta.setDisplayName(ChatColor.AQUA + "Wet " + ChatColor.RESET + getName());
            itemMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            // itemMeta.setLore(Collections.singletonList(ChatColor.GOLD + "Seconds to dryness: " +
            // getDryTime())); //Shows time to dry in the item lore
        }
        item.setItemMeta(itemMeta);

        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setString("id", getId());
        nbtItem.setString("name", getName());
        nbtItem.setInteger("dryTime", getDryTime());

        return nbtItem.getItem();
    }

    public ItemStack getFinalItem() {
        Hide finalHide = this;
        finalHide.setDryTime(0);
        return finalHide.giveItem();
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
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

    public boolean isHelmet() {
        return helmet;
    }

    public void setHelmet(boolean helmet) {
        this.helmet = helmet;
    }

    public boolean isChestplate() {
        return chestplate;
    }

    public void setChestplate(boolean chestplate) {
        this.chestplate = chestplate;
    }

    public boolean isLeggings() {
        return leggings;
    }

    public void setLeggings(boolean leggings) {
        this.leggings = leggings;
    }

    public boolean isBoots() {
        return boots;
    }

    public void setBoots(boolean boots) {
        this.boots = boots;
    }
}
