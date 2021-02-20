CREATE TABLE musclegroup (
	id_muscle_group serial NOT NULL,
	name_muscle_group VARCHAR(300) NOT NULL UNIQUE,
	description_muscle_group TEXT,
	CONSTRAINT pk_musclegroup PRIMARY KEY (id_muscle_group)
);

CREATE TABLE videolesson (
    id_video_lesson serial NOT NULL,
    name_video_lesson  varchar(300) NOT NULL UNIQUE,
    description_video_lesson  text NOT NULL,
    link_video_lesson  text NOT NULL,
    publication_date_video_lesson  date NOT NULL,
    status_video_lesson  boolean NOT NULL DEFAULT TRUE,
    id_muscle_group integer NOT NULL,
    CONSTRAINT pk_video_lesson PRIMARY KEY (id_video_lesson),
    CONSTRAINT fk_videolesson_musclegroup FOREIGN KEY (id_video_lesson) REFERENCES musclegroup (id_muscle_group)
);