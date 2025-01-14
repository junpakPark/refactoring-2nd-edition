package me.junpak.refactoring.chapter1.after;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import me.junpak.refactoring.chapter1.data.Invoice;
import me.junpak.refactoring.chapter1.data.Performance;
import me.junpak.refactoring.chapter1.data.Play;

public class Statement {

    public String statement(Invoice invoice, Map<String, Play> plays) {
        var totalAmount = 0;
        var volumeCredits = 0;
        var result = new StringBuilder("청구 내역 (고객명: " + invoice.customer() + ")\n");
        final NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);

        for (var perf : invoice.performances()) {
            var thisAmount = amountFor(perf, playFor(plays, perf));

            // 포인트를 적립한다.
            volumeCredits += Math.max(perf.audience() - 30, 0);
            // 희극 관객 5명마다 추가 포인트를 제공한다.
            if ("comedy".equals(playFor(plays, perf).type())) {
                volumeCredits += Math.floor(perf.audience() / 5);
            }

            // 청구 내역을 출력한다.
            result.append(
                    String.format(
                            "  %s: %s원 (%d석)\n",
                            playFor(plays, perf).name(),
                            format.format(thisAmount / 100.0),
                            perf.audience()
                    )
            );
            totalAmount += thisAmount;
        }

        result.append(String.format("총액: %s원\n", format.format(totalAmount / 100.0)));
        result.append(String.format("적립 포인트: %d점\n", volumeCredits));

        return result.toString();
    }

    private Play playFor(final Map<String, Play> plays, final Performance aPerformance) {
        return plays.get(aPerformance.playID());
    }

    private int amountFor(final Performance aPerformance, final Play play) {
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
