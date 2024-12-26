package es.ieslosmontecillos;

import javafx.beans.property.*;

import javax.xml.bind.annotation.XmlElement;

public class Registration {

    private final ObjectProperty<RegistrationID> id = new SimpleObjectProperty<>();
    private final ObjectProperty<Student> student = new SimpleObjectProperty<>();
    private final ObjectProperty<Course> course = new SimpleObjectProperty<>();
    private final StringProperty registrationtype = new SimpleStringProperty();
    private final StringProperty registrationdate = new SimpleStringProperty();
    private final DoubleProperty amount = new SimpleDoubleProperty();
    private final BooleanProperty documentation = new SimpleBooleanProperty();
    private final BooleanProperty certification = new SimpleBooleanProperty();

    /* id field */
    @XmlElement (name = "id")
    public RegistrationID getId() {
        return id.get();
    }

    public ObjectProperty idProperty() {
        return id;
    }

    public void setId(RegistrationID id) {
        this.id.set(id);
    }

    // alum field
    @XmlElement (name = "dniStudent")
    public Student getDniStudent()
    {
        return student.get();
    }
    public ObjectProperty studentDniProperty()
    {
        return student;
    }
    public void setDniStudent(Student student)
    {
        this.student.set(student);
    }

    // course field
    @XmlElement (name = "idCourse")
    public Course getIdCourse()
    {
        return course.get();
    }
    public ObjectProperty courseIdProperty()
    {
        return course;
    }
    public void setIdCourse(Course course)
    {
        this.course.set(course);
    }

    // registrationtype field
    @XmlElement (name = "registrationType")
    public String getRegistrationType()
    {
        return registrationtype.get();
    }
    public StringProperty registrationTypeProperty()
    {
        return registrationtype;
    }
    public void setRegistrationType(String registrationtype)
    {
        this.registrationtype.set(registrationtype);
    }

    // registrationdate field
    @XmlElement (name = "registrationDate")
    public String getRegistrationDate()
    {
        return registrationdate.get();
    }
    public StringProperty registrationDateProperty()
    {
        return registrationdate;
    }
    public void setRegistrationDate (String registrationdate)
    {
        this.registrationdate.set(registrationdate);
    }

    // amount field
    @XmlElement (name = "amount")
    public double getAmount()
    {
        return amount.get();
    }
    public DoubleProperty amountProperty()
    {
        return amount;
    }
    public void setAmount(double amount)
    {
        this.amount.set(amount);
    }

    // documentation field
    @XmlElement ( name = "documentation")
    public Boolean getDocumentation()
    {
        return documentation.get();
    }
    public BooleanProperty documentationProperty()
    {
        return documentation;
    }
    public void setDocumentation(Boolean documentation)
    {
        this.documentation.set(documentation);
    }

    // cerification field
    @XmlElement (name = "certification")
    public Boolean getCertification()
    {
        return certification.get();
    }
    public BooleanProperty certificationProperty()
    {
        return certification;
    }
    public void setCertification (Boolean certification)
    {
        this.certification.set(certification);
    }

}