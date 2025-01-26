package me.junpak.refactoring.chapter1.current.model;

import me.junpak.refactoring.chapter1.current.model.data.EnrichPerformance;
import me.junpak.refactoring.chapter1.current.model.data.Performance;
import me.junpak.refactoring.chapter1.current.model.data.Play;

public interface PerformanceCalculator {

    EnrichPerformance enrichPerformance(final Performance aPerformance, final Play aPlay);

}
