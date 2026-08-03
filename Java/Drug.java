public class Drug {
    private int drugId;
    private String drugName;
    private double drugCost;
    private String dosage;

    public Drug() {
    }

    public Drug(int drugId, String drugName, double drugCost, String dosage) {
        this.drugId = drugId;
        this.drugName = drugName;
        this.drugCost = drugCost;
        this.dosage = dosage;
    }

    public int getDrugId() {
        return drugId;
    }

    public void setDrugId(int drugId) {
        this.drugId = drugId;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public double getDrugCost() {
        return drugCost;
    }

    public void setDrugCost(double drugCost) {
        this.drugCost = drugCost;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    @Override
    public String toString() {
        return "Drug{" +
            "drugId=" + drugId +
            ", drugName='" + drugName + '\'' +
            ", drugCost=" + drugCost +
            ", dosage='" + dosage + '\'' +
            '}';
    }
}