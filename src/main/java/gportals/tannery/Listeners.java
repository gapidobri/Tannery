package gportals.tannery;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;

import static gportals.tannery.craft.ItemCraft.itemCraftEvent;
import static gportals.tannery.craft.PreventPickup.preventPickupEvent;
import static gportals.tannery.station.ItemGet.itemGetEvent;
import static gportals.tannery.station.ItemHang.itemHangEvent;

public class Listeners implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        itemCraftEvent(event);
        itemHangEvent(event);
    }

    @EventHandler
    public void onEntityPickup(EntityPickupItemEvent event) {
        preventPickupEvent(event);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        itemGetEvent(event);
    }

}
