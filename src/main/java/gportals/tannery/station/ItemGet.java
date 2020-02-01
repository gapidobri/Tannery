package gportals.tannery.station;

import org.bukkit.event.block.BlockBreakEvent;

import static gportals.tannery.Config.clearStation;
import static gportals.tannery.Config.getStation;

public class ItemGet {

  public static void itemGetEvent(BlockBreakEvent e) {

    Station station = getStation(e.getBlock().getLocation().add(0, -1, 0).getBlock());
    if (station == null) return;

    e.setDropItems(false);

    station.getHide().setDryTime(station.getDryTime());
    e.getPlayer().getInventory().addItem(station.getHide().giveItem());

    clearStation(station);
  }
}
