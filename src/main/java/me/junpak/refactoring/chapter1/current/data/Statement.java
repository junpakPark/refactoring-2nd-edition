package me.junpak.refactoring.chapter1.current.data;

import java.util.List;

public record Statement(
        String customer,
        List<EnrichPerformance> performances,
        int totalAmount,
        int totalVolumeCredits
) {
}
