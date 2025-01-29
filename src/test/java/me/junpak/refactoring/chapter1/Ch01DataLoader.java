package me.junpak.refactoring.chapter1;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.Map;
import me.junpak.refactoring.chapter1.data.Invoice;
import me.junpak.refactoring.chapter1.data.Play;

public final class Ch01DataLoader {

    private final ClassLoader classLoader;
    private final ObjectMapper mapper;

    public Ch01DataLoader(final ClassLoader classLoader, final ObjectMapper mapper) {
        this.classLoader = classLoader;
        this.mapper = mapper;
    }

    public Map<String, Play> loadPlays() {
        return loadResource("chapter1/plays.json", new TypeReference<>() {
        });
    }

    public List<Invoice> loadInvoices() {
        return loadResource("chapter1/invoices.json", new TypeReference<>() {
        });
    }

    private <T> T loadResource(String path, TypeReference<T> typeReference) {
        try (InputStream inputStream = classLoader.getResourceAsStream(path)) {
            if (inputStream == null) {
                throw new IllegalStateException("테스트 리소스가 없습니다!: " + path);
            }
            return mapper.readValue(inputStream, typeReference);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
