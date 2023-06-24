package carecircle.classes;

public class doctor implements personInterface {
    private String doctorID;
    private String name;
    private String phoneNo;
    private String email;
    private String dateOfBirth;
    private String gender;
    private String specialization;

    public doctor(String doctorID, String name, String phoneNo, String email, String dateOfBirth, String gender,
            String specialization) {

                this.doctorID=doctorID;
                this.name=name;
                this.phoneNo=phoneNo;
                this.email=email;
                this.dateOfBirth=dateOfBirth;
                this.gender=gender;
                this.specialization=specialization;
    }
    
        public String getDoctorID() {
            return doctorID;
        }
    
        public void setDoctorID(String doctorID) {
            this.doctorID = doctorID;
        }
    
        public String getName() {
            return name;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    
        public String getPhoneNo() {
            return phoneNo;
        }
    
        public void setPhoneNo(String phoneNo) {
            this.phoneNo = phoneNo;
        }
    
        public String getEmail() {
            return email;
        }
    
        public void setEmail(String email) {
            this.email = email;
        }
    
        public String getDateOfBirth() {
            return dateOfBirth;
        }
    
        public void setDateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }
    
        public String getGender() {
            return gender;
        }
    
        public void setGender(String gender) {
            this.gender = gender;
        }
    
        public String getSpecialization() {
            return specialization;
        }
    
        public void setSpecialization(String specialization) {
            this.specialization = specialization;
        }
    }