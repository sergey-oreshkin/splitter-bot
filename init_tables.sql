CREATE TABLE IF NOT EXISTS users(
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    nic_name VARCHAR(255),
    updated DATE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS chat(
    id SERIAL PRIMARY KEY,
    chat_id BIGINT,
    chat_type VARCHAR(255),
    title VARCHAR(255),
    updated DATE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS user_chat(
    id SERIAL PRIMARY KEY,
    user_id INT,
    chat_id INT,
    CONSTRAINT user_id
        FOREIGN KEY (user_id)
        REFERENCES users(id),
    CONSTRAINT chat_id
        FOREIGN KEY (chat_id)
        REFERENCES chat(id)
);

CREATE TABLE IF NOT EXISTS split(
    id SERIAL PRIMARY KEY,
    owner INT,
    CONSTRAINT owner
        FOREIGN KEY (owner)
        REFERENCES user_chat(id)
);

CREATE TABLE IF NOT EXISTS split_record(
    id SERIAL PRIMARY KEY,
    split INT,
    who_paid INT,
    share jsonb,
    CONSTRAINT split
        FOREIGN KEY (split)
        REFERENCES split(id),
    CONSTRAINT who_paid
        FOREIGN KEY (who_paid)
        REFERENCES users(id)
);