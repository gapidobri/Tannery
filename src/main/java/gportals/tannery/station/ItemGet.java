package gportals.tannery.station;

import org.bukkit.event.block.BlockBreakEvent;

import static gportals.tannery.Config.*;

public class ItemGet {

    public static void itemGetEvent(BlockBreakEvent e) {

        e.setDropItems(false);

        Station station = getStation(e.getBlock().getLocation().add(0, -1,0).getBlock());

        station.getHide().setDryTime(station.getDryTime() - (Math.toIntExact(System.currentTimeMillis() - station.getHangTime()) / 1000));
        if (station.getHide().getDryTime() < 0)
            station.getHide().setDryTime(0);
        e.getPlayer().getInventory().addItem(station.getHide().giveItem());

        clearStation(station);

    }
}
