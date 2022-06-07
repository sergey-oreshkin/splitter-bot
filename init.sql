-- don't use this script!
DROP TABLE IF EXISTS split_record;
DROP TABLE IF EXISTS split;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS chat;

--CREATE TABLE users(
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id BIGINT,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    nic_name VARCHAR(255),
    update DATE DEFAULT CURRENT_TIMESTAMP
);

--CREATE TABLE chat(
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    chat_id BIGINT,
    chat_type VARCHAR(255),
    title VARCHAR(255),
    update DATE DEFAULT CURRENT_TIMESTAMP
);

-- CREATE TABLE user_chat(
--     id INT GENERATED ALWAYS AS IDENTITY UNIQUE,
--     user_id INT,
--     chat_id INT,
--     CONSTRAINT user_id
--         FOREIGN KEY (user_id)
--         REFERENCES users(id),
--     CONSTRAINT chat_id
--         FOREIGN KEY (chat_id)
--         REFERENCES chat(id),
--     CONSTRAINT pk
--         PRIMARY KEY (user_id, chat_id)
-- );

--CREATE TABLE split(
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id INT,
    chat_id INT,
    name varchar(255),
    CONSTRAINT fk_users
        FOREIGN KEY (user_id)
        REFERENCES users(id),
    CONSTRAINT fk_chat
        FOREIGN KEY (chat_id)
            REFERENCES chat(id)
);

--CREATE TABLE split_record(
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    split_id INT,
    who_paid_id INT,
    share jsonb,
    CONSTRAINT fk_split
        FOREIGN KEY (split_id)
        REFERENCES split(id),
    CONSTRAINT fk_users
        FOREIGN KEY (who_paid_id)
        REFERENCES users(id)
);