CREATE TABLE users (
  id  INT not null primary key
      GENERATED ALWAYS AS IDENTITY
      (START WITH 1, INCREMENT BY 1),
  first_name VARCHAR(30),
  last_name  VARCHAR(30),
  address  VARCHAR(80)
);

CREATE TABLE media (
    id INT primary key,
    type varchar(30)
);

create TABLE media_users(
    id_user int references USERS(ID),
    id_media int references media(ID),
    contact varchar(50)
);