import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseService {
    private static final String DEFAULT_DB_URL = "jdbc:postgresql://localhost:5432/qap4_java";
    private static final String DEFAULT_USER = "postgres";
    private static final String DEFAULT_PASS = "Qu4rry2020";

    public void savePatient(Patient patient) {
        ensureSchema();

        String sql = "INSERT INTO patients (patient_id, patient_first_name, patient_last_name, patient_dob) VALUES (?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, patient.getPatientId());
            statement.setString(2, patient.getPatientFirstName());
            statement.setString(3, patient.getPatientLastName());
            statement.setDate(4, Date.valueOf(patient.getPatientDOB()));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to save patient to the database.", e);
        }
    }

    public List<Patient> readAllPatients() {
        ensureSchema();

        String sql = "SELECT patient_id, patient_first_name, patient_last_name, patient_dob FROM patients ORDER BY patient_id";
        List<Patient> patients = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Patient patient = new Patient();
                patient.setPatientId(resultSet.getInt("patient_id"));
                patient.setPatientFirstName(resultSet.getString("patient_first_name"));
                patient.setPatientLastName(resultSet.getString("patient_last_name"));
                patient.setPatientDOB(resultSet.getDate("patient_dob").toLocalDate());
                patients.add(patient);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to read patients from the database.", e);
        }

        return patients;
    }

    private void ensureSchema() {
        String sql = "CREATE TABLE IF NOT EXISTS patients ("
                + "patient_id INTEGER PRIMARY KEY CHECK (patient_id BETWEEN 10000000 AND 99999999),"
                + "patient_first_name VARCHAR(100) NOT NULL,"
                + "patient_last_name VARCHAR(100) NOT NULL,"
                + "patient_dob DATE NOT NULL"
                + ")";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to access the database schema.", e);
        }
    }

    private Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL JDBC driver not found.", e);
        }

        String url = getConfiguredValue("db.url", "QAP4_DB_URL", DEFAULT_DB_URL);
        String user = getConfiguredValue("db.user", "QAP4_DB_USER", DEFAULT_USER);
        String password = getConfiguredValue("db.password", "QAP4_DB_PASSWORD", DEFAULT_PASS);
        return DriverManager.getConnection(url, user, password);
    }

    private String getConfiguredValue(String propertyName, String envVarName, String defaultValue) {
        String value = System.getProperty(propertyName);
        if (value != null && !value.isBlank()) {
            return value;
        }

        value = System.getenv(envVarName);
        if (value != null && !value.isBlank()) {
            return value;
        }

        return defaultValue;
    }
}