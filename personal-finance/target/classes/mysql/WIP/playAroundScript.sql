

INSERT 	INTO financemanagement.user (IsAdmin, EndDate)
VALUES (1, NULL);

INSERT INTO financemanagement.transaction (user_id, timestamp, description, amount)
VALUES (LAST_INSERT_ID(), current_timestamp, 'test', 32);

SELECT * FROM financemanagement.transaction;
SELECT * FROM financemanagement.user;delimiter $$




select current_timestamp;