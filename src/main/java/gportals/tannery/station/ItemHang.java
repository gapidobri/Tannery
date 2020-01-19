package gportals.tannery.station;

import de.tr7zw.nbtapi.NBTItem;
import gportals.tannery.recipe.Hide;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import static gportals.tannery.Config.getRecipes;
import static gportals.tannery.Main.HANG_BLOCK;

public class ItemHang implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {

        if (!e.getClickedBlock().getType().equals(HANG_BLOCK) ||
                !e.getHand().equals(EquipmentSlot.HAND) ||
                e.getItem() == null) return;

        NBTItem nbtItem = new NBTItem(e.getItem());

        //Check if item is a hide
        for (Hide hide : getRecipes()) {
            if (hide.getId().equals(nbtItem.getString("id"))) {
                e.setCancelled(true);
            }
        }

    }
}
