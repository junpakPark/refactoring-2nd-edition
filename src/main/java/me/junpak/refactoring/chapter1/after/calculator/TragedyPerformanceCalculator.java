package me.junpak.refactoring.chapter1.after.calculator;

import me.junpak.refactoring.chapter1.after.PerformanceCalculator;
import me.junpak.refactoring.chapter1.data.Performance;
import me.junpak.refactoring.chapter1.data.Play;

public class TragedyPerformanceCalculator extends PerformanceCalculator {

    public TragedyPerformanceCalculator(final Performance aPerformance, final Play aPlay) {
        super(aPerformance, aPlay);
    }

    @Override
    public int amount() {
        var result = 40000;
        if (performance().audience() > 30) {
            result += 1000 * (performance().audience() - 30);
        }
        return result;
    }

}
