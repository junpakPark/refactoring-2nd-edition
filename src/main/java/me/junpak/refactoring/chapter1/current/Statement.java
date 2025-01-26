package me.junpak.refactoring.chapter1.current;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import me.junpak.refactoring.chapter1.current.data.Invoice;
import me.junpak.refactoring.chapter1.current.data.Play;
import me.junpak.refactoring.chapter1.current.data.StatementData;


public class Statement {

    public static final String LF = System.lineSeparator();
    public static final NumberFormat US_CURRENCY_FORMAT = NumberFormat.getCurrencyInstance(Locale.US);

    private final StatementFactory factory;

    public Statement(final StatementFactory statementFactory) {
        this.factory = statementFactory;
    }

    public String statement(Invoice invoice, Map<String, Play> plays) {
        final StatementData data = createStatementData(invoice, plays);
        return renderPlainText(data);
    }

    private StatementData createStatementData(final Invoice invoice, final Map<String, Play> plays) {
        return new StatementData(
                invoice.customer(),
                invoice.performances().stream()
                        .map(performance -> factory.enrichPerformance(performance, plays.get(performance.playID())))
                        .toList()
        );
    }

    private String renderPlainText(final StatementData data) {
        var result = new StringBuilder("청구 내역 (고객명: " + data.customer() + ")").append(LF);

        for (var perf : data.performances()) {
            result.append(String.format("  %s: %s원 (%d석)", perf.play().name(), usd(perf.amount()),
                    perf.audience())).append(LF);
        }

        result.append(String.format("총액: %s원", usd(totalAmount(data)))).append(LF);
        result.append(String.format("적립 포인트: %d점", totalVolumeCredits(data))).append(LF);

        return result.toString();
    }

    private int totalAmount(final StatementData data) {
        var result = 0;
        for (var perf : data.performances()) {
            result += perf.amount();
        }
        return result;
    }

    private int totalVolumeCredits(final StatementData data) {
        var result = 0;
        for (var perf : data.performances()) {
            result += perf.volumeCredits();
        }
        return result;
    }

    private String usd(final int amount) {
        return US_CURRENCY_FORMAT.format(amount / 100);
    }

}
