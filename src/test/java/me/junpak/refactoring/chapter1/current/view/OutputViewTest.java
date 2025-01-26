package me.junpak.refactoring.chapter1.current.view;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import me.junpak.refactoring.chapter1.current.model.StatementFactory;
import me.junpak.refactoring.chapter1.current.model.calculator.PerformanceCalculatorComposite;
import me.junpak.refactoring.chapter1.current.model.data.Invoice;
import me.junpak.refactoring.chapter1.current.model.data.Play;
import me.junpak.refactoring.chapter1.current.model.data.Statement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OutputViewTest {

    private final OutputView sut = new OutputView();
    private final StatementFactory factory = new StatementFactory(new PerformanceCalculatorComposite());

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

            plays = mapper.readValue(playsStream, new TypeReference<>() {
            });
            invoices = mapper.readValue(invoicesStream, new TypeReference<>() {
            });
        }
    }

    @Test
    void renderPlainText() {
        // given
        final Statement data = factory.createStatement(invoices.get(0), plays);
        final String expected = """
                청구 내역 (고객명: BigCo)
                  Hamlet: $650.00원 (55석)
                  As You Like It: $580.00원 (35석)
                  Othello: $500.00원 (40석)
                총액: $1,730.00원
                적립 포인트: 47점
                """;

        // when
        final String result = sut.renderPlainText(data);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void renderHtml() {
        // given
        final Statement data = factory.createStatement(invoices.get(0), plays);
        final String expected = """
                <h1>청구 내역 (고객명: BigCo)</h1>
                <table>
                  <tr><th>연극</th><th>좌석 수</th><th>금액</th></tr>
                  <tr><td>Hamlet</td><td>55</td><td>$650.00</td></tr>
                  <tr><td>As You Like It</td><td>35</td><td>$580.00</td></tr>
                  <tr><td>Othello</td><td>40</td><td>$500.00</td></tr>
                </table>
                <p>총액: <em>$1,730.00</em></p>
                <p>적립 포인트: <em>47</em>점</p>
                """;

        // when
        final String result = sut.renderHtml(data);

        // then
        assertThat(result).isEqualTo(expected);
    }
}
