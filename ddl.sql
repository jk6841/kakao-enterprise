use assignment;

create table song
(
    id                             bigint auto_increment primary key,
    artist                         varchar(128) null,
    title                          varchar(256) null,
    text                           mediumtext null,
    length                         varchar(8) null,
    emotion                        varchar(32) null,
    genre                          varchar(64) null,
    album                          varchar(255) null,
    release_date                   varchar(10) null,
    year                           int null,
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

create index idx_year_arist_album
    on song (year, artist, album);

create table llike
(
    id        bigint auto_increment
        primary key,
    song_id   bigint   null,
    date_time datetime null,
    user_id   bigint   null,
    constraint like_song_id_user_id_uindex
        unique (song_id, user_id),
    constraint llike_song_id_fk
        foreign key (song_id) references song (id)
);

create index like_date_time_song_id_index
    on llike (date_time, song_id);
