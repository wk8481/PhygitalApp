-- Supervisor
INSERT INTO supervisor (id, name, email, password)
VALUES
    (1, 'John Doe', 'johndoe@example.com', 'supervisor123');

-- Administrator
INSERT INTO administrator (name)
VALUES
    ('Admin 1');

-- SharingPlatformAdmin
INSERT INTO sharing_platform_admin (name, email, password)
VALUES
    ('Platform Admin', 'admin@example.com', 'platform123');

-- SharingPlatform
INSERT INTO sharing_platform (name, contact_email, total_participants, administrator_id)
VALUES
    ('Platform A', 'platformA@example.com', 100, 1);

-- Project
INSERT INTO project (name, theme, active, total_participants, avg_time_spent, sharing_platform_id)
VALUES
    ('Project X', 'Theme X', true, 50, 4.5, 1);

-- Flow
INSERT INTO flow (is_circular, project_id)
VALUES
    (true, 1);

-- SubTheme
INSERT INTO sub_theme (name, information, flow_id)
VALUES
    ('SubTheme 1', 'Information for SubTheme 1', 1);

-- Question
INSERT INTO question (text, type, sub_theme_id)
VALUES
    ('What is your favorite color?', 'MULTIPLE_CHOICE', 1),
    ('How do you feel today?', 'SINGLE_CHOICE', 1);



-- Insert User data
INSERT INTO user_detail (id) VALUES (1);

-- Insert Installation data
INSERT INTO installation (is_running, user_id) VALUES (true, 1);


