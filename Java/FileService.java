import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileService {
    private static final String DEFAULT_DRUG_FILE_PATH = "data/drugs.txt";

    public void saveDrugToFile(Drug drug) {
        saveDrugToFile(drug, DEFAULT_DRUG_FILE_PATH);
    }

    public void saveDrugToFile(Drug drug, String filePath) {
        Path path = resolvePath(filePath);

        try {
            if (path.getParent() != null) {
                Files.createDirectories(path.getParent());
            }

            try (BufferedWriter writer = Files.newBufferedWriter(path,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND)) {
                writer.write(formatDrug(drug));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Drug> readDrugsFromFile() {
        return readDrugsFromFile(DEFAULT_DRUG_FILE_PATH);
    }

    public List<Drug> readDrugsFromFile(String filePath) {
        Path path = resolvePath(filePath);
        List<Drug> drugs = new ArrayList<>();

        if (!Files.exists(path)) {
            return drugs;
        }

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (!line.isBlank()) {
                    drugs.add(parseDrug(line));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return drugs;
    }

    private Path resolvePath(String filePath) {
        Path path = Paths.get(filePath);

        if (path.isAbsolute()) {
            return path;
        }

        Path current = Paths.get("").toAbsolutePath().normalize();
        Path projectRoot = findProjectRoot(current);
        if (projectRoot != null) {
            return projectRoot.resolve(path).normalize();
        }

        Path candidate = current.resolve(path);

        Path search = current;
        while (search != null) {
            Path possible = search.resolve(path);
            if (Files.exists(possible)) {
                return possible;
            }

            search = search.getParent();
        }

        return candidate;
    }

    private Path findProjectRoot(Path start) {
        Path search = start;
        while (search != null) {
            if (Files.exists(search.resolve("pom.xml"))) {
                return search;
            }
            search = search.getParent();
        }
        return null;
    }

    private String formatDrug(Drug drug) {
        return drug.getDrugId() + "," +
            drug.getDrugName() + "," +
            drug.getDrugCost() + "," +
            drug.getDosage();
    }

    private Drug parseDrug(String line) {
        String[] parts = line.split(",", 4);

        Drug drug = new Drug();
        drug.setDrugId(Integer.parseInt(parts[0]));
        drug.setDrugName(parts[1]);
        drug.setDrugCost(Double.parseDouble(parts[2]));
        drug.setDosage(parts[3]);
        return drug;
    }
}