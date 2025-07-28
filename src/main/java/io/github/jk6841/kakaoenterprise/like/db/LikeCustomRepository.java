package io.github.jk6841.kakaoenterprise.like.db;

import io.github.jk6841.kakaoenterprise.like.db.dto.LikedSong;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.convert.MappingR2dbcConverter;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@RequiredArgsConstructor
@Repository
public class LikeCustomRepository {
    private static final String QUERY_TRENDING_SONGS = """
            select likes, s.id as id, title, artist, release_date from
            ( select count(*) as likes, song_id from `like` where date between :from and :to group by song_id order by count(*) desc limit :size ) l 
            left join 
            song s 
            on l.song_id = s.id
            """.trim();
    private final R2dbcEntityTemplate template;
    private final MappingR2dbcConverter converter;

    public Flux<LikedSong> getTrendingSongs(int size) {
        Instant now = Instant.now();
        return template.getDatabaseClient()
                .sql(QUERY_TRENDING_SONGS)
                .bind("from", now.minus(1, ChronoUnit.HOURS).toString())
                .bind("to", now.toString())
                .bind("size", size)
                .map((row, metadata) -> converter.read(LikedSong.class, row, metadata))
                .all();
    }
}
