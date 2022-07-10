CREATE TABLE USER
(
    id       NUMBER AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(255),
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE GAME
(
    id          NUMBER AUTO_INCREMENT PRIMARY KEY,
    user_id     NUMBER,
    total_point NUMBER,
    total_round NUMBER,
    total_hits  NUMBER,
    is_closed   BOOLEAN
);

CREATE TABLE ROUND
(
    id           NUMBER AUTO_INCREMENT PRIMARY KEY,
    game_id      NUMBER,
    user_id      NUMBER,
    round_number NUMBER,
    movie_id1    NUMBER,
    movie_id2    NUMBER,
    answer       BOOLEAN
);

CREATE TABLE MOVIE
(
    id               NUMBER AUTO_INCREMENT PRIMARY KEY,
    title            VARCHAR(255),
    number_of_votes  NUMBER,
    average_of_votes NUMBER,
    total_round      NUMBER
);

CREATE TABLE RANK
(
    id          NUMBER AUTO_INCREMENT PRIMARY KEY,
    user_id     NUMBER,
    total_point NUMBER
);
