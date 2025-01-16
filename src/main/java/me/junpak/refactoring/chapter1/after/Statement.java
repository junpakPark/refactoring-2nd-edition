package me.junpak.refactoring.chapter1.after;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import me.junpak.refactoring.chapter1.data.Invoice;
import me.junpak.refactoring.chapter1.data.Play;

public class Statement {

    public String statement(Invoice invoice, Map<String, Play> plays) {
        final StatementData data = new StatementData(
                invoice.customer(),
                invoice.performances().stream()
                        .map(performance -> new EnrichPerformance(performance, plays))
                        .toList()
        );
        return renderPlainText(data);
    }

    private String renderPlainText(final StatementData data) {
        var result = new StringBuilder("청구 내역 (고객명: " + data.customer() + ")\n");
        for (var perf : data.performances()) {
            result.append(
                    String.format(
                            "  %s: %s원 (%d석)\n",
                            perf.play().name(),
                            usd(amountFor(perf)),
                            perf.audience()
                    )
            );
        }
        result.append(String.format("총액: %s원\n", usd(totalAmount(data))));
        result.append(String.format("적립 포인트: %d점\n", totalVolumeCredits(data)));

        return result.toString();
    }

    private int totalAmount(final StatementData data) {
        var result = 0;
        for (var perf : data.performances()) {
            result += amountFor(perf);
        }
        return result;
    }

    private int totalVolumeCredits(final StatementData data) {
        var result = 0;
        for (var perf : data.performances()) {
            result += volumeCreditsFor(perf);
        }
        return result;
    }

    private String usd(final int aNumber) {
        final NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);

        return format.format(aNumber / 100.0);
    }

    private int volumeCreditsFor(final EnrichPerformance aPerformance) {
        var result = 0;
        result += Math.max(aPerformance.audience() - 30, 0);

        if ("comedy".equals(aPerformance.play().type())) {
            result += Math.floor(aPerformance.audience() / 5);
        }
        return result;
    }

    private int amountFor(final EnrichPerformance aPerformance) {
        var result = 0;
        switch (aPerformance.play().type()) {
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
                throw new IllegalArgumentException("알 수 없는 장르: " + aPerformance.play().type());
        }
        return result;
    }

}
