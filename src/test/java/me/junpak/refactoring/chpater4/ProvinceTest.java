package me.junpak.refactoring.chpater4;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProvinceTest {

    private static final Ch04DataLoader DATA_LOADER = new Ch04DataLoader(
            ProvinceTest.class.getClassLoader(),
            new ObjectMapper()
    );

    private Province asia;

    @BeforeEach
    void setUp() {
        // Arrange
        asia = new Province(DATA_LOADER.sampleProvinceData());
    }

    @Test
    void shortfall() {
        // Action
        final int shortfall = asia.getShortfall();

        // Assert
        assertThat(shortfall).isEqualTo(5);
    }

    @Test
    void profit() {
        // Action
        final int profit = asia.getProfit();

        // Assert
        assertThat(profit).isEqualTo(230);
    }

    @Test
    void changeProduction() {
        // Action
        asia.getProducers().get(0).setProduction("20");

        // Assert
        SoftAssertions.assertSoftly(softly -> {
            assertThat(asia.getShortfall()).isEqualTo(-6);
            assertThat(asia.getProfit()).isEqualTo(292);
        });
    }

    @Test
    void noProducer() {
        // Action
        final Province noProducer = new Province(new ProvinceData("No Producer", new ArrayList<>(), 30, 20));

        // Assert
        SoftAssertions.assertSoftly(softly -> {
            assertThat(noProducer.getShortfall()).isEqualTo(30);
            assertThat(noProducer.getProfit()).isZero();
        });
    }

    @Test
    void zeroDemand() {
        // Action
        asia.setDemand("0");

        // Assert
        SoftAssertions.assertSoftly(softly -> {
            assertThat(asia.getShortfall()).isEqualTo(-25);
            assertThat(asia.getProfit()).isZero();
        });
    }

    @Test
    void negativeDemand() {
        // Action
        asia.setDemand("-1");

        // Assert
        SoftAssertions.assertSoftly(softly -> {
            assertThat(asia.getShortfall()).isEqualTo(-26);
            assertThat(asia.getProfit()).isEqualTo(-10);
        });
    }
}
