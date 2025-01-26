package me.junpak.refactoring.chapter1.current;

import me.junpak.refactoring.chapter1.current.data.EnrichPerformance;
import me.junpak.refactoring.chapter1.current.data.Performance;
import me.junpak.refactoring.chapter1.current.data.Play;

public class StatementFactory {

    public EnrichPerformance enrichPerformance(final Performance aPerformance, final Play aPlay) {
        return new EnrichPerformance(aPerformance.playID(), aPerformance.audience(), aPlay);
    }

}
