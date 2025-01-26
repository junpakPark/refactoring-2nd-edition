package me.junpak.refactoring.chapter1.after;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import me.junpak.refactoring.chapter1.after.data.Invoice;
import me.junpak.refactoring.chapter1.after.data.Play;

public class Statement {

    public String statement(Invoice invoice, Map<String, Play> plays) {
        return renderPlainText(StatementData.createStatementData(invoice, plays));
    }

    public String htmlStatement(Invoice invoice, Map<String, Play> plays) {
        return renderHtml(StatementData.createStatementData(invoice, plays));
    }

    private String renderPlainText(final StatementData data) {
        var result = new StringBuilder(String.format("청구 내역 (고객명: %s)%n", data.customer()));
        for (var perf : data.performances()) {
            result.append(String.format("  %s: %s원 (%d석)\n", perf.play().name(), usd(perf.amount()), perf.audience()));
        }
        result.append(String.format("총액: %s원\n", usd(data.totalAmount())));
        result.append(String.format("적립 포인트: %d점\n", data.totalVolumeCredits()));

        return result.toString();
    }

    private String renderHtml(final StatementData data) {
        var result = new StringBuilder(String.format("<h1>청구 내역 (고객명: %s)</h1>\n", data.customer()));
        result.append("<table>\n");
        result.append("  <tr><th>연극</th><th>좌석 수</th><th>금액</th></tr>\n");
        for (var perf : data.performances()) {
            result.append(String.format("  <tr><td>%s</td><td>%s</td>", perf.play().name(), perf.audience()));
            result.append(String.format("<td>%s</td></tr>\n", usd(perf.amount())));
        }
        result.append("</table>\n");
        result.append(String.format("<p>총액: <em>%s</em></p>\n", usd(data.totalAmount())));
        result.append(String.format("<p>적립 포인트: <em>%s</em>점</p>\n", data.totalVolumeCredits()));

        return result.toString();
    }

    private String usd(final int aNumber) {
        final NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);

        return format.format(aNumber / 100.0);
    }

}
