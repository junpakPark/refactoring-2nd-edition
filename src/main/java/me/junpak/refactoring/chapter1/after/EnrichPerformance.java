package me.junpak.refactoring.chapter1.after;

import java.util.Map;
import me.junpak.refactoring.chapter1.data.Performance;
import me.junpak.refactoring.chapter1.data.Play;

public record EnrichPerformance(
        String playID,
        int audience,
        Play play,
        int amount
) {
    public static EnrichPerformance of(final Performance aPerformance, final Map<String, Play> plays) {
        final Play play = playFor(aPerformance, plays);

        return new EnrichPerformance(
                aPerformance.playID(),
                aPerformance.audience(),
                play,
                amountFor(aPerformance, play)
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

}
