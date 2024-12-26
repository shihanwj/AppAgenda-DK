package es.ieslosmontecillos;

import javafx.beans.property.*;
import javax.xml.bind.annotation.XmlElement;

public class Province {

    /* Elements of the table Provincia */
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty code = new SimpleStringProperty();
    private final StringProperty name = new SimpleStringProperty();

    // ID Field
    @XmlElement(name = "id")
    public Integer getID()
    {
        return id.get();
    }
    public IntegerProperty idProperty()
    {
        return id;
    }
    public void setID(Integer id)
    {
        this.id.set(id);
    }

    // Code Field
    @XmlElement(name = "code")
    public String getCode()
    {
        return code.get();
    }
    public StringProperty codeProperty()
    {
        return code;
    }
    public void setCode(String code)
    {
        this.code.set(code);
    }

    //Name field
    @XmlElement (name = "name")
    public String getName()
    {
        return name.get();
    }
    public StringProperty nameProperty()
    {
        return name;
    }
    public void setName (String name)
    {
        this.name.set(name);
    }

}