-- Administrator
INSERT INTO administrator (name)
VALUES ('Admin 1');

-- SharingPlatformAdmin
INSERT INTO sharing_platform_admin (name, email, password)
VALUES ('Platform Admin', 'admin@example.com', 'platform123');

-- SharingPlatform
INSERT INTO sharing_platform (name, contact_email, total_participants, administrator_id)
VALUES ('Platform A', 'platformA@example.com', 100, 1);



-- Project
INSERT INTO project (name, active, total_participants, avg_time_spent, sharing_platform_id)
VALUES ('Project X', true, 50, 4.5, 1);

-- Theme
INSERT INTO theme (name, information, project_id)
VALUES ('Theme X', 'Information for Theme X', 1);

-- Supervisor
INSERT INTO supervisor (id, name, email, password, sharing_platform_id)
VALUES (1, 'John Doe', 'johndoe@example.com', 'supervisor123', 1);

INSERT INTO supervisor (id, name, email, password, sharing_platform_id)
VALUES (2, 'Jefke Vermeule', 'jefkevermeule@example.com', '123supervisor', 1);

-- Flow
INSERT INTO flow (is_circular, project_id)
VALUES (true, 1);
INSERT INTO flow (is_circular, project_id)
VALUES (false, 1);

-- SubTheme
INSERT INTO sub_theme (name, information, flow_id)
VALUES ('local voting in antwerp', 'Information for voting in Antwerp', 1);
-- SubTheme
INSERT INTO sub_theme (name, information, flow_id)
VALUES ('local voting in brussels', 'Information for voting in Brussels', 1);

-- Question
INSERT INTO question (text, type, sub_theme_id)
VALUES ('What is your favorite color?', 'MULTIPLE_CHOICE', 1),
       ('How do you feel today?', 'SINGLE_CHOICE', 1);


-- Insert User data
INSERT INTO user_detail (id)
VALUES (1);

-- Insert Installation data
INSERT INTO installation (is_running, user_id)
VALUES (true, 1);


