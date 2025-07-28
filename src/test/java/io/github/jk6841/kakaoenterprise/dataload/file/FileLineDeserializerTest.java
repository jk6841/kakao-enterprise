package io.github.jk6841.kakaoenterprise.dataload.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FileLineDeserializerTest {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final FileLineDeserializer fileLineDeserializer = new FileLineDeserializer(objectMapper);

    @Test
    void testDeserialize() {
        // given
        var jsonLine = """
                {"Artist(s)":"!!!","song":"Even When the Waters Cold","text":"Friends told her she was better off at the bottom of a river Than in a bed with him He said \\"Until you try both, you won't know what you like better Why don't we go for a swim?\\" Well, friends told her this and friends told her that But friends don't choose what echoes in your head When she got bored with all the idle chit-and-chat Kept thinking 'bout what he said I'll swim even when the water's cold That's the one thing that I know Even when the water's cold She remembers it fondly, she doesn't remember it all But what she does, she sees clearly She lost his number, and he never called But all she really lost was an earring The other's in a box with others she has lost I wonder if she still hears me I'll swim even when the water's cold That's the one thing that I know Even when the water's cold If you believe in love You know that sometimes it isn't Do you believe in love? Then save the bullshit questions Sometimes it is and sometimes it isn't Sometimes it's just how the light hits their eyes Do you believe in love?","Length":"03:47","emotion":"sadness","Genre":"hip hop","Album":"Thr!!!er","Release Date":"2013-04-29","Key":"D min","Tempo":0.4378698225,"Loudness (db)":0.785065407,"Time signature":"4\\/4","Explicit":"No","Popularity":"40","Energy":"83","Danceability":"71","Positiveness":"87","Speechiness":"4","Liveness":"16","Acousticness":"11","Instrumentalness":"0","Good for Party":0,"Good for Work\\/Study":0,"Good for Relaxation\\/Meditation":0,"Good for Exercise":0,"Good for Running":0,"Good for Yoga\\/Stretching":0,"Good for Driving":0,"Good for Social Gatherings":0,"Good for Morning Routine":0,"Similar Songs":[{"Similar Artist 1":"Corey Smith","Similar Song 1":"If I Could Do It Again","Similarity Score":0.9860607848},{"Similar Artist 2":"Toby Keith","Similar Song 2":"Drinks After Work","Similarity Score":0.9837194774},{"Similar Artist 3":"Space","Similar Song 3":"Neighbourhood","Similarity Score":0.9832363508}]}
                """.trim();
        var expected = new FileLine(
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

        // when, then
        StepVerifier.create(fileLineDeserializer.deserialize(jsonLine))
                .assertNext(actual -> {
                    assertThat(actual).isEqualTo(expected);
                })
                .verifyComplete();
    }
}