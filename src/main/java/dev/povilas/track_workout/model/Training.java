package dev.povilas.track_workout.model;

public record Training(
        String date,
        String muscle,
        String exercise,
        Double kg,
        Integer rep,
        Integer distance,
        Integer time,
        Integer cal,
        String comment
) {
}
