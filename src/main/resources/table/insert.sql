-- 부서
INSERT INTO department (type, created_at, updated_at)
VALUES
    ('AUTOMOBILE', NOW(), NOW()),
    ('STORE', NOW(), NOW()),
    ('SECURITIES', NOW(), NOW()),
    ('SEMICON', NOW(), NOW()),
    ('MEDICAL', NOW(), NOW()),
    ('UNKNOWN', NOW(), NOW()),
    ('MICRO', NOW(), NOW());

-- 직급
INSERT INTO position (type, created_at, updated_at)
VALUES
    ('STAFF', NOW(), NOW()),
    ('ASSISTANT_MANAGER', NOW(), NOW()),
    ('MANAGER', NOW(), NOW()),
    ('DEPUTY_GENERAL_MANAGER', NOW(), NOW()),
    ('GENERAL_MANAGER', NOW(), NOW());

-- 상태
INSERT INTO status (type, created_at, updated_at)
VALUES
    ('JOINED', NOW(), NOW()),
    ('RESIGNED', NOW(), NOW()),
    ('REJOINED', NOW(), NOW()),
    ('ON_LEAVE', NOW(), NOW()),
    ('REINSTATED', NOW(), NOW()),
    ('RETIRED', NOW(), NOW());

-- 직급 기준 연차 지급 개수
INSERT INTO day_off_by_position (position_id, amount, created_at, updated_at)
VALUES
    ((SELECT id FROM position WHERE position.type = 'STAFF'), 15, NOW(), NOW()),
    ((SELECT id FROM position WHERE position.type = 'ASSISTANT_MANAGER'), 17, NOW(), NOW()),
    ((SELECT id FROM position WHERE position.type = 'MANAGER'), 19, NOW(), NOW()),
    ((SELECT id FROM position WHERE position.type = 'DEPUTY_GENERAL_MANAGER'), 21, NOW(), NOW()),
    ((SELECT id FROM position WHERE position.type = 'GENERAL_MANAGER'), 23, NOW(), NOW());
