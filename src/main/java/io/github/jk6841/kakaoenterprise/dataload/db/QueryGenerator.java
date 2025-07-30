package io.github.jk6841.kakaoenterprise.dataload.db;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.jk6841.kakaoenterprise.common.song.Song;
import io.r2dbc.spi.Connection;
import io.r2dbc.spi.Statement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
class QueryGenerator {
    private final ObjectMapper objectMapper;

    private static final String SQL_TEMPLATE = """
            insert into song (
                artist, title, text, 
                length, emotion, genre, 
                album, release_date, year, song_key, 
                tempo, loudness_db, time_signature, 
                explicit, popularity, energy, 
                danceability, positiveness, speechiness, 
                liveness, acousticness, instrumentalness, 
                good_for_party, good_for_work_study, good_for_relaxation_meditation, 
                good_for_exercise, good_for_running, good_for_yoga_stretching,
                good_for_driving, good_for_social_gatherings, good_for_morning_routine, similar_songs
            ) values (
                ?, ?, ?, 
                ?, ?, ?, 
                ?, ?, ?, 
                ?, ?, ?, 
                ?, ?, ?, 
                ?, ?, ?, 
                ?, ?, ?, 
                ?, ?, ?, 
                ?, ?, ?, 
                ?, ?, ?, 
                ?, ?
            )
            """;
    Statement createStatement(List<Song> songs, Connection connection) {
        Statement statement = connection.createStatement(SQL_TEMPLATE);
        int chunkSize = songs.size();
        for (int i = 0; i < chunkSize; i++) {
            Song song = songs.get(i);
            String releaseDate = song.getReleaseDate();
            Integer year = (releaseDate == null)? null  : LocalDate.parse(releaseDate).getYear();
            Arrays.stream(song.getArtist().split(",")).forEach( artist -> {
                bindParameter(artist,0, statement);
                bindParameter(song.getTitle(), 1, statement);
                bindParameter(song.getText(), 2, statement);
                bindParameter(song.getLength(),3, statement);
                bindParameter(song.getEmotion(), 4, statement);
                bindParameter(song.getGenre(),  5, statement);
                bindParameter(song.getAlbum(), 6, statement);
                bindParameter(releaseDate,7, statement);
                bindParameter(year,8, statement);
                bindParameter(song.getKey(), 9, statement);
                bindParameter(song.getTempo(),  10, statement);
                bindParameter(song.getLoudnessDb(), 11, statement);
                bindParameter(song.getTimeSignature(), 12, statement);
                bindParameter(song.getExplicit(), 13, statement);
                bindParameter(song.getPopularity(),14, statement);
                bindParameter(song.getEnergy(),15, statement);
                bindParameter(song.getDanceability(), 16, statement);
                bindParameter(song.getPositiveness(), 17, statement);
                bindParameter(song.getSpeechiness(), 18, statement);
                bindParameter(song.getLiveness(), 19, statement);
                bindParameter(song.getAcousticness(), 20, statement);
                bindParameter(song.getInstrumentalness(),21, statement);
                bindParameter(song.getGoodForParty(), 22, statement);
                bindParameter(song.getGoodForWorkStudy(), 23, statement);
                bindParameter(song.getGoodForRelaxationMeditation(), 24, statement);
                bindParameter(song.getGoodForExercise(), 25, statement);
                bindParameter(song.getGoodForRunning(), 26, statement);
                bindParameter(song.getGoodForYogaStretching(), 27, statement);
                bindParameter(song.getGoodForDriving(),  28, statement);
                bindParameter(song.getGoodForSocialGatherings(),  29, statement);
                bindParameter(song.getGoodForMorningRoutine(), 30, statement);
                bindParameter(song.getSimilarSongs(),  31, statement);
            });

            if (i != chunkSize - 1) {
                statement.add();
            }
        }
        return statement;
    }

    private void bindParameter(String value, int index, Statement statement) {
        bindParameter(value, String.class, index, statement);
    }

    private void bindParameter(Double value, int index, Statement statement) {
        bindParameter(value, Double.class, index, statement);
    }

    private void bindParameter(Integer value, int index, Statement statement) {
        bindParameter(value, Integer.class, index, statement);
    }

    private void bindParameter(Boolean value, int index, Statement statement) {
        bindParameter(value, Boolean.class, index, statement);
    }

    private void bindParameter(Object value, int index, Statement statement) {
        try {
            String valueString = objectMapper.writeValueAsString(value);
            bindParameter(valueString, Object.class, index, statement);
        } catch (JsonProcessingException e) {
            log.warn(e.getMessage());
        }
    }

    private void bindParameter(Object value, Class<?> clazz, int index, Statement statement) {
        if (value == null) {
            statement.bindNull(index, clazz);
        } else {
            statement.bind(index, value);
        }
    }
}
