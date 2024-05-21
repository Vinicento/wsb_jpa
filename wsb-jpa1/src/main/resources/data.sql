-- Inserting data into ADDRESS table
INSERT INTO ADDRESS (id, city, address_line1, address_line2, postal_code) VALUES
(1, 'New York', '123 Broadway', 'Apt 1', '62-030'),
(2, 'Los Angeles', '456 Elm Street', 'Suite 200', '90001'),
(3, 'Chicago', '789 Pine Street', 'Apt 5', '60601');

-- Inserting data into DOCTOR table
INSERT INTO DOCTOR (id, first_name, last_name, telephone_number, email, doctor_number, specialization) VALUES
(1, 'John', 'Doe', '1234567890', 'johndoe@example.com', 'DOC123', 'SURGEON'),
(2, 'Jane', 'Smith', '2345678901', 'janesmith@example.com', 'DOC234', 'DERMATOLOGIST');

-- Inserting data into PATIENT table
INSERT INTO PATIENT (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, is_woman, address_id) VALUES
(1, 'Alice', 'Johnson', '3456789012', 'alice@example.com', 'PAT123', '1985-06-15', TRUE, 1),
(2, 'Bob', 'Lee', '4567890123', 'boblee@example.com', 'PAT234', '1990-12-22', FALSE, 2),
(3, 'Emma', 'Smith', '5678901234', 'emmasmith@example.com', 'PAT345', '1993-07-19', TRUE, 2),
(4, 'David', 'Smith', '6789012345', 'davidsmith@example.com', 'PAT456', '1988-03-15', FALSE, 3);

-- Inserting data into VISIT table
INSERT INTO VISIT (id, description, time, doctor_id, patient_id) VALUES
(1, 'Routine checkup', '2022-05-10 10:00:00', 1, 1),
(2, 'Annual physical', '2024-05-20 14:00:00', 2, 2),
(3, 'Follow-up visit', '2024-05-30 09:00:00', 1, 1),
(4, 'Emergency visit', '2024-05-15 20:00:00', 1, 1),
(5, 'Dental check', '2025-06-01 13:00:00', 2, 3);

-- Inserting data into MEDICAL_TREATMENT table
INSERT INTO MEDICAL_TREATMENT (id, description, type, visit_id) VALUES
(1, 'Blood Test', 'DIAGNOSTIC', 1),
(2, 'Allergy Screening', 'ALLERGY_TEST', 2),
(3, 'MRI Scan', 'DIAGNOSTIC', 3),
(4, 'X-ray', 'DIAGNOSTIC', 4),
(5, 'Teeth Cleaning', 'DENTAL', 5);
