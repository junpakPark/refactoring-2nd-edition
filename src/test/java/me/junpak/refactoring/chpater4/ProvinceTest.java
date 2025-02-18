package me.junpak.refactoring.chpater4;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

class ProvinceTest {

    private static final Ch04DataLoader DATA_LOADER = new Ch04DataLoader(
            ProvinceTest.class.getClassLoader(),
            new ObjectMapper()
    );

    @Test
    void shortfall() {
        // Assert
        final Province asia = new Province(DATA_LOADER.sampleProvinceData());

        // Action
        final int shortfall = asia.getShortfall();

        // Assert
        assertThat(shortfall).isEqualTo(5);
    }

    @Test
    void profit() {
        // Assert
        final Province asia = new Province(DATA_LOADER.sampleProvinceData());

        // Action
        final int profit = asia.getProfit();

        // Assert
        assertThat(profit).isEqualTo(230);
    }

}
