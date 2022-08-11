INSERT INTO END_USER (end_user_name, is_loggedin) VALUES 
('user1', '0'),
('user2', '0');

INSERT INTO GOAL (goal_name, goal_description, end_user_id) VALUES 
('goal 1', 'User 1', '1'),
('goal 2', 'User 1', '1'),
('goal 3', 'User 2', '2');

INSERT INTO TASK (task_name, task_description, goal_id) VALUES 
('task 1', 'task 1 - goal 1', '1'),
('task 2', 'task 2 - goal 2', '2');