package me.junpak.refactoring.chapter1.current.model.calculator;

import me.junpak.refactoring.chapter1.current.model.PerformanceCalculator;
import me.junpak.refactoring.chapter1.current.model.data.EnrichPerformance;
import me.junpak.refactoring.chapter1.current.model.data.Performance;
import me.junpak.refactoring.chapter1.current.model.data.Play;

public abstract class AbstractPerformanceCalculator implements PerformanceCalculator {

    @Override
    public EnrichPerformance enrichPerformance(final Performance aPerformance, final Play aPlay) {
        return new EnrichPerformance(
                aPerformance.playID(),
                aPerformance.audience(),
                aPlay,
                amountFor(aPerformance, aPlay),
                volumeCreditsFor(aPerformance, aPlay)
        );
    }

    protected int amountFor(final Performance aPerformance, final Play aPlay) {
        var result = 0;

        switch (aPlay.type()) {
            case "tragedy":
                throw new IllegalStateException("오류 발생");
            case "comedy":
                result = 30000;
                if (aPerformance.audience() > 20) {
                    result += 10000 + 500 * (aPerformance.audience() - 20);
                }
                result += 300 * aPerformance.audience();
                break;
            default:
                throw new IllegalArgumentException("알 수 없는 장르: " + aPlay.type());
        }
        return result;
    }

    private int volumeCreditsFor(final Performance aPerformance, final Play aPlay) {
        var result = Math.max(aPerformance.audience() - 30, 0);
        if ("comedy".equals(aPlay.type())) {
            result += aPerformance.audience() / 5;
        }
        return result;
    }

}
