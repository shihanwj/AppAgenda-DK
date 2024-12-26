package es.ieslosmontecillos;

import javafx.beans.property.*;

import javax.xml.bind.annotation.XmlElement;

public class RegistrationID {

    private final SimpleStringProperty dniStudent = new SimpleStringProperty();
    private final SimpleIntegerProperty idCourse = new SimpleIntegerProperty();

    @XmlElement(name = "dniStudent")
    public String getStudentDNI()
    {
        return dniStudent.get();
    }
    public StringProperty studentDNIProperty()
    {
        return dniStudent;
    }
    public void setStudentDNI(String studentDNI)
    {
        this.dniStudent.set(studentDNI);
    }

    @XmlElement (name = "idCourse")
    public int getIdCourse()
    {
        return idCourse.get();
    }
    public IntegerProperty idCourseProperty()
    {
        return idCourse;
    }
    public void setIdCourse(int idCourse)
    {
        this.idCourse.set(idCourse);
    }
}