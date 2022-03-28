package com.example.firebaseappd;

public class User {
    String  usrImage;
    String usrFirstName;
    String usrLastName;
    String usrPhone;
    String usrCountry;
    String usrProvince;
    String usrDistrict;
    String usrFullAddress;
    String usrEmail;
    String usrPassword;
    String usrCPassword;

    public User() {
    }

    public String getUsrImage() {
        return usrImage;
    }

    public User(String usrImage, String usrFirstName, String usrLastName, String usrPhone, String usrCountry, String usrProvince, String usrDistrict, String usrFullAddress, String usrEmail, String usrPassword, String usrCPassword) {
        this.usrImage = usrImage;
        this.usrFirstName = usrFirstName;
        this.usrLastName = usrLastName;
        this.usrPhone = usrPhone;
        this.usrCountry = usrCountry;
        this.usrProvince = usrProvince;
        this.usrDistrict = usrDistrict;
        this.usrFullAddress = usrFullAddress;
        this.usrEmail = usrEmail;
        this.usrPassword = usrPassword;
        this.usrCPassword = usrCPassword;
    }

    public String getUsrProvince() {
        return usrProvince;
    }

    public void setUsrProvince(String usrProvince) {
        this.usrProvince = usrProvince;
    }

    public String getUsrDistrict() {
        return usrDistrict;
    }

    public void setUsrDistrict(String usrDistrict) {
        this.usrDistrict = usrDistrict;
    }

    public void setUsrImage(String usrImage) {
        this.usrImage = usrImage;
    }

    public String getUsrFirstName() {
        return usrFirstName;
    }

    public void setUsrFirstName(String usrFirstName) {
        this.usrFirstName = usrFirstName;
    }

    public String getUsrLastName() {
        return usrLastName;
    }

    public void setUsrLastName(String usrLastName) {
        this.usrLastName = usrLastName;
    }

    public String getUsrPhone() {
        return usrPhone;
    }

    public void setUsrPhone(String usrPhone) {
        this.usrPhone = usrPhone;
    }

    public String getUsrCountry() {
        return usrCountry;
    }

    public void setUsrCountry(String usrCountry) {
        this.usrCountry = usrCountry;
    }


    public String getUsrFullAddress() {
        return usrFullAddress;
    }

    public void setUsrFullAddress(String usrFullAddress) {
        this.usrFullAddress = usrFullAddress;
    }

    public String getUsrEmail() {
        return usrEmail;
    }

    public void setUsrEmail(String usrEmail) {
        this.usrEmail = usrEmail;
    }

    public String getUsrPassword() {
        return usrPassword;
    }

    public void setUsrPassword(String usrPassword) {
        this.usrPassword = usrPassword;
    }

    public String getUsrCPassword() {
        return usrCPassword;
    }

    public void setUsrCPassword(String usrCPassword) {
        this.usrCPassword = usrCPassword;
    }
}
