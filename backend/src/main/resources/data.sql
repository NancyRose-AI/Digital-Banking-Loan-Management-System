-- Seed user 1
INSERT INTO users (id, username, password, email, first_name, last_name, phone_number, enabled, created_at)
VALUES (1, 'testuser', '$2a$10$wK2w5J0d3e5U4cO2W4R3U.0U9L5W0U0U0U0U0U0U0U0U0U0U0U0U', 'test@example.com', 'Test', 'User', '1234567890', true, CURRENT_TIMESTAMP);

-- Seed credit score for user 1
INSERT INTO credit_scores (user_id, score, risk_category) VALUES (1, 720, 'MEDIUM');

