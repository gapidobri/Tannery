package gportals.tannery.craft;

import gportals.tannery.recipe.Hide;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

import static gportals.tannery.Config.getRecipes;
import static gportals.tannery.Main.CRAFT_BLOCK;
import static gportals.tannery.Main.HIDE_BLOCK;
import static gportals.tannery.StaticMethods.getItemsAt;
import static gportals.tannery.StaticMethods.removeItem;

public class ItemCraft {

    public static void itemCraftEvent(PlayerInteractEvent e) {

        if (e.getClickedBlock() == null) return;

        //Returns if right clicked block is not CRAFT_BLOCK
        if (!e.getClickedBlock().getType().equals(CRAFT_BLOCK) ||
                !e.getHand().equals(EquipmentSlot.HAND) ||
                !e.getItem().getItemMeta().equals(HIDE_BLOCK.getItemMeta()))
            return;

        ArrayList<Item> droppedItems = getItemsAt(e.getClickedBlock().getLocation());
        ArrayList<ItemStack> droppedItemStacks = new ArrayList<>();

        for (Item item : droppedItems)
            droppedItemStacks.add(item.getItemStack());

        for (Hide recipe : getRecipes()) {
            if (droppedItemStacks.containsAll(recipe.getIngredients())) {

                //Kill items in cauldron
                for (Item item : droppedItems) {
                    item.remove();
                }

                e.getPlayer().playSound(e.getClickedBlock().getLocation(), Sound.ITEM_BUCKET_EMPTY, 3.0f, 1f);

                //Take item from player
                removeItem(e.getPlayer(), e.getItem(), 1);

                //Give item to player
                e.getPlayer().getInventory().addItem(recipe.giveItem());
            }
        }

    }

}
