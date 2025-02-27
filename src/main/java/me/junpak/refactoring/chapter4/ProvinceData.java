package me.junpak.refactoring.chapter4;

import java.util.List;

public record ProvinceData(
        String name,
        List<Producer> producers,
        int demand,
        int price
) {
}
