package io.github.jk6841.kakaoenterprise.dataload.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Component
public class FileLineReader {
    private final Path path;

    public FileLineReader(@Value("${app.dataload.json.path}") String path) {
        this.path = Paths.get(path);
    }

    public Flux<String> readLines() {
        return Flux.using(() -> Files.lines(path), Flux::fromStream, Stream::close)
                .subscribeOn(Schedulers.boundedElastic());
    }
}
