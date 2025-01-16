package me.junpak.refactoring.chapter1.after;

import me.junpak.refactoring.chapter1.data.Performance;
import me.junpak.refactoring.chapter1.data.Play;

public class PerformanceCalculator {

    private final Performance performance;
    private final Play play;

    public PerformanceCalculator(final Performance aPerformance, final Play aPlay) {
        this.performance = aPerformance;
        this.play = aPlay;
    }

    public Performance performance() {
        return performance;
    }

    public Play play() {
        return play;
    }

}
