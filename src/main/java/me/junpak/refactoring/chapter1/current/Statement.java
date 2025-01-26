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
            final Play play = plays.get(perf.playID());
            var thisAmount = amountFor(perf, play);

            // 포인트를 적립한다.
            volumeCredits += Math.max(perf.audience() - 30, 0);
            // 희극 관객 5명마다 추가 포인트를 제공한다.
            if ("comedy".equals(play.type())) {
                volumeCredits += perf.audience() / 5;
            }

            // 청구 내역을 출력한다.
            result.append(
                            String.format("  %s: %s원 (%d석)", play.name(), format.format(thisAmount / 100.0), perf.audience()))
                    .append(LF);
            totalAmount += thisAmount;
        }

        result.append(String.format("총액: %s원", format.format(totalAmount / 100.0))).append(LF);
        result.append(String.format("적립 포인트: %d점", volumeCredits)).append(LF);

        return result.toString();
    }

    private int amountFor(final Performance perf, final Play play) {
        var thisAmount = 0;

        switch (play.type()) {
            case "tragedy":
                thisAmount = 40000;
                if (perf.audience() > 30) {
                    thisAmount += 1000 * (perf.audience() - 30);
                }
                break;
            case "comedy":
                thisAmount = 30000;
                if (perf.audience() > 20) {
                    thisAmount += 10000 + 500 * (perf.audience() - 20);
                }
                thisAmount += 300 * perf.audience();
                break;
            default:
                throw new IllegalArgumentException("알 수 없는 장르: " + play.type());
        }
        return thisAmount;
    }

}
