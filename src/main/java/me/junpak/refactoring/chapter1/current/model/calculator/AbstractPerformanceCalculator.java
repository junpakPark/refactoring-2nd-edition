package me.junpak.refactoring.chapter1.current.model.calculator;

import me.junpak.refactoring.chapter1.current.model.PerformanceCalculator;
import me.junpak.refactoring.chapter1.current.model.data.EnrichPerformance;
import me.junpak.refactoring.chapter1.current.model.data.Performance;
import me.junpak.refactoring.chapter1.current.model.data.Play;

public abstract class AbstractPerformanceCalculator implements PerformanceCalculator {

    @Override
    public EnrichPerformance enrichPerformance(final Performance aPerformance, final Play aPlay) {
        return new EnrichPerformance(
                aPerformance.playID(),
                aPerformance.audience(),
                aPlay,
                amountFor(aPerformance, aPlay),
                volumeCreditsFor(aPerformance, aPlay)
        );
    }

    protected abstract int amountFor(final Performance aPerformance, final Play aPlay);

    private int volumeCreditsFor(final Performance aPerformance, final Play aPlay) {
        var result = Math.max(aPerformance.audience() - 30, 0);
        if ("comedy".equals(aPlay.type())) {
            result += aPerformance.audience() / 5;
        }
        return result;
    }

}
