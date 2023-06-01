CREATE TYPE POST_CATEGORY AS ENUM ('POLITICS', 'TECHNOLOGIES', 'HISTORY');
ALTER TYPE POST_CATEGORY OWNER TO socnet;

CREATE TABLE post
(
    id           VARCHAR       NOT NULL,
    updated_date TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_date TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    title        VARCHAR(1024) NOT NULL,
    author       VARCHAR(1024) NOT NULL,
    link         VARCHAR(1024) NOT NULL,
    category     POST_CATEGORY NOT NULL,
    text         VARCHAR(2000) NOT NULL,
    likes        INTEGER       NOT NULL,
    dislikes     INTEGER       NOT NULL,
    is_ad        BOOLEAN       NOT NULL,
    nsfw         BOOLEAN       NOT NULL,
    is_enabled   BOOLEAN       NOT NULL,

    CONSTRAINT pk_com_post
        PRIMARY KEY (id)
);