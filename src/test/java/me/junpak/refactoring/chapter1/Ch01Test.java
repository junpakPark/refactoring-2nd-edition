package me.junpak.refactoring.chapter1;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Map;
import me.junpak.refactoring.chapter1.data.Invoice;
import me.junpak.refactoring.chapter1.data.Play;
import org.junit.jupiter.api.BeforeEach;

public class Ch01Test {

    private static final Ch01DataLoader DATA_LOADER = new Ch01DataLoader(
            Ch01Test.class.getClassLoader(),
            new ObjectMapper()
    );

    protected Map<String, Play> plays;
    protected List<Invoice> invoices;

    @BeforeEach
    void loadData() {
        plays = DATA_LOADER.loadPlays();
        invoices = DATA_LOADER.loadInvoices();
    }

}
