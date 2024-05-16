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

-- Installation
INSERT INTO installation (name, is_running)
VALUES ('Groenplaats', false),
       ('Linkeroever', false),
       ('Deurne', false);

-- Project
INSERT INTO project (name, background_color_hex, is_active, is_public, total_participants, total_time_spent_in_sec, sharing_platform_id)
VALUES ('Project X', '#fffff', true, false, 50, 4.5, 1);

-- -- Theme
INSERT INTO theme (name, information, project_id)
VALUES ('Theme X', 'Information for Theme X', 1);

-- Flow
INSERT INTO flow (name, is_circular, project_id, total_time_spent_in_sec, total_participants)
VALUES ('Flow 1', true, 1, 0, 0),
       ('Flow 2', false, 1, 0, 0);

-- SubTheme
INSERT INTO sub_theme (name, information, is_visible, flow_id, current_index)
VALUES ('local voting in antwerp', 'Information for voting in Antwerp', true, 1, 0),
       ('local voting in brussels', 'Information for voting in Brussels', true, 1, 0),
       ('local voting in brussels', 'Information for voting in Brussels', true, 2, 0);

-- Question
INSERT INTO question (text, type, is_visible, sub_theme_id)
VALUES ('What color do you like more?', 'MULTIPLE_CHOICE', true, 1),
       ('What is your phones battery %?', 'RANGE', true, 1),
       ('Are you happy currently?', 'SINGLE_CHOICE', true, 1),
       ('How do you feel today?', 'OPEN', true, 1),
       ('What color do you like more?', 'MULTIPLE_CHOICE', true, 3),
       ('What is your phones battery %?', 'RANGE', true, 3),
       ('Are you happy currently?', 'SINGLE_CHOICE', true, 3),
       ('How do you feel today?', 'OPEN', true, 3);

INSERT INTO possible_answers(question_id, answer)
values (1, 'Green'),
       (1, 'Black'),
       (1, 'Blue'),
       (1, 'Yellow'),
       (3, 'Yes'),
       (3, 'No'),
       (5, 'Green'),
       (5, 'Black'),
       (5, 'Blue'),
       (5, 'Yellow'),
       (7, 'Yes'),
       (7, 'No');


Insert INTO answer(answers)
values ('green'),
       ('71'),
       ('yes');

INSERT INTO notes(note)
VALUES ('sdaf');

INSERT INTO session(note_id, session_id, sub_theme_id, user_id, timestamp)
VALUES (1, 1, 1, 1, '2024-05-16T19:12:05.622719');

INSERT INTO session_answer(answer_id, session_id)
VALUES (1, 1),
       (2, 1),
       (3, 1);

INSERT INTO session_question(question_id, session_id)
VALUES (1, 1),
       (2, 1),
       (3, 1);
