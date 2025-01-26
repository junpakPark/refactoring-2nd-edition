package me.junpak.refactoring.chapter1.after.calculator;

import me.junpak.refactoring.chapter1.after.PerformanceCalculator;
import me.junpak.refactoring.chapter1.after.data.Performance;
import me.junpak.refactoring.chapter1.after.data.Play;

public class ComedyPerformanceCalculator extends PerformanceCalculator {

    public ComedyPerformanceCalculator(final Performance aPerformance, final Play aPlay) {
        super(aPerformance, aPlay);
    }

    @Override
    public int amount() {
        var result = 30000;
        if (performance().audience() > 20) {
            result += 10000 + 500 * (performance().audience() - 20);
        }
        result += 300 * performance().audience();

        return result;
    }

    @Override
    public int volumeCredits() {
        return super.volumeCredits() + (performance().audience() / 5);
    }

}
