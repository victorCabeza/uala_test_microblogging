CREATE TABLE users (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255),
    last_name VARCHAR(255)
);

CREATE TABLE messages (
    uuid UUID PRIMARY KEY,
    text VARCHAR(280),
    creation_date TIMESTAMP,
    created_by_id VARCHAR(255),
    FOREIGN KEY (created_by_id) REFERENCES users(id)
);

CREATE TABLE user_follows (
    follower_id VARCHAR(255),
    followed_id VARCHAR(255),
    PRIMARY KEY (follower_id, followed_id),
    FOREIGN KEY (follower_id) REFERENCES users(id),
    FOREIGN KEY (followed_id) REFERENCES users(id)
);