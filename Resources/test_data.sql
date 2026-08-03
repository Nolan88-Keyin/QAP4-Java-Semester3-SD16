INSERT INTO patients (patient_id, patient_first_name, patient_last_name, patient_dob)
VALUES
    (10000001, 'John', 'Smith', '1990-05-12'),
    (10000002, 'Mary', 'Johnson', '1985-11-24'),
    (10000003, 'Alex', 'Brown', '2001-02-08')
ON CONFLICT (patient_id) DO NOTHING;

INSERT INTO drugs (drug_id, drug_name, drug_cost, dosage)
VALUES
    (10000004, 'Aspirin', 5.99, '100mg'),
    (10000005, 'Ibuprofen', 7.50, '200mg'),
    (10000006, 'Amoxicillin', 12.25, '500mg')
ON CONFLICT (drug_id) DO NOTHING;
