package me.junpak.refactoring.chapter1.current.model.data;

import java.util.List;

public record Invoice(
        String customer,
        List<Performance> performances
) {
}
