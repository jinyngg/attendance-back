-- 부서
INSERT INTO department (type, created_at, updated_at)
VALUES
    ('개발팀', NOW(), NOW()),
    ('기획팀', NOW(), NOW()),
    ('영업팀', NOW(), NOW()),
    ('인사팀', NOW(), NOW()),
    ('회계팀', NOW(), NOW()),
    ('법무팀', NOW(), NOW());

-- 직급
INSERT INTO position (type, created_at, updated_at)
VALUES
    ('사원', NOW(), NOW()),
    ('대리', NOW(), NOW()),
    ('과장', NOW(), NOW()),
    ('차장', NOW(), NOW()),
    ('부장', NOW(), NOW());

-- 상태
INSERT INTO status (type, created_at, updated_at)
VALUES
    ('입사', NOW(), NOW()),
    ('퇴사', NOW(), NOW()),
    ('재입사', NOW(), NOW()),
    ('휴직', NOW(), NOW()),
    ('복직', NOW(), NOW()),
    ('퇴직', NOW(), NOW());
