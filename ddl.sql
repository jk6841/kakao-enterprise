use assignment;

create table song
(
    id                             bigint auto_increment primary key,
    artist                         varchar(128) null,
    title                          varchar(256) null,
    text                           mediumtext null,
    length                         char(5) null,
    emotion                        varchar(16) null,
    genre                          varchar(64) null,
    album                          varchar(255) null,
    release_date                   varchar(10) null,
    year                           smallint null,
    song_key                       varchar(6) null,
    tempo double null,
    loudness_db double null,
    time_signature                 char(3) null,
    explicit                       tinyint null,
    popularity                     tinyint null,
    energy                         tinyint null,
    danceability                   tinyint null,
    positiveness                   tinyint null,
    speechiness                    tinyint null,
    liveness                       tinyint null,
    acousticness                   tinyint null,
    instrumentalness               tinyint null,
    good_for_party                 tinyint null,
    good_for_work_study            tinyint null,
    good_for_relaxation_meditation tinyint null,
    good_for_exercise              tinyint null,
    good_for_running               tinyint null,
    good_for_yoga_stretching       tinyint null,
    good_for_driving               tinyint null,
    good_for_social_gatherings     tinyint null,
    good_for_morning_routine       tinyint null,
    similar_songs                  json null
);

create index idx_year_arist_album
    on song (year, artist, album);

create table llike
(
    id        bigint auto_increment
        primary key,
    song_id   bigint null,
    date_time datetime null,
    user_id   bigint null,
    constraint like_song_id_user_id_uindex
        unique (song_id, user_id),
    constraint llike_song_id_fk
        foreign key (song_id) references song (id)
);

create index like_date_time_song_id_index
    on llike (date_time, song_id);
