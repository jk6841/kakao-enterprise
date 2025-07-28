package io.github.jk6841.kakaoenterprise.like.api.dto;

import java.util.List;

public record TrendingSongsResponse(
        List<Song> songs
) {
    public record Song(
            Integer likes,
            Long id,
            String title,
            String artist,
            String releaseDate
    ) { }
}
