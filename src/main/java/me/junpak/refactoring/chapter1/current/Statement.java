package me.junpak.refactoring.chapter1.current;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import me.junpak.refactoring.chapter1.current.data.Invoice;
import me.junpak.refactoring.chapter1.current.data.Performance;
import me.junpak.refactoring.chapter1.current.data.Play;


public class Statement {

    public static final String LF = System.lineSeparator();
    public static final NumberFormat US_CURRENCY_FORMAT = NumberFormat.getCurrencyInstance(Locale.US);

    public String statement(Invoice invoice, Map<String, Play> plays) {
        var totalAmount = 0;
        var volumeCredits = 0;
        var result = new StringBuilder("청구 내역 (고객명: " + invoice.customer() + ")").append(LF);

        for (var perf : invoice.performances()) {
            // 청구 내역을 출력한다.
            result.append(String.format(
                            "  %s: %s원 (%d석)",
                            plays.get(perf.playID()).name(),
                            usd(amountFor(perf, plays.get(perf.playID()))),
                            perf.audience()
                    ))
                    .append(LF);
            totalAmount += amountFor(perf, plays.get(perf.playID()));
        }
        for (var perf : invoice.performances()) {
            volumeCredits += volumeCreditsFor(plays, perf);
        }

        result.append(String.format("총액: %s원", usd(totalAmount))).append(LF);
        result.append(String.format("적립 포인트: %d점", volumeCredits)).append(LF);

        return result.toString();
    }

    private String usd(final int amount) {
        return US_CURRENCY_FORMAT.format(amount / 100);
    }

    private int volumeCreditsFor(final Map<String, Play> plays, final Performance aPerformance) {
        var result = Math.max(aPerformance.audience() - 30, 0);
        if ("comedy".equals(plays.get(aPerformance.playID()).type())) {
            result += aPerformance.audience() / 5;
        }
        return result;
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

}
