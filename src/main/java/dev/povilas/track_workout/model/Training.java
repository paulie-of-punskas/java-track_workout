package dev.povilas.track_workout.model;

import java.util.Date;

public record Training(
        Date date,
        String muscle,
        String exercise,
        Integer kg,
        Integer rep,
        Integer distance,
        Integer time,
        Integer cal,
        String comment
) {
}
