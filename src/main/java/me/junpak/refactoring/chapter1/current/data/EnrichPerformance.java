package me.junpak.refactoring.chapter1.current.data;

public record EnrichPerformance(
        String playID,
        int audience,
        Play play
) {
}
