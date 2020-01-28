package gportals.tannery.station;

import de.tr7zw.nbtapi.NBTItem;
import gportals.tannery.recipe.Hide;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Rotatable;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import static gportals.tannery.Config.*;
import static gportals.tannery.Main.CRAFT_BLOCK;
import static gportals.tannery.StaticMethods.removeItem;
import static gportals.tannery.StaticMethods.yawToFace;

public class ItemHang {

    public static void itemHangEvent(PlayerInteractEvent e) {

        if (e.getClickedBlock() == null || e.getItem() == null) return;

        if (!e.getClickedBlock().getType().equals(CRAFT_BLOCK) ||
                !e.getHand().equals(EquipmentSlot.HAND)) return;

        NBTItem nbtItem = new NBTItem(e.getItem());

        if (getStation(e.getClickedBlock()) != null) {
            if (nbtItem.getInteger("dryTime") <= 0 || !getStation(e.getClickedBlock()).isEmpty()) {
                return;
            }
        }

        //Check if item is a hide
        for (Hide hide : getRecipes()) {

            if (hide.getId().equals(nbtItem.getString("id"))) {
                e.setCancelled(true);

                //Places banner
                Block bannerBlock = e.getClickedBlock().getLocation().add(0, 1, 0).getBlock();
                bannerBlock.setType(Material.BROWN_BANNER);

                BlockData bannerData = bannerBlock.getBlockData();
                if (bannerData instanceof Rotatable) {
                    Rotatable bannerRot = (Rotatable) bannerData;
                    bannerRot.setRotation(yawToFace(e.getPlayer().getLocation().getYaw()));
                    bannerBlock.setBlockData(bannerRot);
                }

                setStation(new Station(e.getClickedBlock().getLocation(), false, hide, System.currentTimeMillis(), nbtItem.getInteger("dryTime")));
                removeItem(e.getPlayer(), e.getItem(), 1);
            }

        }

    }
}
