INSERT INTO roles (name, description, created_at)
VALUES ('ADMIN', 'System administrator', NOW());

INSERT INTO permissions (name, description, created_at)
VALUES
('USER_CREATE', 'Create users', NOW()),
('USER_READ', 'Read users', NOW()),
('USER_UPDATE', 'Update users', NOW()),
('USER_DELETE', 'Delete users', NOW());

INSERT INTO role_permissions (role_id, permission_id)
SELECT r.id, p.id
FROM roles r
CROSS JOIN permissions p
WHERE r.name = 'ADMIN';