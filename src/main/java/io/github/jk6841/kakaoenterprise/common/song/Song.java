package io.github.jk6841.kakaoenterprise.common.song;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Builder
@Getter
@EqualsAndHashCode
@Table
public class Song {
    @Id
    private Long id;

    private String artist;
    private String title;
    private String text;
    private String length;
    private String emotion;
    private String genre;
    private String album;
    private String releaseDate;
    @Column("song_key")
    private String key;
    private Double tempo;
    private Double loudnessDb;
    private String timeSignature;
    private Boolean explicit;
    private Integer popularity;
    private Integer energy;
    private Integer danceability;
    private Integer positiveness;
    private Integer speechiness;
    private Integer liveness;
    private Integer acousticness;
    private Integer instrumentalness;
    private Boolean goodForParty;
    private Boolean goodForWorkStudy;
    private Boolean goodForRelaxationMeditation;
    private Boolean goodForExercise;
    private Boolean goodForRunning;
    private Boolean goodForYogaStretching;
    private Boolean goodForDriving;
    private Boolean goodForSocialGatherings;
    private Boolean goodForMorningRoutine;
    private List<SimilarSong> similarSongs;
}
