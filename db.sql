-- 创建数据库
CREATE DATABASE game_service_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE game_service_db;


-- 创建表
CREATE TABLE games (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    game_code VARCHAR(12) NOT NULL UNIQUE,
    creator_id BIGINT NOT NULL,
    status ENUM('draft', 'published', 'ended') NOT NULL DEFAULT 'draft',
    is_deleted TINYINT(1) NOT NULL DEFAULT 0,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB;

CREATE TABLE questions (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    game_id BIGINT NOT NULL,
    content TEXT NOT NULL,
    type ENUM('single', 'multiple') NOT NULL DEFAULT 'single',
    time_limit INT NOT NULL DEFAULT 10,
    correct_answer JSON NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (game_id) REFERENCES games(id) ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE question_options (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    question_id BIGINT NOT NULL,
    content VARCHAR(255) NOT NULL,
    `order` INT NOT NULL DEFAULT 0,
    FOREIGN KEY (question_id) REFERENCES questions(id) ON DELETE CASCADE
) ENGINE=InnoDB;

-- 插入游戏数据
INSERT INTO games (title, description, game_code, creator_id, status, created_at, updated_at)
VALUES 
('Spring Boot Quiz', 'A quiz about Spring Boot basics.', 'GAME-12345', 1001, 'published', NOW(), NOW()),
('Java Basics Quiz', 'A quiz about Java programming fundamentals.', 'GAME-67890', 1002, 'draft', NOW(), NOW());

-- 插入题目数据
INSERT INTO questions (game_id, content, type, time_limit, correct_answer, created_at, updated_at)
VALUES 
(1, 'What is Spring Boot?', 'single', 10, '[0]', NOW(), NOW()),
(1, 'Which annotation is used to define a REST controller?', 'single', 15, '[2]', NOW(), NOW()),
(2, 'What is the default access modifier in Java?', 'single', 10, '[1]', NOW(), NOW());

-- 插入题目选项数据
INSERT INTO question_options (question_id, content, `order`)
VALUES 
-- 题目 1 的选项
(1, 'A framework', 0),
(1, 'A library', 1),
(1, 'An operating system', 2),
-- 题目 2 的选项
(2, '@Component', 0),
(2, '@Service', 1),
(2, '@RestController', 2),
-- 题目 3 的选项
(3, 'public', 0),
(3, 'default', 1),
(3, 'private', 2);