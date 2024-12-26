package es.ieslosmontecillos;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainViewController {

    @FXML
    public Label startTitle;
    private Course newCourse = new Course();
    private Registration newRegistration = new Registration();
    private RegistrationID regId = new RegistrationID();
    private Student student = new Student();
    private DataUtil dataUtil;
    private Pane rootMain = new Pane();

    @FXML
    private AnchorPane rootMainView;
    @FXML
    private ImageView imgNewCourse;
    @FXML
    private MenuItem menuNewCourse;
    @FXML
    private MenuItem menuNewRegistration;
    @FXML
    private Button btnStudents;
    @FXML
    private MenuButton menuButtons;
    @FXML
    private VBox mainCourse;
    @FXML
    private Button btnInstructors;
    @FXML
    private Button btnReport;
    @FXML
    private HBox mainView;

    @FXML
    public void initiateCourse(Event event){

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/NewCourse.fxml"));
            Pane rootCourseView= fxmlLoader.load();

            rootMain.getChildren().add(rootCourseView);

            CourseViewController courseViewController = fxmlLoader.getController();
            courseViewController.setRootMainView(rootMainView);
            courseViewController.setDataUtil(dataUtil);
            courseViewController.setCourse(newCourse);
            courseViewController.showData();

        }catch (IOException e){
            System.out.println("IOException: " + e);
        }

    }


    @FXML
    public void initiateRegistration(ActionEvent event){

        try{

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/NewRegistration.fxml"));
            Pane rootRegistrationView = fxmlLoader.load();

            rootMain.getChildren().add(rootRegistrationView);

            RegistrationViewController registrationViewController = fxmlLoader.getController();
            registrationViewController.setDataUtil(dataUtil);
            registrationViewController.setRootMainView(rootMainView);
            registrationViewController.setRegistration(newRegistration);
            registrationViewController.setRegId(regId);
            registrationViewController.setStudent(student);
            registrationViewController.showData();


        }catch(IOException e){
            System.out.println("IOException: " + e);
        }
    }

    @FXML
    public void initiateStudents(Event event) {
        try{

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/newStudent.fxml"));
            Pane rootStudentView = fxmlLoader.load();

            rootMain.getChildren().add(rootStudentView);

            StudentViewController studentViewController = fxmlLoader.getController();
            studentViewController.setDataUtil(dataUtil);
            studentViewController.setRootMainView(rootMainView);

            studentViewController.setOlStudent(dataUtil.getOlStudent());
            studentViewController.setOlProvinces(dataUtil.getOlProvince());
            studentViewController.cargarTodasPersonas();

        }catch (IOException e){
            System.out.println(("IOException: " + e));
        }
    }

    public void setDataUtil(DataUtil dataUtil){
        this.dataUtil = dataUtil;
    }

    public void setRootMain(Pane rootMain){
        this.rootMain = rootMain;
    }
}