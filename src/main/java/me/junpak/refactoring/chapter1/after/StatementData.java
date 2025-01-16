package me.junpak.refactoring.chapter1.after;

import java.util.List;

public record StatementData(
        String customer,
        List<EnrichPerformance> performances
) {
}
