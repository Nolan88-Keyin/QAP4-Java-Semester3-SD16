import java.time.LocalDate;

public class Patient {
    private int patientId;
    private String patientFirstName;
    private String patientLastName;
    private LocalDate patientDOB;

    public Patient() {
    }

    public Patient(int patientId, String patientFirstName, String patientLastName, LocalDate patientDOB) {
        this.patientId = patientId;
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
        this.patientDOB = patientDOB;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public LocalDate getPatientDOB() {
        return patientDOB;
    }

    public void setPatientDOB(LocalDate patientDOB) {
        this.patientDOB = patientDOB;
    }

    @Override
    public String toString() {
        return "Patient{" +
            "patientId=" + patientId +
            ", patientFirstName='" + patientFirstName + '\'' +
            ", patientLastName='" + patientLastName + '\'' +
            ", patientDOB=" + patientDOB +
            '}';
    }
}