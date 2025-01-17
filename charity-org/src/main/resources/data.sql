-- Insert data into Address table first
-- Parent address
INSERT INTO address (name, parent_id)
VALUES ('123 Main St', null); -- Assuming this is a root address with no parent

-- Child address under '123 Main St'
INSERT INTO address (name, parent_id)
VALUES ('456 Elm St', 1); -- '456 Elm St' is a child of '123 Main St'
INSERT INTO address (name, parent_id)
VALUES ('456 Green St', 1);
INSERT INTO event (event_name, event_date, event_location_id, description, status)
VALUES
    ('Charity Gala', '2026-12-15', 1, 'A charity event to raise funds for education.', 'UPCOMING'),
    ('Fundraiser for Health', '2026-02-10', 1, 'Raising money for health research.', 'UPCOMING'),
    ('Community Cleanup', '2026-11-20', 1, 'Cleaning up local parks and neighborhoods.', 'CANCELLED'),
    ('Food Drive', '2026-12-05', 1, 'A drive to collect food for the homeless.', 'UPCOMING');

INSERT INTO users (name, email, password) VALUES
                                                       ( 'John Doe', 'johndoe@example.com', 'password123'),
                                                    ('Jane Smith', 'janesmith@example.com', 'password123'),
                                                       ('Courier Mike', 'couriermike@example.com', 'password123'),
                                                        ('Courier Anna', 'courieranna@example.com', 'password123');


-- Insert Dummy Donations
INSERT INTO donation (user_id, date, time, DONATION_STATUS_CLASS_NAME, donation_total_price) VALUES
                                                                                 (1, '2024-11-17', '10:00:00', 'com.charity_org.demo.Classes.State.PendingDonation', 100.00),
                                                                                 (2, '2024-11-16', '12:30:00', 'com.charity_org.demo.Classes.State.PendingDonation', 150.00),
                                                                                 (1, '2024-11-15', '14:00:00', 'com.charity_org.demo.Classes.State.PendingDonation', 200.00),
                                                                                 (2, '2024-11-14', '16:15:00', 'com.charity_org.demo.Classes.State.PendingDonation', 50.00);
INSERT INTO Role (name) VALUES ('ROLE_USER'), ('ROLE_ADMIN'), ('ROLE_COURIER') ,('ROLE_SUPERADMIN') ;

INSERT INTO User_Role (is_Deleted,user_id,role_id) VALUES
                                                       (FALSE,2,1),
                                                       (FALSE,2,2),
                                                       (FALSE,2,4),
                                                       (FALSE,1,1),
                                                       (FALSE,1,2),
                                                       (FALSE, 1, 3);


INSERT INTO currencies (code, name) VALUES
                                        ('EGP', 'Egyptian Pound'),
                                        ('USD', 'US Dollar'),
                                        ('EUR', 'Euro'),
                                        ('GBP', 'British Pound'),
                                        ('JPY', 'Japanese Yen'),
                                        ('CNY', 'Chinese Yuan'),
                                        ('SAR', 'Saudi Riyal'),
                                        ('AED', 'UAE Dirham');
INSERT INTO furniture_types (name, description) VALUES
                                                    ('CHAIR', 'Standard chair'),
                                                    ('TABLE', 'Dining or work table'),
                                                    ('SOFA', 'Living room sofa'),
                                                    ('BED', 'Bedroom bed'),
                                                    ('DESK', 'Work desk'),
                                                    ('CABINET', 'Storage cabinet'),
                                                    ('SHELF', 'Wall shelf'),
                                                    ('DRESSER', 'Bedroom dresser'),
                                                    ('COFFEE_TABLE', 'Living room coffee table'),
                                                    ('ARMCHAIR', 'Comfortable armchair');
-- -- Insert Dummy Assignments
-- INSERT INTO assigments (donation_id, courier_id) VALUES
--                                                          (1, 3), -- Courier Mike is assigned to Donation 1
--                                                          (2, 4); -- Courier Anna is assigned to Donation 2
