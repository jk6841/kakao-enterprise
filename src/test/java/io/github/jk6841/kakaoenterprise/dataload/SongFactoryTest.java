package io.github.jk6841.kakaoenterprise.dataload;

import io.github.jk6841.kakaoenterprise.dataload.file.FileLine;
import io.github.jk6841.kakaoenterprise.common.song.SimilarSong;
import io.github.jk6841.kakaoenterprise.common.song.Song;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SongFactoryTest {
    private final SongFactory songFactory = new SongFactory();

    @Test
    void testCreate() {
        // given
        var fileLine = new FileLine(
                "!!!",
                "Even When the Waters Cold",
                "Friends told her she was better off at the bottom of a river Than in a bed with him He said \"Until you try both, you won't know what you like better Why don't we go for a swim?\" Well, friends told her this and friends told her that But friends don't choose what echoes in your head When she got bored with all the idle chit-and-chat Kept thinking 'bout what he said I'll swim even when the water's cold That's the one thing that I know Even when the water's cold She remembers it fondly, she doesn't remember it all But what she does, she sees clearly She lost his number, and he never called But all she really lost was an earring The other's in a box with others she has lost I wonder if she still hears me I'll swim even when the water's cold That's the one thing that I know Even when the water's cold If you believe in love You know that sometimes it isn't Do you believe in love? Then save the bullshit questions Sometimes it is and sometimes it isn't Sometimes it's just how the light hits their eyes Do you believe in love?",
                "03:47",
                "sadness",
                "hip hop",
                "Thr!!!er",
                "2013-04-29",
                "D min",
                0.4378698225,
                0.785065407,
                "4/4",
                "No",
                40,
                83,
                71,
                87,
                4,
                16,
                11,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                List.of(
                        new FileLine.SimilarSong("Corey Smith", "If I Could Do It Again", 0.9860607848),
                        new FileLine.SimilarSong("Toby Keith", "Drinks After Work", 0.9837194774),
                        new FileLine.SimilarSong("Space", "Neighbourhood", 0.9832363508)
                )
        );
        var expected = Song.builder()
                .artist("!!!")
                .title("Even When the Waters Cold")
                .text("Friends told her she was better off at the bottom of a river Than in a bed with him He said \"Until you try both, you won't know what you like better Why don't we go for a swim?\" Well, friends told her this and friends told her that But friends don't choose what echoes in your head When she got bored with all the idle chit-and-chat Kept thinking 'bout what he said I'll swim even when the water's cold That's the one thing that I know Even when the water's cold She remembers it fondly, she doesn't remember it all But what she does, she sees clearly She lost his number, and he never called But all she really lost was an earring The other's in a box with others she has lost I wonder if she still hears me I'll swim even when the water's cold That's the one thing that I know Even when the water's cold If you believe in love You know that sometimes it isn't Do you believe in love? Then save the bullshit questions Sometimes it is and sometimes it isn't Sometimes it's just how the light hits their eyes Do you believe in love?")
                .length("03:47")
                .emotion("sadness")
                .genre("hip hop")
                .album("Thr!!!er")
                .releaseDate("2013-04-29")
                .key("D min")
                .tempo(0.4378698225)
                .loudnessDb(0.785065407)
                .timeSignature("4/4")
                .explicit("No")
                .popularity(40)
                .energy(83)
                .danceability(71)
                .positiveness(87)
                .speechiness(4)
                .liveness(16)
                .acousticness(11)
                .instrumentalness(0)
                .goodForParty(0)
                .goodForWorkStudy(0)
                .goodForRelaxationMeditation(0)
                .goodForExercise(0)
                .goodForRunning(0)
                .goodForYogaStretching(0)
                .goodForDriving(0)
                .goodForSocialGatherings(0)
                .goodForMorningRoutine(0)
                .similarSongs(List.of(
                        SimilarSong.builder()
                                .similarArtist("Corey Smith")
                                .similarSong("If I Could Do It Again")
                                .similarityScore(0.9860607848)
                                .build(),
                        SimilarSong.builder()
                                .similarArtist("Toby Keith")
                                .similarSong("Drinks After Work")
                                .similarityScore(0.9837194774)
                                .build(),
                        SimilarSong.builder()
                                .similarArtist("Space")
                                .similarSong("Neighbourhood")
                                .similarityScore(0.9832363508)
                                .build()
                ))
                .build();

        // when
        var actual = songFactory.create(fileLine);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}