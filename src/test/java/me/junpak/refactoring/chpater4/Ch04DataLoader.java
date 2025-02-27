package me.junpak.refactoring.chpater4;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Objects;

public class Ch04DataLoader {

    private final ClassLoader classLoader;
    private final ObjectMapper mapper;

    public Ch04DataLoader(final ClassLoader classLoader, final ObjectMapper mapper) {
        this.classLoader = classLoader;
        this.mapper = mapper;
    }

    public ProvinceData sampleProvinceData() {
        return loadResource("chapter4/province.json", new TypeReference<>() {
        });
    }

    private <T> T loadResource(String path, TypeReference<T> typeReference) {
        try (InputStream inputStream = classLoader.getResourceAsStream(path)) {
            if (Objects.isNull(inputStream)) {
                throw new IllegalStateException("테스트 리소스가 없습니다!: " + path);
            }
            return mapper.readValue(inputStream, typeReference);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
