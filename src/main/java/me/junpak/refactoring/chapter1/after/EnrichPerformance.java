package me.junpak.refactoring.chapter1.after;

import java.util.Map;
import me.junpak.refactoring.chapter1.data.Performance;
import me.junpak.refactoring.chapter1.data.Play;

public record EnrichPerformance(
        String playID,
        int audience,
        Play play
) {
    public EnrichPerformance(final Performance aPerformance, final Map<String, Play> plays) {
        this(aPerformance.playID(), aPerformance.audience(), playFor(aPerformance, plays));
    }

    private static Play playFor(final Performance aPerformance, final Map<String, Play> plays) {
        return plays.get(aPerformance.playID());
    }


}
