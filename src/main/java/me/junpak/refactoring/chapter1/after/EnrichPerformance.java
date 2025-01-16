package me.junpak.refactoring.chapter1.after;

import me.junpak.refactoring.chapter1.data.Performance;

public record EnrichPerformance(
        String playID,
        int audience
) {
    public EnrichPerformance(final Performance aPerformance) {
        this(aPerformance.playID(), aPerformance.audience());
    }
}
