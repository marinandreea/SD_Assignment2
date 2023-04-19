
insert into user(id_user,email,first_name,gr,last_name,hobby,password,roles,tkn,usrname) values (1,'teacher@yahoo.com','Andreea',null,'Marin','swimming','$2a$12$AhBpAbb.UzewKycM2bXUbeoAPRZMOLsSTntjL/ARzL0PKkEXYFbV2','TEACHER','token1','teacher');
insert into user(id_user,email,first_name,gr,last_name,hobby,password,roles,tkn,usrname) values (2,'student1@yahoo.com','Razvan','30431','Marin','tennis','$2a$12$4q9741LGUBt9ac3SOXYT6ugixE8rbXKycSfzTu7ACZ.8agpbw.a3q','STUDENT','token2','student1');
insert into user(id_user,email,first_name,gr,last_name,hobby,password,roles,tkn,usrname) values (3,'student2@yahoo.com','Sara','30432','Voicu','handball','$2a$12$bGGrpFx0XVzeyUM96F/c8.BlLudCk.ZsC7VSxzfcluLIza0civb7y','STUDENT','token3','student2');

insert into laboratory(id_lab, curricula, date, description, nr_lab, title) values (1,'ceva','12/03/2023','description','1','Introduction');
insert into laboratory(id_lab, curricula, date, description, nr_lab, title) values (2,'ceva2','19/03/2023','description2','2','Bibliography');

insert into assignment(id_assignment,deadline,description,name,lab_nr) values (1,'20/03/2023','descriptionAssig1','Untold ticket selling',1);
insert into assignment(id_assignment,deadline,description,name,lab_nr) values (2,'30/03/2023','descriptionAssig2','Teacher-student app',2);

insert into attendance(id_attendance,present,lab_nr,student_id) values (1,'p',1,2);
insert into attendance(id_attendance,present,lab_nr,student_id) values (2,'a',2,3);

insert into submission(id_submission,comment,grade, link, assignment_id, user_id) values (1,'comment',9,'link.github',1,2);
insert into submission(id_submission,comment,grade, link, assignment_id, user_id) values (2,'comment',10,'link2.github',2,2);

