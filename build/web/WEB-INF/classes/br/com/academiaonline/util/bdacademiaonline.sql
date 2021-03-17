/*
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
    CONSTRAINT fk_videolesson_musclegroup FOREIGN KEY (id_muscle_group) REFERENCES musclegroup (id_muscle_group)
);

CREATE TABLE person (
    id_person SERIAL NOT NULL,
    name_person varchar(300) NOT NULL,
    cpf_person varchar(11) NOT NULL UNIQUE,
    birthday_date_person date NOT NULL,
    email_person varchar(300) NOT NULL UNIQUE,
    password_person text NOT NULL,
    type_person char(1) NOT NULL,
    CONSTRAINT pk_person PRIMARY KEY (id_person)
);

CREATE TABLE user_account (
    id_user SERIAL NOT NULL,
    registration_date_user DATE NOT NULL,
    profile_picture_user BYTEA,
    id_person INTEGER NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id_user),
    CONSTRAINT fk_user_person FOREIGN KEY (id_person) REFERENCES person(id_person)
);

CREATE TABLE employee (
    id_employee SERIAL NOT NULL,
    curriculum_employee TEXT,
    address_employee varchar(300) NOT NULL,
    city_employee varchar(300) NOT NULL,
    state_employee char(2) NOT NULL,
    postal_code_employee varchar(8) NOT NULL,
    telephone_employee varchar(8) NOT NULL,
    id_person INTEGER NOT NULL,
    CONSTRAINT pk_employee PRIMARY KEY (id_employee),
    CONSTRAINT fk_employee_person FOREIGN KEY (id_person) REFERENCES person(id_person)
);

*/

CREATE TABLE role (
    id SERIAL NOT NULL,
    name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT ,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE employee_role (
    id_employee INTEGER NOT NULL,
    id_role INTEGER NOT NULL,
    CONSTRAINT pk_employee_role PRIMARY KEY (id_role, id_employee),
    CONSTRAINT fk_employee_role FOREIGN KEY (id_role) REFERENCES role(id),
    CONSTRAINT fk_role_employee FOREIGN KEY (id_employee) REFERENCES employee(id_employee)
);