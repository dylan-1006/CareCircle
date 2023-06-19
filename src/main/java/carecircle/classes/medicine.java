package carecircle.classes;

public class medicine {
    private String medicineID;
    private int amountLeft;
    private double dosage;

    public medicine(String medicineID, int amountLeft, double dosage) {
        this.medicineID = medicineID;
        this.amountLeft = amountLeft;
        this.dosage = dosage;
    }

    public String getMedicineID() {
        return medicineID;
    }

    public void setMedicineID(String medicineID) {
        this.medicineID = medicineID;
    }

    public int getAmountLeft() {
        return amountLeft;
    }

    public void setAmountLeft(int amountLeft) {
        this.amountLeft = amountLeft;
    }

    public double getDosage() {
        return dosage;
    }

    public void setDosage(double dosage) {
        this.dosage = dosage;
    }
}
