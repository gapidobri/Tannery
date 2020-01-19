package gportals.tannery.recipe;

import org.bukkit.DyeColor;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class Hide {

    String id;
    String name;
    ItemStack item;
    int dryTime;
    DyeColor color;
    ArrayList<ItemStack> ingredients;

    public Hide(String id, String name, ItemStack item, int dryTime, DyeColor color, ArrayList<ItemStack> ingredients) {
        this.id = id;
        this.name = name;
        this.item = item;
        this.dryTime = dryTime;
        this.color = color;
        this.ingredients = ingredients;
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

    public DyeColor getColor() {
        return color;
    }

    public void setColor(DyeColor color) {
        this.color = color;
    }

    public ArrayList<ItemStack> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<ItemStack> ingredients) {
        this.ingredients = ingredients;
    }

}
