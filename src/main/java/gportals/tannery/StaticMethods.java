package gportals.tannery;

import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

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

    public static BlockFace[] axis = { BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST };
    public static BlockFace[] radial = { BlockFace.NORTH, BlockFace.NORTH_EAST, BlockFace.EAST, BlockFace.SOUTH_EAST,
            BlockFace.SOUTH, BlockFace.SOUTH_WEST, BlockFace.WEST, BlockFace.NORTH_WEST };

    public static BlockFace yawToFace(float yaw) {
        return yawToFace(yaw, true);
    }

    public static BlockFace yawToFace(float yaw, boolean useSubCardinalDirections) {
        if (useSubCardinalDirections) {
            return radial[Math.round(yaw / 45f) & 0x7];
        } else {
            return axis[Math.round(yaw / 90f) & 0x3];
        }
    }

    public static void removeItem(Player player, ItemStack item, int amount) {
        item.setAmount(Math.max(item.getAmount() - amount, 0));
    }

}
