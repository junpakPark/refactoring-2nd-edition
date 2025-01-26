package me.junpak.refactoring.chapter1.current.model.calculator;

import me.junpak.refactoring.chapter1.current.model.data.Performance;
import me.junpak.refactoring.chapter1.current.model.data.Play;

public class ComedyPerformanceCalculator extends AbstractPerformanceCalculator {

    @Override
    protected int amountFor(final Performance aPerformance, final Play aPlay) {
        var result = 30000;
        if (aPerformance.audience() > 20) {
            result += 10000 + 500 * (aPerformance.audience() - 20);
        }
        result += 300 * aPerformance.audience();

        return result;
    }

}
