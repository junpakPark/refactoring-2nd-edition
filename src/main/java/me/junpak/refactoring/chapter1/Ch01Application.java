package me.junpak.refactoring.chapter1;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import me.junpak.refactoring.chapter1.after.Statement;
import me.junpak.refactoring.chapter1.data.Invoice;
import me.junpak.refactoring.chapter1.data.Play;

public class Ch01Application {

    private static final String PLAYS_PATH = "chapter1/plays.json";
    private static final String INVOICES_PATH = "chapter1/invoices.json";

    public static void main(String[] args) throws IOException {
        // plays.json, invoices.json 로드
        final Map<String, Play> plays = loadResource(PLAYS_PATH, new TypeReference<>() {
        });
        final List<Invoice> invoices = loadResource(INVOICES_PATH, new TypeReference<>() {
        });

        // 첫 번째 Invoice 기준으로 statement 계산
        final Invoice invoice = invoices.get(0);
        final Statement statement = new Statement();
        final String result = statement.statement(invoice, plays);

        System.out.println(result);
    }

    /**
     * 리소스 폴더(chapter1)에 있는 JSON 파일을 읽어, ObjectMapper로 역직렬화하는 메서드
     */
    private static <T> T loadResource(final String resourcePath, final TypeReference<T> typeRef) throws IOException {
        final ClassLoader classLoader = Ch01Application.class.getClassLoader();
        final ObjectMapper mapper = new ObjectMapper();

        try (InputStream inputStream = classLoader.getResourceAsStream(resourcePath)) {
            if (Objects.isNull(inputStream)) {
                throw new IllegalStateException(resourcePath + " 파일이 없습니다!");
            }
            return mapper.readValue(inputStream, typeRef);
        }
    }

}
