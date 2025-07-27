package io.github.jk6841.kakaoenterprise.song;

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
    private String explicit;
    private Integer popularity;
    private Integer energy;
    private Integer danceability;
    private Integer positiveness;
    private Integer speechiness;
    private Integer liveness;
    private Integer acousticness;
    private Integer instrumentalness;
    private Integer goodForParty;
    private Integer goodForWorkStudy;
    private Integer goodForRelaxationMeditation;
    private Integer goodForExercise;
    private Integer goodForRunning;
    private Integer goodForYogaStretching;
    private Integer goodForDriving;
    private Integer goodForSocialGatherings;
    private Integer goodForMorningRoutine;
    private List<SimilarSong> similarSongs;
}
