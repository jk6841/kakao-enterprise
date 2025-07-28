package io.github.jk6841.kakaoenterprise.dataload.file;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;

class FileLineReaderTest {
    private static final String SAMPLE_DATA_PATH = "./src/test/resources/sample_data.json";
    private final FileLineReader fileLineReader = new FileLineReader(SAMPLE_DATA_PATH);

    @Test
    void testReadLines() {
        StepVerifier.create(fileLineReader.readLines())
                .assertNext(line -> {
                    assertThat(line).isEqualTo("""
                            {"Artist(s)":"!!!","song":"Even When the Waters Cold","text":"Friends told her she was better off at the bottom of a river Than in a bed with him He said \\"Until you try both, you won't know what you like better Why don't we go for a swim?\\" Well, friends told her this and friends told her that But friends don't choose what echoes in your head When she got bored with all the idle chit-and-chat Kept thinking 'bout what he said I'll swim even when the water's cold That's the one thing that I know Even when the water's cold She remembers it fondly, she doesn't remember it all But what she does, she sees clearly She lost his number, and he never called But all she really lost was an earring The other's in a box with others she has lost I wonder if she still hears me I'll swim even when the water's cold That's the one thing that I know Even when the water's cold If you believe in love You know that sometimes it isn't Do you believe in love? Then save the bullshit questions Sometimes it is and sometimes it isn't Sometimes it's just how the light hits their eyes Do you believe in love?","Length":"03:47","emotion":"sadness","Genre":"hip hop","Album":"Thr!!!er","Release Date":"2013-04-29","Key":"D min","Tempo":0.4378698225,"Loudness (db)":0.785065407,"Time signature":"4\\/4","Explicit":"No","Popularity":"40","Energy":"83","Danceability":"71","Positiveness":"87","Speechiness":"4","Liveness":"16","Acousticness":"11","Instrumentalness":"0","Good for Party":0,"Good for Work\\/Study":0,"Good for Relaxation\\/Meditation":0,"Good for Exercise":0,"Good for Running":0,"Good for Yoga\\/Stretching":0,"Good for Driving":0,"Good for Social Gatherings":0,"Good for Morning Routine":0,"Similar Songs":[{"Similar Artist 1":"Corey Smith","Similar Song 1":"If I Could Do It Again","Similarity Score":0.9860607848},{"Similar Artist 2":"Toby Keith","Similar Song 2":"Drinks After Work","Similarity Score":0.9837194774},{"Similar Artist 3":"Space","Similar Song 3":"Neighbourhood","Similarity Score":0.9832363508}]}
                            """.trim());
                })
                .assertNext(line -> {
                    assertThat(line).isEqualTo("""
                            {"Artist(s)":"!!!","song":"One Girl \\/ One Boy","text":"Well I heard it, playing soft From a drunken bar's jukebox And for a moment I was lost In remembering, what I never forgot And I never felt guilt about The trouble that we got into I just couldn't let that honey hide inside of you And just because now it's different Doesn't change what it meant And when I hear that song You know I'm only gonna Think about one girl I think about you When I sing this song You know I'm only gonna Sing it for one girl Ooh ooh ooh Well I heard it, playing loud I never knew what it was about Till I fell silent in a crowd I just turned around and walked straight out 'Cause I guess I felt guilty About the trouble that I caused you Putting myself first, just like I always do But that doesn't change how I feel 'Cause when I hear it, it feels real And when I hear that song You know I'm only gonna Think about one boy I think about you And when I sing this song You know I'm only gonna Sing it for one boy Ooh ooh ooh Well, I can't forget Things you said or your kisses And I keep your secrets Where I keep your promises But you need my confessions About as much as you need my lies And I guess it took that song to make me realize [Instrumental Break]","Length":"04:03","emotion":"sadness","Genre":"hip hop","Album":"Thr!!!er","Release Date":"2013-04-29","Key":"A# min","Tempo":0.5088757396,"Loudness (db)":0.8050508721,"Time signature":"4\\/4","Explicit":"No","Popularity":"42","Energy":"85","Danceability":"70","Positiveness":"87","Speechiness":"4","Liveness":"32","Acousticness":"0","Instrumentalness":"0","Good for Party":0,"Good for Work\\/Study":0,"Good for Relaxation\\/Meditation":0,"Good for Exercise":0,"Good for Running":0,"Good for Yoga\\/Stretching":0,"Good for Driving":0,"Good for Social Gatherings":0,"Good for Morning Routine":0,"Similar Songs":[{"Similar Artist 1":"Hiroyuki Sawano","Similar Song 1":"BRE@TH\\/\\/LESS","Similarity Score":0.9954090051},{"Similar Artist 2":"When In Rome","Similar Song 2":"Heaven Knows","Similarity Score":0.9909052447},{"Similar Artist 3":"Justice Crew","Similar Song 3":"Everybody","Similarity Score":0.9844825577}]}
                            """.trim());
                })
                .verifyComplete();
    }
}