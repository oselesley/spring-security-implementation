INSERT INTO roles (
    role
)

VALUES ('ADMIN'),
       ('USER'),
       ('SUBMIN');

INSERT INTO users (
                   username, password, role_id,
                   account_non_expired, account_non_locked,
                   credentials_non_expired, enabled
                   )

VALUES ('oselesley', '$2y$12$CoZtN59qBSHmj1I8JAHx7ek/x99/TgwmFktqcIKV6cxdgjneCulBi',
        1, true, true, true, true),

('kenemike', '$2y$12$l04eZlbIFUwoL49Tgp6jheSErCnP3IqDOfIDnsiVbgn9ef6Ub2v4K',
           2, true, true, true, true);

INSERT INTO authorities (
    role_id, authority
)

VALUES ('1', 'user:write'),
('1', 'user:read'),
('1', 'transaction:write'),
('1', 'transaction:read'),
('2', 'user:read'),
('2', 'transaction:read'),
('3', 'student:read');