package me.junpak.refactoring.chapter1.current.model.data;

public record EnrichPerformance(
        int audience,
        Play play,
        int amount,
        int volumeCredits
) {
}
