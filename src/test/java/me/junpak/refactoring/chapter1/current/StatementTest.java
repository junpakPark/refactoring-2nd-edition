package me.junpak.refactoring.chapter1.current;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import me.junpak.refactoring.chapter1.current.data.Invoice;
import me.junpak.refactoring.chapter1.current.data.Play;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StatementTest {

    private Map<String, Play> plays;
    private List<Invoice> invoices;

    @BeforeEach
    void setUp() throws Exception {
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
            this.invoices = mapper.readValue(invoicesStream, new TypeReference<>() {
            });
        }
    }

    @Test
    void statement() {
        // given
        final Statement statement = new Statement();
        final Invoice invoice = invoices.get(0);
        final String expected = """
                청구 내역 (고객명: BigCo)
                  Hamlet: $650.00원 (55석)
                  As You Like It: $580.00원 (35석)
                  Othello: $500.00원 (40석)
                총액: $1,730.00원
                적립 포인트: 47점
                """;

        // when
        final String result = statement.statement(invoice, plays);

        // then
        assertThat(result).isEqualTo(expected);
    }

}
