package me.junpak.refactoring.chapter1.current;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import me.junpak.refactoring.chapter1.current.data.EnrichPerformance;
import me.junpak.refactoring.chapter1.current.data.Invoice;
import me.junpak.refactoring.chapter1.current.data.Performance;
import me.junpak.refactoring.chapter1.current.data.Play;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StatementFactoryTest {

    private StatementFactory sut;
    private Map<String, Play> plays;
    private Invoice invoice;

    @BeforeEach
    void setUp() throws Exception {
        sut = new StatementFactory();

        final ClassLoader classLoader = getClass().getClassLoader();
        final ObjectMapper mapper = new ObjectMapper();

        try (
                final InputStream playsStream = classLoader.getResourceAsStream("chapter1/plays.json");
                final InputStream invoicesStream = classLoader.getResourceAsStream("chapter1/invoices.json");
        ) {

            if (playsStream == null || invoicesStream == null) {
                throw new IllegalStateException("테스트 리소스가 없습니다!");
            }

            this.plays = mapper.readValue(playsStream, new TypeReference<>() {
            });
            final List<Invoice> invoices = mapper.readValue(invoicesStream, new TypeReference<>() {
            });
            this.invoice = invoices.get(0);
        }
    }

    @Test
    void enrichPerformance() {
        // given
        final Performance performance = invoice.performances().get(0);
        final Play play = plays.get(performance.playID());

        // when
        final EnrichPerformance actual = sut.enrichPerformance(performance, play);

        // then
        SoftAssertions.assertSoftly(softly -> {
            assertThat(actual.playID()).isEqualTo(performance.playID());
            assertThat(actual.audience()).isEqualTo(performance.audience());
            assertThat(actual.play()).isEqualTo(play);
        });
    }

}
