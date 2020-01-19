package gportals.tannery.craft;

import de.tr7zw.nbtapi.NBTItem;
import gportals.tannery.recipe.Hide;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

import static gportals.tannery.Config.getRecipes;
import static gportals.tannery.Main.CRAFT_BLOCK;
import static gportals.tannery.Main.HIDE_BLOCK;
import static gportals.tannery.StaticMethods.getItemsAt;

public class ItemCraft implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {

        //Returns if right clicked block is not CRAFT_BLOCK
        if (!e.getClickedBlock().getType().equals(CRAFT_BLOCK) ||
                !e.getHand().equals(EquipmentSlot.HAND) ||
                !e.getMaterial().equals(HIDE_BLOCK))
            return;

        ArrayList<Item> droppedItems = getItemsAt(e.getClickedBlock().getLocation());
        ArrayList<ItemStack> droppedItemStacks = new ArrayList<>();

        for (Item item : droppedItems)
            droppedItemStacks.add(item.getItemStack());

        for (Hide recipe : getRecipes()) {
            if (droppedItemStacks.containsAll(recipe.getIngredients())) {
                e.getPlayer().sendMessage("You crafted " + recipe.getName());

                //Kill items in cauldron
                for (Item item : droppedItems) {
                    item.remove();
                }

                //Take item from player
                e.getPlayer().getInventory().remove(new ItemStack(HIDE_BLOCK, 1));

                //Give item to player
                ItemStack item = recipe.getItem();
                ItemMeta itemMeta = item.getItemMeta();
                itemMeta.setDisplayName(recipe.getName());
                item.setItemMeta(itemMeta);

                NBTItem nbtItem = new NBTItem(item);
                nbtItem.setString("id", recipe.getId());
                nbtItem.setInteger("dryTime", recipe.getDryTime());
                nbtItem.setObject("color", recipe.getColor());

                //TODO Add specials & tools

                e.getPlayer().getInventory().addItem(nbtItem.getItem());
            }
        }

    }

}
