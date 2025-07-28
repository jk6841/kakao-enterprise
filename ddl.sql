create table `like`
(
    id      bigint auto_increment primary key,
    song_id bigint   null,
    date    datetime null,
    user_id int      null,
);

create index like_date_song_id_index
    on `like` (date, song_id);


create table song
(
    id                             bigint auto_increment primary key,
    artist                         text null,
    title                          varchar(256) null,
    text                           mediumtext null,
    length                         varchar(8) null,
    emotion                        varchar(32) null,
    genre                          varchar(64) null,
    album                          text null,
    release_date                   varchar(32) null,
    song_key                       varchar(8) null,
    tempo double null,
    loudness_db double null,
    time_signature                 varchar(8) null,
    explicit                       varchar(8) null,
    popularity                     int null,
    energy                         int null,
    danceability                   int null,
    positiveness                   int null,
    speechiness                    int null,
    liveness                       int null,
    acousticness                   int null,
    instrumentalness               int null,
    good_for_party                 int null,
    good_for_work_study            int null,
    good_for_relaxation_meditation int null,
    good_for_exercise              int null,
    good_for_running               int null,
    good_for_yoga_stretching       int null,
    good_for_driving               int null,
    good_for_social_gatherings     int null,
    good_for_morning_routine       int null,
    similar_songs                  json null
);

