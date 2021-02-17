CREATE TABLE musclegroup (
	id_muscle_group serial NOT NULL,
	name_muscle_group VARCHAR(300) NOT NULL UNIQUE,
	description_muscle_group TEXT,
	CONSTRAINT pk_musclegroup PRIMARY KEY (id_muscle_group)
);