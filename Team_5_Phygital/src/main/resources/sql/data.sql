INSERT INTO users (name, email, password, role)
VALUES ('Noah', 'noah@gmail.com', '$2a$10$Ym.DBt/FfM8jW9jBm9rrpeqsEqZXiKi3R5hm626nzvuQnxIJVIIN6', 'ADMIN'),
       ('Ruth', 'ruth@gmail.com', '$2a$10$Ym.DBt/FfM8jW9jBm9rrpeqsEqZXiKi3R5hm626nzvuQnxIJVIIN6', 'ADMIN'),
       ('William', 'william@gmail.com', '$2a$10$Ym.DBt/FfM8jW9jBm9rrpeqsEqZXiKi3R5hm626nzvuQnxIJVIIN6', 'CLIENT'),
       ('Emre', 'emre@gmail.com', '$2a$10$Ym.DBt/FfM8jW9jBm9rrpeqsEqZXiKi3R5hm626nzvuQnxIJVIIN6', 'SUPERVISOR');

-- Administrator
INSERT INTO administrator (name)
VALUES ('Admin 1');

-- SharingPlatformAdmin
INSERT INTO sharing_platform_admin (name, email, password)
VALUES ('Platform Admin', 'admin@example.com', 'platform123');

-- SharingPlatform
INSERT INTO sharing_platform (name, contact_email, total_participants, administrator_id)
VALUES ('Platform A', 'platformA@example.com', 100, 1);

-- Installation
INSERT INTO installation (name, is_running)
VALUES ('Groenplaats', false),
       ('Linkeroever', false),
       ('Deurne', false);

-- Project
INSERT INTO project (name, background_color_hex, is_active, is_public, total_participants, avg_time_spent, sharing_platform_id)
VALUES ('Project X', '#fffff', true, false, 50, 4.5, 1);

-- -- Theme
INSERT INTO theme (name, information, project_id)
VALUES ('Theme X', 'Information for Theme X', 1);

-- Supervisor
INSERT INTO supervisor (name, email, password, sharing_platform_id)
VALUES ('John Doe', 'johndoe@example.com', 'supervisor123', 1),
       ('Jefke Vermeule', 'jefkevermeule@example.com', '123supervisor', 1);

-- Flow
INSERT INTO flow (name, is_circular, project_id, start_time, end_time)
VALUES ('Flow 1', true, 1, null, null),
       ('Flow 2', false, 1, null, null);

-- SubTheme
INSERT INTO sub_theme (name, information, is_visible, flow_id, current_index)
VALUES ('local voting in antwerp', 'Information for voting in Antwerp', true, 1, 0),
       ('local voting in brussels', 'Information for voting in Brussels', true, 1, 0);

-- Question
INSERT INTO question (text, type, is_visible, sub_theme_id)
VALUES ('What color do you like more?', 'MULTIPLE_CHOICE', true, 1),
       ('What is your phones battery %?', 'RANGE', true, 1),
       ('Are you happy currently?', 'SINGLE_CHOICE', true, 1),
       ('How do you feel today?', 'OPEN', true, 1);

INSERT INTO possible_answers(question_id, answer)
values (1, 'Green'),
       (1, 'Black'),
       (1, 'Blue'),
       (1, 'Yellow'),
       (3, 'Yes'),
       (3, 'No');


