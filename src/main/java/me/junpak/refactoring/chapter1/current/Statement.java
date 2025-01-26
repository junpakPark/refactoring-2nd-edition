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
        final StatementData data = factory.createStatementData(invoice, plays);
        return renderPlainText(data);
    }

    private String renderPlainText(final StatementData data) {
        var result = new StringBuilder("청구 내역 (고객명: " + data.customer() + ")").append(LF);

        for (var perf : data.performances()) {
            result.append(String.format("  %s: %s원 (%d석)", perf.play().name(), usd(perf.amount()),
                    perf.audience())).append(LF);
        }

        result.append(String.format("총액: %s원", usd(data.totalAmount()))).append(LF);
        result.append(String.format("적립 포인트: %d점", data.totalVolumeCredits())).append(LF);

        return result.toString();
    }

    private String usd(final int amount) {
        return US_CURRENCY_FORMAT.format(amount / 100);
    }

}
