

DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS users;

--
-- Table structure for table `users`
--

CREATE TABLE users (
  email varchar(255) NOT NULL,
  password char(255) NOT NULL,
  enabled boolean NOT NULL,
  PRIMARY KEY (email)
) ;

--
-- Inserting data for table `users`
--
-- NOTE: The passwords are encrypted using BCrypt
--
-- A generation tool is avail at: https://www.luv2code.com/generate-bcrypt-password
--
--
--

INSERT INTO users
VALUES 
('admin@phygital.com', '$2a$10$3fcpGe3/7ZjJ5IQWPa7Z4.eeljYNVWdmlgkBfwsOPJDUHBbt0eJva',1),
('sharingplatform@phygital.com', '$2a$10$MbZ9cT0x1dnXxWp89Ie2SuR.yA2a1OA9FdyMJzUXvDEpdU4iDV3M6',1),
('johndoe@phygital.com', '$2a$10$SVV905eADPupXK09qeFHcedZqItfrknH6TSV7rkbA1cSs8Pej5JYO', 1),
('jefkevermeule@phygital', '$2a$10$rNnVug3qTGPbn417Qb9fleLuhLNdl/zg8NtUTrSrNDjB2P09h2g46', 1),
('installations@phygital.com', '$2a$10$wrF/syxed2pOb7ag2WvPz.ZE81iIcLc2uBJjqZAA2rmDxrpEhMhcu',1);


--
INSERT INTO users
VALUES
    ('admin@phygital.com', 'admin123',true),
    ('sharingplatform@phygital.com', 'platform123',true),
    ('johndoe@phygital.com', 'supervisor123', true),
    ('jefkevermeule@phygital', '123supervisor',true),
    ('installations@phygital.com', 'installation123',true);





--
-- Table structure for table `authorities`
--

CREATE TABLE authorities (
                             email varchar(255) NOT NULL,
                             authority varchar(255) NOT NULL,
                             CONSTRAINT authorities_unique UNIQUE (email, authority),
                             CONSTRAINT authorities4_ibfk_1 FOREIGN KEY (email) REFERENCES users (email)
);


--
-- Inserting data for table `authorities`
--

INSERT INTO authorities
VALUES
('johndoe@phygital.com','ROLE_EMPLOYEE'),
('jefkevermeule@phygital','ROLE_EMPLOYEE'),
('sharingplatform@phygital.com','ROLE_MANAGER'),
('sharingplatform@phygital.com','ROLE_EMPLOYEE'),
('installations@phygital.com','ROLE_EMPLOYEE'),
('installations@phygital.com','ROLE_MANAGER'),
('admin@phygital.com','ROLE_EMPLOYEE'),
('admin@phygital.com','ROLE_MANAGER'),
('admin@phygital.com','ROLE_ADMIN');