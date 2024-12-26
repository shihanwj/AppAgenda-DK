package es.ieslosmontecillos;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class StudentViewController implements IButtonsController{

    private Pane rootMainView;
    private DataUtil dataUtil;
    private ObservableList<Student> olStudents;
    private ObservableList<Province> olProvinces;

    @FXML
    private AnchorPane rootStudentView;
    @FXML
    private TableColumn<Student, String> birthday;
    @FXML
    private TableColumn<Student, String> address;
    @FXML
    private TableColumn<Student, String> province;
    @FXML
    private TableColumn<Student, String> phone;
    @FXML
    private TableColumn<Student, String> name;
    @FXML
    private TableColumn<Student, String> locality;
    @FXML
    private TableColumn<Student, String> dni;
    @FXML
    private TableView<Student> tableStudent;
    @FXML
    private Button exit;
    @FXML
    private TableColumn<Student, String> gender;

    public void initialize() {
        dni.setCellValueFactory(new PropertyValueFactory<>("dni"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        locality.setCellValueFactory(new PropertyValueFactory<>("locality"));
        province.setCellValueFactory(
                cellData-> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue().getProvince() != null) {
                        property.set(cellData.getValue().getProvince().getName());
                    }
                    return property;
                });
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        gender.setCellValueFactory(new PropertyValueFactory<>("genre"));
        birthday.setCellValueFactory(new PropertyValueFactory<>("birthday"));
    }

    public void setRootMainView(Pane rootMainView)
    {
        this.rootMainView = rootMainView;
    }

    public void setDataUtil(DataUtil dataUtil)
    {
        this.dataUtil = dataUtil;
    }

    public void setOlProvinces(ObservableList<Province> olProvinces) {
        this.olProvinces = olProvinces;
    }
    public void setOlStudent(ObservableList<Student> olStudents) {
        this.olStudents = olStudents;
    }

    public void cargarTodasPersonas(){
        tableStudent.setItems(FXCollections.observableArrayList(olStudents));
    }

    @FXML
    @Override
    public void onActionButtonCancel(ActionEvent event)
    {
        StackPane rootMain = (StackPane) rootStudentView.getParent();
        rootMain.getChildren().remove(rootStudentView);
    }

    @Override
    public void onActionButtonClean(ActionEvent event) {}
    @Override
    public void onActionButtonSave(ActionEvent event) {}
}