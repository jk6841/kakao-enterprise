package io.github.jk6841.kakaoenterprise.dataload;

import io.github.jk6841.kakaoenterprise.common.song.SimilarSong;
import io.github.jk6841.kakaoenterprise.common.song.Song;
import io.github.jk6841.kakaoenterprise.dataload.file.FileLine;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SongFactory {
    public Song create(FileLine fileLine) {
        List<SimilarSong> similarSongs = getSimilarSongs(fileLine);
        return Song
                .builder()
                .artist(fileLine.artist())
                .title(fileLine.song())
                .text(fileLine.text())
                .length(fileLine.length())
                .emotion(fileLine.emotion())
                .genre(fileLine.genre())
                .album(fileLine.album())
                .releaseDate(fileLine.releaseDate())
                .key(fileLine.key())
                .tempo(fileLine.tempo())
                .loudnessDb(fileLine.loudnessDb())
                .timeSignature(fileLine.timeSignature())
                .explicit(fileLine.explicit().equals("Yes"))
                .popularity(fileLine.popularity())
                .energy(fileLine.energy())
                .danceability(fileLine.danceability())
                .positiveness(fileLine.positiveness())
                .speechiness(fileLine.speechiness())
                .liveness(fileLine.liveness())
                .acousticness(fileLine.acousticness())
                .instrumentalness(fileLine.instrumentalness())
                .goodForParty(fileLine.goodForParty() == 1)
                .goodForWorkStudy(fileLine.goodForWorkStudy() == 1)
                .goodForRelaxationMeditation(fileLine.goodForRelaxationMeditation() == 1)
                .goodForExercise(fileLine.goodForExercise() == 1)
                .goodForRunning(fileLine.goodForRunning() == 1)
                .goodForYogaStretching(fileLine.goodForYogaStretching() == 1)
                .goodForDriving(fileLine.goodForDriving() == 1)
                .goodForSocialGatherings(fileLine.goodForSocialGatherings() == 1)
                .goodForMorningRoutine(fileLine.goodForMorningRoutine() == 1)
                .similarSongs(similarSongs)
                .build();
    }

    private List<SimilarSong> getSimilarSongs(FileLine fileLine) {
        return fileLine.similarSongs().stream()
                .map(this::getSimilarSong).toList();
    }

    private SimilarSong getSimilarSong(FileLine.SimilarSong similarSong) {
        return SimilarSong.builder()
                .similarArtist(similarSong.similarArtist())
                .similarSong(similarSong.similarSong())
                .similarityScore(similarSong.similarityScore())
                .build();
    }
}
