CREATE TABLE IF NOT EXISTS workouts (
    date TIMESTAMP NOT NULL,
    muscle text NOT NULL,
    exercise text NOT NULL,
    kg varchar NOT NULL,
    rep varchar(255) NOT NULL,
    distance varchar(255) NOT NULL,
    time varchar(255) NOT NULL,
    cal varchar(255),
    comment text
);
