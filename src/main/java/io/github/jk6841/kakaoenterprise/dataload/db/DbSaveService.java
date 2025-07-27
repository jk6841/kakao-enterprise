package io.github.jk6841.kakaoenterprise.dataload.db;

import io.github.jk6841.kakaoenterprise.song.Song;
import io.r2dbc.spi.Statement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class DbSaveService {
    private final DatabaseClient databaseClient;
    private final QueryGenerator queryGenerator;

    public Mono<Void> insertInBatch(List<Song> songs) {
        return databaseClient.inConnection(connection -> {
            Statement statement = queryGenerator.createStatement(songs, connection);
            return Flux.from(statement.execute()).then();
        });
    }
}
