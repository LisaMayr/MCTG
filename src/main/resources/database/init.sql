CREATE DATABASE swen;
GRANT ALL PRIVILEGES ON DATABASE swen TO swenuser;
\c swen

CREATE TABLE "user" (
    id serial PRIMARY KEY,
    username VARCHAR ( 255 ) NOT NULL,
    password VARCHAR (255 ) NOT NULL,
    token VARCHAR ( 255 ),
    last_updated TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO "user" (username, password, token)
VALUES
    ('john_doe', 'password123', 'token_john'),
    ('jane_smith', 'securePass456', 'token_jane'),
    ('michael_lee', 'myPassword789', 'token_michael'),
    ('emily_watson', 'passEmily@321', 'token_emily'),
    ('david_jones', 'david!pass987', 'token_david');


CREATE TABLE session (
                        id serial PRIMARY KEY,
                        userid NUMERIC NOT NULL,
                        token VARCHAR ( 255 ) NOT NULL ,
                        last_updated TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
