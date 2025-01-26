package me.junpak.refactoring.chapter1.current.controller;

import java.util.Map;
import me.junpak.refactoring.chapter1.current.model.StatementFactory;
import me.junpak.refactoring.chapter1.current.model.data.Invoice;
import me.junpak.refactoring.chapter1.current.model.data.Play;
import me.junpak.refactoring.chapter1.current.view.OutputView;

public class StatementController {

    private final StatementFactory factory;
    private final OutputView view;

    public StatementController(final StatementFactory statementFactory, final OutputView outputView) {
        this.factory = statementFactory;
        this.view = outputView;
    }

    public String statement(final Invoice invoice, final Map<String, Play> plays) {
        return view.renderPlainText(factory.createStatement(invoice, plays));
    }

    public String htmlStatement(final Invoice invoice, final Map<String, Play> plays) {
        return view.renderHtml(factory.createStatement(invoice, plays));
    }

}
