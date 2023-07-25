-- 직급(사원, 대리, 과장, 부장, 차장, 전무, 상무, 사장 등..)
CREATE TABLE position
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    type       VARCHAR(20)  NOT NULL,
    created_at TIMESTAMP   NOT NULL,
    updated_at TIMESTAMP   NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 회원 상태(입사, 퇴사, 재입사, 휴직, 육아휴직, 복직 등..)
CREATE TABLE status
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    type       VARCHAR(20) NOT NULL,
    created_at TIMESTAMP   NOT NULL,
    updated_at TIMESTAMP   NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 부서(개발, 기획, 영업, 인사, 회계, 법무 등..)
CREATE TABLE department
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    type       VARCHAR(20)  NOT NULL,
    created_at TIMESTAMP   NOT NULL,
    updated_at TIMESTAMP   NOT NULL
);

-- 회원 테이블 생성
CREATE TABLE employee
(
    id             BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    status_id      BIGINT COMMENT '회원상태 ID',
    position_id    BIGINT COMMENT '직급 ID',
    department_id  BIGINT COMMENT '부서 ID',
    email          VARCHAR(100) UNIQUE COMMENT '이메일',
    phone          VARCHAR(60) COMMENT '전화번호',
    name           VARCHAR(100) COMMENT '이름',
    birthdate      DATE COMMENT '생년월일',
    password       CHAR(60) COLLATE ascii_bin COMMENT '비밀번호',
    zip_address    CHAR(5) COLLATE ascii_bin COMMENT '우편번호',
    road_address   VARCHAR(255) COMMENT '주소1(도로명)',
    detail_address VARCHAR(255) COMMENT '주소2(상세주소)',
    role           VARCHAR(20) COMMENT '권한',
    hire_date      DATE COMMENT '입사일',
    quit_date      DATE COMMENT '퇴사일',
    created_at     TIMESTAMP NOT NULL COMMENT '생성일',
    updated_at     TIMESTAMP NOT NULL COMMENT '수정일',
    FOREIGN KEY (position_id) REFERENCES position (id),    -- position 테이블의 id 칼럼을 참조
    FOREIGN KEY (status_id) REFERENCES status (id),        -- status 테이블의 id 칼럼을 참조
    FOREIGN KEY (department_id) REFERENCES department (id) -- department 테이블의 id 칼럼을 참조;
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 회원 상태 이력 테이블 생성
CREATE TABLE status_history
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    employee_id BIGINT    NOT NULL,
    status_id   BIGINT    NOT NULL,
    created_at  TIMESTAMP NOT NULL,
    updated_at  TIMESTAMP NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employee (id),
    FOREIGN KEY (status_id) REFERENCES status (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 연차/당직 테이블
CREATE TABLE attendance
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    member_id  BIGINT      NOT NULL COMMENT '회원 ID',
    start_date DATE        NOT NULL COMMENT '시작일',
    end_date   DATE        NOT NULL COMMENT '종료일',
    type       VARCHAR(20) NOT NULL COMMENT '연차/당직',
    created_at TIMESTAMP   NOT NULL COMMENT '생성일',
    updated_at TIMESTAMP   NOT NULL COMMENT '수정일',
    FOREIGN KEY (member_id) REFERENCES employee (id)
);
