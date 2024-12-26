package es.ieslosmontecillos;

import javafx.beans.property.*;
import javax.xml.bind.annotation.XmlElement;

public class Student {

    private final StringProperty dni = new SimpleStringProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty gender = new SimpleStringProperty();
    private final StringProperty address = new SimpleStringProperty();
    private final StringProperty phone = new SimpleStringProperty();
    private final ObjectProperty province = new SimpleObjectProperty<>();
    private final StringProperty locality = new SimpleStringProperty();
    private final StringProperty birthday = new SimpleStringProperty();

    // DNI field
    @XmlElement (name = "dni")
    public String getDni() {
        return dni.get();
    }
    public StringProperty dniProperty() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni.set(dni);
    }

    // name field
    @XmlElement (name = "name")
    public String getName() {
        return name.get();
    }
    public StringProperty nameProperty() {
        return name;
    }
    public void setName(String name) {
        this.name.set(name);
    }

    // gender field
    @XmlElement (name = "genre" )
    public String getGenre() {
        return gender.get();
    }
    public StringProperty genreProperty() {
        return gender;
    }
    public void setGenre(String gender) {
        this.gender.set(gender);
    }

    // address field
    @XmlElement (name = "address")
    public String getAddress() {
        return address.get();
    }
    public StringProperty addressProperty() {
        return address;
    }
    public void setAddress(String address) {
        this.address.set(address);
    }

    // phone field
    @XmlElement (name = "phone")
    public String getPhone() {
        return phone.get();
    }
    public StringProperty phoneProperty() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    // province field
    @XmlElement (name = "province")
    public Province getProvince() {
        return (Province) province.get();
    }
    public ObjectProperty provinceProperty() {
        return province;
    }
    public void setProvince(Province province) {
        this.province.set(province);
    }

    // locality field
    @XmlElement (name = "locality")
    public String getLocality() {
        return locality.get();
    }
    public StringProperty localityProperty() {
        return locality;
    }
    public void setLocality(String locality) {
        this.locality.set(locality);
    }

    // birthday field
    @XmlElement (name = "birthday")
    public String getBirthday() {
        return birthday.get();
    }
    public StringProperty birthdayProperty() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday.set(birthday);
    }

}