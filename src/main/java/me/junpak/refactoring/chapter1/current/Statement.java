package me.junpak.refactoring.chapter1.current;

import java.util.Map;
import me.junpak.refactoring.chapter1.current.data.Invoice;
import me.junpak.refactoring.chapter1.current.data.Play;
import me.junpak.refactoring.chapter1.current.view.OutputView;


public class Statement {


    private final StatementFactory factory;
    private final OutputView view;

    public Statement(final StatementFactory statementFactory, final OutputView outputView) {
        this.factory = statementFactory;
        this.view = outputView;
    }

    public String statement(Invoice invoice, Map<String, Play> plays) {
        return view.renderPlainText(factory.createStatementData(invoice, plays));
    }

    public String htmlStatement(Invoice invoice, Map<String, Play> plays) {
        return view.renderHtml(factory.createStatementData(invoice, plays));
    }

}
