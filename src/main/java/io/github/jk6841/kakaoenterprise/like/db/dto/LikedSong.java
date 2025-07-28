package io.github.jk6841.kakaoenterprise.like.db.dto;

public record LikedSong(
        Integer likes,
        Long id,
        String title,
        String artist,
        String releaseDate
) {
}
