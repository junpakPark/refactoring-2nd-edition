package me.junpak.refactoring.chapter1.after;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import me.junpak.refactoring.chapter1.data.Invoice;
import me.junpak.refactoring.chapter1.data.Play;

public class Statement {

    public String statement(Invoice invoice, Map<String, Play> plays) {
        return renderPlainText(StatementData.createStatementData(invoice, plays));
    }

    private String renderPlainText(final StatementData data) {
        var result = new StringBuilder("청구 내역 (고객명: " + data.customer() + ")\n");
        for (var perf : data.performances()) {
            result.append(
                    String.format(
                            "  %s: %s원 (%d석)\n",
                            perf.play().name(),
                            usd(perf.amount()),
                            perf.audience()
                    )
            );
        }
        result.append(String.format("총액: %s원\n", usd(data.totalAmount())));
        result.append(String.format("적립 포인트: %d점\n", data.totalVolumeCredits()));

        return result.toString();
    }

    private String usd(final int aNumber) {
        final NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);

        return format.format(aNumber / 100.0);
    }

}
