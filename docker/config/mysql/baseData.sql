USE mysql_db;

INSERT INTO prompts (member_id, prompt_type, title, content, scenario_id, created_at, modified_at)
VALUES (1, 'PRE_SCRIPTED', '테스트 프롬프트', '테스트 프롬프트입니다.', 'SCENARIO_001', NOW(), NOW());

INSERT INTO `translation_tags` (code) VALUES
                                          ('GRAMMAR'),
                                          ('SPELLING'),
                                          ('STRUCTURE'),
                                          ('WORD_UNKNOWN');