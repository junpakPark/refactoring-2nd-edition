package me.junpak.refactoring.chapter1.after;

import java.util.List;
import me.junpak.refactoring.chapter1.data.Performance;

public record StatementData(
        String customer,
        List<Performance> performances
) {
}
