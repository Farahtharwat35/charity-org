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

INSERT INTO users (id, name, email, password) VALUES
                                                       (1, 'John Doe', 'johndoe@example.com', 'password123'),
                                                       (2, 'Jane Smith', 'janesmith@example.com', 'password123'),
                                                       (3, 'Courier Mike', 'couriermike@example.com', 'password123'),
                                                        (4, 'Courier Anna', 'courieranna@example.com', 'password123');

-- Insert Dummy Donations
INSERT INTO donation (id, user_id, date, time, status, donation_total_price) VALUES
                                                                                 (1, 1, '2024-11-17', '10:00:00', 'PENDING', 100.00),
                                                                                 (2, 2, '2024-11-16', '12:30:00', 'PENDING', 150.00),
                                                                                 (3, 1, '2024-11-15', '14:00:00', 'PENDING', 200.00),
                                                                                 (4, 2, '2024-11-14', '16:15:00', 'PENDING', 50.00);

-- Insert Dummy Assignments
INSERT INTO assigments (id, donation_id, courier_id) VALUES
                                                         (1, 1, 3), -- Courier Mike is assigned to Donation 1
                                                         (2, 2, 4); -- Courier Anna is assigned to Donation 2