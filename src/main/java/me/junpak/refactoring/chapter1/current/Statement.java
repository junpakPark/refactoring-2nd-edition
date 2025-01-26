package me.junpak.refactoring.chapter1.current;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import me.junpak.refactoring.chapter1.current.data.Invoice;
import me.junpak.refactoring.chapter1.current.data.Performance;
import me.junpak.refactoring.chapter1.current.data.Play;


public class Statement {

    public static final String LF = System.lineSeparator();

    public String statement(Invoice invoice, Map<String, Play> plays) {
        var totalAmount = 0;
        var volumeCredits = 0;
        var result = new StringBuilder("청구 내역 (고객명: " + invoice.customer() + ")").append(LF);
        final NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);

        for (var perf : invoice.performances()) {

            // 포인트를 적립한다.
            volumeCredits += volumeCreditsFor(plays, perf);

            // 청구 내역을 출력한다.
            result.append(String.format(
                            "  %s: %s원 (%d석)",
                            plays.get(perf.playID()).name(),
                            format.format(amountFor(perf, plays.get(perf.playID())) / 100.0),
                            perf.audience()
                    ))
                    .append(LF);
            totalAmount += amountFor(perf, plays.get(perf.playID()));
        }

        result.append(String.format("총액: %s원", format.format(totalAmount / 100.0))).append(LF);
        result.append(String.format("적립 포인트: %d점", volumeCredits)).append(LF);

        return result.toString();
    }

    private int volumeCreditsFor(final Map<String, Play> plays, final Performance aPerformance) {
        var volumeCredits = Math.max(aPerformance.audience() - 30, 0);
        if ("comedy".equals(plays.get(aPerformance.playID()).type())) {
            volumeCredits += aPerformance.audience() / 5;
        }
        return volumeCredits;
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
