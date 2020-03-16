CREATE TABLE USER (
  ID UUID DEFAULT random_uuid() PRIMARY KEY,
  LOGIN VARCHAR(100) NOT NULL,
  PASSWORD VARCHAR(20) NOT NULL,
  FIO VARCHAR,
  ROLE VARCHAR NOT NULL DEFAULT 'Employee' CONSTRAINT def_c CHECK (ROLE IN ('Employee', 'Client'))
);

insert into user values ( default, 'Maria', '123', 'Petrova Maria Aleksandrovna', default );