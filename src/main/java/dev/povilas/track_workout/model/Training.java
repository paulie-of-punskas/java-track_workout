package dev.povilas.track_workout.model;

import java.time.LocalDateTime;

public record Training(
        LocalDateTime date,
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
