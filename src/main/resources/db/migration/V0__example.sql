create table country(
  id bigserial not null primary key,
  name varchar(1000) not null
);

create table organization(
  id bigserial not null primary key,
  name varchar(1000) not null,
  country_id bigint not null references country(id),
  url varchar(1000),
  created_at timestamp not null default current_timestamp
);

create table person(
  id bigserial not null primary key,
  name varchar(1000) not null,
  country_id bigint not null references country(id),
  created_at timestamp not null default current_timestamp
);

create table membership(
  organization_id bigint references organization(id),
  person_id bigint references person(id),
  created_at timestamp not null default current_timestamp
);
