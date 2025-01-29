package me.junpak.refactoring.chapter1.current.model.calculator;

import me.junpak.refactoring.chapter1.data.Performance;

public class ComedyPerformanceCalculator extends AbstractPerformanceCalculator {

    @Override
    protected int amountFor(final Performance aPerformance) {
        var result = 30000;
        if (aPerformance.audience() > 20) {
            result += 10000 + 500 * (aPerformance.audience() - 20);
        }
        result += 300 * aPerformance.audience();

        return result;
    }

    @Override
    protected int volumeCreditsFor(final Performance aPerformance) {
        return super.volumeCreditsFor(aPerformance) + aPerformance.audience() / 5;
    }

}
