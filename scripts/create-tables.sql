
CREATE TABLE app.Communes
(
	id serial PRIMARY KEY,
	name varchar(50) NOT NULL
);

CREATE TABLE app.Roles
(
	id smallint PRIMARY KEY,
	name varchar(30) NOT NULL
);

CREATE TABLE app.Categories
(
	id smallint PRIMARY KEY,
	name varchar(40) NOT NULL
);

CREATE TABLE app.Users
(
	id serial PRIMARY KEY,
	first_name varchar(50) NOT NULL,
	last_name varchar(50) NOT NULL,
	email varchar(255) NOT NULL,
	password_hash varchar(60) NOT NULL,
	role_id smallint NOT NULL,
	CONSTRAINT us_fk_role FOREIGN KEY(role_id) REFERENCES app.Roles(id)
);

CREATE TABLE app.Institutions
(
	id serial PRIMARY KEY,
	name varchar(150) NOT NULL,
	commune_id integer NOT NULL,
	CONSTRAINT in_fk_commune FOREIGN KEY(commune_id) REFERENCES app.Communes(id)
);

CREATE TABLE app.Departments
(
	id serial PRIMARY KEY,
	name varchar(150) NOT NULL,
	institution_id integer NOT NULL,
	CONSTRAINT de_fk_institution FOREIGN KEY(institution_id) REFERENCES app.Institutions(id)
);

CREATE TABLE app.EmployeesDepartments
(
	user_id integer,
	department_id integer,
	CONSTRAINT em_fk_user FOREIGN KEY(user_id) REFERENCES app.Users(id),
	CONSTRAINT em_fk_department FOREIGN KEY(department_id) REFERENCES app.Departments(id),
	PRIMARY KEY(user_id, department_id)
);

CREATE TABLE app.Issues
(
	id serial PRIMARY KEY,
	creation_date timestamp NOT NULL,
	description varchar(400) NOT NULL,
	latitude decimal(10, 6) NOT NULL,
	longitude decimal(10, 6) NOT NULL,
	street varchar(60),
	house_number varchar(10),
	postcode varchar(6),
	locality varchar(60) NOT NULL,
	is_anonymous boolean NOT NULL,
	category_id smallint NOT NULL,
	user_id integer NOT NULL,
	department_id integer NOT NULL,
	CONSTRAINT is_fk_category FOREIGN KEY(category_id) REFERENCES app.Categories(id),
	CONSTRAINT is_fk_user FOREIGN KEY(user_id) REFERENCES app.Users(id),
	CONSTRAINT is_fk_department FOREIGN KEY(department_id) REFERENCES app.Departments(id)
);

CREATE TABLE app.DepartmentCategories
(
	department_id integer,
	category_id smallint,
	CONSTRAINT deca_fk_department FOREIGN KEY(department_id) REFERENCES app.Departments(id),
	CONSTRAINT deca_fk_category FOREIGN KEY(category_id) REFERENCES app.Categories(id),
	PRIMARY KEY(department_id, category_id)	
);

CREATE TABLE app.Notes
(
	id serial PRIMARY KEY,
	description varchar(500),
	creation_date timestamp NOT NULL,
	issue_id integer NOT NULL,
	user_id integer NOT NULL,
	CONSTRAINT no_fk_issue FOREIGN KEY(issue_id) REFERENCES app.Issues(id),
	CONSTRAINT no_fk_user FOREIGN KEY(user_id) REFERENCES app.Users(id)
);

CREATE TABLE app.Attachments
(
	id serial PRIMARY KEY,
	path varchar(260) NOT NULL,
	note_id integer NOT NULL,
	CONSTRAINT at_fk_note FOREIGN KEY(note_id) REFERENCES app.Notes(id)
);

CREATE TABLE app.Statuses
(
	id smallint PRIMARY KEY,
	name varchar(50) NOT NULL
);

CREATE TABLE app.IssuesStatuses
(
	id serial PRIMARY KEY,
	date timestamp NOT NULL,
	issue_id integer NOT NULL,
	status_id smallint NOT NULL,
	description varchar(400),
	CONSTRAINT isst_fk_issue FOREIGN KEY(issue_id) REFERENCES app.Issues(id),
	CONSTRAINT isst_fk_status FOREIGN KEY(status_id) REFERENCES app.Statuses(id)
);

CREATE TABLE app.Images
(
	id serial PRIMARY KEY,
	path varchar(260) NOT NULL,
	issue_id integer NOT NULL,
	CONSTRAINT im_fk_issue FOREIGN KEY(issue_id) REFERENCES app.Issues(id)
);