package me.junpak.refactoring.chapter1.current.model;

import static org.assertj.core.api.Assertions.assertThat;

import me.junpak.refactoring.chapter1.Ch01Test;
import me.junpak.refactoring.chapter1.current.model.calculator.PerformanceCalculatorComposite;
import me.junpak.refactoring.chapter1.current.model.data.EnrichPerformance;
import me.junpak.refactoring.chapter1.data.Performance;
import me.junpak.refactoring.chapter1.data.Play;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PerformanceCalculatorTest extends Ch01Test {

    private PerformanceCalculator sut;
    private Performance performance;
    private Play play;

    @BeforeEach
    void setUp() {
        sut = new PerformanceCalculatorComposite();
        performance = invoices.get(0).performances().get(0);
        play = plays.get(performance.playID());
    }

    @Test
    void enrichPerformance() {
        // when
        final EnrichPerformance actual = sut.enrichPerformance(performance, play);

        // then
        SoftAssertions.assertSoftly(softly -> {
            assertThat(actual.audience()).isEqualTo(performance.audience());
            assertThat(actual.playName()).isEqualTo(play.name());
            assertThat(actual.amount()).isEqualTo(65000);
            assertThat(actual.volumeCredits()).isEqualTo(25);
        });
    }

}
