-- Admins
INSERT INTO phygital_user (name, email, password, role, user_type, sharing_platform_id)
VALUES ('Noah', 'noah@gmail.com', '$2a$10$Ym.DBt/FfM8jW9jBm9rrpeqsEqZXiKi3R5hm626nzvuQnxIJVIIN6', 'ADMIN', 'ADMIN',
        NULL),
       ('Ruth', 'ruth@gmail.com', '$2a$10$Ym.DBt/FfM8jW9jBm9rrpeqsEqZXiKi3R5hm626nzvuQnxIJVIIN6', 'ADMIN', 'ADMIN',
        NULL);

-- Sharing Platforms
INSERT INTO sharing_platform (name, contact_email, logo_url, total_participants, total_time_spent_in_sec,
                              administrator_id)
VALUES ('Municipal Government', 'belgium@govt.be',
        'https://upload.wikimedia.org/wikipedia/commons/4/47/Belgium.be_logo.svg', 100, 7845, 1),
       ('European Parliament', 'eu@govt.eu',
        'https://seeklogo.com/images/E/European_Union-logo-75776F70C0-seeklogo.com.png', 200, 8763, 2);


-- Clients
INSERT INTO phygital_user (name, email, password, role, user_type, sharing_platform_id)
VALUES ('William', 'william@govt.be', '$2a$10$Ym.DBt/FfM8jW9jBm9rrpeqsEqZXiKi3R5hm626nzvuQnxIJVIIN6', 'CLIENT',
        'CLIENT', 1),
       ('Jerome', 'jerome@eu.be', '$2a$10$ZlT1y3BSmeFvSVVwGcxj0OgpqUUNVL0oSF9/KDFKZTTxxQm44b6Am', 'CLIENT', 'CLIENT',
        2);

-- Supervisors
INSERT INTO phygital_user (name, email, password, role, user_type, sharing_platform_id)
VALUES ('Emre', 'emre@gmail.com', '$2a$10$Ym.DBt/FfM8jW9jBm9rrpeqsEqZXiKi3R5hm626nzvuQnxIJVIIN6', 'SUPERVISOR',
        'SUPERVISOR', 1),
       ('Lukas', 'lukas@gmail.com', '$2a$10$Ym.DBt/FfM8jW9jBm9rrpeqsEqZXiKi3R5hm626nzvuQnxIJVIIN6', 'SUPERVISOR',
        'SUPERVISOR', 1);

-- Locations
INSERT INTO location (province, city, street, street_number)
VALUES ('Antwerp', 'Antwerp', 'Groenplaats', '1'),
       ('Antwerp', 'Antwerp', 'Linkeroever', '2'),
       ('Antwerp', 'Antwerp', 'Deurne', '3');

-- Installations
INSERT INTO installation (name, is_running, location_id)
VALUES ('Groenplaats', false, 1),
       ('Linkeroever', false, 2),
       ('Deurne', false, 3);

-- Projects
INSERT INTO project (name, logo_url, background_color_hex, is_active, is_public, total_participants,
                     total_time_spent_in_sec, sharing_platform_id)
VALUES ('Elections', 'https://blogsmedia.lse.ac.uk/blogs.dir/8/files/2020/12/openelectionslogo-e1607071322402.png',
        '#FFFFFF', true, false, 50, 4.5, 1),
       ('Environment',
        'https://static.vecteezy.com/system/resources/previews/007/634/526/non_2x/environment-logo-icon-design-template-free-vector.jpg',
        '#47EABC', false, false, 35, 2.5, 2);

-- Themes
INSERT INTO theme (name, information, project_id)
VALUES ('Municipal Elections', 'Municipal elections in the different provinces.', 1),
       ('Environment', 'Environmental topics.', 2);

-- Flows
INSERT INTO flow (name, is_circular, project_id, total_time_spent_in_sec, total_participants, info)
VALUES ('Circular Flow', true, 1, 0, 0, 'This flow can be attended by multiple people at the same time.'),
       ('Linear Flow', false, 1, 0, 0, 'This flow can be attended by only one person at a time.'),
       ('Circular Flow', true, 2, 0, 0, 'This flow can be attended by multiple people at the same time.'),
       ('Linear Flow', false, 2, 0, 0, 'This flow can be attended by only one person at a time.');

-- Sub-Themes
INSERT INTO sub_theme (name, information, media_url, is_visible, flow_id, current_index)
VALUES ('Local voting in Antwerp', 'Information for voting in Antwerp',
        'https://lp-cms-production.imgix.net/2023-02/belgium-GettyImages-1393370540-rfc.jpeg?w=600&h=400', true, 1, 0),
       ('Local voting in Saint-Gilles', 'Information for voting in Saint-Gilles',
        'https://www.visit.brussels/content/dam/visitbrussels/images/b2c/what-to-do/les-incontournables-de-bruxelles/GRAND_PLACE_4212-%C2%A9_visit.brussels_-_Jean-Michel_Byl.jpg',
        true, 1, 0),
       ('Local voting in Anderlecht', 'Information for voting in Anderlecht',
        'https://traveler.marriott.com/wp-content/uploads/2023/07/GettyImages-1082428738.jpg', true, 2, 0),
       ('Plastic', 'Information about plastic waste',
        'https://www.smithschool.ox.ac.uk/sites/default/files/styles/news_style_16x9/public/2023-09/the_plastics_dilemma-firefly_a_landscape_made_of_plastic_bottles_plastic_bags_plastic_cups_plastic_clothes_90449.jpg?itok=trrH6786',
        true, 3, 0),
       ('Air Pollution', 'Information about air pollution',
        'https://www.nrdc.org/sites/default/files/styles/social_sharing_1200x630/public/media-uploads/health4_26_airpollguide_istock_2796602_2400.jpg?h=c3635fa2&itok=HMTXSAJe',
        true, 4, 0),
       ('Local voting in Schaerbeek', 'Information for voting in Schaerbeek',
        'https://www.woneninbrussel.be/sites/woneninbrussel/files/2019-08/1006-Schaarbeek-Brabantstraat-Web%20Low.jpg',
        true, 2, 0),
       ('Water Pollution', 'Information about water pollution',
        'https://www.aquaread.com/blog/wp-content/uploads/2023/10/plastic-pollution-scaled.webp', true, 3, 0),
       ('Deforestation', 'Information about deforestation',
        'https://files.worldwildlife.org/wwfcmsprod/images/Deforestation_in_Tesso_Nilo_Sumatra/story_full_width/1e7g2a63k2_deforestation_causes_HI_104236.jpg',
        true, 4, 0);

-- Questions
INSERT INTO question (text, type, is_visible, sub_theme_id)
VALUES ('How important is local voting to you?', 'RANGE', true, 1),
       ('Do you plan to vote in the next local election?', 'SINGLE_CHOICE', true, 1),
       ('Which issue is most important to you in the upcoming election?', 'MULTIPLE_CHOICE', true, 1),
       ('What is your age?', 'RANGE', true, 1),
       ('How do you feel about the current local government?', 'OPEN', true, 1),
       ('What is your opinion on mandatory voting?', 'SINGLE_CHOICE', true, 2),
       ('Do you feel informed about the voting process?', 'SINGLE_CHOICE', true, 2),
       ('Which candidate do you support?', 'MULTIPLE_CHOICE', true, 2),
       ('What improvements would you like to see in your community?', 'OPEN', true, 2),
       ('What is your primary concern for Schaerbeek?', 'OPEN', true, 6),
       ('How likely are you to vote in Schaerbeeks election?', 'RANGE', true, 6),
       ('How serious do you think the plastic pollution problem is?', 'RANGE', true, 3),
       ('Do you take any actions to reduce plastic waste?', 'SINGLE_CHOICE', true, 3),
       ('Which of these do you think is the most effective way to reduce plastic waste?', 'MULTIPLE_CHOICE', true, 3),
       ('How do you feel about the current efforts to combat plastic pollution?', 'OPEN', true, 3),
       ('How concerned are you about water pollution?', 'RANGE', true, 7),
       ('What measures do you think are most important for reducing water pollution?', 'MULTIPLE_CHOICE', true, 7),
       ('How do you feel about the quality of water in your area?', 'OPEN', true, 7),
       ('How significant do you think air pollution is in your city?', 'RANGE', true, 4),
       ('Do you take any actions to reduce air pollution?', 'SINGLE_CHOICE', true, 4),
       ('Which of these measures do you think is most effective in reducing air pollution?', 'MULTIPLE_CHOICE', true,
        4),
       ('What are your thoughts on the current air quality initiatives?', 'OPEN', true, 4),
       ('How urgent is the problem of deforestation?', 'RANGE', true, 8),
       ('What actions do you believe are most effective in combating deforestation?', 'MULTIPLE_CHOICE', true, 8),
       ('What is your opinion on the current deforestation rates?', 'OPEN', true, 8);

-- Possible Answers
INSERT INTO possible_answers(question_id, answer)
VALUES (1, '1'),
       (1, '1'),
       (1, '100'),
       (2, 'No'),
       (3, 'Education'),
       (3, 'Healthcare'),
       (3, 'Environment'),
       (3, 'Infrastructure'),
       (4, '18'),
       (4, '100'),
       (5, NULL),
       (6, 'It is necessary'),
       (6, 'It should be optional'),
       (7, 'Yes'),
       (7, 'No'),
       (8, 'Candidate A'),
       (8, 'Candidate B'),
       (8, 'Candidate C'),
       (9, NULL),
       (10, NULL),
       (11, '1'),
       (11, '10'),
       (12, '1'),
       (12, '10'),
       (13, 'Yes'),
       (13, 'No'),
       (14, 'Recycling'),
       (14, 'Banning single-use plastics'),
       (14, 'Education and awareness'),
       (14, 'Innovative alternatives'),
       (15, NULL),
       (16, '1'),
       (16, '10'),
       (17, 'Improved waste management'),
       (17, 'Stricter regulations'),
       (17, 'Public awareness campaigns'),
       (17, 'Technological innovations'),
       (18, NULL),
       (19, '1'),
       (19, '10'),
       (20, 'Yes'),
       (20, 'No'),
       (21, 'Using public transport'),
       (21, 'Planting more trees'),
       (21, 'Industrial regulations'),
       (21, 'Promoting electric vehicles'),
       (22, NULL),
       (23, '1'),
       (23, '10'),
       (24, 'Protecting existing forests'),
       (24, 'Reforestation initiatives'),
       (24, 'Stricter logging regulations'),
       (24, 'Promoting sustainable land use'),
       (25, NULL);

-- Sample Answers
INSERT INTO answer(answers)
VALUES ('7'),
       ('Improving voter education and accessibility.'),
       ('I am very likely to vote.'),
       ('8'),
       ('The water quality in my area is fairly good, but there are occasional issues with pollution from nearby factories.'),
       ('7'),
       ('The current rate of deforestation is alarming and requires immediate action.');

-- Notes
INSERT INTO notes(note)
VALUES ('This person is very thoughtful...');

-- Sessions
INSERT INTO session(note_id, sub_theme_id, user_id, timestamp)
VALUES (1, 1, 1, '2024-05-16T19:12:05.622719');

-- Session Answers
INSERT INTO session_answer(answer_id, session_id)
VALUES (1, 1),
       (2, 1),
       (3, 1);

-- Session Questions
INSERT INTO session_question(question_id, session_id)
VALUES (1, 1),
       (2, 1),
       (3, 1);

-- Comments
INSERT INTO comment (text, project_id)
VALUES ('I love how the questionnaire is short and simple', 1),
       ('Thank you to the lady that was there to help me with the questions i didnt understand', 1);
