package io.github.jk6841.kakaoenterprise.api.song;

import io.github.jk6841.kakaoenterprise.api.song.db.dto.YearlyArtistAlbumCount;
import io.r2dbc.spi.Statement;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Repository
public class SongCustomRepository {
    private final DatabaseClient databaseClient;

    private final static String SQL_TEMPLATE = """
                select year,artist, COUNT(DISTINCT album) AS album_count from song
                group by year, artist
                order by year, artist
                limit ? offset ?
            """;

    private final static String SQL_TOTAL_TEMPLATE = """
                select count(*) as count from (
                    select year, artist, COUNT(DISTINCT album) AS album_count from song
                    group by year, artist
                    order by year, artist
                ) as grouped
            """;

    public Flux<YearlyArtistAlbumCount> getYearlyArtistAlbumCountPage(int page, int size) {
        int offset = (page - 1) * size;
        int limit = size;
        return databaseClient.inConnectionMany(connection -> {
            Statement statement = connection.createStatement(SQL_TEMPLATE);
            statement.bind(0, limit);
            statement.bind(1, offset);
            return Flux.from(statement.execute())
                    .flatMap(result -> result.map((row, meta) ->
                            new YearlyArtistAlbumCount(
                                    row.get("year", Integer.class),
                                    row.get("artist", String.class),
                                    row.get("album_count", Integer.class)
                            )
                    ));
        });
    }

    public Mono<Integer> getTotalYearlyArtistAlbumCount() {
        return databaseClient.inConnection(connection -> {
            Statement statement = connection.createStatement(SQL_TOTAL_TEMPLATE);
            return Flux.from(statement.execute())
                    .flatMap(result -> result.map((row, meta) ->
                            row.get("count", Integer.class)
                    )).single();
        });
    }
}
