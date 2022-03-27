package pace_Classes;

public class Accounts{

    controllers.NewRecords_controller newRecords_controller;

    private String sirName;
    private String middleName;
    private String lastName;
    private int idNumber;
    private String emailAddress;
    private String password;
    private java.sql.Blob user_photo;
    private String phoneNumber;
    private java.sql.Date birthDay;
    private int zipCode;
    private String Address;
    private String Occupation;
    private String country;
    private String city;
    private String Hobbies;
    private String any_Other_Details;
    private java.io.InputStream inputImage;

    public Accounts(controllers.NewRecords_controller newRecords_controller, String sirName,
                    String middleName, String lastName, int idNumber, String emailAddress,
                    String password, java.sql.Blob user_photo, String phoneNumber,
                    java.sql.Date birthDay, int zipCode, String address, String occupation,
                    String country, String city, String hobbies, String any_Other_Details,
                    java.io.InputStream inputImage) {

        this.newRecords_controller = newRecords_controller;
        this.sirName = sirName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.emailAddress = emailAddress;
        this.password = password;
        this.user_photo = user_photo;
        this.phoneNumber = phoneNumber;
        this.birthDay = birthDay;
        this.zipCode = zipCode;
        Address = address;
        Occupation = occupation;
        this.country = country;
        this.city = city;
        Hobbies = hobbies;
        this.any_Other_Details = any_Other_Details;
        this.inputImage = inputImage;
    }

    public Accounts() {

    }

    public controllers.NewRecords_controller getNewRecords_controller() {
        return newRecords_controller;
    }

    public void setNewRecords_controller(controllers.NewRecords_controller newRecords_controller) {
        this.newRecords_controller = newRecords_controller;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public java.sql.Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(java.sql.Date birthDay) {
        this.birthDay = birthDay;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getOccupation() {
        return Occupation;
    }

    public void setOccupation(String occupation) {
        Occupation = occupation;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHobbies() {
        return Hobbies;
    }

    public void setHobbies(String hobbies) {
        Hobbies = hobbies;
    }

    public String getAny_Other_Details() {
        return any_Other_Details;
    }

    public void setAny_Other_Details(String any_Other_Details) {
        this.any_Other_Details = any_Other_Details;
    }

    public java.io.InputStream getInputImage() {
        return inputImage;
    }

    public void setInputImage(java.io.InputStream inputImage) {
        this.inputImage = inputImage;
    }

    public java.sql.Blob getUser_photo() {
        return user_photo;
    }

    public void setUser_photo(java.sql.Blob user_photo) {
        this.user_photo = user_photo;
    }

    public String getSirName() {
        return sirName;
    }

    public void setSirName(String sirName) {
        this.sirName = sirName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
