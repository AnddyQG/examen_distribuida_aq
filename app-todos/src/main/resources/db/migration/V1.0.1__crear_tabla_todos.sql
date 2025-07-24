CREATE TABLE todo (
                      id SERIAL PRIMARY KEY,
                      user_id INTEGER NOT NULL,
                      title VARCHAR(200) NOT NULL,
                      completed BOOLEAN NOT NULL
);
