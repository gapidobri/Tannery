package gportals.tannery.station;

import gportals.tannery.recipe.Hide;

public class Station {
    boolean isEmpty;
    Hide hide;
    long hangTime;

    public Station(boolean isEmpty, Hide hide, long hangTime) {
        this.isEmpty = isEmpty;
        this.hide = hide;
        this.hangTime = hangTime;
    }


}
