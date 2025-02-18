package me.junpak.refactoring.chpater4;

import com.fasterxml.jackson.databind.ObjectMapper;

class ProvinceTest {

    private static final Ch04DataLoader DATA_LOADER = new Ch04DataLoader(
            ProvinceTest.class.getClassLoader(),
            new ObjectMapper()
    );

}
