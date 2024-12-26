package es.ieslosmontecillos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class CourseViewController implements IButtonsController {

    private Pane rootMainView;
    private Course course;
    private DataUtil dataUtil;

    // the list for all the course instructor
    ObservableList<String> olInstructor = FXCollections.observableArrayList("Profe1", "Profe2", "Profe3");

    // the list for all the categories
    ObservableList<String> olCategories = FXCollections.observableArrayList("BI / Big Data", "Ciberseguridad", "DevOps", "ERP", "IT", "Ofimática", "Programación", "Testing");

    // Elements of NewCourse.fxml
    @FXML
    private ToggleGroup tgCourseType;
    @FXML
    private RadioButton rbVideo;
    @FXML
    private ComboBox<String> cbInstructor;
    @FXML
    private TextField tfProvider;
    @FXML
    private ComboBox<String> cbCategory;
    @FXML
    private TextField tfDuration;
    @FXML
    private DatePicker dpCourseStart;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnClean;
    @FXML
    private Button btnCancel;
    @FXML
    private DatePicker dpCourseEnd;
    @FXML
    private TextField tfCertification;
    @FXML
    private RadioButton rbOfficial;
    @FXML
    private RadioButton rbOnline;
    @FXML
    private TextField tfCourseName;
    @FXML
    private Spinner<Integer> spNumberStudent;
    @FXML
    private AnchorPane rootCourseView;
    @FXML
    private TextField tfAmount;
    @FXML
    private CheckBox checkBoxScholarship;
    @FXML
    private Label lbStartDate;
    @FXML
    private Label lbNumAsist;
    @FXML
    private Label lbCertification;
    @FXML
    private Label lbType;
    @FXML
    private VBox labelTitle;
    @FXML
    private Label lbInstructor;
    @FXML
    private Label lbCategory;
    @FXML
    private Label lbEndDate;
    @FXML
    private Label lbDuration;
    @FXML
    private Label lbPdC;
    @FXML
    private Label lbDac;
    @FXML
    private Label lbCost;
    @FXML
    private Label lbName;
    @FXML
    private GridPane gpDetail;

    public void setRootMainView(Pane rootMainView) {
        this.rootMainView = rootMainView;
    }

    // Set the course we are about to create
    public void setCourse(Course course) {
        this.course = course;
    }

    // Set the datautil we are going to use
    public void setDataUtil(DataUtil dataUtil) {
        this.dataUtil = dataUtil;
    }

    /* Show all the preselected data for the form */
    public void showData()
    {
        // set the selected Radio Button
        rbOfficial.setSelected(true);

        // the set of values for the spinner
        SpinnerValueFactory<Integer> vf = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 200, 1);
        spNumberStudent.setValueFactory(vf);

        // the set of values for the instructors
        cbInstructor.setItems(olInstructor);
        cbInstructor.setValue(olInstructor.get(0));

        // the set of values for the categories
        cbCategory.setItems(olCategories);
        cbCategory.setValue(olCategories.get(0));

    }


    /* create a new course and stores it in the database */
    @FXML
    @Override
    public void onActionButtonSave(ActionEvent event) {

        int duration;
        LocalDate localDateStart = null;
        LocalDate localDateEnd;

        /* Warn the users if they introduce the information wrongly */
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Formato incorrecto");

        /* Inform if the formats of introduced data are correct or not */
        boolean errorFormat = false;

        // We check the name of the course. It cannot be null
        if(!tfCourseName.getText().isBlank() && tfCourseName.getText().length() < 40)
        {
            course.setName(tfCourseName.getText());
        }
        else
        {
            errorFormat = true;
            alert.setContentText("Debe indicar el nombre del curso.");
            alert.setHeaderText(null);
            alert.showAndWait();
            tfCourseName.requestFocus();
        }

        // The course's provider
        if(!tfProvider.getText().isBlank() && tfProvider.getText().length() < 40)
        {
            course.setProvider(tfProvider.getText());
        }
        else if(!errorFormat)
        {
            errorFormat = true;
            alert.setHeaderText(null);
            alert.setContentText("Debe indicar el proveedor del curso.");
            alert.showAndWait();
            tfProvider.requestFocus();
        }

        if(!tfCertification.getText().isBlank() && tfCertification.getText().length() < 40)
        {
            course.setCertification(tfCertification.getText());
        }
        else if(!errorFormat)
        {
            errorFormat = true;
            alert.setContentText("Debe indicar el tipo de certificación.");
            alert.setHeaderText(null);
            alert.showAndWait();
            tfCertification.requestFocus();
        }

        // We check the cost of the course. Input must be a number
        try
        {
            if(!tfAmount.getText().isBlank())
            {
                double amount = Double.parseDouble(tfAmount.getText());
                tfAmount.setText(amount + " €");
                course.setAmount(amount);
            }
            else if(!errorFormat)
            {
                alert.setContentText("Debe introducir el precio del curso.");
                alert.setHeaderText(null);
                alert.showAndWait();
                errorFormat = true;
                tfAmount.requestFocus();
            }
        } catch (NumberFormatException e)
        {
            errorFormat = true;
            alert.setContentText("Debe introducir números.");
            alert.setHeaderText(null);
            alert.showAndWait();
            tfAmount.requestFocus();
        }


        // We check the duration. Input must be a number
        try{
            if(!tfDuration.getText().isBlank())
            {
                duration = Integer.parseInt(tfDuration.getText());
                course.setDuration(duration);
            }
            else if(!errorFormat)
            {
                alert.setContentText("Debe introducir la duración del curso.");
                alert.showAndWait();
                alert.setHeaderText(null);
                errorFormat = true;
                tfDuration.requestFocus();
            }
        }catch (NumberFormatException e)
        {
            errorFormat = true;
            alert.setContentText("Debe introducir números.");
            alert.setHeaderText(null);
            alert.showAndWait();
            tfDuration.requestFocus();
        }

        // Category field
        course.setCategory(cbCategory.getValue());

        // Start Date
        if(dpCourseStart.getValue() != null && !errorFormat)
        {
            localDateStart = dpCourseStart.getValue();
            ZonedDateTime zonedDateTime = localDateStart.atStartOfDay(ZoneId.systemDefault());
            Instant instant = zonedDateTime.toInstant();
            Date date = Date.from(instant);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateAsString = sdf.format(date);
            course.setStartDate(dateAsString);
        }
        else
        {
            course.setStartDate(null);
        }

        /* End Date: the start date has to be filled, and it has to be after the start date */
        if(dpCourseEnd.getValue() != null && dpCourseStart.getValue() != null && !errorFormat)
        {
            localDateEnd = dpCourseEnd.getValue();
            if(localDateEnd.isAfter(localDateStart))
            {
                ZonedDateTime zonedDateTime = localDateEnd.atStartOfDay(ZoneId.systemDefault());
                Instant instant = zonedDateTime.toInstant();
                Date date = Date.from(instant);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dateAsString = sdf.format(date);
                course.setEndDate(dateAsString);
            }
            else
            {
                errorFormat = true;
                alert.setContentText("La fecha de fin debe ser posterior a la fecha de inicio.");
                alert.setHeaderText(null);
                alert.showAndWait();
                dpCourseEnd.requestFocus();
            }
        }
        else
        {
            /* if the end date is selected but the start date no */
            if(dpCourseEnd.getValue() != null && dpCourseStart.getValue() == null && !errorFormat)
            {
                errorFormat = true;
                alert.setContentText("No existe fecha de inicio.");
                alert.setHeaderText(null);
                alert.showAndWait();
                dpCourseStart.requestFocus();
            }
            else
            {
                if(dpCourseStart.getValue() != null && !errorFormat)
                {
                    errorFormat = true;
                    alert.setHeaderText(null);
                    alert.setContentText("No existe fecha de fin.");
                    alert.showAndWait();
                    dpCourseEnd.requestFocus();
                }
                course.setEndDate(null);
            }

        }

        // Number of students field
        course.setNumStudents(spNumberStudent.getValue());

        // Instructor field
        course.setInstructor(cbInstructor.getValue());

        // Type of the course field
        RadioButton rbSelected = (RadioButton) tgCourseType.getSelectedToggle();
        course.setType(rbSelected.getText());

        // Scholarship field
        course.setScholarship(checkBoxScholarship.isSelected());

        /* If there is no error in the information, we save the course */
        if(!errorFormat)
        {
            try
            {
                /* Save the new course object in the Database and hide the actual view */
                dataUtil.addCourse(course);
                alert.setTitle("¡Enhorabuena!");
                alert.setHeaderText(null);
                alert.setContentText("Se ha registrado el curso con éxito.");
                alert.showAndWait();

                cleanData();
            }
            catch (Exception e) {

                Alert error = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("¡Error!");
                alert.setHeaderText(null);
                alert.setContentText("Error al crear el nuevo curso.");
                alert.setContentText(e.getLocalizedMessage());
                alert.showAndWait();

                StackPane rootMain = (StackPane) rootCourseView.getScene().getRoot();
                rootMain.getChildren().remove(rootCourseView);
            }
        }

        dataUtil.obtainAllCourses();
    }

    /* Go back to the home page */
    @FXML
    @Override
    public void onActionButtonCancel(ActionEvent event) {

        StackPane rootMain = (StackPane) rootCourseView.getParent();
        rootMain.getChildren().remove(rootCourseView);

    }

    /* remove all filled information */
    @FXML
    @Override
    public void onActionButtonClean(ActionEvent event) {
        cleanData();
    }

    public void cleanData(){
        // we clean the textfields
        tfProvider.clear();
        tfCertification.clear();
        tfDuration.clear();
        tfCourseName.clear();
        tfAmount.clear();

        // we clean the datepickers
        dpCourseStart.setValue(null);
        dpCourseEnd.setValue(null);

        checkBoxScholarship.setSelected(false);

        // we set the rest of the options to their predetermined values
        showData();
    }
}