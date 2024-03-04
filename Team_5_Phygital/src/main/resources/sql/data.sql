-- Supervisor
INSERT INTO supervisor (id, name, email, password)
VALUES
    (1, 'John Doe', 'johndoe@example.com', 'supervisor123');

-- Administrator
INSERT INTO administrator (id, name)
VALUES
    (1, 'Admin 1');

-- SharingPlatformAdmin
INSERT INTO sharingplatformadmin (id, name, email, password)
VALUES
    (1, 'Platform Admin', 'admin@example.com', 'platform123');

-- SharingPlatform
INSERT INTO sharingplatform (id, name, contactEmail, totalParticipants, administrator_id)
VALUES
    (1, 'Platform A', 'platformA@example.com', 100, 1);

-- Project
INSERT INTO project (id, name, theme, active, totalParticipants, avgTimeSpent)
VALUES
    (1, 'Project X', 'Theme X', true, 50, 4.5);

-- Flow
INSERT INTO flow (id, isCircular, project_id)
VALUES
    (1, true, 1);

-- SubTheme
INSERT INTO subtheme (id, name, information)
VALUES
    (1, 'SubTheme 1', 'Information for SubTheme 1');

-- Question
INSERT INTO question (id, text, type, subtheme_id)
VALUES
    (1, 'What is your favorite color?', 'Multiple Choice', 1),
    (2, 'How do you feel today?', 'Short Answer', 1);

-- Foreign Key Constraints
ALTER TABLE sharingplatform ADD CONSTRAINT fk_administrator_id FOREIGN KEY (administrator_id) REFERENCES administrator(id);
ALTER TABLE flow ADD CONSTRAINT fk_project_id FOREIGN KEY (project_id) REFERENCES project(id);
ALTER TABLE question ADD CONSTRAINT fk_subtheme_id FOREIGN KEY (subtheme_id) REFERENCES subtheme(id);
