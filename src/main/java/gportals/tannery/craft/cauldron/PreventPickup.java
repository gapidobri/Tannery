package gportals.tannery.craft.cauldron;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityPickupItemEvent;

import static gportals.tannery.Tannery.CRAFT_BLOCK;

public class PreventPickup {

    public static void preventPickupEvent(EntityPickupItemEvent e) {
        if (!(e.getEntity() instanceof Player)) return;
        Player player = (Player) e.getEntity();

        if (player.getTargetBlock(5).getType().equals(CRAFT_BLOCK)) e.setCancelled(true);
    }
}
