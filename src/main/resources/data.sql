-- for the password using online bcrypt encoder, encode your password

ALTER TABLE erasmus_Request
    ADD CONSTRAINT FK_CREATEDBY
        FOREIGN KEY (created_by) REFERENCES user(it);

ALTER TABLE erasmus_Request
    ADD CONSTRAINT FK_SENDTO
        FOREIGN KEY (send_to) REFERENCES user(it);

ALTER TABLE erasmus_Request MODIFY COLUMN id INT auto_increment;

-- admin inserts(password=admin)
INSERT INTO user
    VALUES ('admin','admin@gmail.com','admin','admin','$2a$12$K/sVWqkl9HCOdU8W5t35sujQuieTapOTtGtAaDm6phMgMwX8WP5ea','ADMIN');

-- student inserts(password=student)
INSERT INTO user
    VALUES ('218114','stavros@gmail.com','stavros','christoforou','$2a$12$QhowItiSHmlTHRJmHe0r4OtS78acaxPB9yf9zplBoO4HKTMfotVga','STUDENT');

INSERT INTO user
    VALUES ('217104','eleni@gmail.com','eleni','petrou','$2a$12$QhowItiSHmlTHRJmHe0r4OtS78acaxPB9yf9zplBoO4HKTMfotVga','STUDENT');

INSERT INTO user
    VALUES ('217105','michaella@gmail.com','michaella','charalampous','$2a$12$QhowItiSHmlTHRJmHe0r4OtS78acaxPB9yf9zplBoO4HKTMfotVga','STUDENT');

INSERT INTO user
    VALUES ('217103','antonis@gmail.com','antonis','mantzouras','$2a$12$QhowItiSHmlTHRJmHe0r4OtS78acaxPB9yf9zplBoO4HKTMfotVga','STUDENT');

INSERT INTO user
VALUES ('217100','odysseas@gmail.com','odysseas','demosthenous','$2a$12$QhowItiSHmlTHRJmHe0r4OtS78acaxPB9yf9zplBoO4HKTMfotVga','STUDENT');

-- teacher inserts(password=teacher)
INSERT INTO user
    VALUES ('100','anargyros@gmail.com','Anargyros','Tsadimas','$2a$12$MeyFMSrYO5gnqALy2YfHFeKTX.44ExttX40UoSzwF0YYKN0s.7rnG','TEACHER');

INSERT INTO user
VALUES ('101','Mara@gmail.com','Mara','Nikolaidou','$2a$12$MeyFMSrYO5gnqALy2YfHFeKTX.44ExttX40UoSzwF0YYKN0s.7rnG','TEACHER');

INSERT INTO user
VALUES ('102','Vasilis@gmail.com','Vasilis','Dalakas','$2a$12$MeyFMSrYO5gnqALy2YfHFeKTX.44ExttX40UoSzwF0YYKN0s.7rnG','TEACHER');

INSERT INTO user
VALUES ('103','George@gmail.com','George','Dimitrakopoulos','$2a$12$MeyFMSrYO5gnqALy2YfHFeKTX.44ExttX40UoSzwF0YYKN0s.7rnG','TEACHER');

-- erasmus_Request

INSERT INTO erasmus_Request
    VALUES (1,20,'218114','13/04/1996','stavros','6.5','christoforou','100','null');

INSERT INTO erasmus_Request
    VALUES (2,20,'217100','23/03/1996','antonis','6.5','mantzouras','101','null');

INSERT INTO erasmus_Request
    VALUES (3,21,'217104','23/09/1999','eleni','8.6','petrou','102','null');

INSERT INTO erasmus_Request
    VALUES (4,18,'217105','21/01/1993','michaella','5.3','charalampous','103','null');

INSERT INTO erasmus_Request
    VALUES (5,21,'217103','16/10/1996','odysseas','6.7','demosthenous','100','null');

INSERT INTO erasmus_Request
    VALUES (6,19,'217103','16/10/1996','odysseas','6.2','demosthenous','102','null');

INSERT INTO erasmus_Request
    VALUES (7,22,'217104','23/09/1999','eleni','8.7','petrou','103','null');

-- erasmus committee inserts

INSERT INTO erasmus_Committee
VALUES ('harvard@gmail.com','ComputerScience','HarvardUniversity');

INSERT INTO erasmus_Committee
VALUES ('Leeds@gmail.com','ComputerScience','UniversityofLeeds');

INSERT INTO erasmus_Committee
VALUES ('Oxford@gmail.com','ComputerScience','OxfordUniversity');

INSERT INTO erasmus_Committee
VALUES ('Leicester@gmail.com','ComputerScience','UniversityofLeicester');

INSERT INTO erasmus_Committee
VALUES ('Barcelona@gmail.com','ComputerScience','UniversityofBarcelona');

INSERT INTO erasmus_Committee
VALUES ('Cyprus@gmail.com','ComputerScience','UniversityofCyprus');