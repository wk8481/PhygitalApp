
-- Administrator
INSERT INTO administrator (id, name)
VALUES
    (1, 'Admin 1');

-- SharingPlatformAdmin
INSERT INTO sharing_platform_admin (id, name, email, password)
VALUES
    (1, 'Platform Admin', 'admin@example.com', 'platform123');

-- SharingPlatform
INSERT INTO sharing_platform (id, name, contact_email, total_participants, administrator_id)
VALUES
    (1, 'Platform A', 'platformA@example.com', 100, 1);

-- Supervisor
INSERT INTO supervisor (id, name, email, password, sharing_platform_id)
VALUES
    (1, 'John Doe', 'johndoe@example.com', 'supervisor123', 1);
INSERT INTO supervisor (id, name, email, password, sharing_platform_id)
VALUES
    (2, 'Jefke Vermeulen', 'jefkevermeulen@example.com', '123supervisor', 1);

-- Project
INSERT INTO project (id, name, theme, active, total_participants, avg_time_spent, belongs_to_id)
VALUES
    (1, 'Project X', 'Theme X', true, 50, 4.5, 1);
INSERT INTO project (id, name, theme, active, total_participants, avg_time_spent, belongs_to_id)
VALUES
  (2, 'Project Y', 'Theme Y', false, 514, 10.5, 1);

-- Flow
INSERT INTO flow (id, is_circular, project_id)
VALUES
    (1, true, 1);

-- SubTheme
INSERT INTO sub_theme (id, name, information, flow_id)
VALUES
    (1, 'SubTheme 1', 'Information for SubTheme 1', 1);

-- Question
INSERT INTO question (id, text, type, sub_theme_id)
VALUES
    (1, 'What is your favorite color?', 'MULTIPLE_CHOICE', 1),
    (2, 'How do you feel today?', 'SINGLE_CHOICE', 1);

-- Foreign Key Constraints
ALTER TABLE sharing_platform ADD CONSTRAINT fk_administrator_id FOREIGN KEY (administrator_id) REFERENCES administrator(id);
ALTER TABLE flow ADD CONSTRAINT fk_project_id FOREIGN KEY (project_id) REFERENCES project(id);
ALTER TABLE question ADD CONSTRAINT fk_sub_theme_id FOREIGN KEY (sub_theme_id) REFERENCES sub_theme(id);
