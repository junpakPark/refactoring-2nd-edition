package me.junpak.refactoring.chapter1.after;

import java.util.List;
import java.util.Map;
import me.junpak.refactoring.chapter1.data.Invoice;
import me.junpak.refactoring.chapter1.data.Play;

public record StatementData(
        String customer,
        List<EnrichPerformance> performances
) {

    public static StatementData createStatementData(final Invoice invoice, final Map<String, Play> plays) {
        final List<EnrichPerformance> performances = invoice.performances().stream()
                .map(performance -> EnrichPerformance.of(performance, plays))
                .toList();

        return new StatementData(invoice.customer(), performances);
    }
}
