package io.github.jk6841.kakaoenterprise.song;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@EqualsAndHashCode
@ToString
public class SimilarSong {
    private String similarArtist;
    private String similarSong;
    private Double similarityScore;
}
