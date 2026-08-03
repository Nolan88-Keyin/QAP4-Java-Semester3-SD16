CREATE TABLE IF NOT EXISTS patients (
    patient_id INTEGER PRIMARY KEY CHECK (patient_id BETWEEN 10000000 AND 99999999),
    patient_first_name VARCHAR(100) NOT NULL,
    patient_last_name VARCHAR(100) NOT NULL,
    patient_dob DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS drugs (
    drug_id INTEGER PRIMARY KEY CHECK (drug_id BETWEEN 10000000 AND 99999999),
    drug_name VARCHAR(150) NOT NULL,
    drug_cost NUMERIC(10, 2) NOT NULL,
    dosage VARCHAR(100) NOT NULL
);