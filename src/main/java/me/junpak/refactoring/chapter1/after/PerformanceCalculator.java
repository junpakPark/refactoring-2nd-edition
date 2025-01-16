package me.junpak.refactoring.chapter1.after;

import me.junpak.refactoring.chapter1.data.Performance;

public class PerformanceCalculator {

    private final Performance performance;

    public PerformanceCalculator(final Performance aPerformance) {
        this.performance = aPerformance;
    }

    public Performance performance() {
        return performance;
    }

}
