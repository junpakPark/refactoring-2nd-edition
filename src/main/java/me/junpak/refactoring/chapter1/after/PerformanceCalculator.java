package me.junpak.refactoring.chapter1.after;

import me.junpak.refactoring.chapter1.after.calculator.ComedyPerformanceCalculator;
import me.junpak.refactoring.chapter1.after.calculator.TragedyPerformanceCalculator;
import me.junpak.refactoring.chapter1.data.Performance;
import me.junpak.refactoring.chapter1.data.Play;

public class PerformanceCalculator {

    private final Performance performance;
    private final Play play;

    protected PerformanceCalculator(final Performance aPerformance, final Play aPlay) {
        this.performance = aPerformance;
        this.play = aPlay;
    }

    public static PerformanceCalculator createPerformanceCalculator(final Performance aPerformance, final Play aPlay) {
        return switch (aPlay.type()) {
            case "tragedy" -> new TragedyPerformanceCalculator(aPerformance, aPlay);
            case "comedy" -> new ComedyPerformanceCalculator(aPerformance, aPlay);
            default -> throw new IllegalArgumentException("알 수 없는 장르: " + aPlay.type());
        };
    }

    public int amount() {
        var result = 0;
        switch (this.play.type()) {
            case "tragedy":
                throw new IllegalStateException("오류 발생");
            case "comedy":
                result = 30000;
                if (this.performance.audience() > 20) {
                    result += 10000 + 500 * (this.performance.audience() - 20);
                }
                result += 300 * this.performance.audience();
                break;
            default:
                throw new IllegalArgumentException("알 수 없는 장르: " + this.play.type());
        }
        return result;
    }

    public int volumeCredits() {
        var result = 0;
        result += Math.max(this.performance.audience() - 30, 0);

        if ("comedy".equals(this.play.type())) {
            result += Math.floor(this.performance.audience() / 5);
        }
        return result;
    }

    public Performance performance() {
        return performance;
    }

    public Play play() {
        return play;
    }

}
