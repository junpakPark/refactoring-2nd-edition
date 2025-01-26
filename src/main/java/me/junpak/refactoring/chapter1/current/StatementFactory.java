package me.junpak.refactoring.chapter1.current;

import me.junpak.refactoring.chapter1.current.data.EnrichPerformance;
import me.junpak.refactoring.chapter1.current.data.Performance;

public class StatementFactory {

    public EnrichPerformance enrichPerformance(final Performance aPerformance) {
        return new EnrichPerformance(aPerformance.playID(), aPerformance.audience());
    }

}
