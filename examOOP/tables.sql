create table planet(
	id SERIAL,
	radius real,
	kernel_temp real,
	has_atmosphere boolean,
	is_inhabited boolean
);
alter table planet add constraint pk_planet_id primary key (id);
alter table planet add column name CHARACTER VARYING(100);
alter table planet add column galaxy_id int;

create table satellite(
	id SERIAL PRIMARY KEY,
	name character varying(100),
	radius real,
	distance_to_planet real
);
alter table satellite add column planet_id int;

create table galaxy(
	id serial primary key, 
	name character varying(100)
);

alter table planet add constraint fk_planet_galaxy_id foreign key (galaxy_id) references galaxy(id) on delete cascade;
alter table satellite add constraint fk_satellite_planet_id foreign key (planet_id) references planet(id) on delete cascade;

