package me.junpak.refactoring.chapter1.current.data;

import java.util.List;

public record StatementData(String customer, List<Performance> performances) {
}
