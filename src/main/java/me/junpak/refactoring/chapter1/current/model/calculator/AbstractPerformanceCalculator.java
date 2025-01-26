package me.junpak.refactoring.chapter1.current.model.calculator;

import me.junpak.refactoring.chapter1.current.model.PerformanceCalculator;
import me.junpak.refactoring.chapter1.current.model.data.EnrichPerformance;
import me.junpak.refactoring.chapter1.current.model.data.Performance;
import me.junpak.refactoring.chapter1.current.model.data.Play;

public abstract class AbstractPerformanceCalculator implements PerformanceCalculator {

    @Override
    public final EnrichPerformance enrichPerformance(final Performance aPerformance, final Play aPlay) {
        return new EnrichPerformance(
                aPerformance.playID(),
                aPerformance.audience(),
                aPlay,
                amountFor(aPerformance, aPlay),
                volumeCreditsFor(aPerformance)
        );
    }

    protected abstract int amountFor(final Performance aPerformance, final Play aPlay);

    protected int volumeCreditsFor(final Performance aPerformance) {
        return Math.max(aPerformance.audience() - 30, 0);
    }

}
