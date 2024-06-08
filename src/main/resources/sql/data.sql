-- Admin
INSERT INTO phygital_user (name, email, password, role, user_type, sharing_platform_id)
VALUES
    ('Noah', 'noah@gmail.com', '$2a$10$Ym.DBt/FfM8jW9jBm9rrpeqsEqZXiKi3R5hm626nzvuQnxIJVIIN6', 'ADMIN', 'ADMIN', NULL),
    ('Ruth', 'ruth@gmail.com', '$2a$10$Ym.DBt/FfM8jW9jBm9rrpeqsEqZXiKi3R5hm626nzvuQnxIJVIIN6', 'ADMIN', 'ADMIN', NULL);

-- SharingPlatform
INSERT INTO sharing_platform (name, contact_email, total_participants, total_time_spent_in_sec, administrator_id)
VALUES ('Platform A', 'platformA@example.com', 100, 7845, 1);

-- Client
INSERT INTO phygital_user (name, email, password, role, user_type, sharing_platform_id)
VALUES ('William', 'william@gmail.com', '$2a$10$Ym.DBt/FfM8jW9jBm9rrpeqsEqZXiKi3R5hm626nzvuQnxIJVIIN6', 'CLIENT', 'CLIENT', 1);

-- Supervisor
INSERT INTO phygital_user (name, email, password, role, user_type, sharing_platform_id)
VALUES ('Emre', 'emre@gmail.com', '$2a$10$Ym.DBt/FfM8jW9jBm9rrpeqsEqZXiKi3R5hm626nzvuQnxIJVIIN6', 'SUPERVISOR', 'SUPERVISOR', 1),
       ('Lukas', 'lukas@gmail.com', '$2a$10$Ym.DBt/FfM8jW9jBm9rrpeqsEqZXiKi3R5hm626nzvuQnxIJVIIN6', 'SUPERVISOR', 'SUPERVISOR', 1);

-- Location
INSERT INTO location (province, city, street, street_number)
VALUES
    ('Antwerp', 'Antwerp', 'Groenplaats', '1'),
    ('Antwerp', 'Antwerp', 'Linkeroever', '2'),
    ('Antwerp', 'Antwerp', 'Deurne', '3');

-- Installation
INSERT INTO installation (name, is_running, location_id)
VALUES ('Groenplaats', false, 1),
       ('Linkeroever', false, 2),
       ('Deurne', false, 3);

-- Project
INSERT INTO project (name, logo_url, background_color_hex, is_active, is_public, total_participants, total_time_spent_in_sec, sharing_platform_id)
VALUES ('Elections', 'https://blogsmedia.lse.ac.uk/blogs.dir/8/files/2020/12/openelectionslogo-e1607071322402.png', '#FFFFFF', true, false, 50, 4.5, 1),
       ('Environment', 'https://static.vecteezy.com/system/resources/previews/007/634/526/non_2x/environment-logo-icon-design-template-free-vector.jpg', '#00FF00', )

-- -- Theme
INSERT INTO theme (name, information, project_id)
VALUES ('Theme X', 'Information for Theme X', 1);

-- Flow
INSERT INTO flow (name, is_circular, project_id, total_time_spent_in_sec, total_participants, info)
VALUES ('Flow 1', true, 1, 0, 0, 'this flow is circular'),
       ('Flow 2', false, 1, 0, 0, 'this flow is linear');

-- SubTheme
INSERT INTO sub_theme (name, information, is_visible, flow_id, current_index)
VALUES ('local voting in antwerp', 'Information for voting in Antwerp', true, 1, 0),
       ('local voting in brussels', 'Information for voting in Brussels', true, 1, 0),
       ('local voting in brussels', 'Information for voting in Brussels', true, 2, 0),
       ('test', 'test', true, 1, 0);

-- Question
INSERT INTO question (text, type, is_visible, sub_theme_id)
VALUES ('What color do you like more?', 'MULTIPLE_CHOICE', true, 1),
       ('What is your phones battery %?', 'RANGE', true, 1),
       ('Are you happy currently?', 'SINGLE_CHOICE', true, 1),
       ('How do you feel today?', 'OPEN', true, 1),
       ('What color do you like more?', 'MULTIPLE_CHOICE', true, 3),
       ('What is your phones battery %?', 'RANGE', true, 3),
       ('Are you happy currently?', 'SINGLE_CHOICE', true, 3),
       ('How do you feel today?', 'OPEN', true, 3),
       ('would you go to work as chairman, secretary or assistant during voting if you were selected to do so?','SINGLE_CHOICE', true,2),
       ('Should there be a fine for people who do not show up','SINGLE_CHOICE', true,2),
       ('Should the fine be higher for people with a higher income?','SINGLE_CHOICE',true, 2),
       ('test', 'RANGE', true, 4);

INSERT INTO possible_answers(question_id, answer)
values (1, 'Green'),
       (1, 'Black'),
       (1, 'Blue'),
       (1, 'Yellow'),
       (2, '1'),
       (2, '1'),
       (2, '100'),
       (3, 'Yes'),
       (3, 'No'),
       (5, 'Green'),
       (5, 'Black'),
       (5, 'Blue'),
       (5, 'Yellow'),
       (6, '1'),
       (6, '1'),
       (6, '100'),
       (7, 'Yes'),
       (7, 'No'),
       (8, 'Yes'),
       (8, 'No'),
       (9, 'Yes'),
       (9, 'No'),
        (10, 'Yes'),
        (10, 'No'),
        (11, 'Yes'),
        (11, 'No'),
        (11, 'There should be no fine'),
       (12, '100'),
       (12, '1'),
       (12, '200');

Insert INTO answer(answers)
values ('green'),
       ('71'),
       ('yes');

INSERT INTO notes(note)
VALUES ('sdaf');

INSERT INTO session(note_id, sub_theme_id, user_id, timestamp)
VALUES (1, 1, 1, '2024-05-16T19:12:05.622719');

INSERT INTO session_answer(answer_id, session_id)
VALUES (1, 1),
       (2, 1),
       (3, 1);

INSERT INTO session_question(question_id, session_id)
VALUES (1, 1),
       (2, 1),
       (3, 1);

INSERT INTO comment (text, project_id)
VALUES ('I love how the questionaire is short and simple', 1),
       ('Thank you to the lady that was there to help me with the questions i didnt understand', 1);
