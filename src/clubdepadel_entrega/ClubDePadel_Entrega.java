/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clubdepadel_entrega;

import DBAcess.ClubDBAccess;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author RaulP
 */
public class ClubDePadel_Entrega extends Application {
    ClubDBAccess miClub = ClubDBAccess.getSingletonClubDBAccess();

    @Override
    public void start(Stage stage) throws Exception {
        stage.setOnCloseRequest((WindowEvent event) -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle(miClub.getClubName());
            alert.setHeaderText("Saving data in DB");
            alert.setContentText("The application is saving the changes into the database. This action can expend some minutes.");
            alert.show();
            miClub.saveDB();
        });
        Parent root = FXMLLoader.load(getClass().getResource("FXMLLogin.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMinHeight(768);
        stage.setMinWidth(1024);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
