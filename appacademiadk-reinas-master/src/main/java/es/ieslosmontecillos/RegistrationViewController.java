package es.ieslosmontecillos;

import com.gluonhq.connect.GluonObservableObject;
import com.gluonhq.connect.provider.DataProvider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.StringConverter;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class RegistrationViewController implements IButtonsController {

    private Pane rootMainView; //Formulario matricula
    private Registration registration;
    private DataUtil dataUtil;
    private Student student;
    private Course course;
    private RegistrationID regId;
    private boolean newStudent;
    RadioButton rbSave;
    double amount = 0.0;
    LocalDate localDate;
    boolean studentUpdated = false;

    ObservableList<String> olGender = FXCollections.observableArrayList("Mujer", "Hombre", "Ns/Nc");

    @FXML
    private TextField tfLocality;
    @FXML
    private ComboBox<Course>cbCourse;
    @FXML
    private TextField tfPhone;
    @FXML
    private TextField tfAddress;
    @FXML
    private ComboBox<Province> cbProvince;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfDNI;
    @FXML
    private RadioButton rbRepeater;
    @FXML
    private DatePicker dpRegistrationDate;
    @FXML
    private TextField tfAmount;
    @FXML
    private CheckBox checkCertificate;
    @FXML
    private RadioButton rbFamily;
    @FXML
    private CheckBox checkDocumentation;
    @FXML
    private RadioButton rbOrdinary;
    @FXML
    private ToggleGroup tgRegistrationType;
    @FXML
    private Spinner<String> spnGender;
    SpinnerValueFactory<String> svf = new SpinnerValueFactory.ListSpinnerValueFactory<>(olGender);
    @FXML
    private DatePicker dpBirthdate;
    @FXML
    private AnchorPane rootRegistrationView;
    @FXML
    private Label lbPaidAmount;
    @FXML
    private Label lbDni;
    @FXML
    private Label lbGender;
    @FXML
    private Button btnBack;
    @FXML
    private Label lbRegistrationDate;
    @FXML
    private Label lbProvince;
    @FXML
    private Label lbPhone;
    @FXML
    private Label lbLocality;
    @FXML
    private Label lbBirthdate;
    @FXML
    private Button btnAccept;
    @FXML
    private Label lbTRegistration;
    @FXML
    private Label lbName;
    @FXML
    private Label lbCourse;
    @FXML
    private Label lbAddress;
    @FXML
    private Button btnReset;


    public void setRootMainView(Pane rootMainView){
        this.rootMainView = rootMainView;
    }
    public void setDataUtil(DataUtil dataUtil){
        this.dataUtil = dataUtil;
    }
    public void setRegistration(Registration registration){
        this.registration = registration;
    }
    public void setRegId(RegistrationID regId)
    {
        this.regId = regId;
    }
    public void setStudent(Student student)
    {
        this.student = student;
    }

    public void showData(){

        tfName.setEditable(false);
        tfAddress.setEditable(false);
        tfLocality.setEditable(false);
        tfPhone.setEditable(false);

        rbOrdinary.setSelected(true);

        cbCourse.setItems(dataUtil.getOlCourse());
        cbCourse.setCellFactory((ListView<Course> c)-> new ListCell<>(){
            @Override
            protected void updateItem(Course course, boolean empty){
                super.updateItem(course, empty);
                if(course == null || empty){
                    setText("");
                }else{
                    setText(course.getName());
                }
            }
        });

        cbCourse.setConverter((new StringConverter<>() {
            @Override
            public String toString(Course course) {
                if(course == null){
                    return null;
                }else{
                    return course.getName();
                }
            }

            @Override
            public Course fromString(String s) {
                return null;
            }
        }));
        cbCourse.setValue(dataUtil.getOlCourse().get(0));

        cbProvince.setItems(dataUtil.getOlProvince());
        cbProvince.setCellFactory(
                (ListView<Province> l)-> new ListCell<>() {
                    @Override
                    protected void updateItem(Province province, boolean empty) {
                        super.updateItem(province, empty);
                        if(province == null || empty){
                            setText("");
                        }else{
                            setText(province.getCode() + "-" + province.getName());
                        }
                    }
                }
        );
        cbProvince.setConverter(new javafx.util.StringConverter<>() {
            @Override
            public String toString(Province province) {
                if(province == null){
                    return null;
                }else{
                    return province.getCode()+"-"+ province.getName();
                }
            }

            @Override
            public Province fromString(String s) {
                return null;
            }
        });

        cbProvince.setValue(dataUtil.getOlProvince().get(0));

        dpBirthdate.setValue(LocalDate.now());
        dpRegistrationDate.setValue(LocalDate.now());

        course = cbCourse.getValue();
        amount = course.getAmount();
        tfAmount.setText(String.valueOf(amount));

        svf.setValue(olGender.get(0));
        spnGender.setValueFactory(svf);

    }

    @FXML
    @Override
    public void onActionButtonSave(ActionEvent event) {

        boolean errorFormat = false;

        if(!tfDNI.getText().isEmpty()){

            regId.setStudentDNI(tfDNI.getText());

        }else{
            errorFormat = true;
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "DNI necesario");
            alert.setHeaderText(null);
            alert.showAndWait();
            tfDNI.requestFocus();
        }

        if(newStudent){

            if(tfName.getText().isEmpty() || tfAddress.getText().isEmpty() || tfPhone.getText().isEmpty() || tfLocality.getText().isEmpty()){
                errorFormat = true;
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Campo vacio");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
            else if(tfPhone.getLength() != 9)
            {
                errorFormat = true;
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "El teléfono debe tener 9 dígitos.");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
            else
            {
                student.setDni(tfDNI.getText());
                student.setName(tfName.getText());
                student.setAddress(tfAddress.getText());
                student.setPhone(tfPhone.getText());
                String DateAsString = setDate(dpBirthdate);
                student.setBirthday(DateAsString);
                student.setLocality(tfLocality.getText());
                student.setGenre(spnGender.getValue());
                student.setProvince(cbProvince.getValue());
                dataUtil.addStudent(student);
            }
        }

        registration.setDniStudent(student);

        String DateAsString = setDate(dpRegistrationDate);
        registration.setRegistrationDate(DateAsString);

        registration.setDocumentation(checkDocumentation.isSelected());
        registration.setCertification(checkCertificate.isSelected());

        rbSave = (RadioButton) tgRegistrationType.getSelectedToggle();
        registration.setRegistrationType(rbSave.getText());

        amount = Double.parseDouble(tfAmount.getText());
        if(rbSave.equals(rbFamily))
            amount *= 0.9;
        else if(rbSave.equals(rbRepeater))
            amount *= 1.1;

        amount = Math.floor(amount*100)/100;
        tfAmount.setText(String.valueOf(amount));

        registration.setIdCourse(cbCourse.getValue());
        regId.setIdCourse(cbCourse.getValue().getId());

        try {
            if(!tfAmount.getText().isEmpty())
            {
                registration.setAmount(Double.parseDouble(tfAmount.getText()));
            }
        }catch (NumberFormatException e)
        {
            tfDNI.requestFocus();
        }

        registration.setId(regId);

        if(!errorFormat){

            dataUtil.obtainAllStudents();
            dataUtil.addRegistration(registration);

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "¡Se ha creado la matrícula con éxito!");
            alert.setHeaderText(null);
            alert.showAndWait();

            clean();
            showData();
        }

        GluonObservableObject<Registration> GluonRegistration = dataUtil.findRegistrationByID(regId);

        if(GluonRegistration.get() == null)
        {
            dataUtil.addRegistration(registration);
        }

    }


    private String setDate(DatePicker datePicker) {
        localDate = datePicker.getValue();
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        Instant instant = zonedDateTime.toInstant();
        Date date = Date.from(instant);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    @FXML
    @Override
    public void onActionButtonCancel(ActionEvent event)
    {
        StackPane rootMain = (StackPane) rootRegistrationView.getParent();
        rootMain.getChildren().remove(rootRegistrationView);
        dataUtil.obtainAllStudents();
    }

    @FXML
    @Override
    public void onActionButtonClean(ActionEvent event) {

        clean();
        showData();
    }

    public void clean(){

        tfAddress.setText("");
        tfAmount.setText("");
        tfDNI.setText("");
        tfLocality.setText("");
        tfName.setText("");
        tfPhone.setText("");
        checkCertificate.setSelected(false);
        checkDocumentation.setSelected(false);
    }

    public void showStudentData(Student student){

        tfName.setText(student.getName());
        tfPhone.setText(student.getPhone());
        tfAddress.setText(student.getAddress());
        tfLocality.setText(student.getLocality());
        dpBirthdate.setValue(LocalDate.parse(student.getBirthday()));
        svf.setValue(student.getGenre());
        if(student.getProvince() != null){
            cbProvince.setValue(this.student.getProvince());
        }
    }

    @FXML
    public void validateDNI(ActionEvent event) {

        //Comprobar que el dni exista y si no existe que esté escrito en el formato correcto
        GluonObservableObject<Student> student = dataUtil.findStudentByDNI(tfDNI.getText());

        student.initializedProperty().addListener((obs, ov, nv) -> {
            if (nv && student.get() != null) {
                System.out.println("Student recovered from the database: " + student.get().getDni() + " - " + student.get().getName());
                showStudentData(student.get());
                setStudent(student.get());
                newStudent = false;
            }
        });

        newStudent = true;
        if(tfDNI.getText().matches("(\\d{8})([-]?)([A-Z]{1})"))
        {
            tfName.clear();
            tfName.setEditable(true);
            tfLocality.clear();
            tfLocality.setEditable(true);
            tfAddress.clear();
            tfAddress.setEditable(true);
            tfPhone.clear();
            tfPhone.setEditable(true);
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "DNI con formato incorrecto");
            alert.setHeaderText(null);
            alert.showAndWait();
            tfDNI.requestFocus();
        }

    }


    @FXML
    public void validateDocumentation(Event event){

        try{

            amount = Double.parseDouble(tfAmount.getText());

            if(checkDocumentation.isSelected()){

                amount *=1.05;
            }else{

                amount /=1.05;
            }

            amount = Math.floor(amount*100)/100;
            tfAmount.setText(String.valueOf(amount));

        }catch (NumberFormatException nfe){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Campo incorrecto");
            alert.setHeaderText(null);
            alert.showAndWait();
            tfAmount.requestFocus();
        }
    }

    /* if the course selected is changed, we change the price of it too*/
    @FXML
    public void setCourseAmount(ActionEvent actionEvent) {

        if(cbCourse.getValue() != null)
        {
            amount = cbCourse.getValue().getAmount();

            rbSave = (RadioButton) tgRegistrationType.getSelectedToggle();
            if(rbSave.equals(rbFamily))
            {
                amount *= 0.9;
            }
            else if(rbSave.equals(rbRepeater))
                amount *= 1.1;


            if(checkDocumentation.isSelected()){
                amount *=1.05;
            }

            amount = Math.floor(amount*100)/100;
            tfAmount.setText(String.valueOf(amount));
        }
    }


}
