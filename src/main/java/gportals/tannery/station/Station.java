package gportals.tannery.station;

import gportals.tannery.recipe.Hide;
import org.bukkit.Location;

public class Station {
    Location location;
    boolean isEmpty;
    Hide hide;
    long hangTime;
    int dryTime;

    public Station(Location location, boolean isEmpty, Hide hide, long hangTime, int dryTime) {
        this.location = location;
        this.isEmpty = isEmpty;
        this.hide = hide;
        this.hangTime = hangTime;
        this.dryTime = dryTime;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public Hide getHide() {
        return hide;
    }

    public void setHide(Hide hide) {
        this.hide = hide;
    }

    public long getHangTime() {
        return hangTime;
    }

    public void setHangTime(long hangTime) {
        this.hangTime = hangTime;
    }

    public int getDryTime() {
        long longTime = dryTime - ((System.currentTimeMillis() - getHangTime()) / 1000);
        if (longTime < 0)
            dryTime = 0;
        else
            dryTime = (int) longTime;

        return dryTime;
    }

    public void setDryTime(int dryTime) {
        this.dryTime = dryTime;
    }
}
