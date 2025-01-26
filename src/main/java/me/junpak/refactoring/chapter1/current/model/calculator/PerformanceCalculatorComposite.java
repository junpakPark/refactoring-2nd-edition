package me.junpak.refactoring.chapter1.current.model.calculator;

import me.junpak.refactoring.chapter1.current.model.AbstractPerformanceCalculator;
import me.junpak.refactoring.chapter1.current.model.PerformanceCalculator;
import me.junpak.refactoring.chapter1.current.model.data.EnrichPerformance;
import me.junpak.refactoring.chapter1.current.model.data.Performance;
import me.junpak.refactoring.chapter1.current.model.data.Play;

public class PerformanceCalculatorComposite implements PerformanceCalculator {

    private final PerformanceCalculator calculator;

    public PerformanceCalculatorComposite() {
        this.calculator = new AbstractPerformanceCalculator();
    }

    @Override
    public EnrichPerformance enrichPerformance(final Performance aPerformance, final Play aPlay) {
        return calculator.enrichPerformance(aPerformance, aPlay);
    }

}
