package me.junpak.refactoring.chapter1.after;

import static org.assertj.core.api.Assertions.assertThat;

import me.junpak.refactoring.chapter1.Ch01Test;
import me.junpak.refactoring.chapter1.data.Invoice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class StatementTest extends Ch01Test {

    private Statement sut;
    private Invoice invoice;

    @BeforeEach
    void setUp() {
        sut = new Statement();
        invoice = invoices.get(0);
    }

    @Test
    void statement() {
        // given
        final String expected = """
                청구 내역 (고객명: BigCo)
                  Hamlet: $650.00원 (55석)
                  As You Like It: $580.00원 (35석)
                  Othello: $500.00원 (40석)
                총액: $1,730.00원
                적립 포인트: 47점
                """;

        // when
        final String result = sut.statement(invoice, plays);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void htmlStatement() {
        // given
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
        final String result = sut.htmlStatement(invoice, plays);

        // then
        assertThat(result).isEqualTo(expected);
    }

}
