package es.ieslosmontecillos;

import javafx.beans.property.*;

import javax.xml.bind.annotation.XmlElement;


public class Course {

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty category = new SimpleStringProperty();
    private final IntegerProperty duration = new SimpleIntegerProperty();
    private final StringProperty certification = new SimpleStringProperty();
    private final StringProperty provider = new SimpleStringProperty();
    private final StringProperty startDate = new SimpleStringProperty();
    private final StringProperty endDate = new SimpleStringProperty();
    private final IntegerProperty numStudents = new SimpleIntegerProperty();
    private final StringProperty type = new SimpleStringProperty();
    private final BooleanProperty scholarship = new SimpleBooleanProperty();
    private final StringProperty instructor = new SimpleStringProperty();
    private final DoubleProperty amount = new SimpleDoubleProperty();

    // ID field
    @XmlElement (name = "id")
    public Integer getId()
    {
        return id.get();
    }
    public IntegerProperty idProperty()
    {
        return id;
    }
    public void setId(Integer id)
    {
        this.id.set(id);
    }

    // Name field
    @XmlElement (name = "name")
    public String getName()
    {
        return name.get();
    }
    public StringProperty nameProperty()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name.set(name);
    }

    // Category field
    @XmlElement (name = "category")
    public String getCategory()
    {
        return category.get();
    }
    public StringProperty categoryProperty()
    {
        return category;
    }
    public void setCategory(String category)
    {
        this.category.set(category);
    }

    // Duration field
    @XmlElement (name = "duration")
    public Integer getDuration()
    {
        return duration.get();
    }
    public IntegerProperty durationProperty()
    {
        return duration;
    }
    public void setDuration(Integer duration)
    {
        this.duration.set(duration);
    }

    // Certification field
    @XmlElement (name = "certification")
    public String getCertification()
    {
        return certification.get();
    }
    public StringProperty certificationProperty()
    {
        return certification;
    }
    public void setCertification(String certification)
    {
        this.certification.set(certification);
    }

    // Provider field
    @XmlElement (name = "provider")
    public String getProvider()
    {
        return provider.get();
    }
    public StringProperty providerProperty()
    {
        return provider;
    }
    public void setProvider(String provider)
    {
        this.provider.set(provider);
    }

    // StartDate field
    @XmlElement (name = "startDate")
    public String getStartDate()
    {
        return startDate.get();
    }
    public StringProperty startDateProperty()
    {
        return startDate;
    }
    public void setStartDate(String startDate)
    {
        this.startDate.set(startDate);
    }

    // EndDate field
    @XmlElement (name = "endDate")
    public String getEndDate()
    {
        return endDate.get();
    }
    public StringProperty endDateProperty()
    {
        return endDate;
    }
    public void setEndDate (String endDate)
    {
        this.endDate.set(endDate);
    }

    // numStudent field
    @XmlElement (name = "numStudents")
    public int getNumStudents()
    {
        return numStudents.get();
    }
    public IntegerProperty numStudentsProperty()
    {
        return numStudents;
    }
    public void setNumStudents (Integer numStudents)
    {
        this.numStudents.set(numStudents);
    }

    // type field
    @XmlElement (name = "type")
    public String getType()
    {
        return type.get();
    }
    public StringProperty typeProperty()
    {
        return type;
    }
    public void setType(String type)
    {
        this.type.set(type);
    }

    // scholarship field
    @XmlElement (name = "scholarship")
    public Boolean getScholarship()
    {
        return scholarship.get();
    }
    public BooleanProperty scholarshipProperty()
    {
        return scholarship;
    }
    public void setScholarship(Boolean scholarship)
    {
        this.scholarship.set(scholarship);
    }

    // instructor field
    @XmlElement(name = "instructor")
    public String getInstructor()
    {
        return instructor.get();
    }
    public StringProperty instructorProperty()
    {
        return instructor;
    }
    public void setInstructor (String instructor)
    {
        this.instructor.set(instructor);
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
    public void setAmount (double amount)
    {
        this.amount.set(amount);
    }

}
