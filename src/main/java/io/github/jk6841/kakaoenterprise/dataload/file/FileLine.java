package io.github.jk6841.kakaoenterprise.dataload.file;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record FileLine(
        @JsonProperty("Artist(s)")
        String artist,

        @JsonProperty("song")
        String song,

        @JsonProperty("text")
        String text,

        @JsonProperty("Length")
        String length,

        @JsonProperty("emotion")
        String emotion,

        @JsonProperty("Genre")
        String genre,

        @JsonProperty("Album")
        String album,

        @JsonProperty("Release Date")
        String releaseDate,

        @JsonProperty("Key")
        String key,

        @JsonProperty("Tempo")
        Double tempo,

        @JsonProperty("Loudness (db)")
        Double loudnessDb,

        @JsonProperty("Time signature")
        String timeSignature,

        @JsonProperty("Explicit")
        String explicit,

        @JsonProperty("Popularity")
        Integer popularity,

        @JsonProperty("Energy")
        Integer energy,

        @JsonProperty("Danceability")
        Integer danceability,

        @JsonProperty("Positiveness")
        Integer positiveness,

        @JsonProperty("Speechiness")
        Integer speechiness,

        @JsonProperty("Liveness")
        Integer liveness,

        @JsonProperty("Acousticness")
        Integer acousticness,

        @JsonProperty("Instrumentalness")
        Integer instrumentalness,

        @JsonProperty("Good for Party")
        Integer goodForParty,

        @JsonProperty("Good for Work/Study")
        Integer goodForWorkStudy,

        @JsonProperty("Good for Relaxation/Meditation")
        Integer goodForRelaxationMeditation,

        @JsonProperty("Good for Exercise")
        Integer goodForExercise,

        @JsonProperty("Good for Running")
        Integer goodForRunning,

        @JsonProperty("Good for Yoga/Stretching")
        Integer goodForYogaStretching,

        @JsonProperty("Good for Driving")
        Integer goodForDriving,

        @JsonProperty("Good for Social Gatherings")
        Integer goodForSocialGatherings,

        @JsonProperty("Good for Morning Routine")
        Integer goodForMorningRoutine,

        @JsonProperty("Similar Songs")
        List<SimilarSong> similarSongs
) {
    public record SimilarSong(
            @JsonAlias({"Similar Artist 1", "Similar Artist 2", "Similar Artist 3"})
            String similarArtist,

            @JsonAlias({"Similar Song 1", "Similar Song 2", "Similar Song 3"})
            String similarSong,

            @JsonProperty("Similarity Score")
            Double similarityScore
    ) {}
}