package me.junpak.refactoring.chapter1.current.model.calculator;

import me.junpak.refactoring.chapter1.data.Performance;

public class TragedyPerformanceCalculator extends AbstractPerformanceCalculator {

    @Override
    protected int amountFor(final Performance aPerformance) {
        var result = 40000;
        if (aPerformance.audience() > 30) {
            result += 1000 * (aPerformance.audience() - 30);
        }

        return result;
    }

}
