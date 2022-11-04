CREATE TABLE IF NOT EXISTS reservation (
  id                     VARCHAR(60)  DEFAULT RANDOM_UUID() PRIMARY KEY,
  entityId                   NUMBER      NOT NULL FOREIGN KEY,
);