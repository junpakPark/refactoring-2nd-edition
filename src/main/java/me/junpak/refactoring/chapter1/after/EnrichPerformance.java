package me.junpak.refactoring.chapter1.after;

import java.util.Map;
import me.junpak.refactoring.chapter1.data.Performance;
import me.junpak.refactoring.chapter1.data.Play;

public record EnrichPerformance(
        String playID,
        int audience,
        Play play,
        int amount,
        int volumeCredits
) {

    public static EnrichPerformance of(final Performance aPerformance, final Map<String, Play> plays) {
        final PerformanceCalculator calculator = new PerformanceCalculator(aPerformance, playFor(aPerformance, plays));

        return new EnrichPerformance(
                aPerformance.playID(),
                aPerformance.audience(),
                calculator.play(),
                calculator.amount(),
                calculator.volumeCredits()
        );
    }

    private static Play playFor(final Performance aPerformance, final Map<String, Play> plays) {
        return plays.get(aPerformance.playID());
    }

}
