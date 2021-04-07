package org.dreambot.util;

import org.dreambot.api.methods.map.Area;

public enum Location {

    CHICKEN_RIGHT_AREA(new Area(3225, 3301, 3237, 3287)),
    CHICKEN_LEFT_AREA(new Area(3183, 3281, 3192, 3276))
    ;

    private Area area;

    Location(Area area) {
        this.area = area;
    }

    public Area getArea() {
        return area;
    }
}
