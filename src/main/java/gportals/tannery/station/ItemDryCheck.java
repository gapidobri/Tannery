package gportals.tannery.station;

import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import static gportals.tannery.Config.getStation;
import static gportals.tannery.Tannery.CRAFT_BLOCK;

public class ItemDryCheck {

    public static void itemDryCheckEvent(PlayerInteractEvent e) {

        Station station = getStation(e.getClickedBlock());

        if (e.getClickedBlock() == null
                || !e.getHand().equals(EquipmentSlot.HAND)
                || !e.getClickedBlock().getType().equals(CRAFT_BLOCK)
                || !e.getMaterial().equals(Material.CLOCK)
                || station.isEmpty()) return;

        int dryTime = station.getDryTime();
        if (dryTime == 0) {
            e.getPlayer().sendMessage(station.getHide().getName() + " is dry!");
        } else {
            e.getPlayer().sendMessage(station.getDryTime() + " seconds to dryness");
        }
    }
}
