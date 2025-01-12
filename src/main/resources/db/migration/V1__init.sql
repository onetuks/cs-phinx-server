# CREATE TABLE IF NOT EXISTS flyway_schema_history
# (
#     installed_rank INT                     NOT NULL,
#     version        VARCHAR(50)             NULL,
#     `description`  VARCHAR(200)            NOT NULL,
#     type           VARCHAR(20)             NOT NULL,
#     script         VARCHAR(1000)           NOT NULL,
#     checksum       INT                     NULL,
#     installed_by   VARCHAR(100)            NOT NULL,
#     installed_on   timestamp DEFAULT NOW() NOT NULL,
#     execution_time INT                     NOT NULL,
#     success        TINYINT(1)              NOT NULL,
#     CONSTRAINT `PRIMARY` PRIMARY KEY (installed_rank)
# ) ENGINE InnoDB
#   DEFAULT CHARSET = utf8mb4;
#
# CREATE INDEX flyway_schema_history_s_idx ON flyway_schema_history (success);

CREATE TABLE IF NOT EXISTS workbooks
(
    workbook_id     BIGINT       NOT NULL AUTO_INCREMENT COMMENT '문제집 식별자',
    title           VARCHAR(255) NOT NULL COMMENT '문제집 제목',
    description     VARCHAR(255) NOT NULL COMMENT '문제집 설명',
    collection_type ENUM (
        'TOPIC_POINT', 'QUICK_POINT',
        'KEY_POINT', 'TRAP_POINT',
        'DAILY_POINT')           NOT NULL COMMENT '모음집 유형',
    is_active       BOOLEAN      NOT NULL DEFAULT TRUE COMMENT '문제집 활성화 여부',
    updated_at       timestamp             DEFAULT NOW() NOT NULL,
    PRIMARY KEY (workbook_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT = '문제집 테이블';

CREATE TABLE IF NOT EXISTS problems
(
    problem_id  BIGINT            NOT NULL AUTO_INCREMENT COMMENT '문제 식별자',
    title       VARCHAR(255)      NOT NULL COMMENT '문제 제목',
    description VARCHAR(5000)     NOT NULL COMMENT '문제 설명',
    difficulty  ENUM (
        'EASY', 'MEDIUM', 'HARD') NOT NULL COMMENT '문제 난이도',
    topic       ENUM (
        'PROGRAMMING_LANGUAGE',
        'DATA_STRUCTURES',
        'ALGORITHMS',
        'DATABASES',
        'OPERATING_SYSTEMS',
        'NETWORKING',
        'SOFTWARE_ENGINEERING',
        'COMPUTER_ARCHITECTURE',
        'SOFTWARE_TOOLS',
        'WEB_DEVELOPMENT')        NOT NULL COMMENT '문제 주제',
    tags         JSON              NOT NULL COMMENT '문제 태그',
    is_active   BOOLEAN           NOT NULL DEFAULT TRUE COMMENT '문제 활성화 여부',
    updated_at   timestamp                  DEFAULT NOW() NOT NULL,
    PRIMARY KEY (problem_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT = '문제 테이블';

CREATE TABLE IF NOT EXISTS problem_workbooks
(
    problem_workbook_id BIGINT NOT NULL AUTO_INCREMENT,
    workbook_id         BIGINT NOT NULL,
    problem_id          BIGINT NOT NULL,
    PRIMARY KEY (problem_workbook_id),
    FOREIGN KEY fk_problem_workbooks_workbook_id (workbook_id)
        REFERENCES workbooks (workbook_id) ON DELETE CASCADE,
    FOREIGN KEY fk_problem_workbooks_problem_id (problem_id)
        REFERENCES problems (problem_id) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE IF NOT EXISTS answers
(
    answer_id     BIGINT                  NOT NULL AUTO_INCREMENT COMMENT '해답 식별자',
    problem_id    BIGINT                  NOT NULL,
    answer_type   ENUM (
        'CHOICE',
        'SHORT',
        'DESCRIPTION')                    NOT NULL COMMENT '해답 유형',
    answer_values JSON                    NOT NULL COMMENT '모범답안',
    updated_at     timestamp DEFAULT now() NOT NULL,
    PRIMARY KEY (answer_id),
    FOREIGN KEY fk_answers_problem_id (problem_id)
        REFERENCES problems (problem_id) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT = '해답 테이블';
