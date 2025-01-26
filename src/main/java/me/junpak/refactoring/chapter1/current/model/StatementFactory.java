package me.junpak.refactoring.chapter1.current.model;

import java.util.List;
import java.util.Map;
import me.junpak.refactoring.chapter1.current.model.data.EnrichPerformance;
import me.junpak.refactoring.chapter1.current.model.data.Invoice;
import me.junpak.refactoring.chapter1.current.model.data.Performance;
import me.junpak.refactoring.chapter1.current.model.data.Play;
import me.junpak.refactoring.chapter1.current.model.data.Statement;

public class StatementFactory {

    private final PerformanceCalculator calculator;

    public StatementFactory(final PerformanceCalculator performanceCalculator) {
        this.calculator = performanceCalculator;
    }

    public Statement createStatement(final Invoice invoice, final Map<String, Play> plays) {
        final List<EnrichPerformance> enrichPerformances = invoice.performances().stream()
                .map(performance -> enrichPerformance(performance, plays.get(performance.playID())))
                .toList();

        return new Statement(
                invoice.customer(),
                enrichPerformances,
                totalAmount(enrichPerformances),
                totalVolumeCredits(enrichPerformances)
        );
    }

    private int totalAmount(final List<EnrichPerformance> performances) {
        return performances.stream()
                .mapToInt(EnrichPerformance::amount)
                .sum();
    }

    private int totalVolumeCredits(final List<EnrichPerformance> performances) {
        return performances.stream()
                .mapToInt(EnrichPerformance::volumeCredits)
                .sum();
    }

    public EnrichPerformance enrichPerformance(final Performance aPerformance, final Play aPlay) {
        return new EnrichPerformance(
                aPerformance.playID(),
                aPerformance.audience(),
                aPlay,
                amountFor(aPerformance, aPlay),
                volumeCreditsFor(aPerformance, aPlay)
        );
    }

    private int amountFor(final Performance aPerformance, final Play aPlay) {
        var result = 0;

        switch (aPlay.type()) {
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
