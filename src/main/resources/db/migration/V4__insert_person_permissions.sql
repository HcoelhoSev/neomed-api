INSERT INTO permissions (name, description, created_at)
VALUES
('PERSON_CREATE', 'Create persons', NOW()),
('PERSON_READ', 'Read persons', NOW()),
('PERSON_UPDATE', 'Update persons', NOW()),
('PERSON_DELETE', 'Delete persons', NOW());

INSERT INTO role_permissions (role_id, permission_id)
SELECT r.id, p.id
FROM roles r
JOIN permissions p ON p.name IN (
    'PERSON_CREATE',
    'PERSON_READ',
    'PERSON_UPDATE',
    'PERSON_DELETE'
)
WHERE r.name = 'ADMIN';