package io.github.jk6841.kakaoenterprise.dataload.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class FileLineDeserializer {
    private final ObjectMapper objectMapper;

    public Mono<FileLine> deserialize(String jsonItem) {
        return Mono.fromCallable(() -> objectMapper.readValue(jsonItem, FileLine.class));
    }
}
