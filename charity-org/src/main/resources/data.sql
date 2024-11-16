-- Insert data into Address table first
-- Parent address
INSERT INTO address (id, name, parent_id)
VALUES (1, '123 Main St', NULL); -- Assuming this is a root address with no parent

-- Child address under '123 Main St'
INSERT INTO address (id, name, parent_id)
VALUES (2, '456 Elm St', 1); -- '456 Elm St' is a child of '123 Main St'

INSERT INTO event (event_name, event_date, event_location_id, description, status)
VALUES
    ('Charity Gala', '2024-12-15', 1, 'A charity event to raise funds for education.', 'UPCOMING'),
    ('Fundraiser for Health', '2025-01-10', 1, 'Raising money for health research.', 'UPCOMING'),
    ('Community Cleanup', '2024-11-20', 1, 'Cleaning up local parks and neighborhoods.', 'CANCELLED'),
    ('Food Drive', '2024-12-05', 1, 'A drive to collect food for the homeless.', 'UPCOMING');