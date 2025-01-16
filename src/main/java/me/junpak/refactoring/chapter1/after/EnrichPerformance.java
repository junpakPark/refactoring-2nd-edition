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
        final PerformanceCalculator calculator = new PerformanceCalculator(aPerformance);
        final Play play = playFor(aPerformance, plays);

        return new EnrichPerformance(
                aPerformance.playID(),
                aPerformance.audience(),
                play,
                amountFor(aPerformance, play),
                volumeCreditsFor(aPerformance, play)
        );
    }

    private static Play playFor(final Performance aPerformance, final Map<String, Play> plays) {
        return plays.get(aPerformance.playID());
    }

    private static int amountFor(final Performance aPerformance, final Play play) {
        var result = 0;
        switch (play.type()) {
            case "tragedy":
                result = 40000;
                if (aPerformance.audience() > 30) {
                    result += 1000 * (aPerformance.audience() - 30);
                }
                break;
            case "comedy":
                result = 30000;
                if (aPerformance.audience() > 20) {
                    result += 10000 + 500 * (aPerformance.audience() - 20);
                }
                result += 300 * aPerformance.audience();
                break;
            default:
                throw new IllegalArgumentException("알 수 없는 장르: " + play.type());
        }
        return result;
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
