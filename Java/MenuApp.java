import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class MenuApp {
    private final Scanner scanner;
    private final FileService fileService;
    private final DatabaseService databaseService;

    public MenuApp() {
        this.scanner = new Scanner(System.in);
        this.fileService = new FileService();
        this.databaseService = new DatabaseService();
    }

    public static void main(String[] args) {
        new MenuApp().run();
    }

    public static int parseEightDigitId(String input) {
        if (input != null && input.matches("\\d{8}")) {
            int id = Integer.parseInt(input);
            if (id >= 10000000 && id <= 99999999) {
                return id;
            }
        }
        throw new IllegalArgumentException("ID must be exactly 8 digits.");
    }

    public void run() {
        boolean running = true;

        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    saveDrugToFile();
                    break;
                case "2":
                    readDrugsFromFile();
                    break;
                case "3":
                    savePatientToDatabase();
                    break;
                case "4":
                    readPatientsFromDatabase();
                    break;
                case "5":
                    running = false;
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1 to 5.");
            }

            System.out.println();
        }

        scanner.close();
    }

    private void printMenu() {
        System.out.println("=== QAP 4 Menu ===");
        System.out.println("1. Save drug data to file");
        System.out.println("2. Read drug data from file");
        System.out.println("3. Save patient data to database");
        System.out.println("4. Read patient data from database");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
    }

    private void saveDrugToFile() {
        try {
            System.out.print("Enter drug ID (8 digits): ");
            int drugId = parseEightDigitId(scanner.nextLine().trim());

            System.out.print("Enter drug name: ");
            String drugName = scanner.nextLine().trim();

            System.out.print("Enter drug cost: ");
            double drugCost = Double.parseDouble(scanner.nextLine().trim());

            System.out.print("Enter dosage: ");
            String dosage = scanner.nextLine().trim();

            Drug drug = new Drug(drugId, drugName, drugCost, dosage);
            fileService.saveDrugToFile(drug);
            System.out.println("Drug saved to file successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid number entered. Drug was not saved.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + " Drug was not saved.");
        }
    }

    private void readDrugsFromFile() {
        List<Drug> drugs = fileService.readDrugsFromFile();

        if (drugs.isEmpty()) {
            System.out.println("No drug data found in the file.");
            return;
        }

        System.out.println("Drugs from file:");
        for (Drug drug : drugs) {
            System.out.println(drug);
        }
    }

    private void savePatientToDatabase() {
        try {
            System.out.print("Enter patient ID (8 digits): ");
            int patientId = parseEightDigitId(scanner.nextLine().trim());

            System.out.print("Enter patient first name: ");
            String firstName = scanner.nextLine().trim();

            System.out.print("Enter patient last name: ");
            String lastName = scanner.nextLine().trim();

            System.out.print("Enter patient DOB (YYYY-MM-DD): ");
            LocalDate patientDOB = LocalDate.parse(scanner.nextLine().trim());

            Patient patient = new Patient(patientId, firstName, lastName, patientDOB);
            databaseService.savePatient(patient);
            System.out.println("Patient saved to database successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + " Patient was not saved.");
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Use YYYY-MM-DD.");
        } catch (RuntimeException e) {
            System.out.println(formatRuntimeError(e));
        }
    }

    private void readPatientsFromDatabase() {
        try {
            List<Patient> patients = databaseService.readAllPatients();

            if (patients.isEmpty()) {
                System.out.println("No patient data found in the database.");
                return;
            }

            System.out.println("Patients from database:");
            for (Patient patient : patients) {
                System.out.println(patient);
            }
        } catch (RuntimeException e) {
            System.out.println(formatRuntimeError(e));
        }
    }

    private String formatRuntimeError(RuntimeException exception) {
        String rootMessage = getRootCauseMessage(exception);
        if (rootMessage == null || rootMessage.isBlank() || rootMessage.equals(exception.getMessage())) {
            return exception.getMessage();
        }
        return exception.getMessage() + " Details: " + rootMessage;
    }

    private String getRootCauseMessage(Throwable throwable) {
        Throwable current = throwable;
        while (current.getCause() != null) {
            current = current.getCause();
        }
        return current.getMessage();
    }
}