package me.junpak.refactoring.chapter1.current.model;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import me.junpak.refactoring.chapter1.current.model.calculator.PerformanceCalculatorComposite;
import me.junpak.refactoring.chapter1.current.model.data.Invoice;
import me.junpak.refactoring.chapter1.current.model.data.Play;
import me.junpak.refactoring.chapter1.current.model.data.Statement;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StatementFactoryTest {

    private StatementFactory sut;
    private Map<String, Play> plays;
    private Invoice invoice;

    @BeforeEach
    void setUp() throws Exception {
        sut = new StatementFactory(new PerformanceCalculatorComposite());

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
    void createStatement() {
        // when
        final Statement actual = sut.createStatement(invoice, plays);

        // then
        SoftAssertions.assertSoftly(softly -> {
            assertThat(actual.customer()).isEqualTo(invoice.customer());
            assertThat(actual.performances()).hasSize(invoice.performances().size());
            assertThat(actual.totalAmount()).isEqualTo(173000);
            assertThat(actual.totalVolumeCredits()).isEqualTo(47);
        });
    }


}
