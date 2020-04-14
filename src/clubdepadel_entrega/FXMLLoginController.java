/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clubdepadel_entrega;

import DBAcess.ClubDBAccess;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author RaulP
 */
public class FXMLLoginController implements Initializable {

    @FXML
    private TextField user_Input;
    @FXML
    private PasswordField password_Input;

    String caracteresConf = "!@#$%^&*()_+={}[]|<>,.`~?\\/:;'-";
    ClubDBAccess miClub;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        miClub = ClubDBAccess.getSingletonClubDBAccess();
        user_Input.addEventFilter(javafx.scene.input.KeyEvent.KEY_TYPED, (javafx.scene.input.KeyEvent keyEvent) -> {
            if (caracteresConf.contains(keyEvent.getCharacter())) {
                keyEvent.consume();
            }
        });

        password_Input.addEventFilter(javafx.scene.input.KeyEvent.KEY_TYPED, (javafx.scene.input.KeyEvent keyEvent) -> {
            if (caracteresConf.contains(keyEvent.getCharacter())) {
                keyEvent.consume();
            }
        });
    }

    @FXML
    private void login(ActionEvent event) {
        if (miClub.getMemberByCredentials(user_Input.getText(), password_Input.getText()) == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Dialogo de confirmación");
            alert.setHeaderText("Error de Inicio de Sesión");
            alert.setContentText("Puede que hayas introducido los datos erroneamente o aun no estes registrado, revisalos o crea una cuenta");
            alert.showAndWait();

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Dialogo de confirmación");
            alert.setHeaderText("Inicio de Sesion Correcto");
            alert.setContentText("Bienvenido");
            alert.showAndWait();
        }
    }


    @FXML
    private void resgistrarse(ActionEvent event) throws IOException {
        Parent ventanaP = FXMLLoader.load(getClass().getResource("/clubdepadel_entrega/FXMLRegistro.fxml"));
        
        
        
        Scene ventanaS = new Scene(ventanaP);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        

        
        window.setScene(ventanaS);
        
        window.setMinHeight(768);
        window.setMinWidth(1024);
        window.setTitle("Registro");
     
        window.show();
    }

    @FXML
    private void verDisponibilidadPistas(ActionEvent event) {
    }
    
}
