package gportals.tannery;

import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class StaticMethods {

    public static ArrayList<Item> getItemsAt(Location location) {
        ArrayList<Item> items = new ArrayList<>();

        for (Entity entity : location.getChunk().getEntities()) {
            if (entity instanceof Item && entity.getLocation().getBlock().getLocation().equals(location)) {
                items.add((Item) entity);
            }
        }

        return items;
    }

    public static DyeColor toDyeColor(String colorName) {
        colorName = colorName.toUpperCase();
        DyeColor color = null;
        for (DyeColor dyeColor : DyeColor.values()) {
            if (dyeColor.name().equals(colorName)) {
                color = dyeColor;
            }
        }
        return color;
    }

}
