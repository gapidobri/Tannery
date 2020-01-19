package gportals.tannery.craft;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

import static gportals.tannery.Main.CRAFT_BLOCK;

public class PreventPickup implements Listener {

    @EventHandler
    public void onEntityPickupItem(EntityPickupItemEvent e) {
        if (!(e.getEntity() instanceof Player)) return;
        Player player = (Player) e.getEntity();

        if (player.getTargetBlock(5).getType().equals(CRAFT_BLOCK))
            e.setCancelled(true);
    }

}
