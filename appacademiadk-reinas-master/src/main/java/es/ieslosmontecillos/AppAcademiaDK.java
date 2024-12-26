package es.ieslosmontecillos;


import com.gluonhq.charm.glisten.mvc.View;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AppAcademiaDK extends Application {

    private DataUtil dataUtil;
    private MainViewController mainViewController;

    @Override
    public void start(Stage primaryStage) throws IOException {

        StackPane rootMain = new StackPane();
        rootMain.getStyleClass().add("stackStyle");
        ScrollPane rootScrollPane = new ScrollPane();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/MainView.fxml"));
        Pane rootDetalleView = fxmlLoader.load();
        mainViewController = fxmlLoader.getController();
        mainViewController.setRootMain(rootMain);
        rootMain.getChildren().add(rootDetalleView);

        dataUtil = new DataUtil();

        dataUtil.obtainAllCourses();
        dataUtil.obtainAllProvinces();
        dataUtil.obtainAllRegistrations();
        dataUtil.obtainAllStudents();

        mainViewController.setDataUtil(dataUtil);
        mainViewController.setRootMain(rootMain);

        rootScrollPane.setContent(rootMain);

        Scene scene = new Scene(rootScrollPane, 758, 482);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setTitle("App Academia");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    @Override
    public void stop() {
        System.out.println("Stop: Se cerró la app");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        launch(args);
    }

}

