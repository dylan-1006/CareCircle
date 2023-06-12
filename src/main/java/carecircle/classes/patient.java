package carecircle.classes;

public class patient {
    public String patientID;
    public String name;
    public String ic;
    public String phoneNo;
    public String email;
    public String dateOfBirth;
    public String gender;
    public String address;
    public double height;
    public double weight;
    public String bloodType;

    public patient(String patientID, String name, String ic, String phoneNo, String email, String dateOfBirth,
            String gender, String address, double height, double weight, String bloodType) {

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }
}