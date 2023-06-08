package carecircle.classes;

public class doctor {
    public String doctorID;
    public String name;
    public String phoneNo;
    public String email;
    public String dateOfBirth;
    public String gender;
    public String specialization;

    public doctor(String doctorID, String name, String phoneNo, String email, String dateOfBirth, String gender,
            String specialiation) {

                this.doctorID=doctorID;
                this.name=name;
                this.phoneNo=phoneNo;
                this.email=email;
                this.dateOfBirth=dateOfBirth;
                this.gender=gender;
                this.specialization=specialiation;
    }
}
