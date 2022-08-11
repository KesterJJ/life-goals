INSERT INTO END_USER (end_user_name, is_loggedin) VALUES 
('user1', '0'),
('user2', '1');

INSERT INTO GOAL (goal_name, goal_description, end_user_id) VALUES 
('goal1', 'user1', '1'),
('goal2', 'user1', '1'),
('goal3', 'user2', '2');

INSERT INTO TASK (task_name, task_description, goal_id) VALUES 
('task1', 'goal1', '1'),
('task2', 'goal2', '2');