DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS authorization;

CREATE TABLE role (
  role_id INT AUTO_INCREMENT  PRIMARY KEY,
  role_title VARCHAR(250) NOT NULL,
  role_description VARCHAR(250) NOT NULL
);

INSERT INTO role (role_title, role_description) VALUES
  ('FORMATION', 'Formation'),
  ('QCM', 'Qcm'),
  ('EVENT', 'Event'),
  ('CERTIFICATION', 'Certification'),
  ('COLLABORATOR', 'Collaborateur'),
  ('ADMIN', 'Administrator');


CREATE TABLE account (
  account_mail_adresse VARCHAR(250) PRIMARY KEY,
  password  VARCHAR(250) NOT NULL,
  account_first_name VARCHAR(250) DEFAULT NULL,
  account_last_name VARCHAR(250)  DEFAULT NULL,
  role_id INT DEFAULT 0
);

INSERT INTO account (account_mail_adresse, password,account_first_name, account_last_name,role_id) VALUES
  ('yelouardi@sqli.com','123456','Yassine', 'Elouardi',6),
  ('zeljazouli@sqli.com','123456','Zakaria', 'El Jazouli',1),
  ('kladib@sqli.com','123456','Khalid', 'Labib',2),
  ('kkouis@sqli.com','123456','Khalil', 'Kouiss',2),
  ('hbenderouach@sqli.com','123456','Hamza', 'Benderouach',4),
  ('obellouki@sqli.com','123456','Outhmane', 'Bellouki',1),
  ('mbeilil@sqli.com','123456','Mohammed', 'Beilil',3);

  CREATE TABLE authorization (
  authorization_id INT AUTO_INCREMENT  PRIMARY KEY,
  authorization_title VARCHAR(250) NOT NULL,
  authorization_description VARCHAR(250) NOT NULL,
  authorization_url VARCHAR(250) NOT NULL,
  role_id INT NOT NULL
);

INSERT INTO authorization (authorization_title, authorization_description,authorization_url,role_id) VALUES
  ('Create person', 'The creation of the person','/users/signup',3),
  ('Find all Authorization', 'Find all Authorization','/authorization/all',3),
  ('Create role', 'The creation of the role','/role',3),
  ('Find all roles', 'Find all roles','/role/all',3),
  ('Find all persons', 'Find all roles','/persons/all',1),
  ('Find all persons', 'Find all roles','/persons/all',2),
  ('Find all persons', 'Find all roles','/api-matrix/person/all',3),
  ('Create event', 'Create Event','/api-eventmatrix/event',3),
  ('Find all Events', 'Find all events','/api-eventmatrix/event/all',3),
  ('Find all Events By Type', 'Find all Events by Type','/api-eventmatrix/event/all/type',3),
  ('Creaye type Events', 'Creaye type Events','/api-eventmatrix/typeevents',3),
  ('Find type Events By title', 'Find  type events by title','/api-eventmatrix/typeevents/title',3),
  ('Find type Events By id', 'Find  type events by id','/api-eventmatrix/typesevents/id',3),
  ('Find All Type Events', 'Find all type events','/api-eventmatrix/typeevents/all',3),
  ('Create event', 'Create Event','/api-eventmatrix/event',6),
  ('Find all Events', 'Find all events','/api-eventmatrix/event/all',6),
  ('Find all Events By Type', 'Find all Events by Type','/api-eventmatrix/event/all/type',6),
  ('Creaye type Events', 'Creaye type Events','/api-eventmatrix/typeevents',6),
  ('Find type Events By title', 'Find  type events by title','/api-eventmatrix/typeevents/title',6),
  ('Find type Events By id', 'Find  type events by id','/api-eventmatrix/typesevents/id',6),
  ('Find All Type Events', 'Find all type events','/api-eventmatrix/typeevents/all',6)
  ('Find all courses', 'Find all roles','/api-trainingmatrix/course/all',1),
  ('Find all course types', 'Find all roles','/api-trainingmatrix/coursetype/all',1),
  ('Find all interns', 'Find all roles','/api-trainingmatrix/intern/all',1),
  ('Find all trainers', 'Find all roles','/api-trainingmatrix/trainer/all',6),
  ('Find all courses', 'Find all roles','/api-trainingmatrix/course/all',6),
  ('Find all course types', 'Find all roles','/api-trainingmatrix/coursetype/all',6),
  ('Find all interns', 'Find all roles','/api-trainingmatrix/intern/all',6),
  ('Find all trainers', 'Find all roles','/api-trainingmatrix/trainer/all',6);


