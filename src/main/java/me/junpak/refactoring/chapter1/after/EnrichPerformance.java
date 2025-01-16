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
                amountFor(aPerformance, calculator.play()),
                volumeCreditsFor(aPerformance, calculator.play())
        );
    }

    private static Play playFor(final Performance aPerformance, final Map<String, Play> plays) {
        return plays.get(aPerformance.playID());
    }

    private static int amountFor(final Performance aPerformance, final Play play) {
        return new PerformanceCalculator(aPerformance, play).amount();
    }

    private static int volumeCreditsFor(final Performance aPerformance, final Play play) {
        var result = 0;
        result += Math.max(aPerformance.audience() - 30, 0);

        if ("comedy".equals(play.type())) {
            result += Math.floor(aPerformance.audience() / 5);
        }
        return result;
    }

}
